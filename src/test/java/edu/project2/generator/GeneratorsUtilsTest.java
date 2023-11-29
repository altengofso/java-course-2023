package edu.project2.generator;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.project2.generator.GeneratorsUtils.getGenerator;
import static edu.project2.generator.GeneratorsUtils.getGeneratorsList;
import static org.assertj.core.api.Assertions.assertThat;

public class GeneratorsUtilsTest {

    static Stream<Arguments> GetGenerator() {
        return Stream.of(
            Arguments.of(DFSMazeGenerator.class, 1),
            Arguments.of(AldousBroderMazeGenerator.class, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("GetGenerator")
    void testGetGenerator(Object cls, int generatorCode) {
        assertThat(getGenerator(generatorCode).getClass()).isEqualTo(cls);
    }

    @Test
    void testGetGeneratorsList() {
        var generatorsList = getGeneratorsList().trim().split("\n");
        assertThat(generatorsList.length).isEqualTo(Generators.values().length);
        for (int i = 0; i < generatorsList.length; ++i) {
            assertThat(generatorsList[i].endsWith(Arrays.stream(Generators.values()).toList().get(i)
                .getDisplayName())).isTrue();
        }
    }
}
