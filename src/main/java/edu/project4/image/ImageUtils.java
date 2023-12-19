package edu.project4.image;

import edu.project4.records.FractalImage;
import edu.project4.records.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ImageUtils {
    @SneakyThrows
    public static void save(FractalImage image, Path filename, ImageFormat format) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.width(); ++i) {
            for (int j = 0; j < image.height(); ++j) {
                Pixel pixel = image.getPixel(i, j);
                Color color = new Color(pixel.getR(), pixel.getG(), pixel.getB());
                bufferedImage.setRGB(i, j, color.getRGB());
            }
        }
        ImageIO.write(bufferedImage, format.name(), filename.toFile());
    }
}
