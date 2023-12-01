package edu.project3.logs.reader;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import lombok.SneakyThrows;

public class LogReaderHttp implements LogReader {
    @Override
    public List<String> readLogFile(String path) {
        return readFromHttp(path);
    }

    @SneakyThrows
    private List<String> readFromHttp(String path) {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(path))
                .GET()
                .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return Arrays.stream(response.body().split("\n")).toList();
        }
    }
}
