package ca.ubc.ece.cpen221.mp4.operator;

import java.io.IOException;

public class Division implements BinaryOperator
{
    /**
     * Applies the Division Operator on the two numbers given.
     *
     * @param arg1 the first number before the operator
     * @param arg2 the second number after the operator
     * @return the output of the operation given inputs arg1 and arg2
     */
    public double apply(double arg1, double arg2)
    {
        return arg1 / arg2;
    }

    /**
     * Creates a String representation of the Division operator.
     *
     * @return a printable representation of this operator in a form suitable
     * for printing in arithmetic expressions.
     */

    public String toString()
    {
        return "/";
    }
}
