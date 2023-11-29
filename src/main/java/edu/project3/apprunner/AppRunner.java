package edu.project3.apprunner;

import edu.project3.arguments.Arguments;
import edu.project3.logs.parser.LogParser;
import edu.project3.models.statistics.StatisticTable;
import edu.project3.reports.builder.ReportBuilder;
import edu.project3.reports.builder.ReportBuilderFactory;
import edu.project3.reports.printer.ReportPrinter;
import edu.project3.reports.printer.ReportPrinterCLI;
import edu.project3.statistics.collector.GeneralStatisticCollector;
import edu.project3.statistics.collector.HttpMethodStatisticCollector;
import edu.project3.statistics.collector.RequestResourceStatisticCollector;
import edu.project3.statistics.collector.StatisticCollector;
import edu.project3.statistics.collector.StatusCodeStatisticCollector;
import edu.project3.statistics.collector.UserAgentStatisticCollector;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;

public class AppRunner {
    @SneakyThrows
    public void run(String[] args) {
        Arguments arguments = new Arguments(args);
        var logRecords = LogParser.parseLog(arguments.getPaths(), arguments.getFrom(), arguments.getTo());
        List<StatisticCollector> statisticCollectors = List.of(
            new GeneralStatisticCollector(arguments),
            new RequestResourceStatisticCollector(),
            new StatusCodeStatisticCollector(),
            new HttpMethodStatisticCollector(),
            new UserAgentStatisticCollector()
        );
        List<StatisticTable> statisticTables = new ArrayList<>();
        for (var statisticCollector : statisticCollectors) {
            statisticTables.add(statisticCollector.collect(logRecords));
        }
        ReportBuilder reportBuilder = ReportBuilderFactory.getReportBuilder(arguments.getFormat());
        try (ReportPrinter reportPrinter = new ReportPrinterCLI()) {
            reportPrinter.print(reportBuilder.build(statisticTables));
        }
    }
}
