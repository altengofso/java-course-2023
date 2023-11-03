package edu.project2.generator;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DFSMazeGenerator extends AbstractMazeGenerator {
    @Override
    protected void applyGeneratorAlgorithm() {
        dfsGeneratorAlgorithm();
    }

    private void dfsGeneratorAlgorithm() {
        Coordinate startCoordinate = new Coordinate(1, 1);
        grid[1][1] = new Cell(startCoordinate, Cell.Type.PASSAGE);
        Stack<Coordinate> stack = new Stack<>();
        stack.add(startCoordinate);
        while (!stack.empty()) {
            Coordinate current = stack.pop();
            List<AbstractMap.SimpleEntry<Coordinate, int[]>> coordinateList = new ArrayList<>();
            for (int[] direction : directions) {
                Coordinate coordinate = new Coordinate(
                    current.row() + direction[0],
                    current.col() + direction[1]
                );
                if (coordinate.row() > 0 && coordinate.row() < height && coordinate.col() > 0
                    && coordinate.col() < width && grid[coordinate.row()][coordinate.col()] == null) {
                    coordinateList.add(new AbstractMap.SimpleEntry<>(coordinate, direction));
                }
            }
            Collections.shuffle(coordinateList);
            for (var target : coordinateList) {
                var targetCoordinate = target.getKey();
                var targetDirection = target.getValue();
                grid[targetCoordinate.row()][targetCoordinate.col()] =
                    new Cell(targetCoordinate, Cell.Type.PASSAGE);
                Coordinate passageCoordinate = new Coordinate(
                    current.row() + targetDirection[0] / 2,
                    current.col() + targetDirection[1] / 2
                );
                grid[passageCoordinate.row()][passageCoordinate.col()] =
                    new Cell(passageCoordinate, Cell.Type.PASSAGE);
                stack.push(targetCoordinate);
            }
        }
    }
}
