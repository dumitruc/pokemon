package com.dumitruc.training.pokemon.services;

import com.dumitruc.training.pokemon.DeserializerPokemonSummary;
import com.dumitruc.training.pokemon.model.PokemonSummary;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class PokemonInfoServiceImpl implements PokemonInfoService {

    @Value("${pokemon.details.external.service}")
    private String externalUrl;

    @Autowired
    ExternalCallsService externalCallsService;

    @Autowired
    GsonBuilder gsonBuilder;

    @Autowired
    DeserializerPokemonSummary deserializerPokemonSummary;

    @Override
    public PokemonSummary extractPokemon(String name) throws Exception {

        URI url = new URI(externalUrl + "/" + name.toLowerCase());
        String response = externalCallsService.getUrl(url);
        PokemonSummary pokemonSummary = mapToPokemonSummary(response);

        return pokemonSummary;
    }

    private PokemonSummary mapToPokemonSummary(String response) {

        gsonBuilder.registerTypeAdapter(PokemonSummary.class, deserializerPokemonSummary);

        Gson customGson = gsonBuilder.create();
        PokemonSummary pokemonSummary = customGson.fromJson(response, PokemonSummary.class);

        return pokemonSummary;
    }

}
