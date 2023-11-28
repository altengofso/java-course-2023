package edu.hw6.Task2;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileCloner {
    private static final Logger LOGGER = LogManager.getLogger();

    @SneakyThrows
    public Path cloneFile(Path path) {
        String fileName = path.getFileName().toString();
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        Path parent = path.getParent() == null ? Path.of("") : path.getParent();
        Set<Path> files = new HashSet<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(
            parent,
            "%s*.%s".formatted(baseName, extension)
        )) {
            for (Path current : stream) {
                if (Files.isRegularFile(current)) {
                    files.add(current);
                }
            }
        }
        Path destPath = path.resolveSibling(baseName + " - копия." + extension);
        int copyCount = 1;
        while (files.contains(destPath)) {
            copyCount++;
            destPath = path.resolveSibling(baseName + " - копия (" + copyCount + ")." + extension);
        }
        Files.copy(path, destPath, StandardCopyOption.COPY_ATTRIBUTES);
        return destPath;
    }
}
