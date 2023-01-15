package com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty;


import com.github.fabriciossouza.rickandmortyapi.core.client.CustomErrorDecoder;
import com.github.fabriciossouza.rickandmortyapi.domain.client.Response;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto.CharacterResponse;
import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto.EpisodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Set;

@FeignClient(name = "rickandmorty-api",
        url = "${rickandmorty.api.host}",
        configuration = { CustomErrorDecoder.class} )
public interface RickMortyClient {

    @GetMapping("/api/character")
    ResponseEntity<Response<CharacterResponse>> getCharacters(@RequestParam Map<String, String> filters, Pageable pageable);

    @GetMapping("/api/episode/{ids}")
    ResponseEntity<List<EpisodeResponse>> getEpisodes(@PathVariable("ids") Set<Integer> ids);

    @GetMapping("/api/episode/{id}")
    ResponseEntity<EpisodeResponse> getEpisodes(@PathVariable("id") Integer id);


}
