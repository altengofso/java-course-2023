package edu.hw1;

public final class Task4 {
    private Task4() {
    }

    public static String fixString(String givenString) {
        StringBuilder outString = new StringBuilder();
        for (int i = 0; i < givenString.length() - 1; i += 2) {
            outString.append(givenString.charAt(i + 1)).append(givenString.charAt(i));
        }
        if (givenString.length() % 2 == 1) {
            outString.append(givenString.charAt(givenString.length() - 1));
        }
        return outString.toString();
    }
}
