package edu.project3.arguments;

import edu.project3.models.reports.ReportFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

@Getter
public class Arguments {
    private static final String PATH_OPTION = "path";
    private static final String FROM_OPTION = "from";
    private static final String TO_OPTION = "to";
    private static final String FORMAT_OPTION = "format";
    private List<String> paths = new ArrayList<>();
    private LocalDate from = LocalDate.MIN;
    private LocalDate to = LocalDate.MAX;
    private ReportFormat format = ReportFormat.MARKDOWN;

    public Arguments(String[] args) {
        parseArgs(args);
    }

    @SuppressWarnings("InnerAssignment")
    private void parseArgs(String[] args) {
        Options options = new Options();
        options.addOption(Option.builder().longOpt(PATH_OPTION).hasArg().required().build());
        options.addOption(Option.builder().longOpt(FROM_OPTION).hasArg().build());
        options.addOption(Option.builder().longOpt(TO_OPTION).hasArg().build());
        options.addOption(Option.builder().longOpt(FORMAT_OPTION).hasArg().build());
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            this.paths = PathResolver.getPathList(line.getOptionValue(PATH_OPTION));
            if (line.hasOption(FROM_OPTION)) {
                this.from = LocalDate.parse(line.getOptionValue(FROM_OPTION));
            }
            if (line.hasOption(TO_OPTION)) {
                this.to = LocalDate.parse(line.getOptionValue(TO_OPTION));
            }
            if (line.hasOption(FORMAT_OPTION)) {
                this.format = ReportFormat.valueOf(line.getOptionValue(FORMAT_OPTION).toUpperCase());
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
