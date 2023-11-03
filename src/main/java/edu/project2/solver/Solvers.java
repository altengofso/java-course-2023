package edu.project2.solver;

public enum Solvers {
    DFSMazeSolver("edu.project2.solver.DFSMazeSolver", "DFSMazeSolver"),
    BFSMazeSolver("edu.project2.solver.BFSMazeSolver", "BFSMazeSolver");

    private final String fullName;
    private final String displayName;

    Solvers(String fullName, String displayName) {
        this.fullName = fullName;
        this.displayName = displayName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
