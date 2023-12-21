package edu.hw9.task2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class SearchFilesAndFolders {
    private static final ForkJoinPool POOL = ForkJoinPool.commonPool();

    public static List<Path> listFoldersWithMoreThanNFiles(Path root, int minFilesCount) {
        List<Path> pathList = new ArrayList<>();
        if (isDirectory(root)) {
            pathList = POOL.invoke(new SearchFoldersRecursiveTask(root, minFilesCount));
            POOL.close();
        }
        return pathList;
    }

    public static List<Path> listFilesFilteredByPredicate(Path root, Predicate<Path> predicate) {
        List<Path> pathList = new ArrayList<>();
        if (isDirectory(root)) {
            pathList = POOL.invoke(new SearchFilesRecursiveTask(root, predicate));
            POOL.close();
        }
        return pathList;
    }

    private static boolean isDirectory(Path root) {
        if (!Files.isDirectory(root)) {
            throw new IllegalArgumentException("Root path should be a directory");
        }
        return true;
    }
}
