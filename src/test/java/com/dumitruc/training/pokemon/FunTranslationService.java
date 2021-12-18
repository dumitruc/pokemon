package com.dumitruc.training.pokemon;

import com.dumitruc.training.pokemon.services.ExternalCallsServiceImpl;
import com.dumitruc.training.pokemon.services.FunTranslationsServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class FunTranslationService {

    @Autowired
    FunTranslationsServiceImpl funTranslationsService;

    @MockBean
    ExternalCallsServiceImpl externalCallsService;

    @Mock
    MockMvc mockMvc;

    @Test
    public void doSomething() throws Exception {

        //Initiate a pokemonSummary
        //For each logical flow change the pokemonInfoAccordingly
        //          - cave, legendary , make logical table

//        when(externalCallsService.getUrl())

        String whoAreYou = funTranslationsService.getYodaTranslations("who are you");

        System.out.println("whoAreYou = " + whoAreYou);

    }

    @Test
    @Disabled("to be implemeneted")
    public void whenCaveHabitatYodaTranslation(){
        System.out.println("");
    }

    @Test
    @Disabled("to be implemeneted")
    public void whenLegendaryYodaTranslation(){
        System.out.println("");
    }


    @Test
    @Disabled("to be implemeneted")
    public void whenCantTranslateOriginal(){
        System.out.println("");
    }

    @Test
    @Disabled("to be implemeneted")
    public void whenNotLegendaryNotCaveShakespeareTranslation(){
        System.out.println("");
    }


}
