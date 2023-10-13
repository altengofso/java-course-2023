package edu.hw1;

import java.util.Arrays;

public final class Task6 {
    private static final int KARPERCAR_NUMBER = 6174;
    private static final int ALLOWED_NUMBER_LOWER_LIMIT = 1001;
    private static final int ALLOWED_NUMBER_UPPER_LIMIT = 9999;

    private Task6() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countK(int number) {
        if (number < ALLOWED_NUMBER_LOWER_LIMIT || number > ALLOWED_NUMBER_UPPER_LIMIT) {
            throw new IllegalArgumentException("Number should be in range from %d to %d"
                    .formatted(ALLOWED_NUMBER_LOWER_LIMIT, ALLOWED_NUMBER_UPPER_LIMIT));
        }
        return functionK(number, 0);
    }

    private static int functionK(int number, int steps) {
        if (number == KARPERCAR_NUMBER) {
            return steps;
        }
        char[] chars = Integer.toString(number).toCharArray();
        Arrays.sort(chars);
        int smaller = Integer.parseInt(String.valueOf(chars));
        int greater = Integer.parseInt((new StringBuilder(String.valueOf(chars))).reverse().toString());
        return functionK(greater - smaller, steps + 1);
    }
}
