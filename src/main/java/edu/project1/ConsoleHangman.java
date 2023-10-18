package edu.project1;

import java.io.PrintWriter;
import java.util.Scanner;

public class ConsoleHangman {
    private boolean active = true;
    private final int maxAttempts = 5;

    public void run() {
        Dictionary dictionary = new PredefinedDictionary();
        InputReader inputReader = new InputReaderCli(new Scanner(System.in));
        OutputWriter outputWriter = new OutputWriterCli(new PrintWriter(System.out));
        printMessage(new Message.Intro(), outputWriter);
        while (active) {
            String hiddenWord = dictionary.randomWord();
            Session session = new Session(hiddenWord, maxAttempts);
            while (session.isActive()) {
                printMessage(new Message.GuessLetter(session), outputWriter);
                String input = inputReader.read();
                printMessage(tryGuess(session, input), outputWriter);
            }
            switch (session.getLastState()) {
                case SessionLastState.DEFEAT -> printMessage(new Message.Defeat(), outputWriter);
                case SessionLastState.WON -> printMessage(new Message.Win(), outputWriter);
                default -> {
                }
            }
            printMessage(new Message.PlayAgain(), outputWriter);
            while (session.getLastState().equals(SessionLastState.DEFEAT)
                || session.getLastState().equals(SessionLastState.WON)) {
                String input = inputReader.read();
                printMessage(playAgain(session, input), outputWriter);
            }
        }
    }

    private Message tryGuess(Session session, String input) {
        if (input.equals("stop")) {
            return session.giveUp();
        } else if (input.length() == 1) {
            return session.guess(input.charAt(0));
        }
        return new Message.GuessLetter(session);
    }

    private Message playAgain(Session session, String input) {
        switch (input) {
            case "y" -> {
                return session.playAgain();
            }
            case "n" -> {
                active = false;
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
