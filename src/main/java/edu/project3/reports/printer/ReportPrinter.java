package edu.project3.reports.printer;

public interface ReportPrinter extends AutoCloseable {
    void print(String report);
}
