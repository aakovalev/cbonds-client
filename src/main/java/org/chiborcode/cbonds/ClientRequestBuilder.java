package org.chiborcode.cbonds;

import java.util.ArrayList;
import java.util.List;

public class ClientRequestBuilder {
    private String user;
    private String password;
    private final List<Filter> filters = new ArrayList<>();
    private Quantity quantity;

    public ClientRequest build() {
        ClientRequest request = new ClientRequest();
        request.setAuth(new Credentials(user, password));
        request.setFilters(filters);
        request.setQuantity(quantity);
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
}


