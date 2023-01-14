package com.github.fabriciossouza.rickandmortyapi.api.controller.character;

import com.github.fabriciossouza.rickandmortyapi.api.controller.BaseController;
import com.github.fabriciossouza.rickandmortyapi.api.controller.character.dto.CharacterResponse;
import com.github.fabriciossouza.rickandmortyapi.api.controller.character.dto.input.CharacterFilter;
import com.github.fabriciossouza.rickandmortyapi.domain.model.Character;
import com.github.fabriciossouza.rickandmortyapi.domain.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequestMapping({"/api/character"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CharacterController extends BaseController {

    private final CharacterService service;
    @GetMapping
    public ResponseEntity<CharacterResponse> getCharacters(CharacterFilter characterFilter, Pageable pageable) {

        List<Character> characters = service.getCharacters(characterFilter.name(), pageable);
        return getList(characters, CharacterResponse.class);
    }



}