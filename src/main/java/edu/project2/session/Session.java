package edu.project2.session;

import edu.project2.generator.Generator;
import edu.project2.generator.Generators;
import edu.project2.maze.Coordinate;
import edu.project2.maze.Maze;
import edu.project2.renderer.MazeRenderer;
import edu.project2.renderer.Renderer;
import edu.project2.solver.Solver;
import edu.project2.solver.Solvers;
import java.lang.reflect.InvocationTargetException;
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

    public Message processUserAction(String input)
        throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException,
        IllegalAccessException {
        return switch (state) {
            case INTRO -> {
                setState(SessionState.SELECT_SIZE);
                yield new Message.SelectSize();
            }
            case SELECT_SIZE -> {
                if (validator.isValidMazeSizeResponse(input)) {
                    var sizes = Arrays.stream(input.split(" ")).map(Integer::parseInt).toList();
                    height = sizes.get(0);
                    width = sizes.get(1);
                    setState(SessionState.SELECT_GENERATOR);
                    yield new Message.SelectGenerator();
                } else {
                    yield new Message.InvalidInput();
                }
            }
            case SELECT_GENERATOR -> {
                if (validator.isValidGeneratorResponse(input)) {
                    Generator generator =
                        getGenerator(Arrays.stream(Generators.values()).toList().get(Integer.parseInt(input) - 1));
                    maze = generator.generate(height, width);
                    setState(SessionState.SELECT_SOLVER);
                    yield new Message.SelectSolver(new StringBuilder(renderer.render(maze)));
                } else {
                    yield new Message.InvalidInput();
                }
            }
            case SELECT_SOLVER -> {
                if (validator.isValidSolverResponse(input)) {
                    Solver solver =
                        getSolver(Arrays.stream(Solvers.values()).toList().get(Integer.parseInt(input) - 1));
                    List<Coordinate> path = solver.solve(
                        maze,
                        new Coordinate(1, 1),
                        new Coordinate(maze.getHeight() - 1 - 1, maze.getWidth() - 1 - 1)
                    );
                    setState(SessionState.AGAIN);
                    yield new Message.Again(new StringBuilder(renderer.render(maze, path)));
                } else {
                    yield new Message.InvalidInput();
                }
            }
            case AGAIN -> {
                if (validator.isValidAgainResponse(input)) {
                    switch (input) {
                        case "y" -> {
                            setState(SessionState.SELECT_SIZE);
                            yield new Message.SelectSize();
                        }
                        case "n" -> {
                            setState(SessionState.QUIT);
                            yield new Message.Quit();
                        }
                        default -> {
                            yield null;
                        }
                    }
                } else {
                    yield new Message.InvalidInput();
                }
            }
            case QUIT -> null;
        };
    }
}
