package io.github.aakovalev.cbonds.wiremodel.filters;

import static io.github.aakovalev.cbonds.wiremodel.filters.FilterOperator.IS_IN;

public class InFilter extends MultiValueFilter {
    public InFilter(String fieldName, String... values) {
        super(fieldName, IS_IN, values);
    }
}
