package edu.hw3;

public final class Task1 {
    private Task1() {
    }

    public static String atbash(String input) {
        StringBuilder output = new StringBuilder();
        for (char character : input.toCharArray()) {
            if (character >= 'a' && character <= 'z') {
                output.append((char) ('a' + 'z' - character));
            } else if (character >= 'A' && character <= 'Z') {
                output.append((char) ('A' + 'Z' - character));
            } else {
                output.append(character);
            }
        }
        return output.toString();
    }
}
