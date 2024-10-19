package io.github.aakovalev.cbonds;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Map;

import static io.github.aakovalev.cbonds.ApiMethod.GET_STOCKS;
import static java.lang.Integer.valueOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientTest {
    private static final String USER = "test";
    private static final String PASSWORD = "test";

    @Test
    void simple_request() {
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

    @Test
    void should_throw_ClientException_when_problem_occurs()
            throws IOException, InterruptedException
    {
        HttpClient underlyingHttpClient = mock(HttpClient.class);
        Client client = new Client(underlyingHttpClient, USER, PASSWORD);
        when(underlyingHttpClient.send(any(), any()))
                .thenThrow(new IOException("I/O issue"));

        Assertions.assertThrows(
                ClientException.class,
                () -> client.execute(new Request(GET_STOCKS)));
    }

    @Test
    void request_with_sorting_desc() {
        Client client = new Client(USER, PASSWORD);
        Request request = new Request(GET_STOCKS);
        request.addSorting("id", Order.DESC);

        Response response = client.execute(request);

        Map<String, String> first = response.getItems().get(0);
        Map<String, String> second = response.getItems().get(1);
        assertThat(first.get("id"), greaterThan(second.get("id")));
    }

    @Test
    void request_with_sorting_asc() {
        Client client = new Client(USER, PASSWORD);
        Request request = new Request(GET_STOCKS);
        request.addSorting("id", Order.ASC);

        Response response = client.execute(request);

        Map<String, String> first = response.getItems().get(0);
        Map<String, String> second = response.getItems().get(1);
        assertThat(first.get("id"), lessThan(second.get("id")));
    }

    @Test
    void request_with_multiple_sorting() {
        Client client = new Client(USER, PASSWORD);
        Request request = new Request(GET_STOCKS);
        request.addSorting("id", Order.DESC);
        request.addSorting("kind_id", Order.ASC);

        Response response = client.execute(request);

        Map<String, String> first = response.getItems().get(0);
        Map<String, String> second = response.getItems().get(1);
        assertThat(
                valueOf(first.get("id")),
                greaterThan(valueOf(second.get("id"))));
        assertThat(
                valueOf(first.get("kind_id")),
                lessThanOrEqualTo(valueOf(second.get("kind_id"))));
    }
}