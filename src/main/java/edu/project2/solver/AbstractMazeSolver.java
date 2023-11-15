package edu.project2.solver;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMazeSolver implements Solver {
    protected final int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    protected boolean[][] visited;

    protected List<Coordinate> getPossibleTargets(Coordinate current, Maze maze) {
        List<Coordinate> targets = new ArrayList<>();
        for (int[] direction : directions) {
            Coordinate target = new Coordinate(current.row() + direction[0], current.col() + direction[1]);
            if (isInside(target, maze) && isPassage(target, maze) && !visited[target.row()][target.col()]) {
                targets.add(target);
            }
        }
        return targets;
    }

    protected boolean isInside(Coordinate target, Maze maze) {
        return target.row() >= 0 && target.row() < maze.getHeight()
            && target.col() > 0 && target.col() < maze.getWidth();
    }

    protected boolean isPassage(Coordinate target, Maze maze) {
        return maze.getGrid()[target.row()][target.col()].type().equals(Cell.Type.PASSAGE);
    }
}
