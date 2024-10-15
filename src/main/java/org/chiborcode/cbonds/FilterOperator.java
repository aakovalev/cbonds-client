package org.chiborcode.cbonds;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FilterOperator {
    EQ, NE, LT, LE, GT, GE, IN, NI, BW, BN, EW, CN, NC, NU, NN;

    @JsonValue
    public String toJsonValue() {
        return name().toLowerCase();
    }
}
