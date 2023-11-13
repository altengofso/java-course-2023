package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class DashDateHandler extends DateHandler {
    public DashDateHandler() {
        this.matchPattern = "^(\\d{1,4})-(\\d{1,2})-(\\d{1,2})$";
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public LocalDate handle(String date) {
        var pattern = Pattern.compile(matchPattern);
        var matcher = pattern.matcher(date);
        if (matcher.find()) {
            return LocalDate.of(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3))
            );
        }
        return null;
    }
}
