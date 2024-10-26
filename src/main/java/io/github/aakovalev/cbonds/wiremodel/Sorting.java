package io.github.aakovalev.cbonds.wiremodel;

import lombok.Data;

@Data
public class Sorting {
    private final String field;
    private final Order order;

    public Sorting(String field, Order order) {
        this.field = field;
        this.order = order;
    }
}
