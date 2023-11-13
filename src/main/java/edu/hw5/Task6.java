package edu.hw5;

import java.util.regex.Pattern;

public final class Task6 {
    private Task6() {
    }

    public static boolean isSubstring(String substring, String string) {
        var pattern = Pattern.compile(substring);
        var matcher = pattern.matcher(string);
        return matcher.find();
    }
}
