package com.github.fabriciossouza.rickandmortyapi.domain.service;

import com.github.fabriciossouza.rickandmortyapi.domain.client.Response;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.RickMortyClient;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto.CharacterResponse;
import com.github.fabriciossouza.rickandmortyapi.domain.model.Character;
import com.github.fabriciossouza.rickandmortyapi.domain.model.Episode;
import com.github.fabriciossouza.rickandmortyapi.domain.model.dto.CharactersDTO;
import com.github.fabriciossouza.rickandmortyapi.domain.model.dto.PageInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static com.github.fabriciossouza.rickandmortyapi.core.util.GenericMapper.converter;
import static com.github.fabriciossouza.rickandmortyapi.core.util.GenericMapper.converterCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CharacterService {
    private static final String FILTER_CHARACTER_BY_NAME = "name";

    private final RickMortyClient rickMortyClient;
    private final EpisodeService episodeService;

    public CharactersDTO getCharacters(final String name, Pageable pageable) {

        var filters = getFilters(name);

        var rickMortyClientCharacters = rickMortyClient.getCharacters(filters, pageable);
        var responseClientResponse =  rickMortyClientCharacters.getBody();

        return getCharacters(responseClientResponse);
    }

    private  CharactersDTO getCharacters(Response<CharacterResponse> response) {

        var characterResponses = response.getResults();

        var characters = converterCollection(response.getResults(), Character.class);
        var episodes = getEpisodesByCharacters(characterResponses);

        characters.forEach(character -> {
            var characterEpisodeIds = character.getEpisodeIds();
            var episodesByCharacter = episodes.stream()
                    .filter(episode -> characterEpisodeIds.stream()
                            .anyMatch(id -> id.equals(episode.getId())))
                    .collect(toList());

            character.setEpisodes(episodesByCharacter);
        });

        var pageInfo = converter(response.getInfo(), PageInfoDTO.class);

        return CharactersDTO.builder()
                .characters(characters)
                .info(pageInfo)
            .build();
    }

    private List<Episode> getEpisodesByCharacters(List<CharacterResponse> characterResponses) {
        Set<Integer> episodesIds = characterResponses.parallelStream()
                .flatMap(character -> character.getEpisodeIds().stream())
                .collect(toSet());

        return episodeService.getEpisodes(episodesIds);
    }

    private HashMap<String, String> getFilters(String name) {
        var filters = new HashMap<String, String>();
        if(name != null && !name.isEmpty()) {
            filters.put(FILTER_CHARACTER_BY_NAME, name);
        }
        return filters;
    }

}