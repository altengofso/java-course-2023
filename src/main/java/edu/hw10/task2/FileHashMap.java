package edu.hw10.task2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;

public class FileHashMap {
    private final Map<String, Object> map;
    private final String file;

    @SneakyThrows
    public FileHashMap(String file) {
        this.file = file;
        if (Files.exists(Path.of(file))) {
            map = getFromFile(file);
        } else {
            Files.createFile(Path.of(file));
            map = new HashMap<>();
        }
    }

    public boolean containsKey(String k) {
        return map.containsKey(k);
    }

    public Object get(String k) {
        return map.get(k);
    }

    public Object put(String k, Object v) {
        Object vOld = map.put(k, v);
        storeToFile(map, file);
        return vOld;
    }

    @SneakyThrows
    private void storeToFile(Map<String, Object> map, String file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(map);
        }
    }

    @SneakyThrows
    private HashMap<String, Object> getFromFile(String file) {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            //noinspection unchecked
            return (HashMap<String, Object>) objectInputStream.readObject();
        }
    }
}
