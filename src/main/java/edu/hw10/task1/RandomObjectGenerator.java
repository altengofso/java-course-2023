package edu.hw10.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import lombok.SneakyThrows;

public class RandomObjectGenerator {
    private static final Map<Class<?>, ValueGenerator> GENERATORS = Map.of(
        short.class, new ValueGenerator.ShortGenerator(),
        int.class, new ValueGenerator.IntGenerator(),
        long.class, new ValueGenerator.LongGenerator(),
        float.class, new ValueGenerator.FLoatGenerator(),
        double.class, new ValueGenerator.DoubleGenerator(),
        boolean.class, new ValueGenerator.BooleanGenerator(),
        String.class, new ValueGenerator.StringGenerator()
    );

    @SneakyThrows
    public <T> T nextObject(Class<T> clazz) {
        var constructor = getConstructor(clazz);
        var arguments = generateArguments(constructor.getParameters());
        return clazz.cast(constructor.newInstance(arguments));
    }

    @SneakyThrows
    public <T> T nextObject(Class<T> clazz, String factoryMethodName) {
        var factoryMethod = getFactoryMethod(clazz, factoryMethodName);
        Object[] arguments = generateArguments(factoryMethod.getParameters());
        return clazz.cast(factoryMethod.invoke(null, arguments));
    }

    private Constructor<?> getConstructor(Class<?> clazz) {
        var constructors = clazz.getDeclaredConstructors();
        if (constructors.length == 0) {
            throw new IllegalArgumentException("Class %s has no constructors.".formatted(clazz.getName()));
        }
        var result = constructors[0];
        for (var constructor : constructors) {
            if (constructor.getParameterCount() > result.getParameterCount()) {
                result = constructor;
            }
        }
        return result;
    }

    private <T> Method getFactoryMethod(Class<T> clazz, String factoryMethodName) {
        var methods = clazz.getDeclaredMethods();
        for (var method : methods) {
            if (method.getName().equals(factoryMethodName)) {
                return method;
            }
        }
        throw new IllegalArgumentException("Method %s not found.".formatted(factoryMethodName));
    }

    private Object[] generateArguments(Parameter[] parameters) {
        Object[] arguments = new Object[parameters.length];
        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = GENERATORS.get(parameters[i].getType()).generate(parameters[i].getAnnotations());
        }
        return arguments;
    }
}
