package com.dumitruc.training.pokemon.model;


public class PokemonSummary {

    String name;
    String description;
    String habitat;
    Boolean isLegendary;


    public PokemonSummary() {
    }

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

    public void setLegendary(Boolean legendary) {
        isLegendary = legendary;
    }
}
