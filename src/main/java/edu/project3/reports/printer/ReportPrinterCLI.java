package edu.project3.reports.printer;

import java.io.PrintWriter;

public class ReportPrinterCLI implements ReportPrinter {
    private final PrintWriter printWriter;

    public ReportPrinterCLI() {
        printWriter = new PrintWriter(System.out);
    }

    @Override
    public void print(String report) {
        printWriter.write(report);
        printWriter.flush();

    }

    @Override
    public void close() throws Exception {
        printWriter.close();
    }
}
