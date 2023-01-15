package com.github.fabriciossouza.rickandmortyapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
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
