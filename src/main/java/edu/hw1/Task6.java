package edu.hw1;

import java.util.Arrays;

public final class Task6 {
    private Task6() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countK(int number) {
        return functionK(number, 0);

    }

    @SuppressWarnings("MagicNumber")
    public static int functionK(int number, int steps) {
        if (number == 6174) {
            return steps;
        }
        char[] chars = Integer.toString(number).toCharArray();
        Arrays.sort(chars);
        int smaller = Integer.parseInt(String.valueOf(chars));
        int greater = Integer.parseInt((new StringBuilder(String.valueOf(chars))).reverse().toString());
        return functionK(greater - smaller, steps + 1);
    }
}
