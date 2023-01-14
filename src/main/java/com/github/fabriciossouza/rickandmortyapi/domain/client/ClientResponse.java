package com.github.fabriciossouza.rickandmortyapi.domain.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//:TODO veficiar se precisar as anotações
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClientResponse<T> {

    private List<T> results;

    private ClientResponseInfo info;
}
