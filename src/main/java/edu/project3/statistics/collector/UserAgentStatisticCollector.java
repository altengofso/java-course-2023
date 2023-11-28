package edu.project3.statistics.collector;

import edu.project3.models.logs.LogRecord;
import edu.project3.models.statistics.StatisticTable;
import java.util.List;
import java.util.stream.Collectors;

public class UserAgentStatisticCollector implements StatisticCollector {
    @Override
    public StatisticTable collect(List<LogRecord> logs) {
        StatisticTable statisticTable = new StatisticTable("User Agents", List.of("User Agent", "Amount"));
        var userAgents = logs
            .stream()
            .collect(Collectors
                .toMap(LogRecord::httpUserAgent, (logRecord -> 1), Integer::sum))
            .entrySet()
            .stream()
            .sorted((left, right) -> right.getValue() - left.getValue())
            .toList();
        for (var userAgent : userAgents) {
            statisticTable.addRow(List.of(
                userAgent.getKey(),
                userAgent.getValue().toString()
            ));
        }
        return statisticTable;
    }
}
