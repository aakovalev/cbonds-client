package io.github.aakovalev.cbonds;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApiMethodName {
    GET_STOCKS;

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }
}
