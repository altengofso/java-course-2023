package edu.project2.generator;

import java.lang.reflect.InvocationTargetException;

public final class GeneratorsUtils {
    public static Generator getGenerator(Generators generator)
        throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
        IllegalAccessException {
        Class<?> cls = Class.forName(generator.getFullName());
        return (Generator) cls.getDeclaredConstructor().newInstance();
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
