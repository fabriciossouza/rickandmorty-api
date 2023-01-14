package com.github.fabriciossouza.rickandmortyapi.domain.client.model;

import lombok.Data;


@Data
public class Episode {

    private Integer id;
    private String name;
    private String air;
    private String episode;
    private String url;
    private String creator;

}
