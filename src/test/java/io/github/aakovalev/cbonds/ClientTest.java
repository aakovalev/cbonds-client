package io.github.aakovalev.cbonds;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static io.github.aakovalev.cbonds.ApiMethod.GET_STOCKS;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

class ClientTest {
    private static final String USER = "test";
    private static final String PASSWORD = "test";

    @Test
    void simpleRequest() throws URISyntaxException, IOException, InterruptedException {
        Client client = new Client(USER, PASSWORD);

        Response response = client.execute(new Request(GET_STOCKS));

        assertThat(response.getCount(), is(greaterThan(0)));
        assertThat(response.getLimit(), is(greaterThan(0)));
        assertThat(response.getOffset(), is(greaterThanOrEqualTo(0)));
        assertThat(response.getTotal(), is(greaterThan(0)));
        assertThat(response.getItems(), is(notNullValue()));
        assertThat(response.getExecTime(), is(greaterThan(0.0)));
        assertThat(response.getMeta(), is(Matchers.notNullValue()));
    }
}