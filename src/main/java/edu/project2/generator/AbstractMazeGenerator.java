package edu.project2.generator;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;

public abstract class AbstractMazeGenerator implements Generator {
    protected int height;
    protected int width;
    protected Cell[][] grid;
    protected int[][] directions;
    protected static final int UPPER_BOUND = 2;
    protected static final int LOWER_BOUND = -2;
    protected static final int ZERO = 0;

    protected void initialize(int height, int width) {
        this.height = height * 2 + 1;
        this.width = width * 2 + 1;
        this.grid = new Cell[this.height][this.width];
        this.directions =
            new int[][] {{ZERO, UPPER_BOUND}, {UPPER_BOUND, ZERO}, {ZERO, LOWER_BOUND}, {LOWER_BOUND, ZERO}};
        fillWithWalls();
    }

    protected void fillWithWalls() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (i % 2 == 0 || j % 2 == 0) {
                    grid[i][j] = new Cell(new Coordinate(i, j), Cell.Type.WALL);
                }
            }
        }
    }

    protected abstract void applyGeneratorAlgorithm();

    public Maze generate(int height, int width) {
        initialize(height, width);
        applyGeneratorAlgorithm();
        return new Maze(this.height, this.width, this.grid);
    }
}

