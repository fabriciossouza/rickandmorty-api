package com.github.fabriciossouza.rickandmortyapi.api.controller.character.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CharacterEpisodeResponse {

    private Integer id;
    private String name;

    @JsonProperty("air_data")
    private String air;

    private String url;
    private String episode;
    private String created;

}
