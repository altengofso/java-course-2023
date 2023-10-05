package edu.hw1;

public final class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int number, int shift) {
        return cycleBitShift(number, -shift);
    }

    public static int rotateRight(int number, int shift) {
        return cycleBitShift(number, shift);
    }

    public static int cycleBitShift(int number, int shift) {
        String bitNumber = Integer.toBinaryString(number);
        int neededShift = shift % bitNumber.length();
        String shifted = new String();
        int length = bitNumber.length();
        if (neededShift > 0) {
            shifted = bitNumber.substring(length - neededShift, length) + bitNumber.substring(0, length - neededShift);
        } else if (neededShift == 0) {
            shifted = bitNumber;
        } else if (neededShift < 0) {
            neededShift *= -1;
            shifted = bitNumber.substring(neededShift, length)
                    + bitNumber.substring(0, neededShift);
        }
        return Integer.parseInt(shifted, 2);
    }
}
