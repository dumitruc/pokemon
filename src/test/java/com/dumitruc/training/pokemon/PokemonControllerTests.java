package com.dumitruc.training.pokemon;

import com.dumitruc.training.pokemon.controllers.PokemonController;
import com.dumitruc.training.pokemon.model.PokemonSummary;
import com.dumitruc.training.pokemon.services.PokemonInfoServiceImpl;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PokemonControllerTests {


    @Autowired
    private PokemonController pokemonController;

    @MockBean
    private PokemonInfoServiceImpl pokemonInfoService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void pokemonControllerContextLoadsOk() {
        assertThat("", pokemonController, is(IsNull.notNullValue()));
    }

    @Test
    void pokemonInfoPathWorking() throws Exception {

        PokemonSummary infoPokemon = new PokemonSummary("mewtwo", "mew two description", "unknown", false);

        when(pokemonInfoService.extractPokemon(any())).thenReturn(infoPokemon);
        this.mockMvc
                .perform(get("/pokemon/mewtwo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"description\":\"mew two description\",\"legendary\":false,\"name\":\"mewtwo\",\"habitat\":\"unknown\",\"is_legendary\":false}"));
    }

    @Test
    void pokemonTranslatePathWorking() throws Exception {
		PokemonSummary infoPokemon = new PokemonSummary("mewtwo", "mew two description", "unknown", false);

		when(pokemonInfoService.extractPokemon(any())).thenReturn(infoPokemon);
		this.mockMvc
				.perform(get("/pokemon/translated/mewtwo"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\"description\":\"=====translated===\",\"legendary\":false,\"name\":\"mewtwo\",\"habitat\":\"unknown\",\"is_legendary\":false}"));

    }

}
