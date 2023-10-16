package edu.hw2;

import edu.hw2.Task1.Expr.Addition;
import edu.hw2.Task1.Expr.Constant;
import edu.hw2.Task1.Expr.Exponent;
import edu.hw2.Task1.Expr.Multiplication;
import edu.hw2.Task1.Expr.Negate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task1Test {
    @Test
    @DisplayName("Expression Calculator")
    void testExpressionCalculator() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var multi = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(multi, 2);
        var res = new Addition(exp, new Constant(1));
        assertThat(res.evaluate()).isEqualTo(37.0);
        assertThat(res.toString()).isEqualTo("Addition[first=Exponent[base=Multiplication" +
            "[first=Addition[first=Constant[value=2.0], second=Constant[value=4.0]], " +
            "second=Negate[operand=Constant[value=1.0]]], exponent=2], second=Constant[value=1.0]]");
    }
}
