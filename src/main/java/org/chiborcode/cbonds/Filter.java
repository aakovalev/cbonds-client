package org.chiborcode.cbonds;

import lombok.Data;

@Data
public class Filter {
    private final String field;
    private final String operator;
    private final String value;

    public Filter(String field, String operator, String value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }
}
