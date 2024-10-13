package org.chiborcode.cbonds;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientRequest {
    private Credentials auth;

    public void setAuth(Credentials auth) {
        this.auth = auth;
    }

    public Credentials getAuth() {
        return this.auth;
    }

    public String toJSONString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
