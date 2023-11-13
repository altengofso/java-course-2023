package edu.project2.solver;

import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFSMazeSolver extends AbstractMazeSolver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Queue<List<Coordinate>> queue = new ArrayDeque<>();
        visited = new boolean[maze.getHeight()][maze.getWidth()];
        List<Coordinate> coordinateList = new ArrayList<>();
        coordinateList.add(start);
        queue.offer(coordinateList);
        visited[start.row()][start.col()] = true;
        while (!queue.isEmpty()) {
            List<Coordinate> currentPath = queue.poll();
            Coordinate current = currentPath.getLast();
            if (current.equals(end)) {
                return currentPath;
            }
            for (Coordinate target : getPossibleTargets(current, maze)) {
                visited[target.row()][target.col()] = true;
                List<Coordinate> newPath = new ArrayList<>(List.copyOf(currentPath));
                newPath.add(target);
                queue.offer(newPath);
            }
        }
        return null;
    }
}
