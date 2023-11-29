package edu.project3;

import edu.project3.apprunner.AppRunner;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class Main {
    public static void main(String[] args) {
        AppRunner appRunner = new AppRunner();
        appRunner.run(args);
    }
}
