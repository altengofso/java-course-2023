package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class Task1 {
    private static final String DATETIME_PATTERN = "yyyy-MM-dd, HH:mm";
    private static final String VALIDATION_PATTERN =
        "^\\d{4}-\\d{2}-\\d{2},\\s\\d{2}:\\d{2}\\s-\\s\\d{4}-\\d{2}-\\d{2},\\s\\d{2}:\\d{2}$";

    private Task1() {
    }

    public static String averageSessionTime(List<String> sessions) {
        Duration averageDuration = calculateAverageDuration(sessions);
        var hours = averageDuration.toHours();
        var minutes = averageDuration.toMinutesPart();
        return hours + "ч " + minutes + "м";
    }

    private static Duration calculateAverageDuration(List<String> sessions) {
        long totalSeconds = 0;
        for (var session : sessions) {
            if (!session.matches(VALIDATION_PATTERN)) {
                throw new IllegalArgumentException("Session should be like '%s - %s'".formatted(
                    DATETIME_PATTERN,
                    DATETIME_PATTERN
                ));
            }
            String[] parts = session.split(" - ");
            LocalDateTime start = LocalDateTime.parse(parts[0], DateTimeFormatter.ofPattern(DATETIME_PATTERN));
            LocalDateTime end = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern(DATETIME_PATTERN));
            Duration duration = Duration.between(start, end);
            totalSeconds += duration.toSeconds();
        }
        long averageSeconds = totalSeconds / sessions.size();
        return Duration.ofSeconds(averageSeconds);
    }
}
