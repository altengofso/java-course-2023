package edu.hw10.task1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AnnotationsTest {
    @Test
    void testAnnotations() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        TestClass testClass = randomObjectGenerator.nextObject(TestClass.class);
        assertThat(testClass.getStringField()).isNotNull();
        assertThat(testClass.getStringField().length()).isEqualTo(30);
        assertThat(testClass.getShortField()).isBetween((short) 1, (short) 15);
        assertThat(testClass.getIntField()).isBetween(20, 25);
        assertThat(testClass.getLongField()).isBetween(30L, 35L);
        assertThat(testClass.getFloatField()).isBetween(45f, 50f);
        assertThat(testClass.getDoubleField()).isBetween(55d, 60d);
    }
}
