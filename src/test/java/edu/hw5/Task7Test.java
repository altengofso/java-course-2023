package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw5.Task7.isAtLeastThreeCharsAndThirdZero;
import static edu.hw5.Task7.isFirstCharEqualsToLast;
import static edu.hw5.Task7.isNotLessOneNoMoreThree;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    static Stream<Arguments> IsAtLeastThreeCharsAndThirdZero() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", false),
            Arguments.of("00", false),
            Arguments.of("000", true),
            Arguments.of("0000", true),
            Arguments.of("1001", true),
            Arguments.of("001", false),
            Arguments.of("020", false),
            Arguments.of("0002", false)
        );
    }

    @ParameterizedTest
    @MethodSource("IsAtLeastThreeCharsAndThirdZero")
    void testIsAtLeastThreeCharsAndThirdZero(String string, boolean result) {
        assertThat(isAtLeastThreeCharsAndThirdZero(string)).isEqualTo(result);
    }

    static Stream<Arguments> IsFirstCharEqualsToLast() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", true),
            Arguments.of("1", true),
            Arguments.of("2", false),
            Arguments.of("00", true),
            Arguments.of("11", true),
            Arguments.of("010", true),
            Arguments.of("101", true),
            Arguments.of("020", false),
            Arguments.of("222", false)
        );
    }

    @ParameterizedTest
    @MethodSource("IsFirstCharEqualsToLast")
    void testIsFirstCharEqualsToLast(String string, boolean result) {
        assertThat(isFirstCharEqualsToLast(string)).isEqualTo(result);
    }

    static Stream<Arguments> IsNotLessOneNoMoreThree() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", true),
            Arguments.of("01", true),
            Arguments.of("010", true),
            Arguments.of("0101", false),
            Arguments.of("000", true),
            Arguments.of("2", false),
            Arguments.of("02", false),
            Arguments.of("022", false)
        );
    }

    @ParameterizedTest
    @MethodSource("IsNotLessOneNoMoreThree")
    void testIsNotLessOneNoMoreThree(String string, boolean result) {
        assertThat(isNotLessOneNoMoreThree(string)).isEqualTo(result);
    }
}
