package org.chiborcode.cbonds;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

@Data
public class ClientRequest {
    private Credentials auth;
    private List<Filter> filters;
    private Quantity quantity;
    private List<Sorting> sorting;

    public String toJSONString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
