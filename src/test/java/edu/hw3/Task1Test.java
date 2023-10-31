package edu.hw3;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task1Test {
    static Stream<Arguments> AtbashArguments() {
        return Stream.of(
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of("Svool dliow!", "Hello world!")
        );
    }

    @ParameterizedTest
    @MethodSource("AtbashArguments")
    @DisplayName("Test Atbash Encode Decode Algorithm")
    void testAtbashEncodeDecode(String input, String output) {
        assertThat(Task1.encryptViaAtbash(input)).isEqualTo(output);
    }
}
