package com.search.location.domain.search.service;

import com.search.location.domain.search.enums.PlatForm;
import com.search.location.domain.search.vo.CommonLocation;

import java.util.List;

public interface SearchApiService {

    PlatForm getPlatForm();

    List<? extends CommonLocation> searchByKeyword(String keyword);
}
