package edu.hw2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import edu.hw2.Task2.Square;
import edu.hw2.Task2.Rectangle;

public class Task2Test {
    static Arguments[] Rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("Rectangles")
    @DisplayName("Rectangle as Square")
    void testRectangleArea(Rectangle rect) {
        rect.setWidth(20);
        rect.setHeight(10);
        assertThat(rect.area()).isEqualTo(200.0);
    }

    @ParameterizedTest
    @MethodSource("Rectangles")
    @DisplayName("Square as Rectangle")
    void testSquareArea(Rectangle rect) {
        rect.setWidth(20);
        rect.setHeight(20);
        assertThat(rect.area()).isEqualTo(400.0);
    }

    @Test
    @DisplayName("Square set only width")
    void testSquareSetWidth() {
        Square square = new Square();
        square.setWidth(20);
        assertThat(square.area()).isEqualTo(400.0);
    }

    @Test
    @DisplayName("Square set only height")
    void testSquareSetHeight() {
        Square square = new Square();
        square.setHeight(20);
        assertThat(square.area()).isEqualTo(400.0);
    }
}
