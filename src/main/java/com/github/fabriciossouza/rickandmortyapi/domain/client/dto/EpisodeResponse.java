package com.github.fabriciossouza.rickandmortyapi.domain.client.dto;

public record EpisodeResponse(Integer id, String name, String air_date,
                              String episode, String url, String created)  {
}

