package com.dumitruc.training.pokemon;

import com.dumitruc.training.pokemon.services.FunTranslationsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class FunTranslationService {

    @Autowired
    FunTranslationsServiceImpl funTranslationsService;

    @Test
    public void doSomething() throws Exception {

        String whoAreYou = funTranslationsService.getYodaTranslations("who are you");

        System.out.println("whoAreYou = " + whoAreYou);

    }
}
