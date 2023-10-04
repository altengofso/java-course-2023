package edu.hw1;

public class Task1 {

    public static int minutesToSeconds(String inputString) {
        String[] result = inputString.split(":");
        try {
            int minutes = Integer.parseInt(result[0]);
            int seconds = Integer.parseInt(result[1]);
            if (seconds < 0 || seconds > 59 || minutes < 0) {
                return -1;
            } else {
                return minutes * 60 + seconds;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
