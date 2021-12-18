package com.dumitruc.training.pokemon;

import com.dumitruc.training.pokemon.model.PokemonSummary;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class PokemonDeserializerTest {


    @Autowired
    DeserializerPokemonSummary deserializerPokemonSummary;

    @Autowired
    Gson gson;

    @Mock
    JsonDeserializationContext mockJsonContext;

    @Test
    public void correctlyParseBasicWellFormattedJson() throws IOException {

        String path = this.getClass().getClassLoader().getResource("./minimum-pokemon-info.json").getPath();
        String fileContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

        PokemonSummary pokemonSummary = deserializerPokemonSummary
                .deserialize(gson.fromJson(fileContent, JsonObject.class), PokemonSummary.class, mockJsonContext);

        assertThat(pokemonSummary.getName(), is("mewtwo"));
        assertThat(pokemonSummary.getDescription(), is("It was created by a scientist after years of horrific\fgene splicing and DNA engineering experiments."));
        assertThat(pokemonSummary.getLegendary(), is(true));
        assertThat(pokemonSummary.getHabitat(), is("junit"));
    }

    @Test
    public void invalidPokemonDescriptionJsonStructureResultsInNull() {
        String basicJson = "{\n" +
                "  \"description\": \"mew two description\",\n" +
                "  \"is_legendary\": true,\n" +
                "  \"name\": \"mewtwo\",\n" +
                "  \"habitat\": {\n" +
                "    \"name\": \"rare\",\n" +
                "    \"url\": \"https://pokeapi.co/api/v2/pokemon-habitat/5/\"\n" +
                "  }\n" +
                "}";

        PokemonSummary pokemonSummary = deserializerPokemonSummary
                .deserialize(gson.fromJson(basicJson, JsonObject.class), PokemonSummary.class, mockJsonContext);

        assertThat(pokemonSummary, instanceOf(PokemonSummary.class));
        assertThat(pokemonSummary, IsNull.notNullValue());
        assertThat(pokemonSummary.getName(), is("mewtwo"));
        assertThat(pokemonSummary.getDescription(), IsNull.nullValue());

    }

    @Test
    public void parsesValidInformationAndIgnoresTheRest() throws IOException {
        String path = this.getClass().getClassLoader().getResource("./mewtwo.pokemon-species.response.json").getPath();
        String fileContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

        PokemonSummary pokemonSummary = deserializerPokemonSummary
                .deserialize(gson.fromJson(fileContent, JsonObject.class), PokemonSummary.class, mockJsonContext);

        assertThat(pokemonSummary.getName(), is("mewtwo"));
        assertThat(pokemonSummary.getDescription(), is("It was created by a scientist after years of horrific\fgene splicing and DNA engineering experiments."));
        assertThat(pokemonSummary.getLegendary(), is(true));
        assertThat(pokemonSummary.getHabitat(), is("rare"));
    }

    @Test
    public void badInfoNoNullPokemon() {
        String basicJson = "{\"foo\":\"bar\"}";

        PokemonSummary pokemonSummary = deserializerPokemonSummary
                .deserialize(gson.fromJson(basicJson, JsonObject.class), PokemonSummary.class, mockJsonContext);

        assertThat(pokemonSummary, instanceOf(PokemonSummary.class));
        assertThat(pokemonSummary, IsNull.notNullValue());
        assertThat(pokemonSummary.getDescription(), IsNull.nullValue());
        assertThat(pokemonSummary.getName(), IsNull.nullValue());
        assertThat(pokemonSummary.getHabitat(), IsNull.nullValue());
        assertThat(pokemonSummary.getLegendary(), IsNull.nullValue());
    }

    /* Ideally for this test I'll create a small input wrapper, so that is clear what different input affects the output,
rather than how it currently is and 3 tests just use same file.
*/
    @Test
    public void cleanDescriptionNewLine() throws IOException {

        String path = this.getClass().getClassLoader().getResource("./pokemon.newline.description.json").getPath();
        String fileContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

        JsonObject jsonElement = gson.fromJson(fileContent, JsonObject.class);

        PokemonSummary pokemonSummary = deserializerPokemonSummary
                .deserialize(jsonElement, PokemonSummary.class, mockJsonContext);

        assertThat(pokemonSummary.getDescription(), is("this is new line split string"));

    }

    /* Ideally for this test I'll create a small input wrapper, so that is clear what different input affects the output,
    rather than how it currently is and 3 tests just use same file.
     */
    @Test
    public void useEnglishVersionOfDescription() throws IOException {
        String path = this.getClass().getClassLoader().getResource("./pokemon.newline.description.json").getPath();
        String fileContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

        JsonObject jsonElement = gson.fromJson(fileContent, JsonObject.class);

        PokemonSummary pokemonSummary = deserializerPokemonSummary
                .deserialize(jsonElement, PokemonSummary.class, mockJsonContext);

        assertThat(pokemonSummary.getDescription(), is("this is new line split string"));
    }

    /* Ideally for this test I'll create a small input wrapper, so that is clear what different input affects the output,
    rather than how it currently is and 3 tests just use same file.
     */
    @Test
    public void useEnglishNonEmptyTranslation() throws IOException {
        String path = this.getClass().getClassLoader().getResource("./pokemon.newline.description.json").getPath();
        String fileContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);

        JsonObject jsonElement = gson.fromJson(fileContent, JsonObject.class);

        PokemonSummary pokemonSummary = deserializerPokemonSummary
                .deserialize(jsonElement, PokemonSummary.class, mockJsonContext);

        assertThat(pokemonSummary.getDescription(), is("this is new line split string"));
    }
}
