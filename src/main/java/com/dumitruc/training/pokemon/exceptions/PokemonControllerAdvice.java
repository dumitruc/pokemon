package com.dumitruc.training.pokemon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PokemonControllerAdvice {

    @ExceptionHandler(UnableToTranslate.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UnableToTranslateResponse handleCantTranslateException(UnableToTranslate utte) {
        UnableToTranslateResponse unableToTranslateResponse =
                new UnableToTranslateResponse(utte.getMessage());
        return unableToTranslateResponse;
    }

    @ExceptionHandler(UnableToObtainInfo.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UnableToObtainInfoResponse handleCantObtainInfo(UnableToObtainInfo utoi){
        UnableToObtainInfoResponse unableToObtainInfoResponse =
                new UnableToObtainInfoResponse(utoi.getMessage());
        return unableToObtainInfoResponse;
    }

}
