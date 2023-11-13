package edu.project2.session;

import edu.project2.generator.Generator;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.renderer.MazeRenderer;
import edu.project2.renderer.Renderer;
import edu.project2.solver.Solver;
import java.util.Arrays;
import java.util.List;
import static edu.project2.generator.GeneratorsUtils.getGenerator;
import static edu.project2.solver.SolversUtils.getSolver;

public class Session {
    private SessionState state;
    private Maze maze;
    private final Renderer renderer;
    private final Validator validator;
    private int height;
    private int width;

    public Session() {
        this.state = SessionState.INTRO;
        this.renderer = new MazeRenderer();
        this.validator = new Validator();
    }

    public SessionState getState() {
        return state;
    }

    public void setState(SessionState state) {
        this.state = state;
    }

    public Message processUserAction(String input) {
        return switch (state) {
            case INTRO -> processIntro();
            case SELECT_SIZE -> processSelectSize(input);
            case SELECT_GENERATOR -> processSelectGenerator(input);
            case SELECT_SOLVER -> processSelectSolver(input);
            case AGAIN -> processAgain(input);
            case QUIT -> null;
        };
    }

    private Message processIntro() {
        setState(SessionState.SELECT_SIZE);
        return new Message.SelectSize();
    }

    private Message processSelectSize(String input) {
        if (validator.isValidMazeSizeResponse(input)) {
            var sizes = Arrays.stream(input.split(" ")).map(Integer::parseInt).toList();
            height = sizes.get(0);
            width = sizes.get(1);
            setState(SessionState.SELECT_GENERATOR);
            return new Message.SelectGenerator();
        } else {
            return new Message.InvalidInput();
        }
    }

    private Message processSelectGenerator(String input) {
        if (validator.isValidGeneratorResponse(input)) {
            Generator generator =
                getGenerator(Integer.parseInt(input));
            maze = generator.generate(height, width);
            setState(SessionState.SELECT_SOLVER);
            return new Message.SelectSolver(new StringBuilder(renderer.render(maze)));
        } else {
            return new Message.InvalidInput();
        }
    }

    private Message processSelectSolver(String input) {
        if (validator.isValidSolverResponse(input)) {
            Solver solver =
                getSolver(Integer.parseInt(input));
            List<Coordinate> path = solver.solve(
                maze,
                new Coordinate(1, 1),
                new Coordinate(maze.getHeight() - 1 - 1, maze.getWidth() - 1 - 1)
            );
            setState(SessionState.AGAIN);
            return new Message.Again(new StringBuilder(renderer.render(maze, path)));
        } else {
            return new Message.InvalidInput();
        }
    }

    private Message processAgain(String input) {
        if (validator.isValidAgainResponse(input)) {
            return switch (input) {
                case "y" -> {
                    setState(SessionState.SELECT_SIZE);
                    yield new Message.SelectSize();
                }
                case "n" -> {
                    setState(SessionState.QUIT);
                    yield new Message.Quit();
                }
                default -> null;
            };
        } else {
            return new Message.InvalidInput();
        }
    }
}
