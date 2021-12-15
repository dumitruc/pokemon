package com.dumitruc.training.pokemon.controllers;

import com.dumitruc.training.pokemon.model.PokemonSummary;
import com.dumitruc.training.pokemon.services.PokemonInfoService;
import com.dumitruc.training.pokemon.services.PokemonTranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public class PokemonController {

    private final PokemonTranslatorService pokemonTranslatorService;
    private final PokemonInfoService pokemonInfoService;

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
        // TODO: 14/12/2021 Implement proper error handling
        try {
            pokemonSummary = pokemonInfoService.extractPokemon(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pokemonSummary;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping("/pokemon/translated/{name}")
    public PokemonSummary pokemonTranslated(@NotNull @PathVariable String name) {
        PokemonSummary originalPokemonSummary = pokemonInfo(name);
        return pokemonTranslatorService.translatePokemon(originalPokemonSummary);
    }


}
