package com.search.location.exception.type;

import com.search.location.exception.CommonCustomException;

public enum ApiServiceExceptionType implements CommonCustomException {

    ;

    private final int code;

    private final String message;

    ApiServiceExceptionType(int code, String message) {
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
