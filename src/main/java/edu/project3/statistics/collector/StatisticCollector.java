package edu.project3.statistics.collector;

import edu.project3.models.logs.LogRecord;
import edu.project3.models.statistics.StatisticTable;
import java.util.List;

public interface StatisticCollector {
    StatisticTable collect(List<LogRecord> logs);
}
