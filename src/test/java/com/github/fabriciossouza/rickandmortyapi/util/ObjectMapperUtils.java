package com.github.fabriciossouza.rickandmortyapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class ObjectMapperUtils {

    private static ObjectMapper objectMapper;

    public static ObjectMapper get() {
        if(objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        return objectMapper;
    }

}
