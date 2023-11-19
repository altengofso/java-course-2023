package edu.project3.statistics.collector;

import edu.project3.arguments.Arguments;
import edu.project3.arguments.FileResolver;
import edu.project3.models.logs.LogRecord;
import edu.project3.models.statistics.StatisticTable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GeneralStatisticCollector implements StatisticCollector {
    private final Arguments args;

    public GeneralStatisticCollector(Arguments args) {
        this.args = args;
    }

    @Override
    public StatisticTable collect(List<LogRecord> logs) {
        StatisticTable statisticTable = new StatisticTable("General Info", List.of("Metric", "Value"));
        List<String> files = FileResolver.getFiles(args.getPaths());
        for (int i = 0; i < files.size(); ++i) {
            if (i == 0) {
                statisticTable.addRow(List.of("Files", files.get(i)));
            } else {
                statisticTable.addRow(List.of("", files.get(i)));
            }
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        statisticTable.addRow(List.of(
            "Start date",
            args.getFrom() == LocalDate.MIN ? "-" : args.getFrom().format(dateTimeFormatter)
        ));
        statisticTable.addRow(List.of(
            "End date",
            args.getTo() == LocalDate.MAX ? "-" : args.getTo().format(dateTimeFormatter)
        ));
        statisticTable.addRow(List.of(
            "Requests amount",
            String.valueOf(logs.size())
        ));
        statisticTable.addRow(List.of(
            "Average response size",
            (long) logs.stream()
                .mapToLong(LogRecord::bodyBytesSent)
                .average()
                .orElse(0) + "b"
        ));
        return statisticTable;
    }
}
