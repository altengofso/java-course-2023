package edu.project2.solver;

public final class SolversUtils {
    public static Solver getSolver(int solverCode) {
        return switch (solverCode) {
            case 1 -> new DFSMazeSolver();
            case 2 -> new BFSMazeSolver();
            default -> null;
        };
    }

    public static String getSolversList() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (var solver : Solvers.values()) {
            result.append("%s. %s\n".formatted(counter++, solver.getDisplayName()));
        }
        return result.toString();
    }

    private SolversUtils() {
    }
}
