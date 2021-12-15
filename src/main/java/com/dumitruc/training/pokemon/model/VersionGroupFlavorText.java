package com.dumitruc.training.pokemon.model;


import com.google.gson.annotations.SerializedName;

public class VersionGroupFlavorText {

    @SerializedName("flavor_text")
    private String flavorText;

    @SerializedName("language")
    private Language language;

    public VersionGroupFlavorText() {
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

}
