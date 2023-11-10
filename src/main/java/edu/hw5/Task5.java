package edu.hw5;

public final class Task5 {
    private static final String MATCH_PATTERN = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$";

    private Task5() {
    }

    public static boolean isCarNumberValid(String carNumber) {
        return carNumber.matches(MATCH_PATTERN);
    }
}
