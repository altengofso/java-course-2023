package edu.hw9.task1;

import java.util.Objects;
import lombok.Getter;

@Getter
public class MetricStats {
    private final String name;
    private AtomicDouble sum;
    private AtomicDouble min;
    private AtomicDouble max;
    private AtomicDouble avg;

    public MetricStats(String name, double[] values) {
        this.name = name;
        calculateStats(values);
    }

    public MetricStats(String name, double sum, double min, double max, double avg) {
        this.name = name;
        this.sum = new AtomicDouble(sum);
        this.min = new AtomicDouble(min);
        this.max = new AtomicDouble(max);
        this.avg = new AtomicDouble(avg);
    }

    private void calculateStats(double[] values) {
        double currentSum = 0;
        double currentMin = Double.MAX_VALUE;
        double currentMax = Double.MIN_VALUE;
        for (double value : values) {
            currentSum += value;
            currentMin = Math.min(currentMin, value);
            currentMax = Math.max(currentMax, value);
        }
        double currentAvg = currentSum / values.length;

        this.sum = new AtomicDouble(currentSum);
        this.min = new AtomicDouble(currentMin);
        this.max = new AtomicDouble(currentMax);
        this.avg = new AtomicDouble(currentAvg);
    }

    public synchronized void updateStats(MetricStats update) {
        sum.set(sum.get() + update.getSum().get());
        max.set(Math.max(max.get(), update.getMax().get()));
        min.set(Math.min(min.get(), update.getMin().get()));
        avg.set((avg.get() + update.getAvg().get()) / 2);
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MetricStats that = (MetricStats) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getSum(), that.getSum())
            && Objects.equals(getMin(), that.getMin()) && Objects.equals(getMax(), that.getMax())
            && Objects.equals(getAvg(), that.getAvg());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSum(), getMin(), getMax(), getAvg());
    }

    @Override public String toString() {
        return "MetricStats{"
            + "name='" + name + '\''
            + ", sum=" + sum.get()
            + ", min=" + min.get()
            + ", max=" + max.get()
            + ", avg=" + avg.get()
            + '}';
    }
}
