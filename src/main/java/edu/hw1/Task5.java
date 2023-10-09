package edu.hw1;

public final class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(int givenNumber) {
        StringBuilder number = new StringBuilder();
        number.append(Math.abs(givenNumber));
        return isPalindrome(number);
    }

    private static boolean isPalindrome(StringBuilder number) {
        boolean equals = true;
        for (int i = 0; i < number.length(); ++i) {
            if (number.charAt(i) != number.charAt(number.length() - i - 1)) {
                equals = false;
            }
        }
        if (equals) {
            return true;
        } else {
            StringBuilder descendant = createDescendant(number);
            if (descendant.length() > 1) {
                return isPalindrome(descendant);
            }
        }
        return false;
    }

    private static StringBuilder createDescendant(StringBuilder number) {
        StringBuilder descendant = new StringBuilder();
        for (int i = 0; i < number.length() - 1; i += 2) {
            int append = (number.charAt(i) - '0') + (number.charAt(i + 1) - '0');
            descendant.append(append);
        }
        if (number.length() % 2 == 1) {
            descendant.append(number.charAt(number.length() - 1));
        }
        return descendant;
    }
}
