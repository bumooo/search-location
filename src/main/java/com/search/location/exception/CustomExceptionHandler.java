package com.search.location.exception;

import com.search.location.common.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ApiResponse handleCustomException(CustomException e) {
        return ApiResponse.error(e.getMessage());
    }
}
