package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task3.Filter.globMatches;
import static edu.hw6.Task3.Filter.largerThan;
import static edu.hw6.Task3.Filter.magicNumber;
import static edu.hw6.Task3.Filter.readable;
import static edu.hw6.Task3.Filter.regexContains;
import static edu.hw6.Task3.Filter.regularFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AbstractFilterTest {
    @Test
    void testAbstractFilter() throws IOException {
        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(largerThan(100_000))
            .and(magicNumber(0x89, 'P', 'N', 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"));

        try (
            DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/resources/hw6/Task3"), filter)) {
            Set<String> fileSet = new HashSet<>();
            for (var path : entries) {
                fileSet.add(path.getFileName().toString());
            }
            assertThat(fileSet.size()).isEqualTo(1);
            assertThat(fileSet).containsExactly("java-logo.png");
        }
    }
}
