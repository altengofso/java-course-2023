package edu.project3;

import edu.project3.arguments.Arguments;
import edu.project3.logs.exceptions.LogFormatException;
import edu.project3.logs.parser.LogParser;
import edu.project3.logs.reader.LogReaderDisk;
import edu.project3.logs.reader.LogReaderFactory;
import edu.project3.logs.reader.LogReaderHttp;
import edu.project3.models.http.HttpMethod;
import edu.project3.models.http.HttpStatusCode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLogs {
    private static final String[] ARGS =
        {"--path", "log.txt", "--from", "2015-05-17", "--to", "2015-05-18", "--format", "adoc"};
    private static final String LOGFILE = "log.txt";

    private static final String LOGFILE_CONTENT = """
        93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 200 0 "-" "Debian APT-HTTP/1.3"
        93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 200 0 "-" "Debian APT-HTTP/1.3"
        93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 200 0 "-" "Debian APT-HTTP/1.3"
        93.180.71.3 - - [17/May/2015:08:05:32 +0000] "HEAD /downloads/product_2 HTTP/1.1" 301 0 "-" "Ubuntu APT-HTTP/1.3"
        93.180.71.3 - - [17/May/2015:08:05:32 +0000] "HEAD /downloads/product_2 HTTP/1.1" 301 0 "-" "Ubuntu APT-HTTP/1.3"
        93.180.71.3 - - [17/May/2015:08:05:32 +0000] "POST /downloads/product_3 HTTP/1.1" 404 0 "-" "Kali APT-HTTP/1.3"
        """;
    private static final String[] BAD_ARGS =
        {"--path", "bad_log.txt", "--from", "2015-05-17", "--to", "2015-05-18", "--format", "adoc"};
    private static final String BAD_LOGFILE = "bad_log.txt";

    private static final String BAD_LOGFILE_CONTENT = """
        93.180.71.3 - - "GET /downloads/product_1 HTTP/1.1" 200 0 "-" "Debian APT-HTTP/1.3"
        """;

    @BeforeAll
    static void createFiles() throws IOException {
        Files.createFile(Path.of(LOGFILE));
        Files.writeString(Path.of(LOGFILE), LOGFILE_CONTENT);
        Files.createFile(Path.of(BAD_LOGFILE));
        Files.writeString(Path.of(BAD_LOGFILE), BAD_LOGFILE_CONTENT);
    }

    @Test
    void testLogReaderFactory() {
        assertThat(LogReaderFactory.getLogReader(LOGFILE).getClass()).isEqualTo(LogReaderDisk.class);
        assertThat(LogReaderFactory.getLogReader("http://webserver/nginx.log")
            .getClass()).isEqualTo(LogReaderHttp.class);
    }

    @Test
    void testLogReaderDisk() {
        LogReaderDisk logReaderDisk = new LogReaderDisk();
        assertThat(logReaderDisk.readLogFile(LOGFILE)).isEqualTo(Arrays.stream(LOGFILE_CONTENT.split("\n")).toList());
    }

    @Test
    void testLogParser() {
        Arguments arguments = new Arguments(ARGS);
        var logRecord = LogParser.parseLog(arguments.getPaths(), arguments.getFrom(), arguments.getTo()).get(0);
        assertThat(logRecord.remoteAddress()).isEqualTo("93.180.71.3");
        assertThat(logRecord.remoteUser()).isEqualTo("-");
        assertThat(logRecord.timeLocal()).isEqualTo(OffsetDateTime.of(2015, 5, 17, 8, 5, 32, 0, ZoneOffset.UTC));
        assertThat(logRecord.method()).isEqualTo(HttpMethod.GET);
        assertThat(logRecord.request()).isEqualTo("/downloads/product_1");
        assertThat(logRecord.status()).isEqualTo(HttpStatusCode.OK);
        assertThat(logRecord.bodyBytesSent()).isEqualTo(0);
        assertThat(logRecord.httpReferer()).isEqualTo("-");
        assertThat(logRecord.httpUserAgent()).isEqualTo("Debian APT-HTTP/1.3");
    }

    @Test
    void testLogParserThrows() {
        Arguments arguments = new Arguments(BAD_ARGS);
        assertThrows(
            LogFormatException.class,
            () -> LogParser.parseLog(arguments.getPaths(), arguments.getFrom(), arguments.getTo())
        );
    }

    @AfterAll
    static void deleteFiles() throws IOException {
        Files.delete(Path.of(LOGFILE));
        Files.delete(Path.of(BAD_LOGFILE));
    }
}
