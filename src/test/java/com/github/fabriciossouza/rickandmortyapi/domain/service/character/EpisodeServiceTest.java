package com.github.fabriciossouza.rickandmortyapi.domain.service.character;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.RickMortyClient;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto.EpisodeResponse;
import com.github.fabriciossouza.rickandmortyapi.domain.model.Episode;
import com.github.fabriciossouza.rickandmortyapi.domain.service.EpisodeService;
import lombok.SneakyThrows;
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
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;
import static org.springframework.http.ResponseEntity.ok;

@ExtendWith(SpringExtension.class)
public class EpisodeServiceTest {
    @InjectMocks
    private EpisodeService service;
    @Mock
    private  RickMortyClient rickMortyClient;

    @DisplayName("Deve retornar uma lista de vazia quando não for informado nenhum de de episodes")
    @SneakyThrows
    @Test
    void deveRetonarArraayVazioQuandoNaoInformarEpisodioId() {

        List<Episode> episodes = service.getEpisodes(null);

        assertThat(episodes).hasSize(0);
        verify(rickMortyClient, times(0)).getEpisodes(anyInt());
        verify(rickMortyClient, times(0)).getEpisodes(anySet());

    }

    @DisplayName("Deve retornar uma lista de  todos os episorios por array de ids")
    @SneakyThrows
    @Test
    void deveRetornarListaTodosEpisodiosPorArrayIds() {

        Set<Integer> episodeIds = IntStream.range(0, 20)
                .mapToObj(i -> i).
                collect(toSet());

        var episodesResponse = getEpisodesResponse("/json/episode/get-episodes.json");
        when(rickMortyClient.getEpisodes(episodeIds))
                .thenReturn(ok(episodesResponse));

        List<Episode> episodes = service.getEpisodes(episodeIds);

        assertThat(episodes).hasSize(20);
        verify(rickMortyClient, times(0)).getEpisodes(anyInt());
        verify(rickMortyClient, times(1)).getEpisodes(anySet());
    }

    @DisplayName("Deve retornar uma lista com apenas um episodio")
    @SneakyThrows
    @Test
    void deveRetornarUmaListaComApenasUmEpisodio() {

        Set<Integer> episodeIds = IntStream.range(0, 1)
                .mapToObj(i -> i).
                collect(toSet());

        var episodeResponse = getEpisodeResponse("/json/episode/get-episode.json");
        Integer episodeId = episodeIds.stream().findFirst().get();

        when(rickMortyClient.getEpisodes(episodeId))
                .thenReturn(ok(episodeResponse));

        List<Episode> episodes = service.getEpisodes(episodeIds);

        assertThat(episodes).hasSize(1);
        verify(rickMortyClient, times(1)).getEpisodes(anyInt());
        verify(rickMortyClient, times(0)).getEpisodes(anySet());
    }

    @SneakyThrows
    private List<EpisodeResponse> getEpisodesResponse(String json) {
        ObjectMapper objectMapper = getMapper();
        return objectMapper.readValue(getStringFromResource(json), new TypeReference<List<EpisodeResponse>>() {});
    }

    @SneakyThrows
    private EpisodeResponse getEpisodeResponse(String json)  {
        ObjectMapper objectMapper = getMapper();
        return objectMapper.readValue(getStringFromResource(json), EpisodeResponse.class);
    }

    private String getStringFromResource(String json) {
        return getFileStringFromResource(json);
    }


}
