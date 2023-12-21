package edu.project4.image;

import edu.project4.records.FractalImage;
import edu.project4.records.Pixel;

public class ImageGammaCorrection implements ImageProcessor {
    private static final double GAMMA = 2.2;

    @Override
    public void process(FractalImage image) {
        double max = 0;
        for (int row = 0; row < image.width(); ++row) {
            for (int col = 0; col < image.height(); ++col) {
                Pixel pixel = image.getPixel(row, col);
                if (pixel.getHitCount() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHitCount()));
                    max = Math.max(max, pixel.getNormal());
                }
            }
        }
        for (int row = 0; row < image.width(); ++row) {
            for (int col = 0; col < image.height(); ++col) {
                Pixel pixel = image.getPixel(row, col);
                pixel.setNormal(pixel.getNormal() / max);
                pixel.setR((int) (pixel.getR() * Math.pow(pixel.getNormal(), (1.0 / GAMMA))));
                pixel.setG((int) (pixel.getG() * Math.pow(pixel.getNormal(), (1.0 / GAMMA))));
                pixel.setB((int) (pixel.getB() * Math.pow(pixel.getNormal(), (1.0 / GAMMA))));
            }
        }
    }
}
