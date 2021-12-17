package com.dumitruc.training.pokemon;

import com.dumitruc.training.pokemon.model.PokemonSummary;
import com.dumitruc.training.pokemon.model.VersionGroupFlavorText;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Arrays;

@Component
public class DeserializerPokemonSummary implements JsonDeserializer {

    private final PokemonSummary pokemonSummary = new PokemonSummary();

    public static final Logger logger = LoggerFactory.getLogger(DeserializerPokemonSummary.class);


    @Autowired
    private Gson gson;

    @Override
    public PokemonSummary deserialize(JsonElement jsonElement,
                                      Type type,
                                      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        pokemonSummary.setName(getName(jsonObject));
        pokemonSummary.setDescription(getPokemonDescription(jsonObject));
        pokemonSummary.setHabitat(pokemonHabitat(jsonObject));
        pokemonSummary.setLegendary(isLegendary(jsonObject));

        return pokemonSummary;
    }

    private Boolean isLegendary(JsonObject jsonObject) {
        Boolean isLegendary = null;
        try {
            isLegendary = jsonObject.get("is_legendary").getAsBoolean();
        } catch (Throwable ex) {
            logger.warn("Could not extract [isLegendary] info from response object");
        }
        return isLegendary;
    }

    private String getName(JsonObject jsonObject) {
        String name = null;
        try {
            name = jsonObject.get("name").getAsString();
        } catch (Throwable ex) {
            logger.warn("Could not extract [name] info from response object");
        }

        return name;
    }

    private String pokemonHabitat(JsonObject jsonObject) {
        String habitat = null;
        try {
            habitat = jsonObject.get("habitat").getAsJsonObject().get("name").getAsString();
        } catch (Throwable ex) {
            logger.warn("Could not extract [habitat] info from response object");
        }

        return habitat;
    }

    private String getPokemonDescription(JsonObject jsonObject) {

        String pokemonDescription = null;

        try {
            VersionGroupFlavorText[] flavorEvents = gson.
                    fromJson(jsonObject.get("flavor_text_entries"),
                            VersionGroupFlavorText[].class);

            pokemonDescription = Arrays.stream(flavorEvents)
                    .filter(ft -> ft.getLanguage().getName().equalsIgnoreCase(Constants.DEFAULT_LANGUAGE_PREFIX)) //selecting english
                    .map(ft -> ft.getFlavorText())
                    .filter(d -> !d.isEmpty()) //using only items that have a description
                    .map(s->cleanString(s))
                    .findFirst()
                    .orElse(null);

        } catch (Throwable ex) {
            logger.warn("Could not extract [description] info from response object");
        }

        return pokemonDescription;
    }

    private String cleanString(String withAllSortsOfCharacters){
        String cleannedString = withAllSortsOfCharacters.replaceAll("\n", " ");

        return cleannedString;
    }

}

