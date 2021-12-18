package com.dumitruc.training.pokemon;

import com.dumitruc.training.pokemon.model.PokemonSummary;
import com.dumitruc.training.pokemon.services.ExternalCallsService;
import com.dumitruc.training.pokemon.services.PokemonInfoServiceImpl;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PokemonInfoServiceIntTest {

    @Autowired
    private PokemonInfoServiceImpl pokemonInfoService;

    @MockBean
    private ExternalCallsService externalCallsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void pokemonInfoServiceReturnsAPokemonSummary() throws Exception {
        String path = this.getClass().getClassLoader().getResource("./mewtwo.pokemon-species.response.json").getPath();
        String fileContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

        when(externalCallsService.getUrl(any())).thenReturn(fileContent);

        assertThat(pokemonInfoService.extractPokemon(""), instanceOf(PokemonSummary.class));
    }

    @Test
    @Disabled("to be implemented")
    public void useEnglishVersionOfDescription(){
        System.out.println("");
    }

    @Test
    @Disabled("to be implemented")
    public void useEnglishNonEmptyTranslation(){
        System.out.println("");
    }
}
