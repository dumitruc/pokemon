package com.dumitruc.training.pokemon.services;

import com.dumitruc.training.pokemon.PokemonInfoServiceUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class FunTranslationsServiceImpl implements FunTranslationsService {

    public static final Logger logger = LoggerFactory.getLogger(FunTranslationsServiceImpl.class);


    @Value("${external.service.shakespeare}")
    private String shakespeareUrl;

    @Value("${external.service.yoda}")
    private String yoda;

    @Autowired
    ExternalCallsService externalCallsService;

    @Autowired
    Gson gson;


    @Override
    public String getShakespeareTranslations(String srcString) throws Exception {
        logger.info("Translating, Shakespeare service");
        logger.trace("Shakespeare service: {}}",srcString);
        return translateOnService(srcString, shakespeareUrl);
    }


    @Override
    public String getYodaTranslations(String srcString) throws Exception {
        logger.info("Translating, Yoda service");
        logger.trace("Yoda service: {}}",srcString);
        return translateOnService(srcString, yoda);
    }


    private String translateOnService(String srcString, String shakespeareUrl) throws Exception {
        String translatedResponse = externalCallsService.getUrl(buildUri(srcString, shakespeareUrl));
        String translatedValue = extractTranslatedValue(translatedResponse);

        if (translatedValue == null && translatedValue.isEmpty()) {
            translatedValue = PokemonInfoServiceUtils.readableTextFromUrl(srcString);
        }
        return translatedValue;
    }

    private URI buildUri(String stingToTranslate, String serviceUrlPrefix) throws Exception {
        String encodedString = PokemonInfoServiceUtils.cleanTextForUrl(stingToTranslate);
        String uriAsString = String.format(serviceUrlPrefix, encodedString);
        return new URI(uriAsString);
    }

    private String extractTranslatedValue(String responseJson) {
        String translatedValue = null;
        try {
            translatedValue = gson.fromJson(responseJson, JsonElement.class).getAsJsonObject().get("contents").getAsJsonObject().get("translated").getAsString();
        } catch (Exception exception) {
            logger.warn("Could not extract translated text from service.");
        }

        return translatedValue;
    }
}
