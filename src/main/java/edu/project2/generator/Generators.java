package edu.project2.generator;

public enum Generators {
    DFSMazeGenerator("edu.project2.generator.DFSMazeGenerator", "DFSMazeGenerator"),
    AldousBroderMazeGenerator("edu.project2.generator.AldousBroderMazeGenerator", "AldousBroderMazeGenerator");

    private final String fullName;
    private final String displayName;

    Generators(String fullName, String displayName) {
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
