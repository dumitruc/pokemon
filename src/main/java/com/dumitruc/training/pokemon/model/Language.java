package com.dumitruc.training.pokemon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Language {

    @JsonProperty("name")
    String name;
    @JsonProperty("url")
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
