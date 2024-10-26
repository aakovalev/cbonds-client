package io.github.aakovalev.cbonds.wiremodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aakovalev.cbonds.ApiMethodName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"apiMethodName"})
public class Request {
    private final ApiMethodName apiMethodName;
    private Credentials auth;
    private List<Filter> filters;
    private Quantity quantity;
    private List<Sorting> sorting;
    private List<Field> fields;

    public Request(ApiMethodName apiMethodName) {
        this.apiMethodName = apiMethodName;
    }

    public String toJSONString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public String getApiMethodName() {
        return apiMethodName.name();
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