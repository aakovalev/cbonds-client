package io.github.aakovalev.cbonds.wiremodel.filters;

public class NotInFilter extends MultiValueFilter {
    public NotInFilter(String fieldName, String... values) {
        super(fieldName, FilterOperator.IS_NOT_IN, values);
    }
}
