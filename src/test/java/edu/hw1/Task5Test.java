package edu.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Task5Test {
    @ParameterizedTest(name = "{0} is palindrom")
    @ValueSource(ints = { 11211230, 13001120, 23336014, 11, -11 })
    @DisplayName("Is palindrome")
    void testIsPalindromeDescendant(int number) {
        assertThat(Task5.isPalindromeDescendant(number)).isTrue();
    }

    @ParameterizedTest(name = "{0} is not palindrom")
    @ValueSource(ints = { 19, 124, 38476 })
    @DisplayName("Is not palindrome")
    void testIsNotPalindromeDescendant(int number) {
        assertThat(Task5.isPalindromeDescendant(number)).isFalse();
    }
}
