package ca.ubc.ece.cpen221.mp4.operator;

public class AbsoluteValue implements UnaryOperator
{

    /**
    * Applies the Absolute Value Operator on the number given.
    *
    * @param arg a number to apply the operator on
    * @return the number outputted by the Absolute Value operation given input arg
    */
    public double apply(double arg)
    {
        if (arg < 0)
        {
            arg = -arg;
        }
        return arg;
    }

    /**
     * Creates a String representation of an operator.
     *
     * @return a printable representation of this operator in a form suitable
     * for printing in arithmetic expressions (e.g., "sin", "+").
     */
     public String toString()
     {
        return "abs";
     }
}
