package com.github.fabriciossouza.rickandmortyapi.api.controller.character.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class CharacterResponse {

    private Integer id;
    private String name;
    private String image;
    private String status;
    private String url;
    private String created;

    @JsonProperty("Episodes")
    private List<CharacterEpisodeResponse> episodes;


}
