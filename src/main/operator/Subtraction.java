package ca.ubc.ece.cpen221.mp4.operator;

public class Subtraction implements BinaryOperator
{

    /**
     * Applies subtraction on the two numbers given.
     *
     * @param arg1 the first number before the subtraction operator
     * @param arg2 the second number after the subtraction operator
     * @return the output of the subtraction given inputs arg1 and arg2
     */
    public double apply(double arg1, double arg2)
    {
        return (arg1 - arg2);
    }

    /**
     * Creates a String representation of an operator.
     *
     * @return a printable representation of this operator in a form suitable
     * for printing in arithmetic expressions (e.g., "sin", "+").
     */
    public String toString()
    {
        return "-";
    }
}
