package io.github.aakovalev.cbonds;

import lombok.Data;

@Data
public class Filter {
    private final String field;
    private final FilterOperator operator;
    private final String value;

    public Filter(String field, FilterOperator operator, String value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }
}
