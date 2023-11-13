package edu.project2.session;

import static edu.project2.generator.GeneratorsUtils.getGeneratorsList;
import static edu.project2.solver.SolversUtils.getSolversList;

public sealed interface Message {
    String body();

    record Intro(String body) implements Message {
        public Intro() {
            this("""
                  ▄▄▄▄███▄▄▄▄      ▄████████  ▄███████▄     ▄████████\s
                ▄██▀▀▀███▀▀▀██▄   ███    ███ ██▀     ▄██   ███    ███\s
                ███   ███   ███   ███    ███       ▄███▀   ███    █▀ \s
                ███   ███   ███   ███    ███  ▀█▀▄███▀▄▄  ▄███▄▄▄    \s
                ███   ███   ███ ▀███████████   ▄███▀   ▀ ▀▀███▀▀▀    \s
                ███   ███   ███   ███    ███ ▄███▀         ███    █▄ \s
                ███   ███   ███   ███    ███ ███▄     ▄█   ███    ███\s
                 ▀█   ███   █▀    ███    █▀   ▀████████▀   ██████████\s
                                                                     \s
                Generate and solve maze with selected algorithms.    \s
                                                                     \s"""
            );
        }
    }

    record SelectSize(String body) implements Message {
        public SelectSize() {
            this("""
                Type the desired size of the maze. For example: 5 7,
                where 5 is the number of horizontal passages, 7 is vertical."""
            );
        }
    }

    record SelectGenerator(String body) implements Message {

        public SelectGenerator() {
            this("Select generator:\n%s".formatted(getGeneratorsList()));
        }
    }

    record SelectSolver(String body) implements Message {
        SelectSolver(StringBuilder builder) {
            this("%s\nSelect solver:\n%s".formatted(builder.toString(), getSolversList()));
        }

    }

    record Again(String body) implements Message {
        Again(StringBuilder builder) {
            this("%s\nWant to play again? (y/n)".formatted(builder.toString()));
        }
    }

    record Quit(String body) implements Message {
        Quit() {
            this("See you!");
        }
    }

    record InvalidInput(String body) implements Message {
        InvalidInput() {
            this("Invalid input. Try again.");
        }
    }
}
