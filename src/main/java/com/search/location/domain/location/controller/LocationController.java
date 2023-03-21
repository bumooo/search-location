package com.search.location.domain.location.controller;

import com.search.location.common.response.ApiResponse;
import com.search.location.domain.keyword.service.KeywordServiceImpl;
import com.search.location.domain.location.service.LocationServiceImpl;
import com.search.location.domain.location.vo.LocationSearchResultVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationServiceImpl locationService;
    private final KeywordServiceImpl keywordService;

    @GetMapping
    public ApiResponse locationSearchByKeyword(@RequestParam String keyword) {
        List<LocationSearchResultVo> locationSearchResultVos = locationService.searchLocationByKeyword(keyword);
        keywordService.increaseCount(keyword);
        return ApiResponse.ok(locationSearchResultVos);
    }
}
