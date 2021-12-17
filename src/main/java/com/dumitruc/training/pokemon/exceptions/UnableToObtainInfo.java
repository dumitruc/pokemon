package com.dumitruc.training.pokemon.exceptions;

public class UnableToObtainInfo extends RuntimeException {

    private String message;

    public UnableToObtainInfo(){}

    public UnableToObtainInfo(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
