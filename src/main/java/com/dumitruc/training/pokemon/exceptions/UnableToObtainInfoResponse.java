package com.dumitruc.training.pokemon.exceptions;

public class UnableToObtainInfoResponse {
    private String error;

    public UnableToObtainInfoResponse() {
    }

    public UnableToObtainInfoResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
