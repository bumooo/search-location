package com.search.location.domain.keyword.service;

import com.search.location.domain.keyword.entity.Keyword;
import com.search.location.domain.keyword.repository.KeywordRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KeywordServiceImplTest {

    @Autowired private KeywordServiceImpl keywordService;
    @Autowired private KeywordRepository keywordRepository;

    @AfterEach
    public void clearRedis() {
        keywordRepository.deleteAll();
    }

    @Test
    @DisplayName("여러명이 은행을 100번 검색한다.")
    void 여러명이_은행을_100번_검색한다() throws Exception {
        // given
        String keyword = "은행";
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        // when
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    keywordService.increaseCount(keyword);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        // then
        List<Keyword> topKeywords = keywordService.findTopKeyword();

        assertThat(topKeywords.get(0).getName()).isEqualTo("은행");
        assertThat(topKeywords.get(0).getCount()).isEqualTo(100L);
    }
    
    @Test
    @DisplayName("많이 검색한 순서대로 최대 10개 키워드 목록을 제공")
    void 많이_검색한_순서대로_최대_10개_키워드_목록을_제공() throws Exception {
        // given
        List<String> keywords = IntStream.range(1, 12)
                .mapToObj(String::valueOf)
                .collect(toList());

        keywords.forEach(keyword -> keywordService.increaseCount(keyword));

        // when
        List<Keyword> topKeyword = keywordService.findTopKeyword();

        // then
        List<String> topKeywordNames = topKeyword.stream()
                .map(Keyword::getName)
                .collect(toList());

        List<String> result = IntStream.range(1, 11)
                .mapToObj(String::valueOf)
                .collect(toList());

        assertThat(topKeywordNames).containsExactlyElementsOf(result);
        assertThat(topKeywordNames).isNotIn("11");
    }

    @Test
    @DisplayName("count가 같은 경우에 해당 count에 먼저 오른 값이 나와야 한다.")
    void count가_같은_경우에_해당_count에_먼저_오른_값이_나와야_한다() throws Exception {
        // given
        List<String> firstKeywords = IntStream.range(1, 3)
                .mapToObj(String::valueOf)
                .collect(toList());


        List<String> secondKeyword = IntStream.range(1, 3)
                .mapToObj(String::valueOf)
                .sorted(Collections.reverseOrder())
                .collect(toList());

        // when
        firstKeywords.forEach(keyword -> keywordService.increaseCount(keyword));
        List<Keyword> firstTopKeyword = keywordService.findTopKeyword();

        secondKeyword.forEach(keyword -> keywordService.increaseCount(keyword));
        List<Keyword> secondTopKeyword = keywordService.findTopKeyword();

        // then
        List<String> firstTopKeywordNames = firstTopKeyword.stream()
                .map(Keyword::getName)
                .collect(toList());

        List<String> firstResult = IntStream.range(1, 3)
                .mapToObj(String::valueOf)
                .collect(toList());

        List<String> secondTopKeywordNames = secondTopKeyword.stream()
                .map(Keyword::getName)
                .collect(toList());

        List<String> secondResult = IntStream.range(1, 3)
                .mapToObj(String::valueOf)
                .sorted(Collections.reverseOrder())
                .collect(toList());

        assertThat(firstTopKeywordNames).containsExactlyElementsOf(firstResult);
        assertThat(secondTopKeywordNames).containsExactlyElementsOf(secondResult);
    }
}