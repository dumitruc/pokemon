package com.dumitruc.training.pokemon.controllers;

import com.dumitruc.training.pokemon.exceptions.UnableToObtainInfo;
import com.dumitruc.training.pokemon.exceptions.UnableToTranslate;
import com.dumitruc.training.pokemon.model.PokemonSummary;
import com.dumitruc.training.pokemon.services.PokemonInfoService;
import com.dumitruc.training.pokemon.services.PokemonTranslatorService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@Timed
public class PokemonController {

    private final PokemonTranslatorService pokemonTranslatorService;
    private final PokemonInfoService pokemonInfoService;

    private Counter infoVisitCounter;
    private Counter translateVisitCounter;

    public static final Logger logger = LoggerFactory.getLogger(PokemonController.class);

    @Autowired
    public PokemonController(PokemonTranslatorService pokemonTranslatorService,
                             PokemonInfoService pokemonInfoService, MeterRegistry registry) {
        this.pokemonTranslatorService = pokemonTranslatorService;
        this.pokemonInfoService = pokemonInfoService;
        this.infoVisitCounter = registry.counter("info_visit_counter");
        this.translateVisitCounter = registry.counter("translate_visit_counter");
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping("/pokemon/{name}")
    @Timed
    public PokemonSummary pokemonInfo(@NotNull @PathVariable String name) {
        infoVisitCounter.increment();
        PokemonSummary pokemonSummary = null;
        logger.info("Retrieve base info for: {}", name);
        try {
            pokemonSummary = pokemonInfoService.extractPokemon(name);
        } catch (Exception e) {
            logger.error("Could not obtain base info [{}]\n{}\n{}",
                    name, e.getMessage(), e);
            throw new UnableToObtainInfo("Internal error could not process request");
        }
        return pokemonSummary;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping("/pokemon/translated/{name}")
    @Timed
    public PokemonSummary pokemonTranslated(@NotNull @PathVariable String name) {
        translateVisitCounter.increment();
        logger.info("Retrieve translation for: {}", name);
        PokemonSummary pokemonSummary = pokemonInfo(name);
        try {
            pokemonSummary = pokemonTranslatorService.translatePokemon(pokemonSummary);
        } catch (Exception e) {
            logger.error("Could not translate pokemon.\n{}\n{}", e.getMessage(), e);
            throw new UnableToTranslate("Could not translate pokemon.");
        }
        return pokemonSummary;
    }


}
