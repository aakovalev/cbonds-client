package org.chiborcode.cbonds;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    // request fields
    private static final String AUTH = "auth";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FIELD = "field";
    private static final String OPERATOR = "operator";
    private static final String VALUE = "value";
    private static final String FILTERS = "filters";
    private static final String QUANTITY = "quantity";
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";
    private static final String SORTING = "sorting";
    private static final String ORDER = "order";

    @Test
    void serialization() throws JsonProcessingException {
        ClientRequest request = new ClientRequest();
        request.setAuth(new Credentials(TEST_USER, TEST_PASSWORD));
        request.setFilters(List.of(new Filter(
                TEST_FIELD, TEST_OPERATOR, TEST_ISIN_CODES)));
        request.setQuantity(new Quantity(TEST_LIMIT, TEST_OFFSET));
        request.setSorting(List.of(new Sorting(TEST_FIELD, Order.ASC)));

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
                .put(AUTH, new JSONObject()
                        .put(LOGIN, TEST_USER)
                        .put(PASSWORD, TEST_PASSWORD))
                .put(FILTERS, new JSONArray()
                        .put(new JSONObject()
                                .put(FIELD, TEST_FIELD)
                                .put(OPERATOR, TEST_OPERATOR)
                                .put(VALUE, TEST_ISIN_CODES)))
                .put(QUANTITY, new JSONObject()
                        .put(LIMIT, TEST_LIMIT)
                        .put(OFFSET, TEST_OFFSET))
                .put(SORTING, new JSONArray()
                        .put(new JSONObject()
                                .put(FIELD, TEST_FIELD)
                                .put(ORDER, TEST_ORDER)));
    }
}
