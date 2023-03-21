package com.search.location.exception;

import com.search.location.common.response.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ApiResponse handleCustomException(CustomException e) {
        return ApiResponse.error(e.getMessage());
    }
}
