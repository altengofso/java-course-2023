package edu.hw5;

public final class Task8 {

    public static boolean isOddLength(String string) {
        return string.matches("^[01](?:[01]{2})*$");
    }

    public static boolean isZeroOddLengthOrOneEvenLength(String string) {
        return string.matches("^0(?:[01]{2})*$|^1[01](?:[01]{2})*$");
    }

    public static boolean isZerosMultipleOfThree(String string) {
        return string.matches("^(?:1*01*01*01*)+$");
    }

    public static boolean notEquals11Or111(String string) {
        return string.matches("^$|^[01]$|^[01]{2}(?<!11)$|^[01]{3}(?<!111)$|^[01]{4,}$");
    }

    public static boolean isEveryEvenEqualsOne(String string) {
        return string.matches("^1(?:[01]1)*[01]?$");
    }

    public static boolean isZerosTwoAndMoreOnesNotMoreOne(String string) {
        return string.matches("^00+10*$|^0*100+$|^0+10+$|^00+$");
    }

    public static boolean noConsecutiveOnes(String string) {
        return string.matches("^[01]*(?<!11)$");
    }

    private Task8() {
    }
}
