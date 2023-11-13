package edu.hw5;

import java.util.regex.Pattern;

public final class Task4 {
    private static final String MATCH_PATTERN = "[~!@#$%^&*|]";

    private Task4() {
    }

    public static boolean isPasswordSecure(String password) {
        var pattern = Pattern.compile(MATCH_PATTERN);
        var matcher = pattern.matcher(password);
        return matcher.find();
    }
}
