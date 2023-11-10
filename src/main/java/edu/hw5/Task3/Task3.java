package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public final class Task3 {
    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String date) {
        List<DateHandler> handlers =
            List.of(
                new DashDateHandler(),
                new SlashDateHandler(),
                new RelativeDateHandler(),
                new DayAgoDateHandler()
            );
        return handlers
            .stream()
            .filter(dateHandler -> dateHandler.canHandle(date))
            .map(dateHandler -> dateHandler.handle(date))
            .findFirst();
    }
}
