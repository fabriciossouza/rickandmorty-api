package com.github.fabriciossouza.rickandmortyapi.domain.client.model;

import lombok.Data;

import java.util.List;


@Data
public class Character {

    private Integer id;
    private String name;
    private String status;
    private String url;
    private String creator;

    private List<Episode> episode;


}
