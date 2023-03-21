package com.search.location.domain.search.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.location.domain.search.enums.PlatForm;
import com.search.location.domain.search.service.AbstractApiService;
import com.search.location.domain.search.service.SearchApiService;
import com.search.location.domain.search.vo.CommonLocation;
import com.search.location.domain.search.vo.NaverLocationResponseVo;
import com.search.location.utils.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.search.location.domain.search.enums.PlatForm.NAVER;

@Slf4j
@Service
public class NaverSearchApiServiceImpl extends AbstractApiService implements SearchApiService {

    @Value("${naver.clientId}")
    private String clientId;

    @Value("${naver.clientSecret}")
    private String clientSecret;

    @Value("${naver.host}")
    private String host;

    @Value("${naver.search.local}")
    private String searchLocalUrl;


    private static final String QUERY = "query";
    private static final String DISPLAY = "display";

    private static final String HEADER_CLIENT_ID = "X-Naver-Client-Id";
    private static final String HEADER_CLIENT_SECRET = "X-Naver-Client-Secret";

    private static final Map<String, Object> defaultQueryParam = Map.of(
            DISPLAY, 5
    );

    public NaverSearchApiServiceImpl(ObjectMapper objectMapper, RestTemplate restTemplate) {
        super(objectMapper, restTemplate);
    }

    @Override
    public PlatForm getPlatForm() {
        return NAVER;
    }

    private HttpHeaders getDefaultHeaderMap() {
        Map<String, String> headers = new HashMap<>();
        headers.put(HEADER_CLIENT_ID, clientId);
        headers.put(HEADER_CLIENT_SECRET, clientSecret);
        return getHttpHeader(headers);
    }

    @Override
    public List<? extends CommonLocation> searchByKeyword(String keyword) {
        UriComponentsBuilder requestUrl = UrlUtils.getRequestUrl(host, searchLocalUrl);

        Map<String, Object> queryParams = new HashMap<>(defaultQueryParam);
        queryParams.put(QUERY, keyword);
        UrlUtils.addQueryParams(requestUrl, queryParams);

        HttpEntity<Object> httpEntity = new HttpEntity<>(getDefaultHeaderMap());

        ResponseEntity<String> response = get(UrlUtils.encoderUrl(requestUrl), httpEntity);

        if (response.getStatusCode() == HttpStatus.OK) {
            return convertResponseToObject(response.getBody(), NaverLocationResponseVo.class);
        } else {
            return null;
        }
    }
}
