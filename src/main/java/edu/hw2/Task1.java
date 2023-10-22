package edu.hw2;

public final class Task1 {
    private Task1() {
    }

    public sealed interface Expr {
        double evaluate();

        record Constant(double value) implements Expr {

            @Override
            public double evaluate() {
                return value;
            }

            @Override
            public String toString() {
                return "%s".formatted(value);
            }
        }

        record Negate(Expr operand) implements Expr {
            @Override
            public double evaluate() {
                return -operand.evaluate();
            }

            @Override
            public String toString() {
                return "-%s".formatted(operand);
            }
        }

        record Exponent(Expr base, int exponent) implements Expr {
            @Override
            public double evaluate() {
                return Math.pow(base.evaluate(), exponent);
            }

            @Override
            public String toString() {
                return "(%s)^%s".formatted(base, exponent);
            }
        }

        record Addition(Expr first, Expr second) implements Expr {
            @Override
            public double evaluate() {
                return first.evaluate() + second.evaluate();
            }

            @Override
            public String toString() {
                return "%s + %s".formatted(first, second);
            }
        }

        record Multiplication(Expr first, Expr second) implements Expr {
            @Override
            public double evaluate() {
                return first.evaluate() * second.evaluate();
            }

            @Override
            public String toString() {
                return "(%s) * %s".formatted(first, second);
            }
        }
    }
}
