package io.github.aakovalev.cbonds;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientResponse {
    /**
     * Number of records found for the current page
     * (for the last page might not equal to <code>limit</code>)
     **/
    private int count;

    /**
     * Total number of records found according to <code>filters</code>
     * attribute defined in the request
     * (see {@link ClientRequest})
     */
    private int total;

    /**
     * Same value as <code>limit</code> in the request
     * (see {@link ClientRequest})
     */
    private int limit;

    /**
     * The value of <code>offset</code> that is not a multiple of the limit
     * is reduced to a multiple of the <code>limit</code>;
     */
    private int offset;

    /**
     * All records of the current page
     */
    private List<Map<String, String>> items;

    /**
     * Statistics for request execution time.
     * Might be useful for performance optimizations
     */
    @JsonProperty(value = "exec_time")
    private double execTime;

    private Map<String, Object> meta;

    public static ClientResponse fromJSON(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, ClientResponse.class);
    }
}
