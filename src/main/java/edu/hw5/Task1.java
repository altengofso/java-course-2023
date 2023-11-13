package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public final class Task1 {
    private static final String DATETIME_PATTERN = "yyyy-MM-dd, HH:mm";
    private static final String MATCH_PATTERN =
        "^(\\d{4}-\\d{2}-\\d{2},\\s\\d{2}:\\d{2})\\s-\\s(\\d{4}-\\d{2}-\\d{2},\\s\\d{2}:\\d{2})$";

    private Task1() {
    }

    public static String calculateAverageSessionTime(List<String> sessions) {
        Duration averageDuration = calculateAverageDuration(sessions);
        var hours = averageDuration.toHours();
        var minutes = averageDuration.toMinutesPart();
        return hours + "ч " + minutes + "м";
    }

    private static Duration calculateAverageDuration(List<String> sessions) {
        long totalSeconds = 0;
        for (var session : sessions) {
            var pattern = Pattern.compile(MATCH_PATTERN);
            var matcher = pattern.matcher(session);
            if (!matcher.find()) {
                throw new IllegalArgumentException("Session should be like '%s - %s'".formatted(
                    DATETIME_PATTERN,
                    DATETIME_PATTERN
                ));
            }
            LocalDateTime start = LocalDateTime.parse(matcher.group(1), DateTimeFormatter.ofPattern(DATETIME_PATTERN));
            LocalDateTime end = LocalDateTime.parse(matcher.group(2), DateTimeFormatter.ofPattern(DATETIME_PATTERN));
            Duration duration = Duration.between(start, end);
            totalSeconds += duration.toSeconds();
        }
        long averageSeconds = totalSeconds / sessions.size();
        return Duration.ofSeconds(averageSeconds);
    }
}
