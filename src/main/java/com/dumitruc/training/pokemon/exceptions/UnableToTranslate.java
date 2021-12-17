package com.dumitruc.training.pokemon.exceptions;

public class UnableToTranslate extends RuntimeException {

    private String message;

    public UnableToTranslate() {
    }

    public UnableToTranslate(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
