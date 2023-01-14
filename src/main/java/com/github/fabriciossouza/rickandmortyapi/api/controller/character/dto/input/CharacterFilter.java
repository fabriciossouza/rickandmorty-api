package com.github.fabriciossouza.rickandmortyapi.api.controller.character.dto.input;

import javax.validation.constraints.Size;

public record CharacterFilter(@Size(max = 100) String name) {
}
