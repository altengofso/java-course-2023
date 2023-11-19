package edu.project3;

import edu.project3.arguments.Arguments;
import edu.project3.logs.parser.LogParser;
import edu.project3.models.reports.Format;
import edu.project3.models.statistics.StatisticTable;
import edu.project3.reports.builder.ReportBuilder;
import edu.project3.reports.builder.ReportBuilderFactory;
import edu.project3.statistics.collector.GeneralStatisticCollector;
import edu.project3.statistics.collector.HttpMethodStatisticCollector;
import edu.project3.statistics.collector.RequestResourceStatisticCollector;
import edu.project3.statistics.collector.StatisticCollector;
import edu.project3.statistics.collector.StatusCodeStatisticCollector;
import edu.project3.statistics.collector.UserAgentStatisticCollector;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestStatisticsReporting {
    private static final String[] ARGS =
        {"--path", "*.txt", "--from", "2015-05-17", "--to", "2015-05-18"};
    private static final String LOGFILE = "log.txt";

    private static final String LOGFILE_CONTENT = """
        93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 200 0 "-" "Debian APT-HTTP/1.3"
        93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 200 0 "-" "Debian APT-HTTP/1.3"
        93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 200 0 "-" "Debian APT-HTTP/1.3"
        93.180.71.3 - - [17/May/2015:08:05:32 +0000] "HEAD /downloads/product_2 HTTP/1.1" 301 0 "-" "Ubuntu APT-HTTP/1.3"
        93.180.71.3 - - [17/May/2015:08:05:32 +0000] "HEAD /downloads/product_2 HTTP/1.1" 301 0 "-" "Ubuntu APT-HTTP/1.3"
        93.180.71.3 - - [17/May/2015:08:05:32 +0000] "POST /downloads/product_3 HTTP/1.1" 404 0 "-" "Kali APT-HTTP/1.3"
        """;

    @BeforeAll
    static void createFiles() throws IOException {
        Files.createFile(Path.of(LOGFILE));
        Files.writeString(Path.of(LOGFILE), LOGFILE_CONTENT);
    }

    @Test
    void testMarkdownReport() {
        Arguments arguments = new Arguments(ARGS);
        var logRecords = LogParser.parseLog(arguments.getPaths(), arguments.getFrom(), arguments.getTo());
        List<StatisticCollector> statisticCollectors = List.of(
            new GeneralStatisticCollector(arguments),
            new RequestResourceStatisticCollector(),
            new StatusCodeStatisticCollector(),
            new HttpMethodStatisticCollector(),
            new UserAgentStatisticCollector()
        );
        List<StatisticTable> statisticTables = new ArrayList<>();
        for (
            var statisticCollector : statisticCollectors) {
            statisticTables.add(statisticCollector.collect(logRecords));
        }

        ReportBuilder reportBuilder = ReportBuilderFactory.getReportBuilder(Format.MARKDOWN);
        assertThat(reportBuilder.build(statisticTables)).isEqualTo("""
            ### General Info
            |Metric|Value|
            |---|---|
            |Files|log.txt|
            |Start date|17-05-2015|
            |End date|18-05-2015|
            |Requests amount|6|
            |Average response size|0b|

            ### Requested resources
            |Resource|Requests amount|
            |---|---|
            |/downloads/product_1|3|
            |/downloads/product_2|2|
            |/downloads/product_3|1|

            ### Status codes
            |Code|Name|Amount|
            |---|---|---|
            |200|OK|3|
            |301|Moved Permanently|2|
            |404|Not Found|1|

            ### HTTP Methods
            |Method|Amount|
            |---|---|
            |GET|3|
            |HEAD|2|
            |POST|1|

            ### User Agents
            |User Agent|Amount|
            |---|---|
            |Debian APT-HTTP/1.3|3|
            |Ubuntu APT-HTTP/1.3|2|
            |Kali APT-HTTP/1.3|1|

            """);
    }

    @Test
    void testAdocReport() {
        Arguments arguments = new Arguments(ARGS);
        var logRecords = LogParser.parseLog(arguments.getPaths(), arguments.getFrom(), arguments.getTo());
        List<StatisticCollector> statisticCollectors = List.of(
            new GeneralStatisticCollector(arguments),
            new RequestResourceStatisticCollector(),
            new StatusCodeStatisticCollector(),
            new HttpMethodStatisticCollector(),
            new UserAgentStatisticCollector()
        );
        List<StatisticTable> statisticTables = new ArrayList<>();
        for (
            var statisticCollector : statisticCollectors) {
            statisticTables.add(statisticCollector.collect(logRecords));
        }

        ReportBuilder reportBuilder = ReportBuilderFactory.getReportBuilder(Format.ADOC);
        assertThat(reportBuilder.build(statisticTables)).isEqualTo("""
            === General Info
            |====
            |Metric|Value

            |Files|log.txt
            |Start date|17-05-2015
            |End date|18-05-2015
            |Requests amount|6
            |Average response size|0b
            |====

            === Requested resources
            |====
            |Resource|Requests amount

            |/downloads/product_1|3
            |/downloads/product_2|2
            |/downloads/product_3|1
            |====

            === Status codes
            |====
            |Code|Name|Amount

            |200|OK|3
            |301|Moved Permanently|2
            |404|Not Found|1
            |====

            === HTTP Methods
            |====
            |Method|Amount

            |GET|3
            |HEAD|2
            |POST|1
            |====

            === User Agents
            |====
            |User Agent|Amount

            |Debian APT-HTTP/1.3|3
            |Ubuntu APT-HTTP/1.3|2
            |Kali APT-HTTP/1.3|1
            |====

            """);
    }

    @AfterAll
    static void deleteFiles() throws IOException {
        Files.delete(Path.of(LOGFILE));
    }
}
