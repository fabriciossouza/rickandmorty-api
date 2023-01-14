package com.github.fabriciossouza.rickandmortyapi.domain.client.dto;

import java.util.List;

public record CharacterResponse(Integer id, String name, String status, String url,
                                String creator, List<EpisodeResponse> episode )  {
}
