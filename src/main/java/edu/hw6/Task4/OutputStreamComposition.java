package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public final class OutputStreamComposition {
    private OutputStreamComposition() {
    }

    public static void composeOutputStream(String fileName, String text) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Path.of(fileName));
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter =
                 new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8.newEncoder());
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {
            printWriter.write(text);
            printWriter.flush();
        }
    }
}
