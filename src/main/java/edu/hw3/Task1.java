package edu.hw3;

public final class Task1 {
    private static final char LOWER_A = 'a';
    private static final char UPPER_A = 'A';
    private static final char LOWER_Z = 'z';
    private static final char UPPER_Z = 'Z';

    private Task1() {
    }

    public static String encryptViaAtbash(String input) {
        StringBuilder output = new StringBuilder();
        for (char character : input.toCharArray()) {
            if (character >= LOWER_A && character <= LOWER_Z) {
                output.append((char) (LOWER_A + LOWER_Z - character));
            } else if (character >= UPPER_A && character <= UPPER_Z) {
                output.append((char) (UPPER_A + UPPER_Z - character));
            } else {
                output.append(character);
            }
        }
        return output.toString();
    }
}
