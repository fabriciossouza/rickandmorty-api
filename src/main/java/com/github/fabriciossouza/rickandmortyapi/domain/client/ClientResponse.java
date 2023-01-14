package com.github.fabriciossouza.rickandmortyapi.domain.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientResponse<T> {

    private List<T> results;

    private ClientResponseInfo info;
}
