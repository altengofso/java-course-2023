package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FileClonerTest {
    @Test
    void testCloneFile() throws IOException {
        Path path = Path.of("Tinkoff Bank Biggest Secret.txt");
        Files.createFile(path);
        FileCloner fileCloner = new FileCloner();
        assertThat(Files.exists(path)).isTrue();
        Path firstCopy = fileCloner.cloneFile(path);
        assertThat(firstCopy.getFileName().toString()).isEqualTo("Tinkoff Bank Biggest Secret - копия.txt");
        Path secondCopy = fileCloner.cloneFile(path);
        assertThat(secondCopy.getFileName().toString()).isEqualTo("Tinkoff Bank Biggest Secret - копия (2).txt");
        Path copyOfSecondCopy = fileCloner.cloneFile(secondCopy);
        assertThat(copyOfSecondCopy.getFileName().toString()).isEqualTo(
            "Tinkoff Bank Biggest Secret - копия (2) - копия.txt");
        Files.delete(path);
        Files.delete(firstCopy);
        Files.delete(secondCopy);
        Files.delete(copyOfSecondCopy);
    }
}
