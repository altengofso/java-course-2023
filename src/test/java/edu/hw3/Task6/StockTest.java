package edu.hw3.Task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class StockTest {
    @Test
    @DisplayName("Test Stock Class Equals")
    void testStockClassEquals() {
        Stock stock1 = new Stock("ABC", 300);
        Stock stock2 = new Stock("ABC", 300);
        assertThat(stock1).isEqualTo(stock2);
    }

    @Test
    @DisplayName("Test Stock Class Not Equals")
    void testStockClassNotEquals() {
        Stock stock1 = new Stock("ABC", 300);
        Stock stock2 = new Stock("DEF", 400);
        assertThat(stock1).isNotEqualTo(stock2);
        assertThat(stock1).isNotEqualTo(null);
        assertThat(stock1).isNotEqualTo(new Object());
    }

    @Test
    @DisplayName("Test Stock Class Compare")
    void testStockClassCompare() {
        Stock stock1 = new Stock("ABC", 300);
        Stock stock2 = new Stock("ABC", 400);
        assertThat(stock1.compareTo(stock2)).isLessThan(0);
    }
}
