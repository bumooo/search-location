package com.search.location.domain.keyword.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash("keyword")
public class Keyword {

    @Id
    private String name;

    private long count;

    private LocalDateTime modifyDateTime;

    public void increaseCount() {
        this.count++;
        this.modifyDateTime = LocalDateTime.now();
    }

    public static Keyword getInstance(String name) {
        Keyword keyword = new Keyword();
        keyword.name = name;
        keyword.count = 0;
        keyword.modifyDateTime = LocalDateTime.now();
        return keyword;
    }
}
