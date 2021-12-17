package com.dumitruc.training.pokemon.exceptions;

public class UnableToTranslateResponse {
    private String error;

    public UnableToTranslateResponse() {
    }

    public UnableToTranslateResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
