package edu.project3.logs.reader;

import java.util.List;

public interface LogReader {
    List<String> readLogFile(String path);
}
