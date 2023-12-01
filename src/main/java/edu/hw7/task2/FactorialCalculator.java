package edu.hw7.task2;

import java.util.stream.LongStream;

public final class FactorialCalculator {
    private FactorialCalculator() {
    }

    public static long calculateFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non negative.");
        }
        return LongStream.rangeClosed(1, number)
            .parallel()
            .reduce(1, (a, b) -> a * b);
    }
}
