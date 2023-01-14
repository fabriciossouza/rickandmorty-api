package com.github.fabriciossouza.rickandmortyapi.domain.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseInfo {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;

}
