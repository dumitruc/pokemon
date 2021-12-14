package com.dumitruc.training.pokemon.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonSummary {

    @JsonProperty("name")
    String name;

    String description;

    @JsonProperty("habitat")
    String habitat;

    @JsonProperty("is_legendary")
    Boolean isLegendary;


    public PokemonSummary() {}

    public PokemonSummary(String name, String description, String habitat, Boolean isLegendary) {
        this.name = name;
        this.description = description;
        this.habitat = habitat;
        this.isLegendary = isLegendary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public Boolean getLegendary() {
        return isLegendary;
    }

    public void setIsLegendary(Boolean isLegendary) {
        this.isLegendary = isLegendary;
    }
}
