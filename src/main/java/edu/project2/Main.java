package edu.project2;

import edu.project2.appRunner.AppRunner;
import java.lang.reflect.InvocationTargetException;

public final class Main {
    public static void main(String[] args)
        throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException,
        IllegalAccessException {
        AppRunner appRunner = new AppRunner();
        appRunner.runApp();
    }

    private Main() {
    }
}
