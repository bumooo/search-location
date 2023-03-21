package com.search.location.exception.type;

import com.search.location.exception.CommonCustomException;

public enum CommonExceptionType implements CommonCustomException {

    INTERRUPT_THREAD("작업 진행이 중단되었습니다.")
    ;

    private final String message;

    CommonExceptionType(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
