package edu.project3.reports.builder;

import edu.project3.models.statistics.StatisticTable;
import java.util.List;

public class ReportBuilderMarkdown implements ReportBuilder {
    private static final char NEWLINE = '\n';
    private static final String HEADER = "### ";
    private static final char PIPE = '|';
    private static final String TABLE_COL = "---";

    @Override
    public String build(List<StatisticTable> statisticTables) {
        StringBuilder stringBuilder = new StringBuilder();
        for (var statisticTable : statisticTables) {
            stringBuilder.append(buildTable(statisticTable));
        }
        return stringBuilder.toString();
    }

    private StringBuilder buildTable(StatisticTable statisticTable) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HEADER).append(statisticTable.getTitle()).append(NEWLINE);
        stringBuilder.append(PIPE);
        for (var colTitle : statisticTable.getColTitles()) {
            stringBuilder.append(colTitle).append(PIPE);
        }
        stringBuilder.append(NEWLINE);
        stringBuilder.append(PIPE);
        for (int i = 0; i < statisticTable.getColTitles().size(); ++i) {
            stringBuilder.append(TABLE_COL).append(PIPE);
        }
        stringBuilder.append(NEWLINE);
        for (var row : statisticTable.getRows()) {
            stringBuilder.append(PIPE);
            for (var value : row) {
                stringBuilder.append(value).append(PIPE);
            }
            stringBuilder.append(NEWLINE);
        }
        stringBuilder.append(NEWLINE);
        return stringBuilder;
    }
}
