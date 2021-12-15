package com.dumitruc.training.pokemon.model;

import com.google.gson.annotations.SerializedName;

public class Language {

    @SerializedName("name")
    String name;
    @SerializedName("url")
    String url;

    public Language() {
    }

    public Language(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
