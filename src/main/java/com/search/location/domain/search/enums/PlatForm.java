package com.search.location.domain.search.enums;

public enum PlatForm {
    KAKAO(1),
    NAVER(2);

    private final int rank;

    PlatForm(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public int getScore() {
        return values().length - this.rank;
    }

}
