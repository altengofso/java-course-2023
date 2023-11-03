package edu.project2.solver;

import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
