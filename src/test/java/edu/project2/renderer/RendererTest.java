package edu.project2.renderer;

import edu.project2.generator.Generators;
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

public class RendererTest {
    static Stream<Arguments> Render() {
        List<Arguments> argumentsList = new ArrayList<>();
        for (var solver : Solvers.values()) {
            for (var generator : Generators.values()) {
                argumentsList.add(Arguments.of(solver, generator, 1, 2,
                    new Coordinate(1, 1),
                    new Coordinate(1, 3),
                    """
                        ⬛⬛⬛⬛⬛
                        ⬛⬜⬜⬜⬛
                        ⬛⬛⬛⬛⬛
                        """,
                    """
                        ⬛⬛⬛⬛⬛
                        ⬛🔺🟢🔻⬛
                        ⬛⬛⬛⬛⬛
                        """
                ));
                argumentsList.add(Arguments.of(solver, generator, 2, 1,
                    new Coordinate(1, 1),
                    new Coordinate(3, 1),
                    """
                        ⬛⬛⬛
                        ⬛⬜⬛
                        ⬛⬜⬛
                        ⬛⬜⬛
                        ⬛⬛⬛
                        """,
                    """
                        ⬛⬛⬛
                        ⬛🔺⬛
                        ⬛🟢⬛
                        ⬛🔻⬛
                        ⬛⬛⬛
                        """
                ));
            }
        }
        return argumentsList.stream();
    }

    @ParameterizedTest
    @MethodSource("Render")
    void testRender(
        Solvers solver,
        Generators generator,
        int height,
        int width,
        Coordinate start,
        Coordinate end,
        String generated,
        String solved
    ) {
        var renderer = new MazeRenderer();
        var currentGenerator = getGenerator(generator.getCode());
        var currentSolver = getSolver(solver.getCode());
        var maze = currentGenerator.generate(height, width);
        var path = currentSolver.solve(maze, start, end);
        var currentGenerated = renderer.render(maze);
        assertThat(currentGenerated).isEqualTo(generated);
        var currentSolved = renderer.render(maze, path);
        assertThat(currentSolved).isEqualTo(solved);
    }
}
