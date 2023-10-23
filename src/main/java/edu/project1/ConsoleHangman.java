package edu.project1;

import java.io.PrintWriter;
import java.util.Scanner;

public class ConsoleHangman {
    private boolean isGameActive = true;
    private final int maxAttempts = 5;
    private final String giveUpWord = "stop";

    public void run() {
        Dictionary dictionary = new PredefinedDictionary();
        InputReader inputReader = new InputReaderCli(new Scanner(System.in));
        OutputWriter outputWriter = new OutputWriterCli(new PrintWriter(System.out));
        printMessage(new Message.Intro(), outputWriter);
        while (isGameActive) {
            Session session = new Session(dictionary, maxAttempts);
            while (session.isActive()) {
                printMessage(new Message.GuessLetter(session), outputWriter);
                String input = inputReader.read();
                printMessage(tryGuess(session, input), outputWriter);
            }
            if (session.getLastState() == SessionLastState.DEFEAT) {
                printMessage(new Message.Defeat(), outputWriter);
            } else if (session.getLastState() == SessionLastState.WON) {
                printMessage(new Message.Win(), outputWriter);
            }
            printMessage(new Message.PlayAgain(), outputWriter);
            while (session.getLastState().equals(SessionLastState.DEFEAT)
                || session.getLastState().equals(SessionLastState.WON)) {
                String input = inputReader.read();
                printMessage(playAgain(session, input), outputWriter);
            }
        }
        inputReader.close();
        outputWriter.close();
    }

    private Message tryGuess(Session session, String input) {
        if (input.equals(giveUpWord)) {
            return session.giveUp();
        } else if (input.length() == 1) {
            return session.guess(input.charAt(0));
        }
        return session.wrongInput();
    }

    private Message playAgain(Session session, String input) {
        switch (input) {
            case "y" -> {
                return session.playAgain();
            }
            case "n" -> {
                isGameActive = false;
                return session.goodBye();
            }
            default -> {
                return new Message.PlayAgain();
            }
        }
    }

    private void printMessage(Message message, OutputWriter outputWriter) {
        outputWriter.write(message.body());
    }
}
