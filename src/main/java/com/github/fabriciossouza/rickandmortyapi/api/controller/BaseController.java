package com.github.fabriciossouza.rickandmortyapi.api.controller;


import org.springframework.http.ResponseEntity;

import static com.github.fabriciossouza.rickandmortyapi.core.util.GenericMapper.converter;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

public abstract class BaseController {

	public <T> T build(Object object, Class<T> clazz) {
		return converter(object, clazz);
	}

	protected ResponseEntity convert(Object object, Class cls){
		return object != null ? ok(build(object, cls))
				: notFound().build();
	}

}