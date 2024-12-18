package io.github.aakovalev.cbonds.wiremodel.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.github.aakovalev.cbonds.wiremodel.filters.FilterOperator.IS_IN;
import static io.github.aakovalev.cbonds.wiremodel.filters.MultiValueFilter.VALUE_DELIMITER;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultiValueFilterTest {

    private static final String ID = "id";
    private static final String VALUE1 = "id1";
    private static final String VALUE2 = "id2";

    @Test
    void serialization() throws JsonProcessingException {
        MultiValueFilter mvFilter = createFilterFor(getTestValues());
        ObjectMapper mapper = new ObjectMapper();

        String actualJson = mapper.writeValueAsString(mvFilter);

        JSONObject expectedJson = new JSONObject()
                .put("field", getTestField())
                .put("operator", getFilterOperator().toJsonValue())
                .put("value", toJointValue(getTestValues()));
        assertTrue(new JSONObject(actualJson).similar(expectedJson),
                "Should be similar JSONs: \n\t"
                        + expectedJson.toString()
                        + "\n\t" + actualJson);
    }

    protected String getTestField() {
        return ID;
    }

    protected FilterOperator getFilterOperator() {
        return IS_IN;
    }

    protected MultiValueFilter createFilterFor(String[] values) {
        return new MultiValueFilter(getTestField(), getFilterOperator(), values);
    }

    protected String[] getTestValues() {
        return new String[] {VALUE1, VALUE2};
    }

    private static String toJointValue(String[] values) {
        return String.join(VALUE_DELIMITER, values);
    }
}
