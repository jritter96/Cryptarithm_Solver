package ca.ubc.ece.cpen221.mp4.expression;

import ca.ubc.ece.cpen221.mp4.operator.BinaryOperator;

public class BinaryOperationExpression implements Expression
{
    private Expression operand1;
    private Expression operand2;
    private BinaryOperator operator;

    /**
     * creates an expression consisting of two expression separated by an operator
     *
     * @param operand1 the first expression
     * @param operand2 the last expression
     * @param operator the operation performed on the two operands
     */
    public BinaryOperationExpression(BinaryOperator operator, Expression operand1, Expression operand2)
    {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }

    /**
     * Evaluates an arithmetic expression.
     *
     * @return the value to which this expression evaluates
     */
    public double eval()
    {
        return this.operator.apply(this.operand1.eval(), this.operand2.eval());
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
        return this.operand1.toString() + " " + this.operator.toString() + " " + this.operand2.toString();
    }
}
