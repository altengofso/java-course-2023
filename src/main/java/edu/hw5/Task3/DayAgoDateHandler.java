package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class DayAgoDateHandler extends DateHandler {
    public DayAgoDateHandler() {
        this.matchPattern = "^(\\d+)\\sdays?\\sago$";
    }

    @Override
    public LocalDate handle(String date) {
        var pattern = Pattern.compile(matchPattern);
        var matcher = pattern.matcher(date);
        if (matcher.find()) {
            return LocalDate.now().minusDays(Integer.parseInt(matcher.group(1)));
        }
        return null;
    }
}
