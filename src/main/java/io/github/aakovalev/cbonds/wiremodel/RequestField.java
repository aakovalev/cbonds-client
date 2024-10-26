package io.github.aakovalev.cbonds.wiremodel;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RequestField {
    AUTH,
    LOGIN,
    PASSWORD,
    FIELD,
    OPERATOR,
    VALUE,
    FILTERS,
    QUANTITY,
    LIMIT,
    OFFSET,
    SORTING,
    ORDER,
    FIELDS;

    @JsonValue
    public String toJsonValue() {
        return name().toLowerCase();
    }
}
