package com.github.fabriciossouza.rickandmortyapi.core.client;

import com.github.fabriciossouza.rickandmortyapi.domain.exception.CharacterNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {


    //TODO: implement
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 404:
                return new CharacterNotFoundException();
            default:
                return new Exception("messae");
        }
    }
}