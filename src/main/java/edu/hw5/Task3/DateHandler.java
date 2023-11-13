package edu.hw5.Task3;

import java.time.LocalDate;

public abstract class DateHandler {
    protected String matchPattern;

    public boolean canHandle(String date) {
        return date.matches(matchPattern);
    }

    public abstract LocalDate handle(String date);
}
