package com.search.location.common.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ApiResponse {

    @JsonIgnore
    private static final String TOTAL_COUNT = "totalCount";
    @JsonIgnore
    private static final String LIST = "list";

    private final Header header;

    private Object data;

    @Getter
    static class Header {

        private final String message;

        private Header() {
            this.message = "SUCCESS";
        }

        private Header(String message) {
            this.message = message;
        }
    }

    private ApiResponse(Object result) {
        this.header = new Header();

        if (result == null) {
            this.data = new HashMap<>();
        } else {
            if (result instanceof List) {
                setList((List<?>)result);
            } else {
                this.data = result;
            }
        }
    }

    private ApiResponse(String message) {
        this.header = new Header(message);
    }

    public static ApiResponse error(String message) {
        return new ApiResponse(message);
    }

    public static ApiResponse ok(Object result) {
        return new ApiResponse(result);
    }

    private void setList(List<?> result) {
        Map<String, Object> map = new HashMap<>();
        map.put(TOTAL_COUNT, result.size());
        map.put(LIST, result);
        this.data = map;
    }
}
