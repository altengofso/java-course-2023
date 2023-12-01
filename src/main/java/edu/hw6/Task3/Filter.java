package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.PathMatcher;

public final class Filter {
    public static AbstractFilter regularFile = Files::isRegularFile;
    public static AbstractFilter readable = Files::isReadable;

    public static AbstractFilter largerThan(int size) {
        return entry -> {
            try {
                return Files.size(entry) > size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static AbstractFilter magicNumber(int... magicBytes) {
        return entry -> {
            if (Files.isRegularFile(entry)) {
                try {
                    byte[] bytes = Files.readAllBytes(entry);
                    if (bytes.length >= magicBytes.length) {
                        for (int i = 0; i < magicBytes.length; ++i) {
                            if (bytes[i] != (byte) magicBytes[i]) {
                                return false;
                            }
                        }
                        return true;
                    }
                } catch (IOException e) {
                    return false;
                }
            }
            return false;
        };
    }

    public static AbstractFilter globMatches(String glob) {
        return entry -> {
            if (glob.equals("*")) {
                return true;
            }
            FileSystem fs = entry.getFileSystem();
            PathMatcher matcher = fs.getPathMatcher("glob:" + glob);
            return matcher.matches(entry.getFileName());
        };
    }

    public static AbstractFilter regexContains(String regex) {
        return entry -> entry.getFileName().toString().matches(".*" + regex + ".*");
    }

    private Filter() {
    }
}
