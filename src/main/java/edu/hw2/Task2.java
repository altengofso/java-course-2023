package edu.hw2;

public final class Task2 {
    private Task2() {
    }

    public static class Rectangle {
        private int width;
        private int height;

        public Rectangle() {
        }

        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public Rectangle setWidth(int width) {
            this.width = width;
            return this;
        }

        public Rectangle setHeight(int height) {
            this.height = height;
            return this;
        }

        public double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {

        public Square() {
        }

        public Square(int side) {
            super(side, side);
        }

        public Square setSide(int side) {
            return new Square(side);
        }

        @Override
        public Rectangle setWidth(int width) {
            return new Rectangle(width, getHeight());
        }

        @Override
        public Rectangle setHeight(int height) {
            return new Rectangle(getWidth(), height);
        }
    }
}
