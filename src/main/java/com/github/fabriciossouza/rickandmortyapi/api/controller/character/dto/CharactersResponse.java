package com.github.fabriciossouza.rickandmortyapi.api.controller.character.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CharactersResponse {

    private PageResponse info;
    private List<CharacterResponse> characters;

}
