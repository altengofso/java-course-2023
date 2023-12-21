package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ValueGeneratorTest {
    static Stream<Arguments> ValueGeneratorArguments() {
        return Stream.of(
            Arguments.of(new ValueGenerator.BooleanGenerator(), Boolean.class),
            Arguments.of(new ValueGenerator.ShortGenerator(), Short.class),
            Arguments.of(new ValueGenerator.IntGenerator(), Integer.class),
            Arguments.of(new ValueGenerator.LongGenerator(), Long.class),
            Arguments.of(new ValueGenerator.FLoatGenerator(), Float.class),
            Arguments.of(new ValueGenerator.DoubleGenerator(), Double.class),
            Arguments.of(new ValueGenerator.StringGenerator(), String.class)
        );
    }

    @ParameterizedTest
    @MethodSource("ValueGeneratorArguments")
    void testValueGenerator(ValueGenerator valueGenerator, Class<?> clazz) {
        assertThat(valueGenerator.generate(new Annotation[] {}).getClass()).isEqualTo(clazz);
    }
}
