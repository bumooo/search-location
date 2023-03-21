package com.search.location.domain.location.service;

import com.search.location.domain.location.vo.LocationSearchResultVo;
import com.search.location.domain.search.enums.PlatForm;
import com.search.location.domain.search.vo.CommonLocation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.search.location.domain.search.vo.KakaoLocationResponseVo.KakaoLocation;
import static com.search.location.domain.search.vo.NaverLocationResponseVo.NaverLocation;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocationServiceImplTest {

    @Autowired private LocationServiceImpl locationService;

    private final Map<PlatForm, List<? extends CommonLocation>> searchResultMap
        = new EnumMap<>(PlatForm.class);

    private void setUp(int kStart, int kEnd, int nStart, int nEnd) {
        List<KakaoLocation> kakaoLocations = IntStream.range(kStart, kEnd)
                .mapToObj(String::valueOf)
                .map(KakaoLocation::from)
                .collect(toList());

        List<NaverLocation> naverLocationNames = IntStream.range(nStart, nEnd)
                .mapToObj(String::valueOf)
                .map(NaverLocation::from)
                .collect(toList());

        searchResultMap.put(PlatForm.KAKAO, kakaoLocations);
        searchResultMap.put(PlatForm.NAVER, naverLocationNames);
    }

    @AfterEach
    void afterSetUp() {
        searchResultMap.clear();
    }

    @Test
    @DisplayName("검색 결과가 겹치지 않는 경우")
    void 검색_결과가_겹치지_않는_경우 () throws Exception {
        // given
        setUp(1, 6, 6, 11);
        // when
        List<LocationSearchResultVo> sortSearchResults
                = locationService.sortSearchResult(searchResultMap);

        // then
        List<String> titles = sortSearchResults.stream()
                .map(LocationSearchResultVo::getTitle)
                .collect(toList());

        List<String> result = IntStream.range(1, 11)
                .mapToObj(String::valueOf)
                .collect(toList());

        assertThat(titles).containsExactlyElementsOf(result);
    }

    @Test
    @DisplayName("카카오의 5번째 인덱스가 네이버 검색과 겹치는 경우")
    void 카카오의_5번_인덱스가_네이버_검색과_겹치는_경우 () throws Exception {
        // given
        setUp(1, 6, 5, 10);

        // when
        List<LocationSearchResultVo> sortSearchResults
                = locationService.sortSearchResult(searchResultMap);

        // then
        List<String> titles = sortSearchResults.stream()
                .map(LocationSearchResultVo::getTitle)
                .collect(toList());

        List<String> result = Arrays
                .asList("5", "1", "2", "3", "4", "6", "7", "8", "9");

        assertThat(titles).containsExactlyElementsOf(result);
    }

    @Test
    @DisplayName("카카오 4, 5번째 인덱스가 네이버랑 겹치는 경우")
    void 카카오_4번_5번_인덱스가_네이버랑_겹치는_경우() throws Exception {
        // given
        setUp(1, 6, 4, 9);

        // when
        List<LocationSearchResultVo> sortSearchResults
                = locationService.sortSearchResult(searchResultMap);

        // then
        List<String> titles = sortSearchResults.stream()
                .map(LocationSearchResultVo::getTitle)
                .collect(toList());

        List<String> result = Arrays
                .asList("4", "5", "1", "2", "3", "6", "7", "8");

        assertThat(titles).containsExactlyElementsOf(result);
    }
}