package edu.hw3.Task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MarketTest {

    @Test
    @DisplayName("Test Market Class Add Method")
    void testMarketClassAddMethod() {
        Market market = new Market();
        Stock stock = new Stock("ABC", 300);
        assertThat(market.mostValuableStock()).isEqualTo(null);
        market.add(stock);
        assertThat(market.mostValuableStock()).isEqualTo(stock);
    }

    @Test
    @DisplayName("Test Market Class Remove Method")
    void testMarketClassRemoveMethod() {
        Market market = new Market();
        Stock stock = new Stock("ABC", 300);
        assertThat(market.mostValuableStock()).isEqualTo(null);
        market.add(stock);
        assertThat(market.mostValuableStock()).isEqualTo(stock);
        market.remove(stock);
        assertThat(market.mostValuableStock()).isEqualTo(null);
    }

    @Test
    @DisplayName("Test Market Class MostValuableStock Method")
    void testMarketClassMostValuableStockMethod() {
        Market market = new Market();
        Stock stock1 = new Stock("ABC", 300);
        Stock stock2 = new Stock("DEF", 400);
        Stock stock3 = new Stock("GHI", 500);
        market.add(stock1);
        market.add(stock3);
        assertThat(market.mostValuableStock()).isEqualTo(stock3);
        market.add(stock2);
        assertThat(market.mostValuableStock()).isEqualTo(stock3);
        market.remove(stock3);
        assertThat(market.mostValuableStock()).isEqualTo(stock2);
    }
}
