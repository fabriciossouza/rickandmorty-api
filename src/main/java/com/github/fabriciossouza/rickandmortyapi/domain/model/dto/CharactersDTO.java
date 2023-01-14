package com.github.fabriciossouza.rickandmortyapi.domain.model.dto;

import com.github.fabriciossouza.rickandmortyapi.domain.model.Character;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CharactersDTO {

    private List<Character> characters;
    private PageInfoDTO info;

}
