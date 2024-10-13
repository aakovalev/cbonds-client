package org.chiborcode.cbonds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientResponse {
    private String total;
    private String offset;
    private String limit;
    private List<Map<String, String>> items;

    public static ClientResponse fromJSON(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, ClientResponse.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientResponse response = (ClientResponse) o;
        return Objects.equals(total, response.total) && Objects.equals(offset, response.offset) && Objects.equals(limit, response.limit) && Objects.equals(items, response.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, offset, limit, items);
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public void setItems(List<Map<String, String>> items) {
        this.items = items;
    }

    public List<Map<String, String>> getItems() {
        return Collections.unmodifiableList(items);
    }
}
