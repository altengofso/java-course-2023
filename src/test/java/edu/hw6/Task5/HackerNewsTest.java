package edu.hw6.Task5;

import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class HackerNewsTest {
    private static final String BASE_URL = "https://hacker-news.firebaseio.com/v0/";
    private static final String TOP_STORIES = "topstories.json";

    @Test
    void testHackerNewsTopStories() {
        HackerHttpClient httpClient = new HackerHttpClient();
        HackerNews hackerNews = new HackerNews(httpClient);
        long[] ids = hackerNews.hackerNewsTopStories();
        assertThat(ids.length).isNotEqualTo(0);
    }

    static Stream<Arguments> News() {
        return Stream.of(
            Arguments.of(37570037, "JDK 21 Release Notes"),
            Arguments.of(-1, null)
        );
    }

    @ParameterizedTest
    @MethodSource("News")
    void testNews(long id, String title) {
        HackerHttpClient httpClient = new HackerHttpClient();
        HackerNews hackerNews = new HackerNews(httpClient);
        assertThat(hackerNews.news(id)).isEqualTo(title);
    }
}
