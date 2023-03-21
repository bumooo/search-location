package com.search.location.common.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

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
    @Setter
    static class Header {

        private int code = 200;

        private String message = "SUCCESS";
    }

    private ApiResponse(Header header, Object result) {
        this.header = header;

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

    public static ApiResponse ok(Object result) {
        return new ApiResponse(new Header(), result);
    }

    private void setList(List<?> result) {
        Map<String, Object> map = new HashMap<>();
        map.put(TOTAL_COUNT, result.size());
        map.put(LIST, result);
        this.data = map;
    }
}
