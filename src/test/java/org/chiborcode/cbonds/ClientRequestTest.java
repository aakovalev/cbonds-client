package org.chiborcode.cbonds;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.chiborcode.cbonds.ClientRequestField.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ClientRequestTest {
    // test values
    private static final String TEST_USER = "some-user";
    private static final String TEST_PASSWORD = "some-password";
    private static final String TEST_ISIN_CODES = "US037833EL06;USU5876JAM72";
    private static final String TEST_FIELD = "isin_code";
    private static final String TEST_OPERATOR = "in";
    private static final int TEST_LIMIT = 10;
    private static final int TEST_OFFSET = 0;
    private static final String TEST_ORDER = "asc";

    @Test
    void serialization() throws JsonProcessingException {
        ClientRequest request = new ClientRequest();
        request.setAuth(new Credentials(TEST_USER, TEST_PASSWORD));
        request.setFilters(List.of(new Filter(
                TEST_FIELD, TEST_OPERATOR, TEST_ISIN_CODES)));
        request.setQuantity(new Quantity(TEST_LIMIT, TEST_OFFSET));
        request.setSorting(List.of(new Sorting(TEST_FIELD, Order.ASC)));
        request.setFields(List.of(new Field(TEST_FIELD)));

        String actualJsonString = request.toJSONString();

        JSONObject expectedJSON = buildExpectedJSON();
        assertThat("Should be similar JSONs: \n\t"
                        + expectedJSON.toString()
                        + "\n\t" + actualJsonString,
                new JSONObject(actualJsonString).similar(expectedJSON),
                is(true));
    }

    private static JSONObject buildExpectedJSON() {
        return new JSONObject()
                .put(AUTH.toJsonValue(), new JSONObject()
                        .put(LOGIN.toJsonValue(), TEST_USER)
                        .put(PASSWORD.toJsonValue(), TEST_PASSWORD))
                .put(FILTERS.toJsonValue(), new JSONArray()
                        .put(new JSONObject()
                                .put(FIELD.toJsonValue(), TEST_FIELD)
                                .put(OPERATOR.toJsonValue(), TEST_OPERATOR)
                                .put(VALUE.toJsonValue(), TEST_ISIN_CODES)))
                .put(QUANTITY.toJsonValue(), new JSONObject()
                        .put(LIMIT.toJsonValue(), TEST_LIMIT)
                        .put(OFFSET.toJsonValue(), TEST_OFFSET))
                .put(SORTING.toJsonValue(), new JSONArray()
                        .put(new JSONObject()
                                .put(FIELD.toJsonValue(), TEST_FIELD)
                                .put(ORDER.toJsonValue(), TEST_ORDER)))
                .put(FIELDS.toJsonValue(), new JSONArray()
                        .put(new JSONObject()
                                .put(FIELD.toJsonValue(), TEST_FIELD)));
    }
}
