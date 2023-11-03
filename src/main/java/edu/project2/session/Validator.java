package edu.project2.session;

import edu.project2.generator.Generators;
import edu.project2.solver.Solvers;
import java.util.Arrays;

public class Validator {
    private static final String NUMBER_REGEX = "\\d+";
    private static final String TWO_NUMBERS_REGEX = "\\d+ \\d+";
    private static final char YES = 'y';
    private static final char NO = 'n';

    public boolean isValidMazeSizeResponse(String input) {
        if (!input.matches(TWO_NUMBERS_REGEX)) {
            return false;
        }
        var splitted = Arrays.stream(input.split(" ")).map(Integer::parseInt).toList();
        return splitted.get(0) >= 1 && splitted.get(1) >= 1;
    }

    public boolean isValidGeneratorResponse(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            return false;
        }
        int number = Integer.parseInt(input);
        return number >= 1 && number <= Generators.values().length;
    }

    public boolean isValidSolverResponse(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            return false;
        }
        int number = Integer.parseInt(input);
        return number >= 1 && number <= Solvers.values().length;
    }

    public boolean isValidAgainResponse(String input) {
        return input.length() == 1 && (input.charAt(0) == YES || input.charAt(0) == NO);
    }
}
