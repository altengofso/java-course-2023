package edu.hw1;

public class Task2 {
    private Task2() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(int number) {
        if (number == 0) {
            return 1;
        }
        int digits = 0;
        while (number % 10 != 0) {
            digits++;
            number /= 10;
        }
        return digits;
    }
}
