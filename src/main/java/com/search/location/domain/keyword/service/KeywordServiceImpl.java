package com.search.location.domain.keyword.service;


import com.search.location.domain.keyword.entity.Keyword;
import com.search.location.domain.keyword.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordServiceImpl {

    private final RedissonClient redissonClient;
    private final KeywordRepository keywordRepository;

    public Keyword findKeywordByName(String name) {
        return keywordRepository.findById(name)
                .orElseGet(() -> Keyword.getInstance(name));
    }

    public void increaseCount(String name) {
        RLock lock = redissonClient.getLock(name);

        try {
            boolean isAvailable = lock.tryLock(10, 3, TimeUnit.SECONDS);
            if (!isAvailable) {
                return ;
            }
            Keyword findKeyword = findKeywordByName(name);
            findKeyword.increaseCount();
            keywordRepository.save(findKeyword);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public List<Keyword> findTopKeyword() {
        return keywordRepository.findTop10ByOrderByCountDescModifyDateTime();
    }
}
