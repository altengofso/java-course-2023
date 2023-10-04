package edu.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task5Test {
    @ParameterizedTest(name = "{0} is palindrom")
    @ValueSource(ints = { 11211230, 13001120, 23336014, 11 })
    @DisplayName("Is palindrome")
    void testIsPalindromeDescendant(int number) {
        assertThat(Task5.isPalindromeDescendant(number)).isTrue();
    }
}
