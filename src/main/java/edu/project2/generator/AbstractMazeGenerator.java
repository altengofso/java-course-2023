package edu.project2.generator;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;

public abstract class AbstractMazeGenerator implements Generator {
    protected static final int UPPER_BOUND = 2;
    protected static final int LOWER_BOUND = -2;
    protected static final int ZERO = 0;
    protected static int[][] directions =
        new int[][] {{ZERO, UPPER_BOUND}, {UPPER_BOUND, ZERO}, {ZERO, LOWER_BOUND}, {LOWER_BOUND, ZERO}};

    protected Cell[][] initializeGrid(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (i % 2 == 0 || j % 2 == 0) {
                    grid[i][j] = new Cell(new Coordinate(i, j), Cell.Type.WALL);
                }
            }
        }
        return grid;
    }

    protected abstract void applyGeneratorAlgorithm(Cell[][] grid);

    public Maze generate(int height, int width) {
        int gridHeight = height * 2 + 1;
        int gridWidth = width * 2 + 1;
        Cell[][] grid = initializeGrid(gridHeight, gridWidth);
        applyGeneratorAlgorithm(grid);
        return new Maze(gridHeight, gridWidth, grid);
    }
}

