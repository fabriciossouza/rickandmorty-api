package com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty;


import com.github.fabriciossouza.rickandmortyapi.core.client.CustomErrorDecoder;
import com.github.fabriciossouza.rickandmortyapi.domain.client.ClientResponse;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto.CharacterResponse;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto.EpisodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "rickandmorty-api",
        url = "${rickandmorty.api.host}",
        configuration = { CustomErrorDecoder.class} )
public interface RickMortyClient {

    @GetMapping("/api/character?{filters}")
    ResponseEntity<ClientResponse<CharacterResponse>> getCharacters(@RequestParam("filters") Map<String, String> filters);

    @GetMapping("/api/episode/{ids}")
    ResponseEntity<ClientResponse<EpisodeResponse>>  getEpisodes(@RequestParam("ids") String... ids);

}
