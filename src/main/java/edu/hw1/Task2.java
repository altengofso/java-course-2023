package edu.hw1;

public final class Task2 {
    private Task2() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(int givenNumber) {
        if (givenNumber == 0) {
            return 1;
        }
        int number = givenNumber;
        int digits = 0;
        while (number % 10 != 0) {
            digits++;
            number /= 10;
        }
        return digits;
    }
}
