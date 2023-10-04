package edu.hw1;

public final class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] first, int[] second) {
        if (first.length == 0) {
            return true;
        }
        if (first.length != 0 && second.length <= 1) {
            return false;
        }
        return findMin(first) > findMin(second) && findMax(first) < findMax(second);
    }

    public static int findMax(int[] array) {
        int max = array[0];
        for (int number : array) {
            max = number > max ? number : max;
        }
        return max;
    }

    public static int findMin(int[] array) {
        int min = array[0];
        for (int number : array) {
            min = number < min ? number : min;
        }
        return min;
    }
}
