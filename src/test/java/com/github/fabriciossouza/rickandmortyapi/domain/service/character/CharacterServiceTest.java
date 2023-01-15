package com.github.fabriciossouza.rickandmortyapi.domain.service.character;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciossouza.rickandmortyapi.domain.client.Response;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.RickMortyClient;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto.CharacterResponse;
import com.github.fabriciossouza.rickandmortyapi.domain.model.Episode;
import com.github.fabriciossouza.rickandmortyapi.domain.service.CharacterService;
import com.github.fabriciossouza.rickandmortyapi.domain.service.EpisodeService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static com.github.fabriciossouza.rickandmortyapi.util.FileResourcesUtils.getFileStringFromResource;
import static com.github.fabriciossouza.rickandmortyapi.util.ObjectMapperUtils.getMapper;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.ResponseEntity.ok;

@ExtendWith(SpringExtension.class)
public class CharacterServiceTest {

    @InjectMocks
    private CharacterService service;

    @Mock
    private  RickMortyClient rickMortyClient;

    @Mock
    private  EpisodeService episodeService;

    @BeforeEach
    @SneakyThrows
    void setMockOutput() {
        when(episodeService.getEpisodes(any()))
                .thenReturn(getEpisodes());

    }

    @DisplayName("Deve retornar listagem de todos os personagens")
    @SneakyThrows
    @Test
    void deveRetornarListaDeTodosPersonagens() {

        var characterResponseResponse = getCharacterResponseResponse("json/character/get-all-character.json");

        when(rickMortyClient.getCharacters(any(), any()))
                .thenReturn(ok(characterResponseResponse));

        var charactersDTO = service.getCharacters(eq(null), any());

        var characters = charactersDTO.getCharacters();
        var info = charactersDTO.getInfo();

        Set<Integer> episodesIds = characters.parallelStream()
                .flatMap(character -> character.getEpisodeIds().stream())
                .collect(toSet());

        assertThat(characters).hasSize(20);
        assertThat(episodesIds).hasSize(51);
        assertThat(info.getCount()).isEqualTo(826);
        assertThat(info.getPages()).isEqualTo(42);

        verify(rickMortyClient, times(1)).getCharacters(any(), any());
        verify(episodeService, times(1)).getEpisodes(any());
    }

    @DisplayName("Deve retornar listagem apenas um personagem")
    @SneakyThrows
    @Test
    void deveRetornarApenasUmPersonagem() {

        var personagem = "Jim";

        var characterResponseResponse = getCharacterResponseResponse("json/character/get-only-character.json");

        when(rickMortyClient.getCharacters(any(), any()))
                .thenReturn(ok(characterResponseResponse));

        var charactersDTO = service.getCharacters(eq(personagem), any());

        var characters = charactersDTO.getCharacters();
        var info = charactersDTO.getInfo();

        Set<Integer> episodesIds = characters.parallelStream()
                .flatMap(character -> character.getEpisodeIds().stream())
                .collect(toSet());

        assertThat(characters).hasSize(1);
        assertThat(episodesIds).hasSize(1);
        assertThat(info.getCount()).isEqualTo(1);
        assertThat(info.getPages()).isEqualTo(1);

        verify(rickMortyClient, times(1)).getCharacters(any(), any());
        verify(episodeService, times(1)).getEpisodes(any());
    }

    private Response<CharacterResponse> getCharacterResponseResponse(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = getMapper();
        String fileStringFromResource = getFileStringFromResource(json);
        return  objectMapper.readValue(fileStringFromResource, new TypeReference<Response<CharacterResponse>>() {});
    }

    private  List<Episode> getEpisodes() {
        List<Episode> episodes = IntStream
                .range(0, 10)
                .mapToObj(value -> Episode.builder()
                        .id(1)
                        .name("Pilot")
                        .url("https://rickandmortyapi.com/api/episode/1")
                        .air("December 2, 2013")
                        .episode("S01E01")
                        .created("2017-11-10T12:56:33.798Z")
                        .build())
                .collect(toList());
        return episodes;
    }


}
