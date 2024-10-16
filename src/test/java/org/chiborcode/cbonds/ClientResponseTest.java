package org.chiborcode.cbonds;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ClientResponseTest {

    private static final String ITEMS = "items";
    private static final String ID = "id";
    private static final String TOTAL = "total";
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";
    private static final String TEST_ID1 = "123";
    private static final String TEST_ID2 = "456";
    private static final String ISIN = "isin";
    private static final String ISIN1 = "RU0007984761";
    private static final String ISIN2 = "RU000A0ET123";
    private static final int TOTAL_VALUE = 9;
    private static final int LIMIT_VALUE = 10;
    private static final int OFFSET_VALUE = 0;

    @Test
    void deserialization() throws JsonProcessingException {
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(ITEMS, new JSONArray()
                .put(new JSONObject()
                        .put(ID, TEST_ID1)
                        .put(ISIN, ISIN1))
                .put(new JSONObject()
                        .put(ID, TEST_ID2)
                        .put(ISIN, ISIN2)
                ));
        jsonResponse.put(TOTAL, TOTAL_VALUE);
        jsonResponse.put(LIMIT, LIMIT_VALUE);
        jsonResponse.put(OFFSET, OFFSET_VALUE);

        ClientResponse actualResponse = ClientResponse.fromJSON(jsonResponse.toString());

        ClientResponse expectedResponse = new ClientResponse();
        expectedResponse.setLimit(LIMIT_VALUE);
        expectedResponse.setTotal(TOTAL_VALUE);
        expectedResponse.setOffset(OFFSET_VALUE);
        List<Map<String, String>> items = new ArrayList<>();
        items.add(Map.of(ID, TEST_ID1, ISIN, ISIN1));
        items.add(Map.of(ID, TEST_ID2, ISIN, ISIN2));
        expectedResponse.setItems(items);
        assertThat(actualResponse, equalTo(expectedResponse));
    }
}
