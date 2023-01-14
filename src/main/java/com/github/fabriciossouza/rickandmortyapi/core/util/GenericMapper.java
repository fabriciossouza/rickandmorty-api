package com.github.fabriciossouza.rickandmortyapi.core.util;

import org.modelmapper.ModelMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.modelmapper.Conditions.isNotNull;


public class GenericMapper {

	private static ModelMapper mapper;

	public static <T> List<T> converterCollection(List<?> objects, Class<T> clazz) {
		return objects.stream()
				.map(obj -> converter(obj, clazz))
				.collect(toList());
	}

	public static <T> T converter(Object object, Class<T> clazz) {
		return mapper().map(object, clazz);
	}

	private static ModelMapper mapper() {
		if( mapper == null ){
			mapper = new ModelMapper();
			mapper.getConfiguration().setPropertyCondition(isNotNull());
		}

		return mapper;
	}
}