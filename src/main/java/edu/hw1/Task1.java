package edu.hw1;

public final class Task1 {
    private static final int SECONDS_LOWER_LIMIT = 0;
    private static final int SECONDS_UPPER_LIMIT = 59;
    private static final int SECONDS_IN_MINUTE = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String inputString) {
        if (!inputString.matches("\\d+:\\d+")) {
            return -1;
        }
        String[] result = inputString.split(":");
        if (result.length != 2) {
            return -1;
        }
        int minutes = Integer.parseInt(result[0]);
        int seconds = Integer.parseInt(result[1]);
        if (seconds < SECONDS_LOWER_LIMIT || seconds > SECONDS_UPPER_LIMIT) {
            return -1;
        }
        return minutes * SECONDS_IN_MINUTE + seconds;
    }
}
