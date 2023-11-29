package edu.project2.generator;

public enum Generators {
    DFSMazeGenerator(1, DFSMazeGenerator.class.getSimpleName()),
    AldousBroderMazeGenerator(2, AldousBroderMazeGenerator.class.getSimpleName());

    private final int code;
    private final String displayName;

    Generators(int code, String displayName) {
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
