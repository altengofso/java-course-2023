package edu.hw9.task2;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import lombok.SneakyThrows;

public class SearchFoldersRecursiveTask extends RecursiveTask<List<Path>> {
    private final Path root;
    private final int minFilesCount;
    private int currentFilesCount;

    public SearchFoldersRecursiveTask(Path root, int minFilesCount) {
        this.root = root;
        this.minFilesCount = minFilesCount;
        this.currentFilesCount = 0;
    }

    public SearchFoldersRecursiveTask(Path root, int minFilesCount, int currentFilesCount) {
        this.root = root;
        this.minFilesCount = minFilesCount;
        this.currentFilesCount = currentFilesCount;
    }

    @Override
    @SneakyThrows
    protected List<Path> compute() {
        List<Path> pathList = new ArrayList<>();
        List<SearchFoldersRecursiveTask> tasks = new ArrayList<>();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(root)) {
            for (Path path : paths) {
                if (Files.isRegularFile(path)) {
                    currentFilesCount++;
                } else if (Files.isDirectory(path)) {
                    SearchFoldersRecursiveTask task =
                        new SearchFoldersRecursiveTask(path, minFilesCount, currentFilesCount);
                    task.fork();
                    tasks.add(task);
                }
            }
        }
        for (SearchFoldersRecursiveTask task : tasks) {
            pathList.addAll(task.join());
        }
        if (currentFilesCount >= minFilesCount) {
            pathList.add(root);
        }
        return pathList;
    }
}
