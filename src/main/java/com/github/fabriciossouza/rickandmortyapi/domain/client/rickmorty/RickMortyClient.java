package com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty;


import com.github.fabriciossouza.rickandmortyapi.core.client.CustomErrorDecoder;
import com.github.fabriciossouza.rickandmortyapi.domain.client.Response;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto.CharacterResponse;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto.EpisodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "rickandmorty-api",
        url = "${rickandmorty.api.host}",
        configuration = { CustomErrorDecoder.class} )
public interface RickMortyClient {

    @GetMapping("/api/character?{filters}")
    ResponseEntity<Response<CharacterResponse>> getCharacters(@RequestParam("filters") Map<String, String> filters,
                                                              Pageable pageable);

    @GetMapping("/api/episode/{ids[]}")
    ResponseEntity<Response<EpisodeResponse>> getEpisodes(@RequestParam("ids") Integer... ids);


}
