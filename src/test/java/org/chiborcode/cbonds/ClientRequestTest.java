package org.chiborcode.cbonds;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ClientRequestTest {
    // test values
    private static final String TEST_USER = "some-user";
    private static final String TEST_PASSWORD = "some-password";
    private static final String TEST_ISIN_CODES = "US037833EL06;USU5876JAM72";
    private static final String TEST_FIELD = "isin_code";
    private static final String TEST_OPERATOR = "in";

    // request fields
    private static final String AUTH = "auth";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FIELD = "field";
    private static final String OPERATOR = "operator";
    private static final String VALUE = "value";

    @Test
    void serialization() throws JsonProcessingException {
        ClientRequest request = new ClientRequest();
        request.setAuth(new Credentials(TEST_USER, TEST_PASSWORD));
        request.setFilters(asList(new Filter(
                TEST_FIELD, TEST_OPERATOR, TEST_ISIN_CODES)));

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
                .put("filters", new JSONArray()
                        .put(new JSONObject()
                                .put(FIELD, TEST_FIELD)
                                .put(OPERATOR, TEST_OPERATOR)
                                .put(VALUE, TEST_ISIN_CODES)));
    }
}
