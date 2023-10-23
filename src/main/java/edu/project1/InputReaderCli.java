package edu.project1;

import java.util.Scanner;

public class InputReaderCli implements InputReader {
    private final Scanner scanner;

    public InputReaderCli(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
