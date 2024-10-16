package org.chiborcode.cbonds;

import java.util.ArrayList;
import java.util.List;

public class ClientRequestBuilder {
    private String user;
    private String password;
    private final List<Filter> filters = new ArrayList<>();
    private Quantity quantity;
    private final List<Sorting> sortings = new ArrayList<>();
    private final List<Field> fields = new ArrayList<>();

    public ClientRequest build() {
        ClientRequest request = new ClientRequest();
        request.setAuth(new Credentials(user, password));
        request.setFilters(filters);
        request.setQuantity(quantity);
        request.setSorting(sortings);
        request.setFields(fields);
        return request;
    }

    public ClientRequestBuilder withCredentials(String user, String password) {
        this.user = user;
        this.password = password;
        return this;
    }

    public ClientRequestBuilder withFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    public ClientRequestBuilder withQuantity(Quantity quantity) {
        this.quantity = quantity;
        return this;
    }

    public ClientRequestBuilder withSorting(Sorting sorting) {
        sortings.add(sorting);
        return this;
    }

    public ClientRequestBuilder withField(String fieldName) {
        fields.add(new Field(fieldName));
        return this;
    }
}


