package edu.project3.reports.builder;

import edu.project3.models.statistics.StatisticTable;
import java.util.List;

public interface ReportBuilder {
    String build(List<StatisticTable> statisticTables);
}
