package edu.project3.logs.reader;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class LogReaderFactory {
    public static LogReader getLogReader(String path) {
        if (path.contains("://")) {
            return new LogReaderHttp();
        }
        return new LogReaderDisk();
    }
}
