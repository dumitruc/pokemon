package com.dumitruc.training.pokemon.controllers;

import com.dumitruc.training.pokemon.exceptions.UnableToObtainInfo;
import com.dumitruc.training.pokemon.exceptions.UnableToTranslate;
import com.dumitruc.training.pokemon.model.PokemonSummary;
import com.dumitruc.training.pokemon.services.PokemonInfoService;
import com.dumitruc.training.pokemon.services.PokemonTranslatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.net.URISyntaxException;

@RestController
public class PokemonController {

    private final PokemonTranslatorService pokemonTranslatorService;
    private final PokemonInfoService pokemonInfoService;

    public static final Logger logger = LoggerFactory.getLogger(PokemonController.class);

    @Autowired
    public PokemonController(PokemonTranslatorService pokemonTranslatorService,
                             PokemonInfoService pokemonInfoService) {
        this.pokemonTranslatorService = pokemonTranslatorService;
        this.pokemonInfoService = pokemonInfoService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping("/pokemon/{name}")
    public PokemonSummary pokemonInfo(@NotNull @PathVariable String name) {
        PokemonSummary pokemonSummary = null;
        try {
            pokemonSummary = pokemonInfoService.extractPokemon(name);
        } catch (URISyntaxException e) {
            logger.error("Could not encode the URI using the name provided: [{}]\n{}\n{}",
                    name,e.getMessage(),e);
            throw new UnableToObtainInfo(e.getMessage());
        }
        return pokemonSummary;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping("/pokemon/translated/{name}")
    public PokemonSummary pokemonTranslated(@NotNull @PathVariable String name) {
        PokemonSummary pokemonSummary = pokemonInfo(name);
        try {
            pokemonSummary = pokemonTranslatorService.translatePokemon(pokemonSummary);
        } catch (Exception e) {
            logger.error("Could not translate pokemon.\n{}\n{}",e.getMessage(),e);
            throw new UnableToTranslate("Could not translate pokemon.");
        }
        return pokemonSummary;
    }


}
