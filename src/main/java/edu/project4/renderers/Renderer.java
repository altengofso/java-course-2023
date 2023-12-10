package edu.project4.renderers;

import edu.project4.records.FractalImage;
import edu.project4.records.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;

public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> transformations,
        int samples,
        int iterPerSample,
        int symmetry
    );
}
