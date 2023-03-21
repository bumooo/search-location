package com.search.location.domain.keyword.controller;

import com.search.location.common.response.ApiResponse;
import com.search.location.domain.keyword.dto.KeywordResponseDto;
import com.search.location.domain.keyword.entity.Keyword;
import com.search.location.domain.keyword.service.KeywordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/keyword")
public class KeywordController {

    private final KeywordServiceImpl keywordService;

    @GetMapping
    public ApiResponse findKeywords() {

        List<Keyword> topKeywords = keywordService.findTopKeyword();

        List<KeywordResponseDto> topKeywordResponse = topKeywords.stream()
                .map(KeywordResponseDto::from)
                .collect(toList());
        return ApiResponse.ok(topKeywordResponse);
    }
}
