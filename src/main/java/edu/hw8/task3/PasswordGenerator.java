package edu.hw8.task3;

public class PasswordGenerator {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int length;
    private final int[] indexes;

    public PasswordGenerator(int length) {
        this.length = length;
        this.indexes = new int[length];
    }

    public synchronized String nextPassword() {
        if (hasNextPassword()) {
            StringBuilder password = new StringBuilder();
            for (int index : indexes) {
                password.append(ALPHABET.charAt(index));
            }
            return password.toString();
        }
        return null;
    }

    private boolean hasNextPassword() {
        for (int i = length - 1; i >= 0; i--) {
            if (indexes[i] < ALPHABET.length() - 1) {
                indexes[i]++;
                return true;
            }
            indexes[i] = 0;
        }
        return false;
    }
}
