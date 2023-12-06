package edu.hw9.task3;

import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.solver.AbstractMazeSolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.SneakyThrows;

public class MultiThreadMazeSolver extends AbstractMazeSolver {
    private final BlockingQueue<List<Coordinate>> queue = new LinkedBlockingQueue<>();
    private final Set<Coordinate> visited = ConcurrentHashMap.newKeySet();
    private final AtomicBoolean isPathFound = new AtomicBoolean(false);
    private List<Coordinate> path;

    @Override
    @SneakyThrows
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Coordinate> coordinateList = new ArrayList<>();
        coordinateList.add(start);
        queue.put(coordinateList);
        visited.add(start);
        try (ExecutorService executorService =
                 Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
            while (!isPathFound.get()) {
                executorService.submit(() -> executorTask(maze, end));
            }
        }
        return path;
    }

    @SneakyThrows
    private void executorTask(Maze maze, Coordinate end) {
        List<Coordinate> currentPath = queue.poll();
        if (currentPath != null) {
            Coordinate current = currentPath.getLast();
            if (current.equals(end)) {
                isPathFound.set(true);
                path = currentPath;
                return;
            }
            for (Coordinate target : getPossibleTargets(current, maze)) {
                visited.add(target);
                List<Coordinate> newPath = new ArrayList<>(List.copyOf(currentPath));
                newPath.add(target);
                queue.put(newPath);
            }
        }
    }

    @Override
    protected List<Coordinate> getPossibleTargets(Coordinate current, Maze maze) {
        List<Coordinate> targets = new ArrayList<>();
        for (int[] direction : directions) {
            Coordinate target = new Coordinate(current.row() + direction[0], current.col() + direction[1]);
            if (isInside(target, maze) && isPassage(target, maze) && !visited.contains(target)) {
                targets.add(target);
            }
        }
        return targets;
    }
}
