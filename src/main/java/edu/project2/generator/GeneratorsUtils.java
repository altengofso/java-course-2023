package edu.project2.generator;

public final class GeneratorsUtils {
    public static Generator getGenerator(int generatorCode) {
        return switch (generatorCode) {
            case 1 -> new DFSMazeGenerator();
            case 2 -> new AldousBroderMazeGenerator();
            default -> null;
        };
    }

    public static String getGeneratorsList() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (var generator : Generators.values()) {
            result.append("%s. %s\n".formatted(counter++, generator.getDisplayName()));
        }
        return result.toString();
    }

    private GeneratorsUtils() {
    }
}
