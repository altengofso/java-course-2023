package edu.hw2;

public final class Task2 {
    private Task2() {
    }

    public static class Rectangle {
        private int width;
        private int height;

        public Rectangle() {
            width = 0;
            height = 0;
        }

        public final int getWidth() {
            return width;
        }

        public final int getHeight() {
            return height;
        }

        public final void setWidth(int width) {
            this.width = width;
        }

        public final void setHeight(int height) {
            this.height = height;
        }

        public double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {
        @Override
        public double area() {
            if (getHeight() * getWidth() == 0) {
                return Math.max(Math.pow(getHeight(), 2), Math.pow(getWidth(), 2));
            }
            return super.area();
        }
    }
}
