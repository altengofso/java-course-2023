package edu.project1;

import java.io.PrintWriter;

public class OutputWriterCli implements OutputWriter {
    private final PrintWriter writer;

    public OutputWriterCli(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void write(String output) {
        writer.println(output);
        writer.flush();
    }
}
