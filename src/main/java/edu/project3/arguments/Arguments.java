package edu.project3.arguments;

import edu.project3.models.reports.Format;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Arguments {
    private List<String> paths = new ArrayList<>();
    private LocalDate from = LocalDate.MIN;
    private LocalDate to = LocalDate.MAX;
    private Format format = Format.MARKDOWN;

    public Arguments(String[] args) {
        parseArgs(args);
    }

    @SuppressWarnings("InnerAssignment")
    private void parseArgs(String[] args) {
        String argName = "";
        for (var arg : args) {
            if (arg.startsWith("--")) {
                argName = arg;
            } else {
                switch (argName) {
                    case "--path" -> this.paths = PathResolver.getPathList(arg);
                    case "--from" -> this.from = LocalDate.parse(arg);
                    case "--to" -> this.to = LocalDate.parse(arg);
                    case "--format" -> this.format = Format.valueOf(arg.toUpperCase());
                    default -> {
                    }
                }
            }
        }
        if (paths.isEmpty()) {
            throw new IllegalArgumentException("Argument --path required.");
        }
    }

    public List<String> getPaths() {
        return paths;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public Format getFormat() {
        return format;
    }
}
