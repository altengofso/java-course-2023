package edu.project2.solver;

import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.List;
import java.util.Stack;

public class DFSMazeSolver extends AbstractMazeSolver {
    private final Stack<Coordinate> coordinateStack = new Stack<>();

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
        for (Coordinate target : getPossibleTargets(current, maze)) {
            visited[target.row()][target.col()] = true;
            coordinateStack.push(target);
            var ret = dfs(maze, end);
            if (ret != null) {
                return ret.stream().toList();
            }
        }
        coordinateStack.pop();
        return null;
    }
}
