package com.search.location.common.enums;

public enum HttpScheme {
    HTTP("http"), HTTPS("https");

    private final String value;

    HttpScheme(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
