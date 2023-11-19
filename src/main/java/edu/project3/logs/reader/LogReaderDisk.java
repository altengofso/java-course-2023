package edu.project3.logs.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LogReaderDisk implements LogReader {
    @Override
    public List<String> readLogFile(String path) {
        return readFromDisk(path);
    }

    private List<String> readFromDisk(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
