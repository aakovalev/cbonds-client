package io.github.aakovalev.cbonds.wiremodel.filters;

class MultiValueFilter extends Filter {
    public static final String VALUE_DELIMITER = ";";

    public MultiValueFilter(String fieldName, FilterOperator operator, String... values) {
        super(fieldName, operator, valuesToString(values));
    }

    private static String valuesToString(String... values) {
        return String.join(VALUE_DELIMITER, values);
    }
}
