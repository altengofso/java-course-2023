package edu.hw11.task2;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class ByteBuddySumOperandModificationTest {
    @Test
    @SneakyThrows
    void testByteBuddySumOperandModification() {
        Class<?> dynamicType = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(named("sum"))
            .intercept(MethodDelegation.to(MultiplyDelegate.class))
            .make()
            .load(ArithmeticUtils.class.getClassLoader())
            .getLoaded();

        int a = 2;
        int b = 3;
        int expected = 6;
        int actual = ((ArithmeticUtils) dynamicType.getDeclaredConstructor().newInstance()).sum(a, b);

        assertThat(actual).isEqualTo(expected);
    }
}
