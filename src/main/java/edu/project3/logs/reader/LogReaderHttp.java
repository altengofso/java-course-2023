package edu.project3.logs.reader;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class LogReaderHttp implements LogReader {
    @Override
    public List<String> readLogFile(String path) {
        return readFromHttp(path);
    }

    private List<String> readFromHttp(String path) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(path))
                .GET()
                .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return Arrays.stream(response.body().split("\n")).toList();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
