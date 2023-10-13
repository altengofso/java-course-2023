package edu.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Task3Test {
    @ParameterizedTest
    @MethodSource("IsNestableArgumentSource")
    @DisplayName("Array is nestable in other array")
    void testIsNestable(int[] first, int[] second) {
        assertThat(Task3.isNestable(first, second)).isTrue();
    }

    static Stream<Arguments> IsNestableArgumentSource() {
        return Stream.of(
                Arguments.of(new Object[] { new int[] { 1, 2, 3, 4 }, new int[] { 0, 6 } }),
                Arguments.of(new Object[] { new int[] { 3, 1 }, new int[] { 4, 0 } }),
                Arguments.of(new Object[] { new int[] {}, new int[] {} }),
                Arguments.of(new Object[] { new int[] {}, new int[] { 1 } }),
                Arguments.of(new Object[] { new int[] {}, new int[] { 1, 2 } }),
                Arguments.of(new Object[] { new int[] { 2 }, new int[] { 1, 3 } }));
    }

    @ParameterizedTest
    @MethodSource("IsNotNestableArgumentSource")
    @DisplayName("Array is not nestable in other array")
    void testIsNotNestable(int[] first, int[] second) {
        assertThat(Task3.isNestable(first, second)).isFalse();
    }

    static Stream<Arguments> IsNotNestableArgumentSource() {
        return Stream.of(
                Arguments.of(new Object[] { new int[] { 9, 9, 8 }, new int[] { 8, 9 } }),
                Arguments.of(new Object[] { new int[] { 1, 2, 3, 4 }, new int[] { 2, 3 } }),
                Arguments.of(new Object[] { new int[] { 1 }, new int[] { 2 } }),
                Arguments.of(new Object[] { new int[] { 1 }, new int[] {} }));
    }
}
