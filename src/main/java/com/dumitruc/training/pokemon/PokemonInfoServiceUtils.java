package com.dumitruc.training.pokemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class PokemonInfoServiceUtils {

    public static final Logger logger = LoggerFactory.getLogger(PokemonInfoServiceUtils.class);

    public static String cleanTextForUrl(String input) {
        String encodedString = null;
        try {
            encodedString = URLEncoder.encode(input, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            logger.warn("could not URI encode provided text: {}", input);
            e.printStackTrace();
        }
        return encodedString;
    }

    public static String readableTextFromUrl(String input) {
        String decodedString = null;
        try {
            decodedString = URLDecoder.decode(input, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            logger.warn("could not URI decode provided text: {}", input);
            e.printStackTrace();
        }
        return decodedString;
    }
}
