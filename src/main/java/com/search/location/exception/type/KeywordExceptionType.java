package com.search.location.exception.type;

import com.search.location.exception.CommonCustomException;

public enum KeywordExceptionType implements CommonCustomException {

    FAIL_INCREASE_KEYWORD_COUNT("키워드 카운트 증가 도중에 오류가 발생했습니다.")
    ;

    private final String message;

    KeywordExceptionType(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
