package edu.hw1;

public final class Task1 {
    private Task1() {
    }

    @SuppressWarnings("MagicNumber")
    public static int minutesToSeconds(String inputString) {
        String[] result = inputString.split(":");
        int minutes;
        int seconds;
        try {
            minutes = Integer.parseInt(result[0]);
            seconds = Integer.parseInt(result[1]);
            if (seconds < 0 || seconds > 59) {
                throw new IllegalArgumentException("Seconds should be in the range from 0 to 59");
            }
            if (minutes < 0) {
                throw new IllegalArgumentException("Minutes must be a non-negative number");
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            return -1;
        }
        return minutes * 60 + seconds;
    }
}
