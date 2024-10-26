package io.github.aakovalev.cbonds.wiremodel.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.github.aakovalev.cbonds.wiremodel.filters.FilterOperator.IS_IN;
import static io.github.aakovalev.cbonds.wiremodel.filters.MultiValueFilter.VALUE_DELIMITER;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InFilterTest {
    private static final String ID = "id";
    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";

    @Test
    void serialization() throws JsonProcessingException {
        InFilter inFilter = new InFilter(ID, VALUE1, VALUE2);
        ObjectMapper mapper = new ObjectMapper();

        String actualJson = mapper.writeValueAsString(inFilter);

        JSONObject expectedJson = new JSONObject()
                .put("field", ID)
                .put("operator", IS_IN.toJsonValue())
                .put("value", VALUE1 + VALUE_DELIMITER + VALUE2);
        assertTrue(new JSONObject(actualJson).similar(expectedJson),
                "Should be similar JSONs: \n\t"
                        + expectedJson.toString()
                        + "\n\t" + actualJson);
    }
}
