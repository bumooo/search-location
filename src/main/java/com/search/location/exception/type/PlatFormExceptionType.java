package com.search.location.exception.type;

import com.search.location.exception.CommonCustomException;

public enum PlatFormExceptionType implements CommonCustomException {

    NOT_SUPPORT_PLATFORM(20000, "지원하지 않는 플랫폼입니다.")
    ;


    private final int code;

    private final String message;

    PlatFormExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
