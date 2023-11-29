package edu.project3.statistics.collector;

import edu.project3.models.logs.LogRecord;
import edu.project3.models.statistics.StatisticTable;
import java.util.List;
import java.util.stream.Collectors;

public class RequestResourceStatisticCollector implements StatisticCollector {
    @Override
    public StatisticTable collect(List<LogRecord> logs) {
        StatisticTable statisticTable =
            new StatisticTable("Requested resources", List.of("Resource", "Requests amount"));
        var resources = logs
            .stream()
            .collect(Collectors
                .toMap(LogRecord::request, (logRecord -> 1), Integer::sum))
            .entrySet()
            .stream()
            .sorted((left, right) -> right.getValue() - left.getValue())
            .toList();
        for (var resource : resources) {
            statisticTable.addRow(List.of(
                resource.getKey(),
                resource.getValue().toString()
            ));
        }
        return statisticTable;
    }
}
