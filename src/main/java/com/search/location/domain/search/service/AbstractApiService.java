package com.search.location.domain.search.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.location.domain.search.vo.CommonLocation;
import com.search.location.domain.search.vo.CommonLocationResponseVo;
import com.search.location.exception.CustomException;
import com.search.location.exception.type.ApiServiceExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractApiService {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public HttpHeaders getHttpHeader(Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        for (Map.Entry<String, String> header : headers.entrySet()) {
            httpHeaders.set(header.getKey(), header.getValue());
        }
        return httpHeaders;
    }

    public ResponseEntity<String> get(URI uri, HttpEntity<?> requestEntity) {
        return restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
    }

    public <T extends CommonLocationResponseVo> List<? extends CommonLocation> convertResponseToObject (String body, Class<T> responseType) {
        try {
            return responseType.cast(objectMapper.readValue(body, responseType)).getResult();
        } catch (JsonProcessingException e) {
            throw new CustomException(ApiServiceExceptionType.FAIL_JSON_PARSE);
        }
    }
}
