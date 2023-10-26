package edu.hw3.Task6;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private final String name;
    private final int value;

    public Stock(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        return getValue() == stock.getValue() && Objects.equals(getName(), stock.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getValue());
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        return getValue() - o.getValue();
    }
}
