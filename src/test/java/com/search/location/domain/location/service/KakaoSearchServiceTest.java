package com.search.location.domain.location.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.location.domain.search.service.impl.KakaoSearchApiServiceImpl;
import com.search.location.domain.search.vo.CommonLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
class KakaoSearchServiceTest {

    @InjectMocks private static KakaoSearchApiServiceImpl kakaoSearchService;

    @Spy private RestTemplate restTemplate;
    @Spy private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(kakaoSearchService, "host", "dapi.kakao.com");
        ReflectionTestUtils.setField(kakaoSearchService, "apiKey", "09a326b99c78cd66672fcd0d0eed4c1c");
        ReflectionTestUtils.setField(kakaoSearchService, "searchKeyWordUrl", "/v2/local/search/keyword.json");
        ReflectionTestUtils.setField(kakaoSearchService, "authroizationKey", "KakaoAK ");
    }

    @Test
    @DisplayName("카카오 검색 테스트")
    void searchByKeyword () throws Exception {
        // given
        String keyword = "은행";
        // when
        List<? extends CommonLocation> resultLocations = kakaoSearchService.searchByKeyword(keyword);
        // then
    }
}