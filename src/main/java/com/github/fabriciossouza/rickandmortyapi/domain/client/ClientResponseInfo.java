package com.github.fabriciossouza.rickandmortyapi.domain.client;

public record ClientResponseInfo (Integer count, Integer pages,
                                  String next, String prev) {
}
