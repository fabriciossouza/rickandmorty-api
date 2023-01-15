package com.github.fabriciossouza.rickandmortyapi.api.controller.character.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CharacterFilter {

    @Size(max = 100)
    private String name;

}
