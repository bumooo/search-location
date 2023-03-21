package com.search.location.domain.keyword.repository;

import com.search.location.domain.keyword.entity.Keyword;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KeywordRepository extends CrudRepository<Keyword, String> {

    List<Keyword> findTop10ByOrderByCountDescModifyDateTime();

}
