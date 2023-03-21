package com.search.location.exception.type;

import com.search.location.exception.CommonCustomException;

public enum ApiServiceExceptionType implements CommonCustomException {

    NOT_FOUND_INFORMATION("정보를 받아오기 실패했습니다."),
    FAIL_JSON_PARSE("JSON 파싱을 실패했습니다.")
    ;

    private final String message;

    ApiServiceExceptionType(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
