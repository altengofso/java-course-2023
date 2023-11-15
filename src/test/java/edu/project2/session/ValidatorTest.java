package edu.project2.session;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {
    static Stream<Arguments> ValidMazeSize() {
        return Stream.of(
            Arguments.of("not valid", false),
            Arguments.of("valid", false),
            Arguments.of("1", false),
            Arguments.of("-1 3", false),
            Arguments.of("1 3", true)
        );
    }

    @ParameterizedTest
    @MethodSource("ValidMazeSize")
    void testIsValidMazeSize(String input, boolean result) {
        var validator = new Validator();
        assertThat(validator.isValidMazeSizeResponse(input)).isEqualTo(result);
    }

    static Stream<Arguments> ValidGenerator() {
        return Stream.of(
            Arguments.of("not valid", false),
            Arguments.of("valid", false),
            Arguments.of("-1", false),
            Arguments.of("0", false),
            Arguments.of("1", true)
        );
    }

    @ParameterizedTest
    @MethodSource("ValidGenerator")
    void testIsValidGenerator(String input, boolean result) {
        var validator = new Validator();
        assertThat(validator.isValidGeneratorResponse(input)).isEqualTo(result);
    }

    static Stream<Arguments> ValidSolver() {
        return Stream.of(
            Arguments.of("not valid", false),
            Arguments.of("valid", false),
            Arguments.of("-1", false),
            Arguments.of("0", false),
            Arguments.of("1", true)
        );
    }

    @ParameterizedTest
    @MethodSource("ValidSolver")
    void testIsValidSolver(String input, boolean result) {
        var validator = new Validator();
        assertThat(validator.isValidSolverResponse(input)).isEqualTo(result);
    }

    static Stream<Arguments> ValidAgain() {
        return Stream.of(
            Arguments.of("yes", false),
            Arguments.of("no", false),
            Arguments.of("1", false),
            Arguments.of("0", false),
            Arguments.of("y", true),
            Arguments.of("n", true)
        );
    }

    @ParameterizedTest
    @MethodSource("ValidAgain")
    void testIsValidAgain(String input, boolean result) {
        var validator = new Validator();
        assertThat(validator.isValidAgainResponse(input)).isEqualTo(result);
    }
}
