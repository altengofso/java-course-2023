package edu.hw1;

public final class Task1 {
    private static final int[] SECONDS_RANGE = {
            0, 59
    };
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
        if (seconds < SECONDS_RANGE[0] || seconds > SECONDS_RANGE[1]) {
            return -1;
        }
        return minutes * SECONDS_IN_MINUTE + seconds;
    }
}
