package edu.project3;

import edu.project3.apprunner.AppRunner;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        AppRunner appRunner = new AppRunner();
        appRunner.run(args);
    }
}
