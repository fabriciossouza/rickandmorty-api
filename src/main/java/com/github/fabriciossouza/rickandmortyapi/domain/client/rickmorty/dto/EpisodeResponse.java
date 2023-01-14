package com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeResponse {

    private Integer id;
    private String name;

    @JsonProperty("air_date")
    private  String air;
    private String episode;
    private String url;
    private String created;

}
