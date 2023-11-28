package edu.hw6.Task1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DiskMap implements Map<String, String> {
    private static final int BUFFER_SIZE = 1024;
    private final String fileName;
    private final Map<String, String> inMemoryMap;

    public DiskMap(String fileName) {
        this.fileName = fileName;
        this.inMemoryMap = new HashMap<>();
        loadFromFile();
    }

    private void loadFromFile() {
        createDiskMapFileIfNotExists();
        String fileContent = readFileWithLock();
        loadInMemoryMapFromFileContent(fileContent);
    }

    private void createDiskMapFileIfNotExists() {
        if (!Files.exists(Path.of(fileName))) {
            try {
                Files.createFile(Path.of(fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String readFileWithLock() {
        StringBuilder fileContent = new StringBuilder();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             FileChannel fileChannel = fileInputStream.getChannel();
             FileLock lock = fileChannel.lock(0, Long.MAX_VALUE, true)) {
            while (fileChannel.read(buffer) != -1) {
                buffer.flip();
                byte[] chunk = new byte[buffer.limit()];
                buffer.get(chunk);
                fileContent.append(new String(chunk, StandardCharsets.UTF_8));
                buffer.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileContent.toString();
    }

    private void loadInMemoryMapFromFileContent(String fileContent) {
        String[] lines = fileContent.split("\n");
        for (String line : lines) {
            String[] keyValue = line.split(":");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();
                inMemoryMap.put(key, value);
            }
        }
    }

    private void saveToFile() {
        String content = convertInMemoryMapToString();
        writeFileWithLock(content);
    }

    private String convertInMemoryMapToString() {
        StringBuilder content = new StringBuilder();
        for (var entry : inMemoryMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            content.append("%s:%s\n".formatted(key, value));
        }
        return content.toString();
    }

    private void writeFileWithLock(String content) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             FileChannel fileChannel = fileOutputStream.getChannel();
             FileLock lock = fileChannel.lock()) {
            byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            int bytesWritten = 0;
            while (bytesWritten < contentBytes.length) {
                int remainingBytes = contentBytes.length - bytesWritten;
                int chunkSize = Math.min(BUFFER_SIZE, remainingBytes);
                buffer.clear();
                buffer.put(contentBytes, bytesWritten, chunkSize);
                buffer.flip();
                fileChannel.write(buffer);
                bytesWritten += chunkSize;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
