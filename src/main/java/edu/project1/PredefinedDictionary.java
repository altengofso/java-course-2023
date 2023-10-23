package edu.project1;

import java.util.Random;

public class PredefinedDictionary implements Dictionary {
    private final String[] words = {"java", "hello", "world"};

    @Override
    public String randomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    @Override
    public String[] getAllWords() {
        return words;
    }
}
