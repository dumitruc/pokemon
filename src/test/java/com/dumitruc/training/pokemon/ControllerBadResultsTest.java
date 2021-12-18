package com.dumitruc.training.pokemon;

import com.dumitruc.training.pokemon.controllers.PokemonController;
import com.dumitruc.training.pokemon.services.PokemonInfoServiceImpl;
import com.dumitruc.training.pokemon.services.PokemonTranslatorServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerBadResultsTest {

    @Autowired
    private PokemonController pokemonController;

    @MockBean
    private PokemonInfoServiceImpl pokemonInfoService;

    @MockBean
    private PokemonTranslatorServiceImpl pokemonTranslatorService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void badResponseInfoFailInfo() throws Exception {

        when(pokemonInfoService.extractPokemon(any())).thenThrow(new RuntimeException("random"));
        this.mockMvc
                .perform(get("/pokemon/mewtwo"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{\"error\":\"Internal error could not process request\"}"));

    }

    @Test
    public void badResponseTranslateFailInfo() throws Exception {

        when(pokemonInfoService.extractPokemon(any())).thenThrow(new RuntimeException("random"));
        this.mockMvc
                .perform(get("/pokemon/translated/mewtwo"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{\"error\":\"Internal error could not process request\"}"));


    }

    @Test
    public void badResponseTranslateFailTranslate() throws Exception {

        when(pokemonTranslatorService.translatePokemon(any())).thenThrow(new RuntimeException("random"));
        this.mockMvc
                .perform(get("/pokemon/translated/mewtwo"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{\"error\":\"Could not translate pokemon.\"}"));


    }

}
