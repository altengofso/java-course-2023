package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class Task2 {
    private static final int THIRTEEN = 13;

    private Task2() {
    }

    public static List<LocalDate> findFridays13(int year) {
        List<LocalDate> fridays13 = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, THIRTEEN);
        while (date.getYear() == year) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays13.add(date);
            }
            date = date.plusMonths(1);
        }
        return fridays13;
    }

    public static LocalDate findNextFriday13(LocalDate date) {
        LocalDate nextFriday13 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        while (nextFriday13.getDayOfMonth() != THIRTEEN) {
            nextFriday13 = nextFriday13.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return nextFriday13;
    }
}
