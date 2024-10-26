package io.github.aakovalev.cbonds.wiremodel;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FilterOperator {
    EQUAL("eq"),
    NOT_EQUAL("ne"),
    LESS_THAN("lt"),
    LESS_OR_EQUAL("le"),
    GREATER_THAN("gt"),
    GREATER_OR_EQUAL("ge"),
    IS_IN("in"),
    IS_NOT_IN("ni"),
    BEGINS_WITH("bw"),
    NOT_BEGIN_WITH("bn"),
    ENDS_WITH("ew"),
    CONTAINS("cn"),
    NOT_CONTAIN("nc"),
    IS_NULL("nu"),
    IS_NOT_NULL("nn");

    private String code;

    FilterOperator(String operatorCode) {
        this.code = operatorCode;
    }

    @JsonValue
    public String toJsonValue() {
        return code;
    }
}
