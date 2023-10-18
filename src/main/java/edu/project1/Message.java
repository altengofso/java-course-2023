package edu.project1;

import org.jetbrains.annotations.NotNull;

public sealed interface Message {
    @NotNull
    String body();

    record Intro(String body) implements Message {
        Intro() {
            this("""
                 _                                            \s
                | |                                           \s
                | |__   __ _ _ __   __ _ _ __ ___   __ _ _ __ \s
                | '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\\s
                | | | | (_| | | | | (_| | | | | | | (_| | | | |
                |_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|
                                    __/ |                     \s
                                   |___/                      \s

                Guess the letters in a random word.
                If your letter is actually a part of the word it is added to the spelling.
                You can make a mistake no more than 5 times.
                You can give up if you type stop."""
            );
        }
    }

    record GuessLetter(String body) implements Message {
        GuessLetter(Session session) {
            this(
                "The word to guess: " + String.valueOf(session.getUserAnswer()) + "\nGuess a letter:"
            );
        }
    }

    record GiveUp(String body) implements Message {
        GiveUp() {
            this("You couldn't stand the challenge and gave up.");
        }
    }

    record Defeat(String body) implements Message {
        Defeat() {
            this("You lost!");
        }
    }

    record Win(String body) implements Message {
        Win() {
            this("You won!");
        }
    }

    record SuccessfulUserInput(String body) implements Message {
        SuccessfulUserInput() {
            this("Hit!");
        }
    }

    record FailedUserInput(String body) implements Message {
        FailedUserInput(Session session) {
            this("Missed, mistake " + session.getAttempts() + " out of " + session.getMaxAttempts());
        }
    }

    record PlayAgain(String body) implements Message {
        PlayAgain() {
            this("Do you want to play again?(y/n)");
        }
    }

    record NewSession(String body) implements Message {
        NewSession() {
            this("Great! A new word has been made. The rules are the same.");
        }
    }

    record GoodBye(String body) implements Message {
        GoodBye() {
            this("Goodbye!");
        }
    }
}
