package edu.hw6.Task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.SneakyThrows;

public class HackerHttpClient {
    private final HttpClient httpClient;

    public HackerHttpClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    @SneakyThrows
    public String get(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .GET()
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
