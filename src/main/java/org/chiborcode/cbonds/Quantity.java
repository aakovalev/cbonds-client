package org.chiborcode.cbonds;

import lombok.Data;

@Data
public class Quantity {
    private final int limit;
    private final int offset;

    public Quantity(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }
}
