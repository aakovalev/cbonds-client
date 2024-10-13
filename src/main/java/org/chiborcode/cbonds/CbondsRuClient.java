package org.chiborcode.cbonds;

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

    public CbondsRuClient(HttpClient client, String url) {
        this.url = url;
        this.httpClient = client;
    }

    public ClientResponse execute(CbondsMethod method, ClientRequest clientRequest)
            throws URISyntaxException, IOException, InterruptedException
    {
        HttpRequest request = buildRequest(
                method.name(), clientRequest.toJSONString());
        HttpResponse<String> response =
                httpClient.send(request, BodyHandlers.ofString());
        return ClientResponse.fromJSON(response.body());
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
