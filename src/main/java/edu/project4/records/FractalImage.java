package edu.project4.records;

public record FractalImage(Pixel[][] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[][] data = new Pixel[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                data[i][j] = new Pixel(0, 0, 0, 0, 0);
            }
        }
        return new FractalImage(data, width, height);
    }

    boolean containsPixel(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y <= height;
    }

    public Pixel getPixel(int x, int y) {
        if (containsPixel(x, y)) {
            return data[y][x];
        }
        return null;
    }
}
