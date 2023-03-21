package com.search.location.domain.location.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LocationScoreVo {

    private int score;

    private int index;

    public void addScore(int score) {
        this.score = this.score | (1 << score);
    }
}
