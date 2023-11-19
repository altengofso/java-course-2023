package edu.project3.logs.reader;

public final class LogReaderFactory {
    private LogReaderFactory() {
    }

    public static LogReader getLogReader(String path) {
        if (path.contains("://")) {
            return new LogReaderHttp();
        }
        return new LogReaderDisk();
    }
}
