package edu.project3.reports.builder;

import edu.project3.models.reports.Format;

public final class ReportBuilderFactory {
    private ReportBuilderFactory() {
    }

    public static ReportBuilder getReportBuilder(Format format) {
        return switch (format) {
            case MARKDOWN -> new ReportBuilderMarkdown();
            case ADOC -> new ReportBuilderAdoc();
        };
    }
}
