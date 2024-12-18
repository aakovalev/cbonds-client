package io.github.aakovalev.cbonds;

import io.github.aakovalev.cbonds.wiremodel.Order;
import io.github.aakovalev.cbonds.wiremodel.*;
import io.github.aakovalev.cbonds.wiremodel.filters.Filter;
import io.github.aakovalev.cbonds.wiremodel.filters.FilterOperator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.List;
import java.util.Map;

import static io.github.aakovalev.cbonds.ApiMethodName.GET_STOCKS;
import static java.lang.Integer.valueOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientTest {
    private static final String USER = "test";
    private static final String PASSWORD = "test";
    private static final String ID = "id";
    private static final String TEST_ID = "169";
    private static final String TEST_ID2 = "6181";
    private static final String TEST_KIND_ID = "2";
    private static final String KIND_ID = "kind_id";
    private static final String ID_UP_LIMIT = "6190";
    private static final String ISIN = "isin";
    public static final String EMITENT_INN = "emitent_inn";
    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client(USER, PASSWORD);
    }

    @Test
    void simple_request() {
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
        client = new Client(underlyingHttpClient, USER, PASSWORD);
        when(underlyingHttpClient.send(any(), any()))
                .thenThrow(new IOException("I/O issue"));

        Assertions.assertThrows(
                ClientException.class,
                () -> client.execute(new Request(GET_STOCKS)));
    }

    @Test
    void request_with_sorting_desc() {
        Request request = new Request(GET_STOCKS);
        request.addSorting(ID, io.github.aakovalev.cbonds.wiremodel.Order.DESC);

        Response response = client.execute(request);

        Map<String, String> first = response.getItems().get(0);
        Map<String, String> second = response.getItems().get(1);
        assertThat(first.get(ID), greaterThan(second.get(ID)));
    }

    @Test
    void request_with_sorting_asc() {
        Request request = new Request(GET_STOCKS);
        request.addSorting(ID, io.github.aakovalev.cbonds.wiremodel.Order.ASC);

        Response response = client.execute(request);

        Map<String, String> first = response.getItems().get(0);
        Map<String, String> second = response.getItems().get(1);
        assertThat(first.get(ID), lessThan(second.get(ID)));
    }

    @Test
    void request_with_multiple_sorting() {
        Request request = new Request(GET_STOCKS);
        request.addSorting(ID, io.github.aakovalev.cbonds.wiremodel.Order.DESC);
        request.addSorting(KIND_ID, Order.ASC);

        Response response = client.execute(request);

        Map<String, String> first = response.getItems().get(0);
        Map<String, String> second = response.getItems().get(1);
        assertThat(
                valueOf(first.get(ID)),
                greaterThan(valueOf(second.get(ID))));
        assertThat(
                valueOf(first.get(KIND_ID)),
                lessThanOrEqualTo(valueOf(second.get(KIND_ID))));
    }

    @Test
    void request_with_filter() {
        Request request = new Request(GET_STOCKS);
        request.addFilter(new Filter(ID, FilterOperator.EQUAL, TEST_ID));

        Response response = client.execute(request);

        List<Map<String, String>> items = response.getItems();
        assertThat(items, notNullValue());
        assertThat(items.size(), equalTo(1));
        assertThat(items.get(0).get(ID), is(TEST_ID));
    }

    @Test
    void request_with_multiple_filters() {
        Request request = new Request(GET_STOCKS);
        request.addFilter(new Filter(ID, FilterOperator.LESS_THAN, ID_UP_LIMIT));
        request.addFilter(new Filter(KIND_ID, FilterOperator.EQUAL, TEST_KIND_ID));

        Response response = client.execute(request);

        List<Map<String, String>> items = response.getItems();
        assertThat(items, notNullValue());
        assertThat(items.size(), equalTo(1));
        assertThat(items.get(0).get(ID), is(TEST_ID2));
    }

    @Test
    void request_with_filtered_field() {
        Request request = new Request(GET_STOCKS);
        request.addField(ISIN);
        request.addField(EMITENT_INN);

        Response response = client.execute(request);

        Map<String, String> firstItem = response.getItems().get(0);
        assertTrue(firstItem.containsKey(ISIN));
        assertTrue(firstItem.containsKey(EMITENT_INN));
        assertFalse(firstItem.containsKey(ID));
    }

    @Test
    void example_for_documentation() {
        client = new Client("test", "test");
        Request request = new Request("get_stocks");

        // specify the fields that are needed in the response
        request.addField("id");
        request.addField("isin");
        request.addField("emitent_inn");

        // specify filter that is needed
        request.addFilter(new Filter("kind_id", FilterOperator.EQUAL, "2" /* stocks only */));

        // specify the order of result items
        request.addSorting("id", Order.DESC);

        Response response = client.execute(request);
        System.out.println("Returned items: " + response.getItems());
    }

    @Test
    void request_arbitrary_api_method() {
        Request request = new Request("get_entity_types");

        Response response = client.execute(request);

        List<Map<String, String>> actualItems = response.getItems();
        assertThat(actualItems, is(notNullValue()));
        assertThat(actualItems.size(), is(greaterThan(0)));
    }
}