package edu.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Task7Test {
    @ParameterizedTest
    @MethodSource("TestRotateRightProvider")
    @DisplayName("Cyclic shift of bits to the right")
    void testRotateRight(int number, int shift, int answer) {
        assertThat(Task7.rotateRight(number, shift)).isEqualTo(answer);
    }

    static Stream<Arguments> TestRotateRightProvider() {
        return Stream.of(
                Arguments.of(8, 1, 4),
                Arguments.of(8, 5, 4),
                Arguments.of(8, 9, 4),
                Arguments.of(8, 0, 8),
                Arguments.of(8, 4, 8),
                Arguments.of(8, 8, 8));
    }

    @ParameterizedTest
    @MethodSource("TestRotateLeftProvider")
    @DisplayName("Cyclic shift of bits to the left")
    void testRotateLeft(int number, int shift, int answer) {
        assertThat(Task7.rotateLeft(number, shift)).isEqualTo(answer);
    }

    static Stream<Arguments> TestRotateLeftProvider() {
        return Stream.of(
                Arguments.of(16, 1, 1),
                Arguments.of(17, 2, 6),
                Arguments.of(17, 7, 6),
                Arguments.of(17, 12, 6),
                Arguments.of(17, 0, 17),
                Arguments.of(17, 5, 17),
                Arguments.of(17, 10, 17));
    }
}
