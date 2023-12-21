package edu.project4;

import edu.project4.image.ImageFormat;
import edu.project4.image.ImageGammaCorrection;
import edu.project4.records.Rect;
import edu.project4.renderers.MultiThreadRenderer;
import edu.project4.renderers.Renderer;
import edu.project4.renderers.SingleThreadRenderer;
import edu.project4.transformations.HeartTransformation;
import edu.project4.transformations.HyperbolicTransformation;
import edu.project4.transformations.PowerTransformation;
import edu.project4.transformations.SphereTransformation;
import edu.project4.transformations.SwirlTransformation;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class FractalGeneratorTest {
    @TempDir
    private Path path;

    static Stream<Arguments> Renderers() {
        return Stream.of(
            Arguments.of(new SingleThreadRenderer()),
            Arguments.of(new MultiThreadRenderer())
        );
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("Renderers")
    void testFractalGenerator(Renderer renderer) {
        FractalGenerator fractalGenerator = new FractalGenerator(
            30, 30,
            renderer,
            new Rect(-2.5, -3.5, 5, 7.0),
            List.of(
                new HeartTransformation(),
                new HyperbolicTransformation(),
                new PowerTransformation(),
                new SphereTransformation(),
                new SwirlTransformation()
            ),
            new ImageGammaCorrection()
        );
        fractalGenerator.generate(path.resolve("image.png"), ImageFormat.PNG, 100, 100, 1);
        assertThat(path).exists();
        BufferedImage image = ImageIO.read(path.resolve("image.png").toFile());
        assertThat(image.getWidth()).isEqualTo(30);
        assertThat(image.getHeight()).isEqualTo(30);
    }
}
