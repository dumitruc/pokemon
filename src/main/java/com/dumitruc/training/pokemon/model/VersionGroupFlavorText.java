package com.dumitruc.training.pokemon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class VersionGroupFlavorText {

    @JsonProperty("flavor_text")
    String flavorText;

    @JsonProperty("language")
    Language language;

    @JsonProperty("version")
    JsonNode version;


    public VersionGroupFlavorText() {
    }
}
