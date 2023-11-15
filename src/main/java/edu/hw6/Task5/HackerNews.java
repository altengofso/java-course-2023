package edu.hw6.Task5;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private static final String BASE_URL = "https://hacker-news.firebaseio.com/v0/";
    private static final String TOP_STORIES = "topstories.json";
    private static final String NEWS_ITEM = "item/%s.json";
    private static final String NEWS_ITEM_PATTERN = "\"title\":\"(.*?)\"";
    private final HackerHttpClient httpClient;

    public HackerNews(HackerHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public long[] hackerNewsTopStories() {
        try {
            String response = httpClient.getResponse(BASE_URL + TOP_STORIES);
            return Arrays
                .stream(response
                    .substring(1, response.length() - 1)
                    .split(","))
                .mapToLong(Long::parseLong)
                .toArray();
        } catch (IOException | InterruptedException e) {
            return new long[] {};
        }
    }

    public String news(long id) throws IOException, InterruptedException {
        String response = httpClient.getResponse(BASE_URL + NEWS_ITEM.formatted(id));
        Pattern pattern = Pattern.compile(NEWS_ITEM_PATTERN);
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
