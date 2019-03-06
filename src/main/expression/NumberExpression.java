package ca.ubc.ece.cpen221.mp4.expression;

public class NumberExpression implements Expression
{
    private double value;

    /**
     * Creates an expression that consists of a single double number
     *
     * @param value the value
     */
    public NumberExpression(double value)
    {
        this.value = value;
    }


    /**
     * Returns the number contained in the NumberExpression
     *
     * @return the double value contained within the number expression
     */
    public double eval()
    {
        return this.value;
    }

    /**
     * Creates a String representation of an arithmetic expression.
     *
     * @return this expression in standard form, suitable for inclusion
     * in a program or text document (e.g., "(2 - 4 * (7 + 2))"). Note
     * that the string can have "unnecessary" parentheses as this (toy)
     * system does not know about operator precedence.
     */
    public String toString()
    {
        return Double.toString(this.value);
    }
}
