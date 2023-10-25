package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    static Arguments[] Rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("Rectangles")
    @DisplayName("Test area calculation for different width and height")
    void testAreaCalculationWithDifferentSides(Rectangle rectangle) {
        rectangle = rectangle.setWidth(20);
        rectangle = rectangle.setHeight(10);
        assertThat(rectangle.area()).isEqualTo(200.0);
    }

    @ParameterizedTest
    @MethodSource("Rectangles")
    @DisplayName("Test area calculation for the same width and height")
    void testAreaCalculationWithSameSides(Rectangle rectangle) {
        rectangle = rectangle.setWidth(20);
        rectangle = rectangle.setHeight(20);
        assertThat(rectangle.area()).isEqualTo(400.0);
    }

    @Test
    @DisplayName("Test square set side")
    void testSquareSetSide() {
        Square square = new Square();
        square = square.setSide(20);
        assertThat(square.area()).isEqualTo(400.0);
        square = square.setSide(30);
        assertThat(square.area()).isEqualTo(900.0);
    }

    @Test
    @DisplayName("Square set only height")
    void testSquareSetHeight() {
        Square square = new Square(10);
        assertThat(square.area()).isEqualTo(100.0);
        Rectangle rectangle = square.setHeight(20);
        assertThat(rectangle.area()).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Square set only width")
    void testSquareSetWidth() {
        Square square = new Square(10);
        assertThat(square.area()).isEqualTo(100.0);
        Rectangle rectangle = square.setWidth(20);
        assertThat(rectangle.area()).isEqualTo(200.0);
    }
}
