package com.github.fabriciossouza.rickandmortyapi.api.controller;


import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.github.fabriciossouza.rickandmortyapi.core.util.GenericMapper.converterCollection;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

public abstract class BaseController {

	protected  <T> List<T> build(List<?> objects, Class<T> clazz) {
		return converterCollection(objects, clazz);
	}

	protected ResponseEntity getList(final List<?> responses, Class cls){

		return responses != null && !responses.isEmpty()
				? ok(build(responses, cls))
				: notFound().build();
	}

}