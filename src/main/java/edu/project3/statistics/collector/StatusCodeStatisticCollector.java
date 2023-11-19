package edu.project3.statistics.collector;

import edu.project3.models.logs.LogRecord;
import edu.project3.models.statistics.StatisticTable;
import java.util.List;
import java.util.stream.Collectors;

public class StatusCodeStatisticCollector implements StatisticCollector {
    @Override
    public StatisticTable collect(List<LogRecord> logs) {
        StatisticTable statisticTable = new StatisticTable("Status codes", List.of("Code", "Name", "Amount"));
        var statusCodes = logs
            .stream()
            .collect(Collectors
                .toMap(LogRecord::status, (logRecord -> 1), Integer::sum))
            .entrySet()
            .stream()
            .sorted((left, right) -> right.getValue() - left.getValue())
            .toList();
        for (var statusCode : statusCodes) {
            statisticTable.addRow(List.of(
                String.valueOf(statusCode.getKey().getCode()),
                statusCode.getKey().getValue(),
                statusCode.getValue().toString()
            ));
        }
        return statisticTable;
    }
}
