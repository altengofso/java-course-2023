package edu.hw7.Task1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class AtomicIncrementorTest {
    static Stream<Arguments> AtomicIncrementorArguments() {
        return Stream.of(
            Arguments.of(1, 10000, 10000),
            Arguments.of(2, 10000, 20000),
            Arguments.of(4, 10000, 40000)
        );
    }

    @ParameterizedTest
    @MethodSource("AtomicIncrementorArguments")
    void testAtomicIncrementor(int threadsNumber, int incrementCount, int expected) {
        assertThat(AtomicIncrementor.run(threadsNumber, incrementCount)).isEqualTo(expected);
    }
}
