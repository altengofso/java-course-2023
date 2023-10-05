package edu.hw1;

public final class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(int givenNumber) {
        return isPalindrome(Integer.toString(Math.abs(givenNumber)));
    }

    public static boolean isPalindrome(String number) {
        String reversed = (new StringBuilder(number)).reverse().toString();
        if (number.equals(reversed)) {
            return true;
        } else {
            String descendant = createDescendant(number);
            if (descendant.length() > 1) {
                return isPalindrome(descendant);
            }
        }
        return false;
    }

    public static String createDescendant(String number) {
        StringBuilder descendant = new StringBuilder();
        for (int i = 0; i < number.length() - 1; i += 2) {
            Integer append = (number.charAt(i) - '0') + (number.charAt(i + 1) - '0');
            descendant.append(append.toString());
        }
        if (number.length() % 2 == 1) {
            descendant.append(number.charAt(number.length() - 1));
        }
        return descendant.toString();
    }
}
