package com.dumitruc.training.pokemon.services;

import com.dumitruc.training.pokemon.model.PokemonSummary;
import org.springframework.stereotype.Service;

@Service
public class PokemonTranslatorServiceImpl implements PokemonTranslatorService{


    @Override
    public PokemonSummary translatePokemon(PokemonSummary pokemonOriginalSummary){
        pokemonOriginalSummary.setDescription("=====translated===");
        return pokemonOriginalSummary;
    }
}
