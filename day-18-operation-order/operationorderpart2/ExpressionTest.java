package operationorderpart2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionTest {

    @Test
    public void simple_addition() {
        assertEquals(3, Expression.evaluate("1 + 2"));
    }

    @Test
    public void simple_multiplication() {
        assertEquals(6, Expression.evaluate("2 * 3"));
    }

    @Test
    public void simple_parentheses() {
        assertEquals(3, Expression.evaluate("(1 + 2)"));
    }

    @Test
    public void addition_followed_by_multiplication() {
        assertEquals(9, Expression.evaluate("1 + 2 * 3"));
    }

    @Test
    public void multiplication_followed_by_addition() {
        assertEquals(8, Expression.evaluate("2 * 3 + 1"));
    }

    @Test
    public void addition_followed_by_parentheses() {
        assertEquals(7, Expression.evaluate("1 + (2 * 3)"));
    }

    @Test
    public void multiplication_followed_by_parentheses() {
        assertEquals(6, Expression.evaluate("2 * (1 + 2)"));
    }

    @Test
    public void nested_parentheses() {
        assertEquals(11, Expression.evaluate("2 + (3 + (2 * 3))"));
    }

    @Test
    public void repeated_parentheses() {
        assertEquals(12, Expression.evaluate("(2 * 3) + (2 * 3)"));
    }
}
