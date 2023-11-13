package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class SlashDateHandler extends DateHandler {
    public SlashDateHandler() {
        this.matchPattern = "^(\\d{1,2})\\/(\\d{1,2})\\/(\\d{1,4})$";
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public LocalDate handle(String date) {
        var pattern = Pattern.compile(matchPattern);
        var matcher = pattern.matcher(date);
        if (matcher.find()) {
            return LocalDate.of(
                Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(1))
            );
        }
        return null;
    }
}
