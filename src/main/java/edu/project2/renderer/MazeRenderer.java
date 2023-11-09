package edu.project2.renderer;

import edu.project2.maze.Cell;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import java.util.List;

public class MazeRenderer implements Renderer {
    private static final char WALL_SYMBOL = '⬛';
    private static final char PASSAGE_SYMBOL = '⬜';
    private static final String START_SYMBOL = "🔺";
    private static final String END_SYMBOL = "🔻";
    private static final String PATH_SYMBOL = "🟢";
    private static final char ENDLINE_SYMBOL = '\n';

    @Override
    public String render(Maze maze) {
        return render(maze, null);
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        START_SYMBOL.codePoints();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); ++i) {
            for (int j = 0; j < maze.getWidth(); ++j) {
                Coordinate coordinate = new Coordinate(i, j);
                if (path != null && path.contains(coordinate)) {
                    if (coordinate.equals(path.getFirst())) {
                        stringBuilder.append(START_SYMBOL);
                    } else if (coordinate.equals(path.getLast())) {
                        stringBuilder.append(END_SYMBOL);
                    } else {
                        stringBuilder.append(PATH_SYMBOL);
                    }
                } else if (maze.getGrid()[i][j].type().equals(Cell.Type.WALL)) {
                    stringBuilder.append(WALL_SYMBOL);
                } else {
                    stringBuilder.append(PASSAGE_SYMBOL);
                }
            }
            stringBuilder.append(ENDLINE_SYMBOL);
        }
        return stringBuilder.toString();
    }
}
