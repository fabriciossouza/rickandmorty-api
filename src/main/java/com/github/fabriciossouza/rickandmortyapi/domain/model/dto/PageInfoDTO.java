package com.github.fabriciossouza.rickandmortyapi.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.github.fabriciossouza.rickandmortyapi.core.util.StringUtil.getNumber;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageInfoDTO {

    private Integer count;
    private Integer pages;
    private String next;
    private String prev;

    public String getNext() {
        return getNumber(next);
    }

    public String getPrev() {
        return getNumber(prev);
    }


}
