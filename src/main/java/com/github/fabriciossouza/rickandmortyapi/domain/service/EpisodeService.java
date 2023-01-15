package com.github.fabriciossouza.rickandmortyapi.domain.service;

import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.RickMortyClient;
import com.github.fabriciossouza.rickandmortyapi.domain.model.Episode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.github.fabriciossouza.rickandmortyapi.core.util.GenericMapper.converter;
import static com.github.fabriciossouza.rickandmortyapi.core.util.GenericMapper.converterCollection;
import static com.github.fabriciossouza.rickandmortyapi.core.util.StringUtil.getNumberByString;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EpisodeService {
    private final RickMortyClient rickMortyClient;

    public List<Episode> getEpisodesByUrl(Set<String> urls) {
        Set<Integer> episodesIds = converterUrlEpisodeInEpisodeIds(urls);

        if (episodesIds == null || episodesIds.isEmpty()) {
            return new ArrayList();
        }

        if(episodesIds.size() == 1) {
            var episodeId = episodesIds.iterator().next();
            return asList(getEpisodeById(episodeId));
        }

        return getEpisodesByIds(episodesIds);
    }

    private List<Episode> getEpisodesByIds(Set<Integer> episodesIds) {
        var rickMortyClientEpisodes = rickMortyClient.getEpisodes(episodesIds);
        var results = rickMortyClientEpisodes.getBody();
        return converterCollection(results, Episode.class);
    }

    private Episode getEpisodeById(Integer episodesId) {
        var rickMortyClientEpisodes = rickMortyClient.getEpisodes(episodesId);
        var results = rickMortyClientEpisodes.getBody();
        return converter(results, Episode.class);
    }

    private Set<Integer> converterUrlEpisodeInEpisodeIds(final Set<String> urls) {
        return urls.stream()
                .map(episode -> getNumberByString(episode))
                .collect(toSet());

    }

}