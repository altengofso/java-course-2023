package edu.project3.logs.reader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import lombok.SneakyThrows;

public class LogReaderDisk implements LogReader {
    @Override
    public List<String> readLogFile(String path) {
        return readFromDisk(path);
    }

    @SneakyThrows
    private List<String> readFromDisk(String path) {
        return Files.readAllLines(Path.of(path));
    }
}
