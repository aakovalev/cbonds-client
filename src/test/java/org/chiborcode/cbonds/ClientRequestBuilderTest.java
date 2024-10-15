package org.chiborcode.cbonds;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.chiborcode.cbonds.FilterOperator.EQ;
import static org.chiborcode.cbonds.FilterOperator.GT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ClientRequestBuilderTest {
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String ISIN_CODE = "ISIN_CODE";
    private static final String APPLE_STOCK = "US0378331005";
    private static final String ISSUED_DATE = "ISSUED_DATE";
    private static final String JUN12_2020 = "2020-06-12";

    @Test
    void should_build_request_with_given_credentials() {
        ClientRequest request = new ClientRequestBuilder()
                .withCredentials(USER, PASSWORD)
                .build();

        Credentials actualCredentials = request.getAuth();
        Credentials expectedCredentials = new Credentials(USER, PASSWORD);
        assertThat(actualCredentials, equalTo(expectedCredentials));
    }

    @Test
    void should_build_request_with_given_filters() {
        ClientRequest request = new ClientRequestBuilder()
                .withFilter(new Filter(ISIN_CODE, EQ, APPLE_STOCK))
                .withFilter(new Filter(ISSUED_DATE, GT, JUN12_2020))
                .build();

        List<Filter> actualFilters = request.getFilters();
        List<Filter> expectedFilters = List.of(
                new Filter(ISIN_CODE, EQ, APPLE_STOCK),
                new Filter(ISSUED_DATE, GT, JUN12_2020));
        assertThat(actualFilters, equalTo(expectedFilters));
    }
}