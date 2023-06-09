package com.search.location.exception.type;

import com.search.location.exception.CommonCustomException;

public enum PlatFormExceptionType implements CommonCustomException {

    NOT_SUPPORT_PLATFORM("지원하지 않는 플랫폼입니다.")
    ;


    private final String message;

    PlatFormExceptionType(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
