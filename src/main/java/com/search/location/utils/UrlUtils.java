package com.search.location.utils;

import com.search.location.common.enums.HttpScheme;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

public class UrlUtils {

    private UrlUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static UriComponentsBuilder getRequestUrl(String host, String path) {
        return UriComponentsBuilder.newInstance()
                .scheme(HttpScheme.HTTPS.getValue())
                .host(host)
                .path(path);
    }

    public static void addQueryParams(UriComponentsBuilder uri, Map<String, Object> queryParams) {
        for (Map.Entry<String, Object> queryParam : queryParams.entrySet()) {
            uri.queryParam(queryParam.getKey(), queryParam.getValue());
        }
    }

    public static URI encoderUrl(UriComponentsBuilder uri) {
        return uri.build().encode().toUri();
    }
}
