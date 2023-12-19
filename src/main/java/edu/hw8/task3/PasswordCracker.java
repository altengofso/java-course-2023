package edu.hw8.task3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class PasswordCracker {
    private Map<String, String> crackingDatabase;
    private final Map<String, String> crackedDatabase;

    public PasswordCracker() {
        this.crackedDatabase = new ConcurrentHashMap<>();
    }

    public Map<String, String> crackDatabase(
        Map<String, String> passwordDatabase,
        int threadsCount,
        int maximumPasswordLength
    ) {
        crackingDatabase = new ConcurrentHashMap<>(passwordDatabase);
        ExecutorService threadPool = Executors.newFixedThreadPool(threadsCount);
        for (int length = 1; length <= maximumPasswordLength; length++) {
            PasswordGenerator passwordGenerator = new PasswordGenerator(length);
            for (int i = 0; i <= threadsCount; i++) {
                threadPool.execute(() -> crackPasswords(passwordGenerator));
            }
        }
        threadPool.close();
        return crackedDatabase;
    }

    private void crackPasswords(PasswordGenerator passwordGenerator) {
        String password = "";
        while ((password = passwordGenerator.nextPassword()) != null && !crackingDatabase.isEmpty()) {
            String hash = md5(password);
            if (crackingDatabase.containsKey(hash)) {
                crackedDatabase.put(crackingDatabase.get(hash), password);
                crackingDatabase.remove(hash);
            }
        }
    }

    private String md5(String password) {
        return md5Hex(password);
    }
}
