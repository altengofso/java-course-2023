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

    private static int cycleBitShift(int number, int shift) {
        String bitNumber = Integer.toBinaryString(number);
        int neededShift = shift % bitNumber.length();
        int length = bitNumber.length();
        switch (Integer.signum(neededShift)) {
            case 1 -> {
                return Integer.parseInt(bitNumber.substring(length - neededShift, length)
                        + bitNumber.substring(0, length - neededShift), 2);
            }
            case -1 -> {
                return Integer.parseInt(bitNumber.substring(-neededShift, length)
                        + bitNumber.substring(0, -neededShift), 2);
            }
            default -> {
                return Integer.parseInt(bitNumber, 2);
            }
        }
    }
}
