package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SearchFilesAndFoldersTest {

    @TempDir
    private static Path tempPath;

    @BeforeAll
    @SneakyThrows
    static void initTempFolderStructure() {
        String[] extensions = {".txt", ".jpg", ".docx", ".xlsx", ".zip"};
        for (int i = 0; i < 5; ++i) {
            Path directory = Files.createDirectory(tempPath.resolve("directory" + i));
            for (int j = 0; j <= i; ++j) {
                Path file = Files.createFile(tempPath.resolve(directory.resolve("file" + j + extensions[j])));
                Files.writeString(file, "test");
                Path subDirectory = Files.createDirectory(directory.resolve("subDirectory" + j));
                for (int k = 0; k <= j; ++k) {
                    Files.createFile(subDirectory.resolve("file" + k + extensions[k]));
                }
            }
        }
    }

    @Test
    void testSearchFilesAndFoldersThrowsWhenFilePathGiven() {
        assertThatThrownBy(() -> SearchFilesAndFolders.listFoldersWithMoreThanNFiles(
            tempPath.resolve("directory0").resolve("file0.txt"), 0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Root path should be a directory");
    }

    @Test
    void testSearchFoldersRecursiveTask() {
        var list = SearchFilesAndFolders.listFoldersWithMoreThanNFiles(tempPath, 10);
        assertThat(list).isEqualTo(List.of(tempPath.resolve("directory4").resolve("subDirectory4")));
    }

    @Test
    void testSearchFilesRecursiveTask() {
        String extension = ".txt";
        int minFileSize = 4;
        Predicate<Path> predicate = path -> {
            try {
                if (Files.size(path) < minFileSize || !path.toString().endsWith(extension)) {
                    return false;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        };
        var list = SearchFilesAndFolders.listFilesFilteredByPredicate(tempPath, predicate);
        assertThat(list.stream().map(path -> path.getFileName().toString()).toList())
            .isEqualTo(Stream.generate(() -> "file0.txt").limit(5).toList());
    }
}
