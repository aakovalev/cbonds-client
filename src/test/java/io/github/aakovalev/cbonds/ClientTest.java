package io.github.aakovalev.cbonds;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;

import static io.github.aakovalev.cbonds.ApiMethod.GET_STOCKS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientTest {
    private static final String USER = "test";
    private static final String PASSWORD = "test";

    @Test
    void simpleRequest() {
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
}