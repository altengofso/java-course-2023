package edu.hw10.task2.test_classes;

public class FibonacciCacheImpl implements FibonacciCache {
    @Override
    public long getFibonacci(int number) {
        if (number == 1 || number == 0) {
            return number;
        }
        return getFibonacci(number - 1) + getFibonacci(number - 2);
    }
}
