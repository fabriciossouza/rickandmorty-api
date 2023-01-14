package com.github.fabriciossouza.rickandmortyapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Character {

    private Integer id;
    private String name;
    private String status;
    private String url;
    private String created;
    private Set<Episode> episodes;

    private List<Integer> episodeIds;




}
