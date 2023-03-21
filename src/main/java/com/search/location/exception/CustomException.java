package com.search.location.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final String message;

    public CustomException(CommonCustomException exception) {
        super(exception.getMessage());
        this.message = exception.getMessage();
    }
}
