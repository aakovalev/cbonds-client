package io.github.aakovalev.cbonds;

import io.github.aakovalev.cbonds.wiremodel.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.aakovalev.cbonds.ApiMethodName.GET_STOCKS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class RequestBuilderTest {
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String ISIN_CODE = "ISIN_CODE";
    private static final String APPLE_STOCK = "US0378331005";
    private static final String ISSUED_DATE = "ISSUED_DATE";
    private static final String JUN12_2020 = "2020-06-12";
    private static final int LIMIT = 100;
    private static final int OFFSET = 200;

    @Test
    void should_build_request_with_given_credentials() {
        Request request = new RequestBuilder()
                .withCredentials(USER, PASSWORD)
                .build(GET_STOCKS);

        Credentials actualCredentials = request.getAuth();
        Credentials expectedCredentials = new Credentials(USER, PASSWORD);
        assertThat(actualCredentials, equalTo(expectedCredentials));
    }

    @Test
    void should_build_request_with_given_filters() {
        Request request = new RequestBuilder()
                .withFilter(new Filter(ISIN_CODE, FilterOperator.EQUAL, APPLE_STOCK))
                .withFilter(new Filter(ISSUED_DATE, FilterOperator.GREATER_THAN, JUN12_2020))
                .build(GET_STOCKS);

        List<Filter> actualFilters = request.getFilters();
        List<Filter> expectedFilters = List.of(
                new Filter(ISIN_CODE, FilterOperator.EQUAL, APPLE_STOCK),
                new Filter(ISSUED_DATE, FilterOperator.GREATER_THAN, JUN12_2020));
        assertThat(actualFilters, equalTo(expectedFilters));
    }

    @Test
    void should_build_request_with_given_quantity() {
        Request request = new RequestBuilder()
                .withQuantity(new Quantity(LIMIT, OFFSET))
                .build(GET_STOCKS);

        Quantity actualQuantity = request.getQuantity();
        Quantity expectedQuantity = new Quantity(LIMIT, OFFSET);
        assertThat(actualQuantity, equalTo(expectedQuantity));
    }

    @Test
    void should_build_request_with_given_sorting() {
        Request request = new RequestBuilder()
                .withSorting(new Sorting(ISIN_CODE, Order.DESC))
                .build(GET_STOCKS);

        List<Sorting> actualSorting = request.getSorting();
        List<Sorting> expectedSorting = List.of(new Sorting(ISIN_CODE, Order.DESC));
        assertThat(actualSorting, equalTo(expectedSorting));
    }

    @Test
    void should_build_request_with_given_fields() {
        Request request = new RequestBuilder()
                .withField(ISIN_CODE)
                .withField(ISSUED_DATE)
                .build(GET_STOCKS);

        List<Field> actualFields = request.getFields();
        List<Field> expectedFields = List.of(
                new Field(ISIN_CODE), new Field(ISSUED_DATE));
        assertThat(actualFields, equalTo(expectedFields));
    }
}