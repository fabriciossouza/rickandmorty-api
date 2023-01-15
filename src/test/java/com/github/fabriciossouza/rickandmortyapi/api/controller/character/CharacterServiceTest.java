package com.github.fabriciossouza.rickandmortyapi.api.controller.character;


import com.fasterxml.jackson.core.type.TypeReference;
import com.github.fabriciossouza.rickandmortyapi.domain.client.Response;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.RickMortyClient;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto.CharacterResponse;
import com.github.fabriciossouza.rickandmortyapi.domain.model.Episode;
import com.github.fabriciossouza.rickandmortyapi.domain.model.dto.CharactersDTO;
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
import java.util.stream.IntStream;

import static com.github.fabriciossouza.rickandmortyapi.util.FileResourcesUtils.getFileStringFromResource;
import static com.github.fabriciossouza.rickandmortyapi.util.ObjectMapperUtils.get;
import static java.util.stream.Collectors.toList;
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

       String response = getFileStringFromResource("character/get-all.character.json");
        var characterResponseResponse = get().readValue(response, new TypeReference<Response<CharacterResponse>>() {});

        when(rickMortyClient.getCharacters(any(), any()))
                .thenReturn(ok(characterResponseResponse));

        when(episodeService.getEpisodes(any()))
                .thenReturn(getEpisodes());

    }

    @DisplayName("Deve retornar listagem de todos os personagens")
    @Test
    void deveRetornarListaDeTodosPersonagens() {

        CharactersDTO charactersDTO = service.getCharacters(eq(null), any());

        verify(rickMortyClient, times(1)).getCharacters(any(), any());
        verify(episodeService, times(1)).getEpisodes(any());

    }

    private static List<Episode> getEpisodes() {
        List<Episode> episodes = IntStream
                .range(0, 10)
                .mapToObj(value -> Episode.builder()
                        .id(1)
                        .name("Pilot")
                        .url("https://rickandmortyapi.com/api/episode/1")
                        .episode("S01E01")
                        .created("2017-11-10T12:56:33.798Z")
                        .build())
                .collect(toList());
        return episodes;
    }


}
