package io.github.aakovalev.cbonds.wiremodel;

import io.github.aakovalev.cbonds.ApiMethodName;
import io.github.aakovalev.cbonds.wiremodel.filters.Filter;

import java.util.ArrayList;
import java.util.List;

public class RequestBuilder {
    private String user;
    private String password;
    private final List<Filter> filters = new ArrayList<>();
    private Quantity quantity;
    private final List<Sorting> sortings = new ArrayList<>();
    private final List<Field> fields = new ArrayList<>();

    public Request build(ApiMethodName apiMethodName) {
        Request request = new Request(apiMethodName);
        request.setAuth(new Credentials(user, password));
        request.setFilters(filters);
        request.setQuantity(quantity);
        request.setSorting(sortings);
        request.setFields(fields);
        return request;
    }

    public RequestBuilder withCredentials(String user, String password) {
        this.user = user;
        this.password = password;
        return this;
    }

    public RequestBuilder withFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    public RequestBuilder withQuantity(Quantity quantity) {
        this.quantity = quantity;
        return this;
    }

    public RequestBuilder withSorting(Sorting sorting) {
        sortings.add(sorting);
        return this;
    }

    public RequestBuilder withField(String fieldName) {
        fields.add(new Field(fieldName));
        return this;
    }
}


