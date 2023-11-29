package edu.project2.generator;

import edu.project2.maze.Coordinate;
import edu.project2.solver.Solvers;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.project2.generator.GeneratorsUtils.getGenerator;
import static edu.project2.solver.SolversUtils.getSolver;
import static org.assertj.core.api.Assertions.assertThat;

public class GeneratorsTest {
    static Stream<Arguments> Generate() {
        List<Arguments> argumentsList = new ArrayList<>();
        for (var solver : Solvers.values()) {
            for (var generator : Generators.values()) {
                argumentsList.add(Arguments.of(solver, generator, 3, 3,
                    new Coordinate(1, 1),
                    new Coordinate(5, 5)
                ));
            }
        }
        return argumentsList.stream();
    }

    @ParameterizedTest
    @MethodSource("Generate")
    void testGenerate(Solvers solver, Generators generator, int height, int width, Coordinate start, Coordinate end) {
        var currentGenerator = getGenerator(generator.getCode());
        var currentSolver = getSolver(solver.getCode());
        var maze = currentGenerator.generate(height, width);
        var path = currentSolver.solve(maze, start, end);
        assertThat(path != null).isTrue();
    }
}
