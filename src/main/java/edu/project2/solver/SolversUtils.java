package edu.project2.solver;

import java.lang.reflect.InvocationTargetException;

public final class SolversUtils {
    public static Solver getSolver(Solvers solver)
        throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
        IllegalAccessException {
        Class<?> cls = Class.forName(solver.getFullName());
        return (Solver) cls.getDeclaredConstructor().newInstance();
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
