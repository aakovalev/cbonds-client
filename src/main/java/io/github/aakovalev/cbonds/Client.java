package io.github.aakovalev.cbonds;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.*;
import static java.net.http.HttpResponse.*;

public class Client {
    private final String url;
    private final HttpClient httpClient;
    private final String user;
    private final String password;
    public static final String DEFAULT_URL =
            "https://ws.cbonds.info/services/json/";

    public Client(HttpClient client, String url, String user, String password) {
        this.url = url;
        this.httpClient = client;
        this.user = user;
        this.password = password;
    }

    public Client(String user, String password) {
        this(HttpClient.newHttpClient(), DEFAULT_URL, user, password);
    }

    public Response execute(Request request)
    {
        request.setAuth(new Credentials(user, password));
        try {
            HttpRequest httpRequest = toHttpRequest(request);
            HttpResponse<String> response =
                    httpClient.send(httpRequest, BodyHandlers.ofString());
            return Response.fromJSON(response.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
           throw new ClientException(e);
        }
    }

    private HttpRequest toHttpRequest(Request request)
            throws URISyntaxException, JsonProcessingException {
        return newBuilder()
                .uri(buildURI(request.getApiMethodName()))
                .POST(BodyPublishers.ofString(request.toJSONString()))
                .build();
    }

    private URI buildURI(String methodName) throws URISyntaxException {
        return new URI(url + methodName);
    }
}
