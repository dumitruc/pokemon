package com.dumitruc.training.pokemon.services;

import com.dumitruc.training.pokemon.model.PokemonSummary;

import java.net.URISyntaxException;

public interface PokemonInfoService {
    PokemonSummary extractPokemon(String pokemonOriginalSummary) throws URISyntaxException;
}
