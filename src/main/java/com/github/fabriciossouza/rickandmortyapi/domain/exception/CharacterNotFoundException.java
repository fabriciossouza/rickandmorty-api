package com.github.fabriciossouza.rickandmortyapi.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@ResponseStatus(NOT_FOUND)
public class CharacterNotFoundException extends RuntimeException {

	public CharacterNotFoundException(final String str) {
		super(format("Character not found with : %s", str));
	}
	
}
