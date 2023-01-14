package com.github.fabriciossouza.rickandmortyapi.domain.service;

import com.github.fabriciossouza.rickandmortyapi.domain.client.ClientResponse;
import com.github.fabriciossouza.rickandmortyapi.domain.client.model.Character;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.RickMortyClient;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto.CharacterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import static com.github.fabriciossouza.rickandmortyapi.core.util.GenericMapper.converterCollection;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CharacterService {
    private static final String FILTER_CHARACTER_BY_NAME = "name";
    private final RickMortyClient rickMortyClient;

    public List<Character> getCharacters(final String name) {

        var filters = getFilters(name);

        var response = rickMortyClient.getCharacters(filters);

        final ClientResponse<CharacterResponse> responseClientResponse =  response.getBody();
        List<CharacterResponse> characters = responseClientResponse.getResults();

        return converterCollection(characters, Character.class);
    }

    private HashMap<String, String> getFilters(String name) {
        var filters = new HashMap<String, String>();
        if(name != null && !name.isEmpty()) {
            filters.put(FILTER_CHARACTER_BY_NAME, name);
        }
        return filters;
    }

}