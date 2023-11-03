package edu.project2.solver;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFSMazeSolver implements Solver {
    private final int[][] directions;

    public BFSMazeSolver() {
        directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Queue<List<Coordinate>> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];
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
            for (int[] direction : directions) {
                Coordinate target =
                    new Coordinate(current.row() + direction[0], current.col() + direction[1]);
                if (target.row() >= 0 && target.row() < maze.getHeight()
                    && target.col() > 0 && target.col() < maze.getWidth()
                    && !visited[target.row()][target.col()]
                    && maze.getGrid()[target.row()][target.col()].type().equals(Cell.Type.PASSAGE)) {
                    visited[target.row()][target.col()] = true;
                    List<Coordinate> newPath = new ArrayList<>(List.copyOf(currentPath));
                    newPath.add(target);
                    queue.offer(newPath);
                }
            }
        }
        return null;
    }
}
