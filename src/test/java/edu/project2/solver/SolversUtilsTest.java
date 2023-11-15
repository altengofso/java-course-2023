package edu.project2.solver;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.project2.solver.SolversUtils.getSolver;
import static edu.project2.solver.SolversUtils.getSolversList;
import static org.assertj.core.api.Assertions.assertThat;

public class SolversUtilsTest {

    static Stream<Arguments> GetSolver() {
        return Stream.of(
            Arguments.of(DFSMazeSolver.class, 1),
            Arguments.of(BFSMazeSolver.class, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("GetSolver")
    void testGetSolver(Object cls, int solverCode) {
        assertThat(getSolver(solverCode).getClass()).isEqualTo(cls);
    }

    @Test
    void testGetSolversList() {
        var solversList = getSolversList().trim().split("\n");
        assertThat(solversList.length).isEqualTo(Solvers.values().length);
        for (int i = 0; i < solversList.length; ++i) {
            assertThat(solversList[i].endsWith(Arrays.stream(Solvers.values()).toList().get(i)
                .getDisplayName())).isTrue();
        }
    }
}
