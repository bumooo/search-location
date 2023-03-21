package com.search.location.domain.location.service;

import com.search.location.domain.location.vo.LocationScoreVo;
import com.search.location.domain.location.vo.LocationSearchResultVo;
import com.search.location.domain.search.enums.PlatForm;
import com.search.location.domain.search.service.SearchApiService;
import com.search.location.domain.search.service.SearchServiceProviderFactory;
import com.search.location.domain.search.vo.CommonLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.search.location.domain.search.enums.PlatForm.values;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl {

    private final SearchServiceProviderFactory serviceProviderFactory;

    public List<LocationSearchResultVo> searchLocationByKeyword(String keyword) {

        Map<PlatForm, List<? extends CommonLocation>> searchResultMapByPlatForm = new EnumMap<>(PlatForm.class);

        for (PlatForm platForm : values()) {
            SearchApiService searchService = serviceProviderFactory.getService(platForm);
            List<? extends CommonLocation> commonLocations = searchService.searchByKeyword(keyword);
            searchResultMapByPlatForm.put(platForm, commonLocations);
        }
        return sortSearchResult(searchResultMapByPlatForm);
    }

    public List<LocationSearchResultVo> sortSearchResult(Map<PlatForm, List<? extends CommonLocation>> searchResultMapByPlatForm) {
        Map<String, LocationScoreVo> searchLocationMap = new HashMap<>();

        for (Map.Entry<PlatForm, List<? extends CommonLocation>> searchResultEntry : searchResultMapByPlatForm.entrySet()) {
            calculateSearchResult(searchLocationMap, searchResultEntry);
        }

        List<LocationSearchResultVo> searchResultVos = new ArrayList<>();
        for (Map.Entry<String, LocationScoreVo> locationScoreEntry : searchLocationMap.entrySet()) {
            searchResultVos.add(LocationSearchResultVo.of(locationScoreEntry.getKey(), locationScoreEntry.getValue()));
        }

        Collections.sort(searchResultVos);
        return searchResultVos;
    }

    private void calculateSearchResult(Map<String, LocationScoreVo> searchLocationMap, Map.Entry<PlatForm, List<? extends CommonLocation>> locationByPlatformEntry) {

        int index = 0;
        int priority = locationByPlatformEntry.getKey().getScore();

        for (CommonLocation location : locationByPlatformEntry.getValue()) {
            String name = location.getName().strip();
            searchLocationMap.putIfAbsent(name, new LocationScoreVo(0, 0));

            LocationScoreVo locationScoreVo = searchLocationMap.get(name);
            locationScoreVo.addScore(priority);
            locationScoreVo.setIndex(index++);
        }
    }

}
