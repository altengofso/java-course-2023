package edu.hw10.task2.test_classes;

import edu.hw10.task2.Cache;

public interface FibonacciCache {
    @Cache
    long getFibonacci(int number);
}
