package edu.hw10.task1;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomObjectGeneratorTest {
    @Test
    void testRandomObjectGeneratorUsingConstructor() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        TestClass testObject = randomObjectGenerator.nextObject(TestClass.class);
        assertThat(testObject).isInstanceOf(TestClass.class);
    }

    @Test
    void testRandomObjectGeneratorUsingFactoryMethod() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        TestClass testObject = randomObjectGenerator.nextObject(TestClass.class, "create");
        assertThat(testObject).isInstanceOf(TestClass.class);
    }

    @Test
    void testRandomObjectGeneratorThrowsWhenFactoryMethodInvalid() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        assertThrows(NoSuchElementException.class, () -> randomObjectGenerator.nextObject(TestClass.class, "new"));
    }
}
