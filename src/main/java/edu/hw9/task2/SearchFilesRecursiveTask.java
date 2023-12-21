package edu.hw9.task2;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import lombok.SneakyThrows;

public class SearchFilesRecursiveTask extends RecursiveTask<List<Path>> {
    private final Path root;
    private final Predicate<Path> predicate;

    public SearchFilesRecursiveTask(Path root, Predicate<Path> predicate) {
        this.root = root;
        this.predicate = predicate;
    }

    @Override
    @SneakyThrows
    protected List<Path> compute() {
        List<Path> pathList = new ArrayList<>();
        List<SearchFilesRecursiveTask> tasks = new ArrayList<>();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(root)) {
            for (Path path : paths) {
                if (Files.isDirectory(path)) {
                    SearchFilesRecursiveTask task = new SearchFilesRecursiveTask(path, predicate);
                    task.fork();
                    tasks.add(task);
                } else if (Files.isRegularFile(path) && predicate.test(path)) {
                    pathList.add(path);
                }
            }
        }
        for (SearchFilesRecursiveTask task : tasks) {
            pathList.addAll(task.join());
        }
        return pathList;
    }
}
