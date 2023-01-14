package com.github.fabriciossouza.rickandmortyapi.api.controller.character.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageResponse {
    private Integer count;
    private Integer pages;
    private Integer next;
    private Integer prev;

}
