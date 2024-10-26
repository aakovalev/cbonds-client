package io.github.aakovalev.cbonds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"method"})
public class Request {
    private final ApiMethod method;
    private Credentials auth;
    private List<Filter> filters;
    private Quantity quantity;
    private List<Sorting> sorting;
    private List<Field> fields;

    public Request(ApiMethod method) {
        this.method = method;
    }

    public String toJSONString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    protected String getApiMethodName() {
        return method.name();
    }

    public void addSorting(String fieldName, Order order) {
        if (sorting == null) {
            sorting = new ArrayList<>();
        }
        sorting.add(new Sorting(fieldName, order));
    }

    public void addFilter(Filter filter) {
        if (filters == null) {
            filters = new ArrayList<>();
        }
        filters.add(filter);
    }
}
