package io.github.aakovalev.cbonds;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.*;
import static java.net.http.HttpResponse.*;

public class CbondsRuClient {
    private final String url;
    private final HttpClient httpClient;
    public static final String DEFAULT_URL =
            "https://ws.cbonds.info/services/json/";

    public CbondsRuClient(HttpClient client, String url) {
        this.url = url;
        this.httpClient = client;
    }

    public CbondsRuClient() {
        this(HttpClient.newHttpClient(), DEFAULT_URL);
    }

    public Response execute(MethodName method, Request clientRequest)
            throws URISyntaxException, IOException, InterruptedException
    {
        HttpRequest request = buildRequest(
                method.name(), clientRequest.toJSONString());
        HttpResponse<String> response =
                httpClient.send(request, BodyHandlers.ofString());
        return Response.fromJSON(response.body());
    }

    private HttpRequest buildRequest(String methodName, String requestAsJSON)
            throws URISyntaxException
    {
        return newBuilder()
                .uri(buildURI(methodName))
                .POST(BodyPublishers.ofString(requestAsJSON))
                .build();
    }

    private URI buildURI(String methodName) throws URISyntaxException {
        return new URI(url + methodName);
    }
}
