package com.github.fabriciossouza.rickandmortyapi.domain.client.model.decorator;

import com.github.fabriciossouza.rickandmortyapi.domain.client.model.Episode;

import java.util.List;

public class CharacterDecorator {

    private Character character;
    private List<String> urlEpisodes;
    private List<Integer> idsEpisodes;
    private List<Episode> episodes;
}
