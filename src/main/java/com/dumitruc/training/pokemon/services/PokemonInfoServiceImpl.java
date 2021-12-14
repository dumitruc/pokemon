package com.dumitruc.training.pokemon.services;

import com.dumitruc.training.pokemon.model.PokemonSummary;
import com.dumitruc.training.pokemon.model.VersionGroupFlavorText;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonInfoServiceImpl implements PokemonInfoService{

    @Value("${pokemon.details.external.service}")
    private String externalUrl;

    @Override
    public PokemonSummary extractPokemon(String name){

        //Suicune
        //Corean

        //https://pokeapi.co/docs/v2#pokemon-species

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(externalUrl+"/"+ name.toLowerCase(), String.class);



        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        JsonNode jsonNode = null;
        String pokemonName = null;
        String flavorText = null;
        String habitat = null;
        Boolean isLegendary = null;
        try {
            jsonNode = mapper.readTree(response);
            pokemonName  = jsonNode.get("name").asText();
            flavorText  = jsonNode.get("flavor_text_entries").get(0).get("flavor_text").asText();

            JsonNode flavorTextEntries = jsonNode.get("flavor_text_entries");

            VersionGroupFlavorText[] flavorEvents = mapper.readValue(flavorTextEntries.toPrettyString(), VersionGroupFlavorText[].class);

            habitat  = jsonNode.get("habitat").asText();
            isLegendary  = jsonNode.get("is_legendary").asBoolean();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        PokemonSummary pokemonSummary = new PokemonSummary(pokemonName, flavorText, habitat, isLegendary);


        return pokemonSummary;
    }

}
