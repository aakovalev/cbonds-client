package io.github.aakovalev.cbonds.wiremodel.filters;

class InFilterTest extends MultiValueFilterTest {
    private static final String ID = "id";
    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";

    @Override
    protected MultiValueFilter createFilterFor(String[] values) {
        return new InFilter(ID, values);
    }

    @Override
    protected String[] getTestValues() {
        return new String[] {VALUE1, VALUE2};
    }

    @Override
    protected FilterOperator getFilterOperator() {
        return FilterOperator.IS_IN;
    }

    @Override
    protected String getTestField() {
        return ID;
    }
}
