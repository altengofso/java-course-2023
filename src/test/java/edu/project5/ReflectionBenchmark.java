package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import lombok.SneakyThrows;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmark {
    private static final int WARMUP_TIME = 5;
    private static final int MEASURE_TIME = 5;
    private static final String METHOD_NAME = "name";

    @SneakyThrows
    public static void main(String[] args) {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(WARMUP_TIME))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(MEASURE_TIME))
            .build();

        new Runner(options).run();
    }

    record Student(String name, String surname) {
    }

    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Supplier<String> lambda;

    @Setup
    @SneakyThrows
    public void setup() {
        student = new Student("Jon", "Bon Jovi");
        method = Student.class.getDeclaredMethod(METHOD_NAME);
        methodHandle =
            MethodHandles.lookup().findVirtual(Student.class, METHOD_NAME, MethodType.methodType(String.class));
        //noinspection unchecked
        lambda = (Supplier<String>) LambdaMetafactory.metafactory(
            MethodHandles.lookup(),
            "get",
            MethodType.methodType(Supplier.class, Student.class),
            MethodType.methodType(Object.class),
            methodHandle,
            MethodType.methodType(String.class)
        ).getTarget().invokeExact(student);
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    @SneakyThrows
    public void reflectionAccess(Blackhole bh) {
        Object name = method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    @SneakyThrows
    public void methodHandlesAccess(Blackhole bh) {
        Object name = methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetafactoryAccess(Blackhole bh) {
        String name = lambda.get();
        bh.consume(name);
    }
}

