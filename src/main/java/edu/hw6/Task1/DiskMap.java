package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiskMap implements Map<String, String> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String fileName;
    private final Map<String, String> inMemoryMap;

    public DiskMap(String fileName) {
        this.fileName = fileName;
        this.inMemoryMap = new HashMap<>();
        loadFromFile();
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] keyValue = line.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    inMemoryMap.put(key, value);
                }
            }
        } catch (IOException e) {
            LOGGER.info("Error occurred while loading data from file: %s".formatted(e.getMessage()));
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (var entry : inMemoryMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                writer.write(key + ":" + value);
                writer.newLine();
            }
        } catch (IOException e) {
            LOGGER.info("Error occurred while saving data to file: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public int size() {
        return inMemoryMap.size();
    }

    @Override
    public boolean isEmpty() {
        return inMemoryMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return inMemoryMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return inMemoryMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return inMemoryMap.get(key);
    }

    @Override
    public String put(String key, String value) {
        String previousValue = inMemoryMap.put(key, value);
        saveToFile();
        return previousValue;
    }

    @Override
    public String remove(Object key) {
        String removedValue = inMemoryMap.remove(key);
        saveToFile();
        return removedValue;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        inMemoryMap.putAll(m);
        saveToFile();
    }

    @Override
    public void clear() {
        inMemoryMap.clear();
        saveToFile();
    }

    @Override
    public Set<String> keySet() {
        return inMemoryMap.keySet();
    }

    @Override
    public Collection<String> values() {
        return inMemoryMap.values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return inMemoryMap.entrySet();
    }
}
