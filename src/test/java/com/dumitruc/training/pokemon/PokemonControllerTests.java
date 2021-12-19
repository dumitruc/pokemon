package com.dumitruc.training.pokemon;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.dumitruc.training.pokemon.controllers.PokemonController;
import com.dumitruc.training.pokemon.model.PokemonSummary;
import com.dumitruc.training.pokemon.services.PokemonInfoServiceImpl;
import com.dumitruc.training.pokemon.services.PokemonTranslatorServiceImpl;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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

    @MockBean
    private PokemonTranslatorServiceImpl pokemonTranslatorService;

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
                .andExpect(content().json("{" +
                        "\"description\":\"mew two description\"," +
                        "\"legendary\":false," +
                        "\"name\":\"mewtwo\"," +
                        "\"habitat\":\"unknown\"}"));
    }

    @Test
    void pokemonTranslatePathWorking() throws Exception {
        PokemonSummary infoPokemon = new PokemonSummary("mewtwo", "mew translated two description", "unknown", false);

        when(pokemonTranslatorService.translatePokemon(any())).thenReturn(infoPokemon);
        this.mockMvc
                .perform(get("/pokemon/translated/mewtwo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"description\":\"mew translated two description\",\"legendary\":false,\"name\":\"mewtwo\",\"habitat\":\"unknown\"}"));

    }


    @Test
    void loggingInfoRequest() throws Exception {
        List<ILoggingEvent> logsList = initLoggingSpy();

        PokemonSummary infoPokemon = new PokemonSummary("mewtwo", "mew two description", "unknown", false);

        when(pokemonInfoService.extractPokemon(any())).thenReturn(infoPokemon);
        this.mockMvc
                .perform(get("/pokemon/mewtwo"))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(logsList.get(0).getFormattedMessage(), is("Retrieve base info for: mewtwo"));
        assertThat(logsList.get(0).getLevel(), is(Level.INFO));

    }

    @Test
    void loggingTranslateRequest() throws Exception {
        List<ILoggingEvent> logsList = initLoggingSpy();

        PokemonSummary infoPokemon = new PokemonSummary("snorlax", "mew two description", "unknown", false);

        when(pokemonInfoService.extractPokemon(any())).thenReturn(infoPokemon);
        this.mockMvc
                .perform(get("/pokemon/translated/snorlax"))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(logsList.get(0).getFormattedMessage(), is("Retrieve translation for: snorlax"));
        assertThat(logsList.get(0).getLevel(), is(Level.INFO));

    }

    private List<ILoggingEvent> initLoggingSpy() {
        Logger fooLogger = (Logger) LoggerFactory.getLogger(PokemonController.class);

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        fooLogger.addAppender(listAppender);
        List<ILoggingEvent> logsList = listAppender.list;
        return logsList;
    }


}
