package com.dumitruc.training.pokemon;

import com.dumitruc.training.pokemon.model.PokemonSummary;
import com.dumitruc.training.pokemon.model.VersionGroupFlavorText;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Arrays;

@Component
public class DeserializerPokemonSummary implements JsonDeserializer {

    @Autowired
    private Gson gson;

    @Override
    public Object deserialize(JsonElement jsonElement,
                              Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        VersionGroupFlavorText versionGroupFlavorText = getPokemonDescription(jsonObject);

        PokemonSummary pokemonSummary = new PokemonSummary(
                jsonObject.get("name").getAsString(),
                versionGroupFlavorText.getFlavorText(),
                jsonObject.get("habitat").getAsJsonObject().get("name").getAsString(),
                jsonObject.get("is_legendary").getAsBoolean());

        return pokemonSummary;
    }

    private VersionGroupFlavorText getPokemonDescription(JsonObject jsonObject) {

        VersionGroupFlavorText[] flavorEvents = gson.
                fromJson(jsonObject.get("flavor_text_entries"),
                        VersionGroupFlavorText[].class);

        VersionGroupFlavorText versionGroupFlavorText = Arrays.stream(flavorEvents)
                .filter(ft -> ft.getLanguage().getName().equalsIgnoreCase(Constants.DEFAULT_LANGUAGE_PREFIX))
                .filter(ft -> !ft.getFlavorText().isEmpty())
                .findFirst()
                .get();
        return versionGroupFlavorText;
    }
}

