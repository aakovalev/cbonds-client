package org.chiborcode.cbonds;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ClientRequestTest {
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    @Test
    void serialization() throws JsonProcessingException {
        ClientRequest request = new ClientRequest();
        request.setAuth(new Credentials(USER, PASSWORD));

        String actualJsonString = request.toJSONString();

        JSONObject expectedJSON = new JSONObject()
                .put("auth", new JSONObject()
                        .put("login", USER)
                        .put("password", PASSWORD));
        assertThat(new JSONObject(actualJsonString).similar(expectedJSON), is(true));
    }
}
