package edu.project3.reports.builder;

import edu.project3.models.statistics.StatisticTable;
import java.util.List;

public class ReportBuilderAdoc implements ReportBuilder {
    private static final char NEWLINE = '\n';
    private static final String TABLE_BOUND = "|====";
    private static final char PIPE = '|';
    private static final String HEADER = "=== ";

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
        stringBuilder.append(TABLE_BOUND).append(NEWLINE);
        for (var colTitle : statisticTable.getColTitles()) {
            stringBuilder.append(PIPE).append(colTitle);
        }
        stringBuilder.append(NEWLINE).append(NEWLINE);
        for (var row : statisticTable.getRows()) {
            for (var value : row) {
                stringBuilder.append(PIPE).append(value);
            }
            stringBuilder.append(NEWLINE);
        }
        stringBuilder.append(TABLE_BOUND).append(NEWLINE).append(NEWLINE);
        return stringBuilder;
    }
}
