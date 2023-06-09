package com.search.location.domain.search.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.location.domain.search.enums.PlatForm;
import com.search.location.domain.search.service.AbstractApiService;
import com.search.location.domain.search.service.SearchApiService;
import com.search.location.domain.search.vo.CommonLocation;
import com.search.location.domain.search.vo.KakaoLocationResponseVo;
import com.search.location.exception.CustomException;
import com.search.location.exception.type.ApiServiceExceptionType;
import com.search.location.utils.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.search.location.domain.search.enums.PlatForm.KAKAO;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Service
public class KakaoSearchApiServiceImpl extends AbstractApiService implements SearchApiService {

    @Value("${kakao.apiKey}")
    private String apiKey;

    @Value("${kakao.host}")
    private String host;

    @Value("${kakao.search.keywordUrl}")
    private String searchKeyWordUrl;

    @Value("${kakao.header}")
    private String authroizationKey;

    private static final String QUERY = "query";
    private static final String SIZE = "size";
    private static final String PAGE = "page";

    private static final Map<String, Object> defaultQueryParam = Map.of(
            SIZE, 5,
            PAGE, 1
    );

    public KakaoSearchApiServiceImpl(ObjectMapper objectMapper, RestTemplate restTemplate) {
        super(objectMapper, restTemplate);
    }

    public PlatForm getPlatForm() {
        return KAKAO;
    }

    private HttpHeaders getDefaultHeaderMap() {
        Map<String, String> headers = new HashMap<>();
        headers.put(AUTHORIZATION, authroizationKey + apiKey);
        return getHttpHeader(headers);
    }

    @Override
    public List<? extends CommonLocation> searchByKeyword(String keyword) {
        UriComponentsBuilder requestUrl = UrlUtils.getRequestUrl(host, searchKeyWordUrl);

        Map<String, Object> queryParams = new HashMap<>(defaultQueryParam);
        queryParams.put(QUERY, keyword);
        UrlUtils.addQueryParams(requestUrl, queryParams);

        HttpEntity<Object> httpEntity = new HttpEntity<>(getDefaultHeaderMap());

        try {
            ResponseEntity<String> response = get(UrlUtils.encoderUrl(requestUrl), httpEntity);
            if (response.getStatusCode().is2xxSuccessful()) {
                return convertResponseToObject(response.getBody(), KakaoLocationResponseVo.class);
            }
        } catch (Exception e) {
            log.error("정보를 받아오기 실패했습니다. : {} {}", getPlatForm(), requestUrl.toUriString());
        }
        throw new CustomException(ApiServiceExceptionType.NOT_FOUND_INFORMATION);
    }
}
