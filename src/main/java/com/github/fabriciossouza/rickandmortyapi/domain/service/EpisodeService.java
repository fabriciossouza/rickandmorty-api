package com.github.fabriciossouza.rickandmortyapi.domain.service;

import com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.RickMortyClient;
import com.github.fabriciossouza.rickandmortyapi.domain.model.Episode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.github.fabriciossouza.rickandmortyapi.core.util.GenericMapper.converterCollection;
import static com.github.fabriciossouza.rickandmortyapi.core.util.StringUtil.getNumberByString;
import static java.util.stream.Collectors.toSet;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EpisodeService {
    private final RickMortyClient rickMortyClient;

    public List<Episode> getEpisodesByUrl(Set<String> urls) {
        Integer[] episodesIds = converterUrlEpisodeInIds(urls);
        return getEpisodesByIds(episodesIds);
    }

    public List<Episode> getEpisodesByIds(Integer[] episodesIds) {

        //TODO: estudar a possibilidade de colocar en uma thread
        var episodes = new ArrayList();
        for (Integer[] ids : splitArray(episodesIds, 20)) {
            var rickMortyClientEpisodes = rickMortyClient.getEpisodes(ids);
            var episodesResponse = rickMortyClientEpisodes.getBody();
            var results = episodesResponse.getResults();
            episodes.addAll(episodes.size(), converterCollection(results, Episode.class));
        }

        return episodes;

    }

    private Integer[] converterUrlEpisodeInIds(final Set<String> urls) {
        Set<Integer> ids = urls.stream()
                .map(episode -> getNumberByString(episode))
                .collect(toSet());

        return ids.toArray(Integer[]::new);
    }

    public static <T extends Object> List<T[]> splitArray(T[] array, int splitSize) {

        int numberOfArrays = array.length / splitSize;
        int remainder = array.length % splitSize;

        int start = 0;
        int end = 0;

        List<T[]> list = new ArrayList<T[]>();
        for (int i = 0; i < numberOfArrays; i++) {
            end += splitSize;
            list.add(Arrays.copyOfRange(array, start, end));
            start = end;
        }

        if(remainder > 0) {
            list.add(Arrays.copyOfRange(array, start, (start + remainder)));
        }
        return list;
    }

}