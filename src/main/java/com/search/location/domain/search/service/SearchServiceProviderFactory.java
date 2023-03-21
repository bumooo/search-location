package com.search.location.domain.search.service;


import com.search.location.domain.search.enums.PlatForm;
import com.search.location.exception.CustomException;
import com.search.location.exception.type.PlatFormExceptionType;
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
        throw new CustomException(PlatFormExceptionType.NOT_SUPPORT_PLATFORM);
    }
}
