package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Task0Test {
    @Test
    @DisplayName("Log Message Hello World!")
    void testLogHelloWorld() {
        ByteArrayOutputStream logOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(logOutput));
        System.setErr(new PrintStream(logOutput));

        Task0.logHelloWorld();

        String logMessage = logOutput.toString();
        assertThat(logMessage).contains("Hello, world!");
    }
}
