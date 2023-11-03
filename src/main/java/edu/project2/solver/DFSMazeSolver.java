package edu.project2.solver;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.List;
import java.util.Stack;

public class DFSMazeSolver implements Solver {
    private final int[][] directions;
    private boolean[][] visited;
    private final Stack<Coordinate> coordinateStack;

    public DFSMazeSolver() {
        directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        coordinateStack = new Stack<>();
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        visited = new boolean[maze.getHeight()][maze.getWidth()];
        visited[start.row()][start.col()] = true;
        coordinateStack.add(start);
        dfs(maze, end);
        return coordinateStack.stream().toList();

    }

    private List<Coordinate> dfs(Maze maze, Coordinate end) {
        Coordinate current = coordinateStack.peek();
        if (current.equals(end)) {
            return coordinateStack.stream().toList();
        }
        for (int[] direction : directions) {
            Coordinate target = new Coordinate(current.row() + direction[0], current.col() + direction[1]);
            if (target.row() >= 0 && target.row() < maze.getHeight()
                && target.col() > 0 && target.col() < maze.getWidth()
                && !visited[target.row()][target.col()]
                && maze.getGrid()[target.row()][target.col()].type().equals(Cell.Type.PASSAGE)) {
                visited[target.row()][target.col()] = true;
                coordinateStack.push(target);
                var ret = dfs(maze, end);
                if (ret != null) {
                    return ret.stream().toList();
                }
            }
        }
        coordinateStack.pop();
        return null;
    }
}
