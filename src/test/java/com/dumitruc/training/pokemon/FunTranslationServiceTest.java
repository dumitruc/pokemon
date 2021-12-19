package com.dumitruc.training.pokemon;

import com.dumitruc.training.pokemon.services.ExternalCallsServiceImpl;
import com.dumitruc.training.pokemon.services.FunTranslationsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FunTranslationServiceTest {

    @Autowired
    FunTranslationsServiceImpl funTranslationsService;

    @MockBean
    ExternalCallsServiceImpl externalCallsService;

    private static final String YODA_TRANSLATED = "yoda-translated";
    private static final String SHAKESPEARE_TRANSLATED = "shakespeare-translated";
    private static final String ORIGINAL_DESCRIPTION = "unit-test-description";


    @Test
    public void emptyTranslation() throws Exception {

        when(externalCallsService.getUrl(any()))
                .thenReturn(buildFunTranslateResponse(""));
        String shakespeareTranslations = funTranslationsService.getShakespeareTranslations(ORIGINAL_DESCRIPTION);

        assertThat(shakespeareTranslations, is(ORIGINAL_DESCRIPTION));

    }

    @Test
    public void nullTranslation() throws Exception {

        when(externalCallsService.getUrl(any()))
                .thenReturn(null);
        String shakespeareTranslations = funTranslationsService.getShakespeareTranslations(ORIGINAL_DESCRIPTION);

        assertThat(shakespeareTranslations, is(ORIGINAL_DESCRIPTION));

    }

    @Test
    public void readDataFromYodaTranslation() throws Exception {

        when(externalCallsService.getUrl(any()))
                .thenReturn(buildFunTranslateResponse(YODA_TRANSLATED));
        String shakespeareTranslations = funTranslationsService.getShakespeareTranslations(ORIGINAL_DESCRIPTION);

        assertThat(shakespeareTranslations, is(YODA_TRANSLATED));

    }

    @Test
    public void readDataFromShakespeareTranslation() throws Exception {

        when(externalCallsService.getUrl(any()))
                .thenReturn(buildFunTranslateResponse(SHAKESPEARE_TRANSLATED));
        String shakespeareTranslations = funTranslationsService.getShakespeareTranslations(ORIGINAL_DESCRIPTION);

        assertThat(shakespeareTranslations, is(SHAKESPEARE_TRANSLATED));

    }

    private String buildFunTranslateResponse(String translatedValue) {
        String template = "{\"success\": {\"total\": 1}, \"contents\": {\"translated\": \"%s\", \"text\": \"who are you\", \"translation\": \"yoda\"}}";

        return String.format(template, translatedValue);
    }
}
