package edu.hw6.Task4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task4.OutputStreamComposition.composeOutputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class OutputStreamCompositionTest {
    @Test
    void testOutputStreamComposition() throws IOException {
        String fileName = "test.txt";
        String text = "Programming is learned by writing programs. ― Brian Kernighan";
        composeOutputStream(fileName, text);
        assertThat(Files.exists(Path.of(fileName))).isTrue();
        assertThat(Files.readAllLines(Path.of(fileName))).isEqualTo(List.of(text));
        Files.delete(Path.of(fileName));
    }
}
