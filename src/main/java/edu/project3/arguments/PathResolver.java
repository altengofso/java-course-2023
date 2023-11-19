package edu.project3.arguments;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public final class PathResolver {
    private PathResolver() {
    }

    public static List<String> getPathList(String path) {
        if (path.contains("://") || new File(path).exists()) {
            return List.of(path);
        }
        return getAllPaths(path);
    }

    private static List<String> getAllPaths(String path) {
        List<String> paths = new ArrayList<>();
        Path start = getStartPath(path);
        String pattern = "glob:" + path.replaceAll("\\\\", "/");
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher(pattern);
        try {
            Files.walkFileTree(start, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (matcher.matches(path)) {
                        paths.add(path.toString());
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return paths;
    }

    private static Path getStartPath(String path) {
        int firstAsteriskIndex = path.indexOf("*");
        if (firstAsteriskIndex != -1) {
            int lastSlashIndex =
                Math.max(path.lastIndexOf("/", firstAsteriskIndex), path.lastIndexOf("\\", firstAsteriskIndex));
            if (lastSlashIndex != -1) {
                return Path.of(path.substring(0, lastSlashIndex + 1));
            }
        }
        return Path.of("");
    }
}
