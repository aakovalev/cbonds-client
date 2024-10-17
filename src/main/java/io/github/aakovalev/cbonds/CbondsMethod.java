package io.github.aakovalev.cbonds;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CbondsMethod {
    GET_STOCKS;

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }
}
