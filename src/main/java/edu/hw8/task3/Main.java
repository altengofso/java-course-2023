package edu.hw8.task3;

import java.util.Map;
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@UtilityClass
public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<String, String> CRACKING_DATABASE = Map.of(
        "a7b915cdc6697b0efa48641034eaa308", "user1",
        "d61f1652fd3fdf3b798c2da9992aad4b", "user2"
    );
    private static final int THREADS_NUMBER = 6;
    private static final int MAX_PASSWORD_LENGTH = 4;

    public static void main(String[] args) {
        PasswordCracker passwordCracker = new PasswordCracker();

        long startTimeSingle = System.nanoTime();
        passwordCracker.crackDatabase(CRACKING_DATABASE, 1, MAX_PASSWORD_LENGTH);
        long finishTimeSingle = System.nanoTime();
        long spentTimeSingle = finishTimeSingle - startTimeSingle;

        long startTimeMulti = System.nanoTime();
        passwordCracker.crackDatabase(CRACKING_DATABASE, THREADS_NUMBER, MAX_PASSWORD_LENGTH);
        long finishTimeMulti = System.nanoTime();
        long spentTimeMulti = finishTimeMulti - startTimeMulti;

        LOGGER.info("Acceleration of cracking database: %f times".formatted((double) spentTimeSingle / spentTimeMulti));
    }
}
