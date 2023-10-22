package edu.hw2;

public final class Task4 {
    private Task4() {
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        StackTraceElement caller = stackTrace[1];
        return new CallingInfo(caller.getClassName(), caller.getMethodName());
    }

    public record CallingInfo(String className, String methodName) {
    }
}
