package edu.project2.solver;

public enum Solvers {
    DFSMazeSolver(1, DFSMazeSolver.class.getSimpleName()),
    BFSMazeSolver(2, BFSMazeSolver.class.getSimpleName());

    private final int code;
    private final String displayName;

    Solvers(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
