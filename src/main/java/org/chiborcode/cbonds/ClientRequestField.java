package org.chiborcode.cbonds;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ClientRequestField {
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