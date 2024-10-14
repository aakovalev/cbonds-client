package org.chiborcode.cbonds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
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
}
