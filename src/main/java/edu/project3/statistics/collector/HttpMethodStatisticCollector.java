package edu.project3.statistics.collector;

import edu.project3.models.logs.LogRecord;
import edu.project3.models.statistics.StatisticTable;
import java.util.List;
import java.util.stream.Collectors;

public class HttpMethodStatisticCollector implements StatisticCollector {
    @Override
    public StatisticTable collect(List<LogRecord> logs) {
        StatisticTable statisticTable = new StatisticTable("HTTP Methods", List.of("Method", "Amount"));
        var httpMethods = logs
            .stream()
            .collect(Collectors
                .toMap(LogRecord::method, (logRecord -> 1), Integer::sum))
            .entrySet()
            .stream()
            .sorted((left, right) -> right.getValue() - left.getValue())
            .toList();
        for (var httpMethod : httpMethods) {
            statisticTable.addRow(List.of(
                httpMethod.getKey().toString(),
                httpMethod.getValue().toString()
            ));
        }
        return statisticTable;
    }
}
