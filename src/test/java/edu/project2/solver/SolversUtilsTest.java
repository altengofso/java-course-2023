package edu.project2.solver;

import java.lang.reflect.InvocationTargetException;
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
            Arguments.of(Solvers.DFSMazeSolver, "DFSMazeSolver"),
            Arguments.of(Solvers.BFSMazeSolver, "BFSMazeSolver")
        );
    }

    @ParameterizedTest
    @MethodSource("GetSolver")
    void testGetSolver(Solvers solver, String name)
        throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException,
        IllegalAccessException {
        assertThat(getSolver(solver).getClass().getSimpleName()).isEqualTo(name);
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
