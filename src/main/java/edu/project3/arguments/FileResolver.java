package edu.project3.arguments;

import java.util.ArrayList;
import java.util.List;

public final class FileResolver {
    private FileResolver() {
    }

    public static List<String> getFiles(List<String> paths) {
        List<String> files = new ArrayList<>();
        for (var path : paths) {
            files.add(path.substring(Math.max(path.lastIndexOf("/"), path.lastIndexOf("\\")) + 1));
        }
        return files;
    }
}
