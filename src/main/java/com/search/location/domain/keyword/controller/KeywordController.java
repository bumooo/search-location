package com.search.location.domain.keyword.controller;

import com.search.location.common.response.ApiResponse;
import com.search.location.domain.keyword.service.KeywordServiceImpl;
import com.search.location.exception.CustomException;
import com.search.location.exception.type.KeywordExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("{version:v1.*}/keywords")
public class KeywordController {

    private final KeywordServiceImpl keywordService;

    @GetMapping
    public ApiResponse findKeywords() {
        throw new CustomException(KeywordExceptionType.FAIL_INCREASE_KEYWORD_COUNT);
//        List<Keyword> topKeywords = keywordService.findTopKeyword();
//
//        List<KeywordResponseDto> topKeywordResponse = topKeywords.stream()
//                .map(KeywordResponseDto::from)
//                .collect(toList());
//        return ApiResponse.ok(topKeywordResponse);
    }
}
