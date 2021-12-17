package com.dumitruc.training.pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PokemonApplication {

    // TODO: 17/12/2021 Enrich tests coveraeg
    // TODO: 17/12/2021 Add sonnarcube action for github pipeline
    // TODO: 17/12/2021 Containerise
    // TODO: 17/12/2021 Add cashing service (container)
    // TODO: 17/12/2021 update readme with how to run details

    public static void main(String[] args) {
        SpringApplication.run(PokemonApplication.class, args);
    }

}
