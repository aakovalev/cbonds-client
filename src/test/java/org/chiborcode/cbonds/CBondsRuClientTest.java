package org.chiborcode.cbonds;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;

import static org.chiborcode.cbonds.CbondsMethod.GET_STOCKS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class CBondsRuClientTest {
    private static final String USER = "test";
    private static final String PASSWORD = "test";
    private static final String URL = "https://ws.cbonds.info/services/json/";

    @Test
    void simpleRequest() throws URISyntaxException, IOException, InterruptedException {
        CbondsRuClient client = new CbondsRuClient(HttpClient.newHttpClient(), URL);
        ClientRequest request = new ClientRequestBuilder()
                .withCredentials(USER, PASSWORD)
                .build();

        ClientResponse response = client.execute(GET_STOCKS, request);

        assertThat(response.getItems(), is(notNullValue()));
    }
}