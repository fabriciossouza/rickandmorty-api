package com.github.fabriciossouza.rickandmortyapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Episode {

    private Integer id;
    private String name;
    private String air;
    private String episode;
    private String url;
    private String created;

}
