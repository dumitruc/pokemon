package com.dumitruc.training.pokemon.services;

import com.dumitruc.training.pokemon.model.PokemonSummary;

public interface PokemonTranslatorService {
    PokemonSummary translatePokemon(PokemonSummary pokemonOriginalSummary);
}
