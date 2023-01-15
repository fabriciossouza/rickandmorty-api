package com.github.fabriciossouza.rickandmortyapi.api.controller.character;

import com.github.fabriciossouza.rickandmortyapi.api.controller.character.dto.CharactersResponse;
import com.github.fabriciossouza.rickandmortyapi.api.controller.BaseController;
import com.github.fabriciossouza.rickandmortyapi.api.controller.character.dto.input.CharacterFilter;
import com.github.fabriciossouza.rickandmortyapi.domain.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping({"/api/character"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CharacterController extends BaseController {

    private final CharacterService service;
    @GetMapping
    public ResponseEntity<CharactersResponse> getCharacters(@Valid CharacterFilter characterFilter,
                                                            Pageable pageable) {

        var charactersDTO = service.getCharacters(characterFilter.getName(), pageable);
        return convert(charactersDTO, CharactersResponse.class);
    }


}