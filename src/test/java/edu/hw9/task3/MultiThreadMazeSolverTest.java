package edu.hw9.task3;

import edu.project2.generator.Generators;
import edu.project2.maze.Coordinate;
import edu.project2.solver.Solver;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.project2.generator.GeneratorsUtils.getGenerator;
import static org.assertj.core.api.Assertions.assertThat;

public class MultiThreadMazeSolverTest {
    static Stream<Arguments> Solve() {
        List<Arguments> argumentsList = new ArrayList<>();
        for (var generator : Generators.values()) {
            argumentsList.add(Arguments.of(generator, 3, 3,
                new Coordinate(1, 1),
                new Coordinate(5, 5)
            ));
        }
        return argumentsList.stream();
    }

    @ParameterizedTest
    @MethodSource("Solve")
    void testMultiThreadMazeSolver(Generators generator, int height, int width, Coordinate start, Coordinate end) {
        var currentGenerator = getGenerator(generator.getCode());
        var maze = currentGenerator.generate(height, width);
        Solver solver = new MultiThreadMazeSolver();
        var path = solver.solve(maze, start, end);
        assertThat(path != null).isTrue();
        assertThat(path.getFirst()).isEqualTo(start);
        assertThat(path.getLast()).isEqualTo(end);
    }
}
