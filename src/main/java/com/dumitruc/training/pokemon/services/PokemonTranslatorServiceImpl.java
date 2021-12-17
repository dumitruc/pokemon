package com.dumitruc.training.pokemon.services;

import com.dumitruc.training.pokemon.model.PokemonSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonTranslatorServiceImpl implements PokemonTranslatorService {

    public static final Logger logger = LoggerFactory.getLogger(PokemonTranslatorServiceImpl.class);


    @Autowired
    FunTranslationsService funTranslationsService;

    public static final String HABITAT_CAVE = "cave";

    @Override
    public PokemonSummary translatePokemon(PokemonSummary pokemonOriginalSummary) {

        String translatedDescription = null;
        try {
            if (isCavePokemon(pokemonOriginalSummary) && isLegendaryPokemon(pokemonOriginalSummary)) {
                translatedDescription = funTranslationsService.getYodaTranslations(pokemonOriginalSummary.getDescription());
            } else {
                translatedDescription = funTranslationsService.getShakespeareTranslations(pokemonOriginalSummary.getDescription());
            }
        } catch (Exception ex) {
            logger.warn("Could not translate\n{}", ex);
        }
        if (translatedDescription != null && !translatedDescription.isEmpty()) {
            pokemonOriginalSummary.setDescription(translatedDescription);
        }

        return pokemonOriginalSummary;
    }

    private Boolean isLegendaryPokemon(PokemonSummary pokemonOriginalSummary) {
        return pokemonOriginalSummary.getLegendary();
    }

    private boolean isCavePokemon(PokemonSummary pokemonOriginalSummary) {
        return HABITAT_CAVE.equalsIgnoreCase(pokemonOriginalSummary.getHabitat());
    }
}
