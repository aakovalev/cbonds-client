package org.chiborcode.cbonds;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Order {
    ASC, DESC;

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }
}
