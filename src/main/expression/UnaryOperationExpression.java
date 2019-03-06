package ca.ubc.ece.cpen221.mp4.expression;

import ca.ubc.ece.cpen221.mp4.operator.UnaryOperator;

public class UnaryOperationExpression implements Expression
{
    private Expression operand;
    private UnaryOperator operator;

    /**
     * Constructs a unary operation expression consisting of a single operand being operated upon by an operator
     *
     * @param operand
     *            the name of the variable
     * @param operator
     *            the unary operator applied to the variable
     */
    public UnaryOperationExpression(UnaryOperator operator, Expression operand)
    {
        this.operator = operator;
        this.operand = operand;
    }

    /**
     * Evaluates an arithmetic expression.
     *
     * @return the value to which this expression evaluates
     */
    public double eval()
    {
        return operator.apply(operand.eval());
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
        return operator.toString() + " " + operand.toString();
    }
}
