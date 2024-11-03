package io.github.aakovalev.cbonds.wiremodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aakovalev.cbonds.ApiMethodName;
import io.github.aakovalev.cbonds.wiremodel.filters.Filter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"methodName"})
public class Request {
    private final String methodName;
    private Credentials auth;
    private List<Filter> filters;
    private Quantity quantity;
    private List<Sorting> sorting;
    private List<Field> fields;

    public Request(ApiMethodName apiMethodName) {
        this(apiMethodName.name().toLowerCase());
    }

    public Request(String methodName) {
        this.methodName = methodName;
    }

    public String toJSONString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
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

    public void addField(String fieldName) {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        fields.add(new Field(fieldName));
    }
}
