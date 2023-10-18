package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConsoleHangmanTest {
    @Test
    @DisplayName("Dictionary contains not empty strings")
    void testDictionary() {
        Dictionary dictionary = new PredefinedDictionary();
        for (String word : dictionary.getAllWords()) {
            assertThat(word.length()).isGreaterThan(0);
        }
    }

    @Test
    @DisplayName("Game correctly change state after successful guess")
    void testGameCorrectlyChangeStateAfterSuccessfulGuess() {
        String hiddenWord = "hello";
        int maxAttempts = 5;
        Session session = new Session(hiddenWord, maxAttempts);
        assertThat(session.getLastState()).isEqualTo(SessionLastState.NONE);
        session.guess('h');
        assertThat(session.getLastState()).isEqualTo(SessionLastState.SUCCESS);
        assertThat(session.getAttempts()).isEqualTo(0);
    }

    @Test
    @DisplayName("Game correctly change state after failed guess")
    void testGameCorrectlyChangeStateAfterFailedGuess() {
        String hiddenWord = "hello";
        int maxAttempts = 5;
        Session session = new Session(hiddenWord, maxAttempts);
        assertThat(session.getLastState()).isEqualTo(SessionLastState.NONE);
        session.guess('?');
        assertThat(session.getLastState()).isEqualTo(SessionLastState.FAIL);
        assertThat(session.getAttempts()).isEqualTo(1);
    }

    @Test
    @DisplayName("Game correctly returns win")
    void testGameCorrectlyReturnsWin() {
        String hiddenWord = "hello";
        int maxAttempts = 5;
        Session session = new Session(hiddenWord, maxAttempts);
        assertThat(session.getLastState()).isEqualTo(SessionLastState.NONE);
        session.guess('h');
        session.guess('e');
        session.guess('l');
        session.guess('o');
        assertThat(session.getLastState()).isEqualTo(SessionLastState.WON);
        assertThat(session.getAttempts()).isEqualTo(0);
    }

    @Test
    @DisplayName("Game correctly returns defeat")
    void testGameCorrectlyReturnsDefeat() {
        Dictionary dictionary = new PredefinedDictionary();
        String hiddenWord = dictionary.randomWord();
        int maxAttempts = 5;
        Session session = new Session(hiddenWord, maxAttempts);
        for (int i = 0; i < maxAttempts; ++i) {
            session.guess('?');
        }
        assertThat(session.isActive()).isFalse();
        assertThat(session.getLastState()).isEqualTo(SessionLastState.DEFEAT);
    }
}
