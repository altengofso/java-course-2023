package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class PredefinedDictionary implements Dictionary {
    private final String[] words = {"java", "hello", "world"};

    @Override
    public @NotNull String randomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    @Override
    public String[] getAllWords() {
        return words;
    }
}
