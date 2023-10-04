package edu.hw1;

public class Task2 {
    private Task2() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(int givenNumber) {
        int number = givenNumber;
        int digits = 0;
        if (number == 0) {
            digits = 1;
        }
        while (number % 10 != 0) {
            digits++;
            number /= 10;
        }
        return digits;
    }
}
