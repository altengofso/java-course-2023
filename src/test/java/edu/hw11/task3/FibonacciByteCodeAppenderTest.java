package edu.hw11.task3;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FibonacciByteCodeAppenderTest {
    @Test
    @SneakyThrows
    void testFibonacciByteCodeAppender() {
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("getFibonacci", long.class, Opcodes.ACC_PUBLIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(new FibonacciByteCodeAppender()))
            .make()
            .load(ClassLoader.getSystemClassLoader())
            .getLoaded();

        long expected = 55L;
        long actual = (long) dynamicType.getMethod("getFibonacci", int.class)
            .invoke(dynamicType.getDeclaredConstructor().newInstance(), 10);

        assertThat(actual).isEqualTo(expected);
    }
}
