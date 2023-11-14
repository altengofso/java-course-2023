package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw5.Task8.isEveryOddEqualsOne;
import static edu.hw5.Task8.isOddLength;
import static edu.hw5.Task8.isZeroOddLengthOrOneEvenLength;
import static edu.hw5.Task8.isZerosMultipleOfThree;
import static edu.hw5.Task8.isZerosTwoAndMoreOnesNotMoreOne;
import static edu.hw5.Task8.isNoConsecutiveOnes;
import static edu.hw5.Task8.isNotEquals11Or111;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    static Stream<Arguments> IsOddLength() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("1", true),
            Arguments.of("0", true),
            Arguments.of("11", false),
            Arguments.of("0101", false),
            Arguments.of("101", true),
            Arguments.of("10101", true),
            Arguments.of("222", false)
        );
    }

    @ParameterizedTest
    @MethodSource("IsOddLength")
    void testIsOddLength(String string, boolean result) {
        assertThat(isOddLength(string)).isEqualTo(result);
    }

    static Stream<Arguments> IsZeroOddLengthOrOneEvenLength() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("1", false),
            Arguments.of("10", true),
            Arguments.of("101", false),
            Arguments.of("1010", true),
            Arguments.of("0", true),
            Arguments.of("01", false),
            Arguments.of("010", true),
            Arguments.of("0101", false),
            Arguments.of("022", false)
        );
    }

    @ParameterizedTest
    @MethodSource("IsZeroOddLengthOrOneEvenLength")
    void testIsZeroOddLengthOrOneEvenLength(String string, boolean result) {
        assertThat(isZeroOddLengthOrOneEvenLength(string)).isEqualTo(result);
    }

    static Stream<Arguments> IsZerosMultipleOfThree() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", false),
            Arguments.of("010", false),
            Arguments.of("01010", true),
            Arguments.of("10001", true),
            Arguments.of("1010101", true),
            Arguments.of("1001001", false),
            Arguments.of("000", true),
            Arguments.of("0001", true),
            Arguments.of("0002", false)
        );
    }

    @ParameterizedTest
    @MethodSource("IsZerosMultipleOfThree")
    void testIsZerosMultipleOfThree(String string, boolean result) {
        assertThat(isZerosMultipleOfThree(string)).isEqualTo(result);
    }

    static Stream<Arguments> IsNotEquals11Or111() {
        return Stream.of(
            Arguments.of("", true),
            Arguments.of("1", true),
            Arguments.of("0", true),
            Arguments.of("10", true),
            Arguments.of("11", false),
            Arguments.of("01", true),
            Arguments.of("111", false),
            Arguments.of("010", true),
            Arguments.of("1111", true),
            Arguments.of("1112", false)
        );
    }

    @ParameterizedTest
    @MethodSource("IsNotEquals11Or111")
    void testIsNotEquals11Or111(String string, boolean result) {
        assertThat(isNotEquals11Or111(string)).isEqualTo(result);
    }

    static Stream<Arguments> IsEveryOddEqualsOne() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("1", true),
            Arguments.of("1111", true),
            Arguments.of("11111", true),
            Arguments.of("1010", true),
            Arguments.of("10101", true),
            Arguments.of("11011", false),
            Arguments.of("121", false)
        );
    }

    @ParameterizedTest
    @MethodSource("IsEveryOddEqualsOne")
    void testIsEveryOddEqualsOne(String string, boolean result) {
        assertThat(isEveryOddEqualsOne(string)).isEqualTo(result);
    }

    static Stream<Arguments> IsZerosTwoAndMoreOnesNotMoreOne() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("001", true),
            Arguments.of("100", true),
            Arguments.of("010", true),
            Arguments.of("00010", true),
            Arguments.of("01000", true),
            Arguments.of("00100", true),
            Arguments.of("00", true),
            Arguments.of("000", true),
            Arguments.of("01", false),
            Arguments.of("011", false),
            Arguments.of("001001", false),
            Arguments.of("001002", false)
        );
    }

    @ParameterizedTest
    @MethodSource("IsZerosTwoAndMoreOnesNotMoreOne")
    void testIsZerosTwoAndMoreOnesNotMoreOne(String string, boolean result) {
        assertThat(isZerosTwoAndMoreOnesNotMoreOne(string)).isEqualTo(result);
    }

    static Stream<Arguments> IsNoConsecutiveOnes() {
        return Stream.of(
            Arguments.of("", true),
            Arguments.of("1", true),
            Arguments.of("0", true),
            Arguments.of("10", true),
            Arguments.of("01", true),
            Arguments.of("11", false),
            Arguments.of("011", false),
            Arguments.of("0101", true),
            Arguments.of("101011", false),
            Arguments.of("01012", false)
        );
    }

    @ParameterizedTest
    @MethodSource("IsNoConsecutiveOnes")
    void testIsNoConsecutiveOnes(String string, boolean result) {
        assertThat(isNoConsecutiveOnes(string)).isEqualTo(result);
    }
}
