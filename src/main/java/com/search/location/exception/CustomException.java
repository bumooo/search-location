package com.search.location.exception;

public class CustomException extends RuntimeException {

    public CustomException(CommonCustomException exception) {
        super(exception.getMessage());
    }

    public CustomException(String message) {
        super(message);
    }
}
