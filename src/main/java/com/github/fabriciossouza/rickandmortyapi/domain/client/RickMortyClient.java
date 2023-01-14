package com.github.fabriciossouza.rickandmortyapi.domain.client;


import com.github.fabriciossouza.rickandmortyapi.core.client.CustomErrorDecoder;
import com.github.fabriciossouza.rickandmortyapi.domain.client.dto.CharacterResponse;
import com.github.fabriciossouza.rickandmortyapi.domain.client.dto.EpisodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "rickandmorty-api",
        url = "${rickandmorty.api.host}",
        configuration = { CustomErrorDecoder.class} )
public interface RickMortyClient {

    @GetMapping("/api/character?{filters")
    ResponseEntity<ClientResponse<CharacterResponse>> getCharacters(@RequestParam("filters") Map<String, Object> filters);

    @GetMapping("/api/episode?{filters")
    ResponseEntity<ClientResponse<EpisodeResponse>>  getEpisodes(@RequestParam("filters") Map<String, Object> filters);

}
