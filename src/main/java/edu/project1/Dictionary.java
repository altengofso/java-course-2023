package edu.project1;

import org.jetbrains.annotations.NotNull;

public interface Dictionary {
    @NotNull
    String randomWord();

    String[] getAllWords();
}
