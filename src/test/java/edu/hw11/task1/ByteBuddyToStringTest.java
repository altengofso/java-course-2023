package edu.hw11.task1;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class ByteBuddyToStringTest {
    @Test
    @SneakyThrows
    void testByteBuddyToString() {
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
        String expected = "Hello, ByteBuddy!";
        String actual = dynamicType.getDeclaredConstructor().newInstance().toString();
        assertThat(actual).isEqualTo(expected);
    }
}
