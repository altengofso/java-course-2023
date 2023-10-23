package edu.project1;

public class Session {
    private final String hiddenWord;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;
    private boolean active;
    private SessionLastState lastState;

    Session(Dictionary dictionary, int maxAttempts) {
        this.hiddenWord = dictionary.randomWord();
        this.userAnswer = initializeUserAnswer(hiddenWord);
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        this.active = true;
        this.lastState = SessionLastState.NONE;
    }

    private char[] initializeUserAnswer(String hiddenWord) {
        return "*".repeat(hiddenWord.length()).toCharArray();
    }

    public Message guess(char guess) {
        int found = 0;
        for (int i = 0; i < hiddenWord.length(); ++i) {
            if (hiddenWord.charAt(i) == guess) {
                found = 1;
                userAnswer[i] = guess;
            }
        }
        Message message = switch (found) {
            case 0 -> {
                attempts++;
                lastState = SessionLastState.FAIL;
                yield new Message.FailedUserInput(this);
            }
            case 1 -> {
                lastState = SessionLastState.SUCCESS;
                yield new Message.SuccessfulUserInput();
            }
            default -> null;
        };
        if (attempts == maxAttempts) {
            active = false;
            lastState = SessionLastState.DEFEAT;
        }
        if (hiddenWord.equals(String.valueOf(userAnswer))) {
            active = false;
            lastState = SessionLastState.WON;
        }
        return message;
    }

    public Message wrongInput() {
        lastState = SessionLastState.WRONGINPUT;
        return new Message.WrongInput();
    }

    public Message giveUp() {
        active = false;
        lastState = SessionLastState.DEFEAT;
        return new Message.GiveUp();
    }

    public Message playAgain() {
        lastState = SessionLastState.PLAYAGAIN;
        return new Message.NewSession();
    }

    public Message goodBye() {
        lastState = SessionLastState.GOODBYE;
        return new Message.GoodBye();
    }

    public char[] getUserAnswer() {
        return userAnswer;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public boolean isActive() {
        return active;
    }

    public SessionLastState getLastState() {
        return lastState;
    }
}
