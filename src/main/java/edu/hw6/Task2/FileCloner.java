package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileCloner {
    private static final Logger LOGGER = LogManager.getLogger();

    public Path cloneFile(Path path) {
        String fileName = path.getFileName().toString();
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        Path destPath = path.resolveSibling(baseName + " - копия" + extension);
        int copyCount = 1;
        while (Files.exists(destPath)) {
            copyCount++;
            destPath = path.resolveSibling(baseName + " - копия (" + copyCount + ")" + extension);
        }
        try {
            Files.copy(path, destPath, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            LOGGER.info("Failed to create a copy of the file: %s".formatted(e.getMessage()));
        }
        return destPath;
    }
}
