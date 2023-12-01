package edu.project3.reports.builder;

import edu.project3.models.reports.ReportFormat;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ReportBuilderFactory {
    public static ReportBuilder getReportBuilder(ReportFormat format) {
        return switch (format) {
            case MARKDOWN -> new ReportBuilderMarkdown();
            case ADOC -> new ReportBuilderAdoc();
        };
    }
}
