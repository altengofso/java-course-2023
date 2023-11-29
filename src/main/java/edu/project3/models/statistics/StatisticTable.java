package edu.project3.models.statistics;

import java.util.ArrayList;
import java.util.List;

public class StatisticTable {
    private final String title;
    private final List<String> colTitles;
    private final List<List<String>> rows;

    public StatisticTable(String title, List<String> colTitles) {
        this.title = title;
        this.colTitles = colTitles;
        this.rows = new ArrayList<>();
    }

    public void addRow(List<String> row) {
        rows.add(row);
    }

    public String getTitle() {
        return title;
    }

    public List<String> getColTitles() {
        return colTitles;
    }

    public List<List<String>> getRows() {
        return rows;
    }
}
