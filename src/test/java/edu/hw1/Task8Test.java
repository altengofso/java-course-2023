package edu.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task8Test {

    @ParameterizedTest
    @MethodSource("KnightBoardCapture")
    @DisplayName("No knight can capture another knight")
    void testKnightBoardCapture(int[][] board) {
        assertThat(Task8.knightBoardCapture(board)).isTrue();
    }

    static Stream<Arguments> KnightBoardCapture() {
        return Stream.of(
                Arguments.of(new Object[] { new int[][] { { 0, 0, 0, 1, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 1, 0, 0, 0, 1, 0, 0 },
                        { 0, 0, 0, 0, 1, 0, 1, 0 },
                        { 0, 1, 0, 0, 0, 1, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 1, 0, 0, 0, 0, 0, 1 },
                        { 0, 0, 0, 0, 1, 0, 0, 0 } } }));
    }

    @ParameterizedTest
    @MethodSource("KnightBoardCaptureFalse")
    @DisplayName("A knight can capture another knight")
    void testKnightBoardCaptureFalse(int[][] board) {
        assertThat(Task8.knightBoardCapture(board)).isFalse();
    }

    static Stream<Arguments> KnightBoardCaptureFalse() {
        return Stream.of(
                Arguments.of(new Object[] { new int[][] { { 1, 0, 1, 0, 1, 0, 1, 0 },
                        { 0, 1, 0, 1, 0, 1, 0, 1 },
                        { 0, 0, 0, 0, 1, 0, 1, 0 },
                        { 0, 0, 1, 0, 0, 1, 0, 1 },
                        { 1, 0, 0, 0, 1, 0, 1, 0 },
                        { 0, 0, 0, 0, 0, 1, 0, 1 },
                        { 1, 0, 0, 0, 1, 0, 1, 0 },
                        { 0, 0, 0, 1, 0, 1, 0, 1 } } }),
                Arguments.of(new Object[] { new int[][] { { 0, 0, 0, 0, 1, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 1, 0, 0 },
                        { 0, 0, 0, 1, 0, 0, 0, 0 },
                        { 1, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 1, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 1, 0, 0 },
                        { 0, 0, 0, 0, 0, 1, 0, 0 },
                        { 1, 0, 0, 0, 0, 0, 0, 0 } } }));
    }
}
