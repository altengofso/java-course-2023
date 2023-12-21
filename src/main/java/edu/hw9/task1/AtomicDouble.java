package edu.hw9.task1;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicDouble extends Number {
    private final AtomicLong bits;

    public AtomicDouble() {
        this(0d);
    }

    public AtomicDouble(double initValue) {
        bits = new AtomicLong(Double.doubleToLongBits(initValue));
    }

    public final double get() {
        return Double.longBitsToDouble(bits.get());
    }

    public final void set(double newValue) {
        bits.set(Double.doubleToLongBits(newValue));
    }

    @Override
    public int intValue() {
        return (int) get();
    }

    @Override
    public long longValue() {
        return (long) get();
    }

    @Override
    public float floatValue() {
        return (float) get();
    }

    @Override
    public double doubleValue() {
        return get();
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AtomicDouble that = (AtomicDouble) o;
        return Objects.equals(bits.get(), that.bits.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(bits.get());
    }
}
