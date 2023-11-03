package edu.project2.generator;

import java.lang.reflect.InvocationTargetException;
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
            Arguments.of(Generators.DFSMazeGenerator, "DFSMazeGenerator"),
            Arguments.of(Generators.AldousBroderMazeGenerator, "AldousBroderMazeGenerator")
        );
    }

    @ParameterizedTest
    @MethodSource("GetGenerator")
    void testGetGenerator(Generators generator, String name)
        throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException,
        IllegalAccessException {
        assertThat(getGenerator(generator).getClass().getSimpleName()).isEqualTo(name);
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
