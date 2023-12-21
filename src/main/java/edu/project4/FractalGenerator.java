package edu.project4;

import edu.project4.image.ImageFormat;
import edu.project4.image.ImageProcessor;
import edu.project4.image.ImageUtils;
import edu.project4.records.FractalImage;
import edu.project4.records.Rect;
import edu.project4.renderers.Renderer;
import edu.project4.transformations.Transformation;
import java.nio.file.Path;
import java.util.List;

public class FractalGenerator {
    private final FractalImage canvas;
    private final Renderer renderer;
    private final Rect world;
    private final List<Transformation> transformations;
    private final ImageProcessor imageProcessor;

    public FractalGenerator(
        int width,
        int height,
        Renderer renderer,
        Rect world,
        List<Transformation> transformations,
        ImageProcessor imageProcessor
    ) {
        this.canvas = FractalImage.create(width, height);
        this.renderer = renderer;
        this.world = world;
        this.transformations = transformations;
        this.imageProcessor = imageProcessor;
    }

    public void generate(Path filePath, ImageFormat imageFormat, int samples, int iterPerSample, int symmetry) {
        renderer.render(canvas, world, transformations, samples, iterPerSample, symmetry);
        imageProcessor.process(canvas);
        ImageUtils.save(canvas, filePath, imageFormat);
    }
}
