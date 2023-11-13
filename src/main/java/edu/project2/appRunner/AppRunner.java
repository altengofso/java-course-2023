package edu.project2.appRunner;

import edu.project2.inputReader.InputReader;
import edu.project2.inputReader.InputReaderCli;
import edu.project2.outputWriter.OutputWriter;
import edu.project2.outputWriter.OutputWriterCli;
import edu.project2.session.Message;
import edu.project2.session.Session;
import edu.project2.session.SessionState;
import java.io.PrintWriter;
import java.util.Scanner;

public class AppRunner {
    public void runApp() {
        InputReader inputReader = new InputReaderCli(new Scanner(System.in));
        OutputWriter outputWriter = new OutputWriterCli(new PrintWriter(System.out));
        Session session = new Session();
        printMessage(new Message.Intro(), outputWriter);
        printMessage(session.processUserAction(""), outputWriter);
        while (!session.getState().equals(SessionState.QUIT)) {
            String input = inputReader.read();
            printMessage(session.processUserAction(input), outputWriter);
        }
        inputReader.close();
        outputWriter.close();
    }

    private void printMessage(Message message, OutputWriter outputWriter) {
        outputWriter.write(message.body());
    }
}
