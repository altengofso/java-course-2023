package edu.project2.generator;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AldousBroderMazeGenerator extends AbstractMazeGenerator {
    private final int[][] directions;
    private static final int UPPER_BOUND = 2;
    private static final int LOWER_BOUND = -2;
    private static final int ZERO = 0;

    public AldousBroderMazeGenerator() {
        directions = new int[][] {{ZERO, UPPER_BOUND}, {UPPER_BOUND, ZERO}, {ZERO, LOWER_BOUND}, {LOWER_BOUND, ZERO}};
    }

    @Override
    protected void applyGeneratorAlgorithm() {
        aldousBroderGeneratorAlgorithm();
    }

    private void aldousBroderGeneratorAlgorithm() {
        int neededAmount = (height - 1) / 2 * (width - 1) / 2;
        int currentAmount = 1;
        Coordinate current = new Coordinate(1, 1);
        grid[current.row()][current.col()] = new Cell(current, Cell.Type.PASSAGE);
        while (currentAmount < neededAmount) {
            List<AbstractMap.SimpleEntry<Coordinate, int[]>> coordinateList = new ArrayList<>();
            for (int[] direction : directions) {
                Coordinate coordinate = new Coordinate(
                    current.row() + direction[0],
                    current.col() + direction[1]
                );
                if (coordinate.row() > 0 && coordinate.row() < height && coordinate.col() > 0
                    && coordinate.col() < width) {
                    coordinateList.add(new AbstractMap.SimpleEntry<>(coordinate, direction));
                }
            }
            Collections.shuffle(coordinateList);
            Coordinate targetCoordinate = coordinateList.getFirst().getKey();
            int[] targetDirection = coordinateList.getFirst().getValue();
            if (grid[targetCoordinate.row()][targetCoordinate.col()] == null) {
                grid[targetCoordinate.row()][targetCoordinate.col()] =
                    new Cell(targetCoordinate, Cell.Type.PASSAGE);
                Coordinate passageCoordinate = new Coordinate(
                    current.row() + targetDirection[0] / 2,
                    current.col() + targetDirection[1] / 2
                );
                grid[passageCoordinate.row()][passageCoordinate.col()] =
                    new Cell(passageCoordinate, Cell.Type.PASSAGE);
                currentAmount++;
            }

            current = targetCoordinate;
        }
    }
}
