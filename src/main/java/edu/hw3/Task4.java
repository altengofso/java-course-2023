package edu.hw3;

public final class Task4 {
    private static final int[] ARABIC_VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMAN_SYMBOLS =
        {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    private static final int ALLOWED_NUMBER_LOWER_LIMIT = 1;
    private static final int ALLOWED_NUMBER_UPPER_LIMIT = 3999;

    private Task4() {
    }

    public static String convertToRoman(int number) {
        if (number < ALLOWED_NUMBER_LOWER_LIMIT || number > ALLOWED_NUMBER_UPPER_LIMIT) {
            throw new IllegalArgumentException("Number must be between %d and %d".formatted(
                ALLOWED_NUMBER_LOWER_LIMIT,
                ALLOWED_NUMBER_UPPER_LIMIT
            ));
        }
        StringBuilder result = new StringBuilder();
        int givenNumber = number;
        for (int i = 0; i < ARABIC_VALUES.length; ++i) {
            while (givenNumber >= ARABIC_VALUES[i]) {
                result.append(ROMAN_SYMBOLS[i]);
                givenNumber -= ARABIC_VALUES[i];
            }
        }
        return result.toString();
    }
}
