package com.github.fabriciossouza.rickandmortyapi.domain.client.rickmorty.dto;

import com.github.fabriciossouza.rickandmortyapi.core.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CharacterResponse {

    private Integer id;
    private String name;
    private String status;
    private String url;
    private String created;
    private List<Integer> episodeIds;

    public void setEpisode(List<String> episodes) {
        if(episodes != null && !episodes.isEmpty()) {
            this.episodeIds = episodes.stream()
                    .map(StringUtil::getNumberByString)
                    .toList();
        }
    }
}
