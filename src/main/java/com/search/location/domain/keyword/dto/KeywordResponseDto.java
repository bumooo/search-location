package com.search.location.domain.keyword.dto;

import com.search.location.domain.keyword.entity.Keyword;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KeywordResponseDto {

    private String name;

    private long count;

    public static KeywordResponseDto from(Keyword keyword) {
        KeywordResponseDto responseDto = new KeywordResponseDto();
        responseDto.name = keyword.getName();
        responseDto.count = keyword.getCount();
        return responseDto;
    }
}
