package com.dumitruc.training.pokemon;

import com.dumitruc.training.pokemon.model.PokemonSummary;
import com.dumitruc.training.pokemon.services.FunTranslationsServiceImpl;
import com.dumitruc.training.pokemon.services.PokemonTranslatorServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PokemonTranslationService {

    @MockBean
    FunTranslationsServiceImpl funTranslationsService;

    @Autowired
    PokemonTranslatorServiceImpl pokemonTranslatorService;


    private static final String YODA_TRANSLATED = "yoda-translated";
    private static final String SHAKESPEARE_TRANSLATED = "shakespeare-translated";
    private static final String ORIGINAL_DESCRIPTION = "unit-test-description";

    @Test
    public void whenCaveHabitatYodaTranslation() throws Exception {

        initFunTRanslationService();
        PokemonSummary pokemonSummary = new PokemonSummary("unit-test", ORIGINAL_DESCRIPTION, "cave", null);

        String actualTranslatedDescription = pokemonTranslatorService.translatePokemon(pokemonSummary).getDescription();

        assertThat(actualTranslatedDescription, is(YODA_TRANSLATED));
    }

    @Test
    public void whenLegendaryYodaTranslation() throws Exception {
        initFunTRanslationService();
        PokemonSummary pokemonSummary = new PokemonSummary("unit-test", ORIGINAL_DESCRIPTION, "grasslands", true);

        String actualTranslatedDescription = pokemonTranslatorService.translatePokemon(pokemonSummary).getDescription();

        assertThat(actualTranslatedDescription, is(YODA_TRANSLATED));
    }

    @Test
    public void whenNotLegendaryNotCaveShakespeareTranslation() throws Exception {
        initFunTRanslationService();
        PokemonSummary pokemonSummary = new PokemonSummary("unit-test", ORIGINAL_DESCRIPTION, "grasslands", false);

        String actualTranslatedDescription = pokemonTranslatorService.translatePokemon(pokemonSummary).getDescription();

        assertThat(actualTranslatedDescription, is(SHAKESPEARE_TRANSLATED));
    }

    @Test
    public void whenCantTranslateOriginal() throws Exception {
        when(funTranslationsService.getYodaTranslations("unit-test"))
                .thenThrow(new Exception("can't translate YODA"));
        when(funTranslationsService.getShakespeareTranslations("unit-test"))
                .thenThrow(new Exception("can't translate Shakespeare"));


        PokemonSummary pokemonSummary = new PokemonSummary("unit-test", ORIGINAL_DESCRIPTION, "grasslands", false);

        String actualTranslatedDescription = pokemonTranslatorService.translatePokemon(pokemonSummary).getDescription();

        assertThat(actualTranslatedDescription, is(ORIGINAL_DESCRIPTION));
    }

    @Test
    public void whenEmptyTranslateOriginal() throws Exception {
        when(funTranslationsService.getYodaTranslations(any()))
                .thenReturn("");
        when(funTranslationsService.getShakespeareTranslations(any()))
                .thenReturn("");


        PokemonSummary pokemonSummary = new PokemonSummary("unit-test", ORIGINAL_DESCRIPTION, "cave", true);

        String actualTranslatedDescription = pokemonTranslatorService.translatePokemon(pokemonSummary).getDescription();

        assertThat(actualTranslatedDescription, is(ORIGINAL_DESCRIPTION));
    }

    private void initFunTRanslationService() throws Exception {
        when(funTranslationsService.getYodaTranslations(any()))
                .thenReturn(YODA_TRANSLATED);
        when(funTranslationsService.getShakespeareTranslations(any()))
                .thenReturn(SHAKESPEARE_TRANSLATED);
    }


}
