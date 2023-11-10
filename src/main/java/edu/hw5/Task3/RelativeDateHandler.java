package edu.hw5.Task3;

import java.time.LocalDate;

public class RelativeDateHandler extends DateHandler {
    public RelativeDateHandler() {
        this.matchPattern = "^tomorrow|today|yesterday$";
    }

    @Override
    public LocalDate handle(String date) {
        return switch (date) {
            case "tomorrow" -> LocalDate.now().plusDays(1);
            case "today" -> LocalDate.now();
            case "yesterday" -> LocalDate.now().minusDays(1);
            default -> throw new IllegalStateException("Unexpected value: " + date);
        };
    }
}
