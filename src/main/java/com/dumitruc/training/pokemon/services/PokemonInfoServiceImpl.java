package com.dumitruc.training.pokemon.services;

import com.dumitruc.training.pokemon.DeserializerPokemonSummary;
import com.dumitruc.training.pokemon.PokemonInfoServiceUtils;
import com.dumitruc.training.pokemon.model.PokemonSummary;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class PokemonInfoServiceImpl implements PokemonInfoService {

    public static final Logger logger = LoggerFactory.getLogger(PokemonInfoServiceImpl.class);


    @Value("${external.service.pokemon.details}")
    private String pokemonMainRepo;

    @Autowired
    ExternalCallsService externalCallsService;

    @Autowired
    GsonBuilder gsonBuilder;

    @Autowired
    Gson gson;

    @Autowired
    DeserializerPokemonSummary deserializerPokemonSummary;

    @Override
    public PokemonSummary extractPokemon(String name) throws URISyntaxException {
        String cleanName = PokemonInfoServiceUtils.cleanTextForUrl(name.toLowerCase());
        URI url = new URI(pokemonMainRepo + "/" + cleanName);
        String response = externalCallsService.getUrl(url);
        PokemonSummary pokemonSummary = mapToPokemonSummary(response);
        return pokemonSummary;
    }

    private PokemonSummary mapToPokemonSummary(String response) {

        try {
            gson.fromJson(response, Object.class);
        } catch (Exception ex) {
            logger.warn("Could not map response to {} object", this.getClass().getName());
        }

        gsonBuilder.registerTypeAdapter(PokemonSummary.class, deserializerPokemonSummary);

        Gson customGson = gsonBuilder.create();
        PokemonSummary pokemonSummary = customGson.fromJson(response, PokemonSummary.class);

        return pokemonSummary;
    }

}
