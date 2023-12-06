package edu.hw9.task1;

import java.util.Objects;
import lombok.Getter;

@Getter
public class MetricStats {
    private final String name;
    private volatile double sum;
    private volatile double min;
    private volatile double max;
    private volatile double avg;

    public MetricStats(String name, double[] values) {
        this.name = name;
        calculateStats(values);
    }

    public MetricStats(String name, double sum, double min, double max, double avg) {
        this.name = name;
        this.sum = sum;
        this.min = min;
        this.max = max;
        this.avg = avg;
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

        this.sum = currentSum;
        this.min = currentMin;
        this.max = currentMax;
        this.avg = currentAvg;
    }

    public synchronized void updateStats(MetricStats update) {
        this.sum += update.getSum();
        this.max = Math.max(this.max, update.getMax());
        this.min = Math.min(this.min, update.getMin());
        this.avg = (this.avg + update.getAvg()) / 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MetricStats that = (MetricStats) o;
        return Double.compare(getSum(), that.getSum()) == 0 && Double.compare(getMin(), that.getMin()) == 0
            && Double.compare(getMax(), that.getMax()) == 0 && Double.compare(getAvg(), that.getAvg()) == 0
            && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSum(), getMin(), getMax(), getAvg());
    }

    @Override
    public String toString() {
        return "MetricStats{"
            + "name='" + name + '\''
            + ", sum=" + sum
            + ", min=" + min
            + ", max=" + max
            + ", avg=" + avg
            + '}';
    }
}
