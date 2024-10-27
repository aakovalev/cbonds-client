package io.github.aakovalev.cbonds.wiremodel.filters;

class NotInFilterTest extends MultiValueFilterTest {

    private static final String SOME_FIELD = "some_field";
    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";

    @Override
    protected FilterOperator getFilterOperator() {
        return FilterOperator.IS_NOT_IN;
    }

    @Override
    protected MultiValueFilter createFilterFor(String[] values) {
        return new NotInFilter(SOME_FIELD, values);
    }

    @Override
    protected String[] getTestValues() {
        return new String[] {VALUE1, VALUE2};
    }

    @Override
    protected String getTestField() {
        return SOME_FIELD;
    }
}
