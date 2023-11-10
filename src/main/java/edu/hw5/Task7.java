package edu.hw5;

import java.util.regex.Pattern;

public final class Task7 {

    public static boolean isAtLeastThreeCharsAndThirdZero(String string) {
        return matchPatter(string, "^[01]{2}0[01]*$");
    }

    public static boolean isFirstCharEqualsToLast(String string) {
        return matchPatter(string, "^(0|1)[01]*\\1$|^(?:0|1)$");
    }

    public static boolean isNotLessOneNoMoreThree(String string) {
        return matchPatter(string, "^[01]{1,3}$");
    }

    private static boolean matchPatter(String string, String pattern) {
        var p = Pattern.compile(pattern);
        var m = p.matcher(string);
        return m.find();
    }

    private Task7() {
    }
}
