package com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EpisodeResponse(Integer id, String name,
                              @JsonProperty("air_date") String air,
                              String episode, String url, String created)  {
}

