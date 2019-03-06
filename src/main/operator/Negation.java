package ca.ubc.ece.cpen221.mp4.operator;

public class Negation implements UnaryOperator {
    /**
     * Applies negation on the number given.
     *
     * @param arg a number to apply negation on
     * @return the number outputted by the negation given input arg
     */
    public double apply(double arg)
    {
        return (- arg);
    }

    /**
     * Creates a String representation of an operator.
     *
     * @return a printable representation of this operator in a form suitable
     * for printing in arithmetic expressions (e.g., "sin", "+").
     */
    public String toString()
    {
        return "neg";
    }
}
