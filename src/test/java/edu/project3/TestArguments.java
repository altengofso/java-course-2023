package edu.project3;

import edu.project3.arguments.Arguments;
import edu.project3.arguments.FileResolver;
import edu.project3.models.reports.ReportFormat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestArguments {
    private static final String LOGFILE = "log.txt";
    private static final String[] ARGS =
        {"--path", "*.txt", "--from", "2023-08-01", "--to", "2023-08-31", "--format", "adoc"};

    @BeforeAll
    static void createFiles() throws IOException {
        Files.createFile(Path.of(LOGFILE));
    }

    @Test
    void testArgumentsParse() {
        Arguments arguments = new Arguments(ARGS);
        assertThat(arguments.getPaths()).isEqualTo(List.of(LOGFILE));
        assertThat(arguments.getFrom()).isEqualTo(LocalDate.parse("2023-08-01"));
        assertThat(arguments.getTo()).isEqualTo(LocalDate.parse("2023-08-31"));
        assertThat(arguments.getFormat()).isEqualTo(ReportFormat.ADOC);
    }

    @Test
    void testArgumentsParseThrows() {
        String[] args = Arrays.copyOfRange(ARGS, 2, 8);
        assertThrows(IllegalArgumentException.class, () -> new Arguments(args));
    }

    @Test
    void testFileResolver() {
        Arguments arguments = new Arguments(ARGS);
        assertThat(FileResolver.getFiles(arguments.getPaths())).isEqualTo(List.of(LOGFILE));
    }

    @AfterAll
    static void deleteFiles() throws IOException {
        Files.delete(Path.of(LOGFILE));
    }
}
