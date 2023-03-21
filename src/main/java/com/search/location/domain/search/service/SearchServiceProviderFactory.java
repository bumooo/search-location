package com.search.location.domain.search.service;


import com.search.location.domain.search.enums.PlatForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceProviderFactory {

    private final List<SearchApiService> services;
    private Map<PlatForm, SearchApiService> serviceMap;

    @PostConstruct
    public void init() {
        serviceMap = services.stream()
                .collect(Collectors.toMap(SearchApiService::getPlatForm, v -> v));
    }

    public SearchApiService getService(PlatForm platForm) {

        if (serviceMap.containsKey(platForm)) {
            return serviceMap.get(platForm);
        }
        throw new IllegalArgumentException("해당 플랫폼을 지원하지 않습니다. : " + platForm);
    }
}
