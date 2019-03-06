package ca.ubc.ece.cpen221.mp4.expression;

/**
 * DerivativeExpression - The derivative of specific function.
 *
 */
public class DerivativeExpression implements Expression
{
	private final double DELTA_X = 1e-9;
	private Expression fn;
	private VariableExpression x;

	/**
	 * Create an expression representing the derivative of the specified
	 * function with respect to the specified variable.
	 *
	 * @param fn the function whose derivative this expression represents
	 * @param independentVar the variable with respect to which we're
	 * 		  differentiating
	 */
	public DerivativeExpression(Expression fn, VariableExpression independentVar)
	{
		this.fn = fn;
		this.x = independentVar;
	}

	/**
	 * Returns the approximate derivative of the expression it is called on.
	 * The infinitesimal value dx used to calculate the value is arbitrarily set to 10^-9.
	 *
	 * @return the double value of the approximated derivative of an expression
	 */
	public double eval()
	{
		//originalVal used to preserve x value
		double originalVal = this.x.eval();
		//calculate f(x)
		double fx = this.fn.eval();
		this.x.store(this.x.eval() + DELTA_X);
		//calculate f(x + dx)
		double fdx = this.fn.eval();
		//restore x value
		this.x.store(originalVal);
		return (fdx - fx) / DELTA_X;
	}

	/**
	 * Creates a String representation of the derivative of an arithmetic expression.
	 *
	 * @return the derivative of this expression in the form d/dx(f(x)), suitable for inclusion
	 * in a program or text document (e.g., "d/dx(2 - 4 * (7 + 2))"). Note
	 * that the string can have "unnecessary" parentheses as this (toy)
	 * system does not know about operator precedence.
	 */
	public String toString()
	{
		return "d/dx(" + fn.toString() + ")";
	}

	/**
	 * Returns a zero of the specified function using
	 * Newton’s method with approxZero as the initial estimate.
	 *
	 * @param fn the function whose zero is to be found. Newton's method must converge for fn.
	 * @param x the independent variable of the function
	 * @param approxZero initial approximation for the
	 *        zero of the function
	 * @param tolerance how close to zero the returned
	 *        zero has to be
	 */
	public static double newtonsMethod(Expression fn, VariableExpression x, double approxZero, double tolerance)
	{

		x.store(approxZero);
		//check if evaluation of f(x) is within tolerance surrounding 0
		if (Math.abs(fn.eval()) < tolerance)
		{
			return approxZero;
		}
		//if not within tolerance, compute closer zero and reevaluate
		else
		{
			DerivativeExpression fprime = new DerivativeExpression(fn, x);
			double nextZero = approxZero - (fn.eval() / fprime.eval());
			return newtonsMethod(fn, x, nextZero, tolerance);
		}
	}
}
