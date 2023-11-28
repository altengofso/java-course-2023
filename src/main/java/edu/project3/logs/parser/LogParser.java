package edu.project3.logs.parser;

import edu.project3.logs.exceptions.LogFormatException;
import edu.project3.logs.reader.LogReader;
import edu.project3.logs.reader.LogReaderFactory;
import edu.project3.models.http.HttpMethod;
import edu.project3.models.http.HttpStatusCode;
import edu.project3.models.logs.LogRecord;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class LogParser {
    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
    private static final Pattern LOG_PATTERN = Pattern.compile("(?<remoteAddress>.*) - "
        + "(?<remoteUser>.*) "
        + "\\[(?<timeLocal>.*)\\] "
        + "\\\"(?<method>.*) "
        + "(?<request>.*) "
        + "(?<protocol>.*)\\\" "
        + "(?<status>\\d+) "
        + "(?<bodyBytesSent>\\d+) "
        + "\\\"(?<httpReferer>.*)\\\" "
        + "\\\"(?<httpUserAgent>.*)\\\"");

    public static List<LogRecord> parseLog(List<String> paths, LocalDate from, LocalDate to) {
        LogReader logReader = LogReaderFactory.getLogReader(paths.get(0));
        LocalDate fromInclusive = from.isEqual(LocalDate.MIN) ? from : from.minusDays(1);
        List<LogRecord> logRecords = new ArrayList<>();
        for (var path : paths) {
            logRecords.addAll(logReader.readLogFile(path)
                .stream()
                .map(LogParser::parseLogString)
                .filter(logRecord -> logRecord.timeLocal().toLocalDate().isAfter(fromInclusive)
                    && logRecord.timeLocal().toLocalDate().isBefore(to))
                .toList());
        }
        return logRecords;
    }

    private static LogRecord parseLogString(String log) {
        Matcher matcher = LOG_PATTERN.matcher(log);
        if (!matcher.matches()) {
            throw new LogFormatException("Wrong log format.");
        }
        return new LogRecord(
            matcher.group("remoteAddress"),
            matcher.group("remoteUser"),
            parseTimeLocal(matcher.group("timeLocal")),
            HttpMethod.valueOf(matcher.group("method").toUpperCase()),
            matcher.group("request"),
            matcher.group("protocol"),
            HttpStatusCode.getByStatusCode(Integer.parseInt(matcher.group("status"))),
            Long.parseLong(matcher.group("bodyBytesSent")),
            matcher.group("httpReferer"),
            matcher.group("httpUserAgent")
        );
    }

    private static OffsetDateTime parseTimeLocal(String timeLocal) {
        return OffsetDateTime.parse(timeLocal, FORMATTER);
    }
}
