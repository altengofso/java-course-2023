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
        }

        record Negate(Expr operand) implements Expr {
            @Override
            public double evaluate() {
                return -operand.evaluate();
            }
        }

        record Exponent(Expr base, int exponent) implements Expr {
            @Override
            public double evaluate() {
                return Math.pow(base.evaluate(), exponent);
            }
        }

        record Addition(Expr first, Expr second) implements Expr {
            @Override
            public double evaluate() {
                return first.evaluate() + second.evaluate();
            }
        }

        record Multiplication(Expr first, Expr second) implements Expr {
            @Override
            public double evaluate() {
                return first.evaluate() * second.evaluate();
            }
        }
    }
}
