package ca.ubc.ece.cpen221.mp4.cryptarithm;

import ca.ubc.ece.cpen221.mp4.expression.*;
import ca.ubc.ece.cpen221.mp4.operator.*;

import java.util.*;

import static ca.ubc.ece.cpen221.mp4.permutation.Permutation.getAllPerms;

/**
 * Cryptarithm - a datatype that represents a cryptarithm
 *
 */
public class Cryptarithm
{

	private String[] Cryptarithm;

	/**
	 * Cryptarithm constructor
	 * 
	 * @param cryptarithm
	 *            where each element is a String that represents part of the
	 *            cryptarithm
	 */
	public Cryptarithm(String[] cryptarithm) // constructor for cryptarithm
	{
		this.Cryptarithm = cryptarithm;
	}

	/**
	 * Find solutions to the cryptarithm
	 * 
	 * @return a list of all possible solutions to the given cryptarithm. A
	 *         solution is a map that provides the value for each alphabet in
	 *         the cryptarithm.
	 */
	public List<Map<Character, Integer>> solve() throws NoSolutionException
	{
		int i, j, k, MaxVars = 10, eqIndex = 0;
		Expression finalArgExpression; // the expression containing the entire argument of the cryptarithm
		Expression finalAnsExpression; // the expression containing the entire answer of the cryptarithm
		List<Character> vars =  new ArrayList<>(); // list to hold the each character
		List<Character> firstLets = new ArrayList<>(); // list to the characters that are the first letters of words
		List<VariableExpression> varList = new ArrayList<>(); // list to hold the variables used in the cryptarithm
		List<Map<Character, Integer>> allSolutions = new ArrayList<>(); // list to hold the solutions for the cryptarithm
		ArrayList<Integer[]> permutations = getAllPerms(new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}); // list containing all possible permutations of the integers 0 - 9
		List<String> argument = new ArrayList<>(); // list to hold the word elements of the argument
		List<String> argOperators = new ArrayList<>(); // list to hold the operators of the argument
		List<String> answer = new ArrayList<>(); // list to hold the words of the answer
		List<String> ansOperators = new ArrayList<>(); // list to hold the operators of the answer
		List<BinaryOperator> operators = new ArrayList<>(); // list to hold the valid operators
		operators.add(new Addition()); // adding the operators to the list
		operators.add(new Subtraction());
		operators.add(new Multiplication());
		operators.add(new Division());

		for (i = 0; i < Cryptarithm.length; i += 2) // iterating over the even elements of Cryptarithm (aka just the words)
		{
			for (j = 0; j < Cryptarithm[i].length(); j++) // iterating over each letter of the current word
			{
				Character c = Cryptarithm[i].charAt(j); // assign the current character of the current word to c
				if (Character.isAlphabetic(c)) // check if c is a letter, if not throw an exception
				{
					if (!vars.contains(c)) // check whether c is already in the list of characters used
					{
						vars.add(c); // add c to the list of characters
						VariableExpression var = new VariableExpression(Character.toString(c)); // create a new variable expression labelled c
						varList.add(var); // add the variable expression to the list of variables
					}
					if (j == 0 && !firstLets.contains(c)) // if c is the first letter of the current word and not already in the list of first letters
					{
						firstLets.add(c); // add c to the list
					}
				}
				else
				{
					throw new IllegalArgumentException("Invalid Cryptarithm");
				}
			}
		}
		if (vars.size() > MaxVars) // if there are more than 10 letters used throw an exception
		{
			throw new IllegalArgumentException("Invalid Cryptarithm");
		}
		for (i = 0; i < Cryptarithm.length; i++) // iterate over the elements of Cryptarithm
		{
			if (Cryptarithm[i].equals("=")) // if the current element is the =, save the index and break out of the loop
			{
				eqIndex = i;
				break;
			}
			else if (Cryptarithm[i].equals("+") || Cryptarithm[i].equals("-") || Cryptarithm[i].equals("*") || Cryptarithm[i].equals("/")) // if the current element is an operator
			{
				argOperators.add(Cryptarithm[i]); // add the current element to the argument's operator list
			}
			else // if the current element is a word
			{
				argument.add(Cryptarithm[i]); // add the current element to the argument's word list
			}
		}
		for (i = eqIndex + 1; i < Cryptarithm.length; i++) // iterate over the elements of Cryptharithm after the =
		{

			if (Cryptarithm[i].equals("+") || Cryptarithm[i].equals("-") || Cryptarithm[i].equals("*") || Cryptarithm[i].equals("/")) // if the current element is an operator
			{
				ansOperators.add(Cryptarithm[i]); // add the current element to the answer's operator list
			}
			else // if the current element is a word
			{
				answer.add(Cryptarithm[i]); // add the current element to the answer's word list
			}
		}
		List<BinaryOperationExpression> argExpressions = new ArrayList<>(); // list to hold the expressions that make up each word of the argument of the cryptarithm
		for (i = 0; i < argument.size(); i++) // iterate over the words in argument
		{
			k = argument.get(i).length() - 1; // assign k as one less than the length of the sword
			List<BinaryOperationExpression> digitExpressions = new ArrayList<>(); // temporary list to hold the multiplication expressions for each letter
			for (j = 0; j < argument.get(i).length(); j++) // iterates over the letters of the current word
			{
				Expression powOfTen = new NumberExpression(Math.pow(10, k)); // creates a number expression for the power of 10 value corresponding to the digit that the letter represents in the word
				Character c = argument.get(i).charAt(j); // assign the current character to c
				digitExpressions.add(new BinaryOperationExpression(operators.get(2), powOfTen, getVar(c, varList))); // create a new expression multiplying the value of the letter by the appropriate power of 10
				k--; // decrement k for the next letter
			}
			List<BinaryOperationExpression> digitAddition = new ArrayList<>(); // temporary list to hold the building up addition expression that will create a single word
			if (argument.get(i).length() == 1) // if the word is only 1 letter long
			{
				argExpressions.add(digitExpressions.get(0)); // add the words one expression to the list of words for the argument
			}
			else
			{
				for (j = 1; j < digitExpressions.size(); j++) // iterates over the elements of digit expression
				{
					if (j == 1) // for the first expression
					{
						BinaryOperationExpression initialExpression = new BinaryOperationExpression(operators.get(0), digitExpressions.get(0), digitExpressions.get(1)); // create new expression adding the first and second letters's expressions
						digitAddition.add(initialExpression); // add the expression to the list
					}
					else
					{
						BinaryOperationExpression nextDigitExpression = new BinaryOperationExpression(operators.get(0), digitAddition.get(j - 2), digitExpressions.get(j)); // create a new expression adding the previously created expression and the next letter's expression
						digitAddition.add(nextDigitExpression); // add the expression to the list
					}
				}
				argExpressions.add(digitAddition.get(digitAddition.size() - 1)); // add the finished word (the last element of the temporary list because each element is building on the previous one) to the list of words for the argument
			}
		}
		List<BinaryOperationExpression> ansExpressions = new ArrayList<>(); // list to hold the expressions that make up each word of the answer of the cryptarithm
		for (i = 0; i < answer.size(); i++) // iterate over the words in answer
		{
			k = answer.get(i).length() - 1; // assign k as one less than the length of the sword
			List<BinaryOperationExpression> digitExpressions = new ArrayList<>(); // temporary list to hold the multiplication expressions for each letter
			for (j = 0; j < answer.get(i).length(); j++) // iterates over the letters of the current word
			{
				Expression powOfTen = new NumberExpression(Math.pow(10, k)); // creates a number expression for the power of 10 value corresponding to the digit that the letter represents in the word
				Character c = answer.get(i).charAt(j); // assign the current character to c
				digitExpressions.add(new BinaryOperationExpression(operators.get(2), powOfTen, getVar(c, varList))); // create a new expression multiplying the value of the letter by the appropriate power of 10
				k--; // decrement k for the next letter
			}
			List<BinaryOperationExpression> digitAddition = new ArrayList<>(); // temporary list to hold the building up addition expression that will create a single word
			if (answer.get(i).length() == 1) // if the word is only one letter long
			{
				ansExpressions.add(digitExpressions.get(0)); // add the letter's expression to the list of words for the answer
			}
			else
			{
				for (j = 1; j < digitExpressions.size(); j++) // iterates over the elements of digit expression
				{
					if (j == 1) // for the first expression
					{
						BinaryOperationExpression initialExpression = new BinaryOperationExpression(operators.get(0), digitExpressions.get(0), digitExpressions.get(1)); // create new expression adding the first and second letters's expressions
						digitAddition.add(initialExpression); // add the expression to the list
					}
					else
					{
						BinaryOperationExpression nextDigitExpression = new BinaryOperationExpression(operators.get(0), digitAddition.get(j - 2), digitExpressions.get(j)); // create a new expression adding the previously created expression and the next letter's expression
						digitAddition.add(nextDigitExpression); // add the expression to the list
					}
				}
				ansExpressions.add(digitAddition.get(digitAddition.size() - 1));// add the finished word (the last element of the temporary list because each element is building on the previous one) to the list of words for the answer
			}
		}
		List<BinaryOperationExpression> argWordExpressions = new ArrayList<>(); // temporary list to hold the high level expressions for the argument of the cryptarithm
		if (argOperators.size() == 0) // if there are no operators in the argument
		{
			finalArgExpression = argExpressions.get(0); // assign the argument's total expression the value of the one word in the argument
		}
		else
		{
			for (i = 0; i < argOperators.size(); i++) // iterate over the elements of argOperators
			{
				if (i == 0) // for the first operator
				{
					BinaryOperationExpression initialWordExpression = new BinaryOperationExpression(getOp(argOperators.get(i), operators), argExpressions.get(0), argExpressions.get(1)); // create a new expression using the current operator, the first word and the second word
					argWordExpressions.add(initialWordExpression); // add the expression to the list
				}
				else
				{
					BinaryOperationExpression nextWordExpression = new BinaryOperationExpression(getOp(argOperators.get(i), operators), argWordExpressions.get(argWordExpressions.size() - 1), argExpressions.get(i + 1)); // create a new expression using the current operator, the previously created expression and the next word
					argWordExpressions.add(nextWordExpression); // add the expression to the list
				}
			}
			finalArgExpression = argWordExpressions.get(argWordExpressions.size() - 1); // assign the arguemnt's finished expression (the last element of the temporary list because each element is building on the previous one) to the finalArgExpression
		}
		List<BinaryOperationExpression> ansWordExpressions = new ArrayList<>();// temporary list to hold the high level expressions for the answer of the cryptarithm
		if (ansOperators.size() == 0) // if there are no operators in the answer
		{
			finalAnsExpression = ansExpressions.get(0); // assign the answer's total expression the value of the one word in the answer
		}
		else
		{
			for (i = 0; i < ansOperators.size(); i++) // iterate over the elements of ansOperators
			{
				if (i == 0) // for the first operator
				{
					BinaryOperationExpression initialWordExpression = new BinaryOperationExpression(getOp(ansOperators.get(i), operators), ansExpressions.get(0), ansExpressions.get(1)); // create a new expression using the current operator, the first word and the second word
					ansWordExpressions.add(initialWordExpression); // add the expression to the list
				}
				else
				{
					BinaryOperationExpression nextWordExpression = new BinaryOperationExpression(getOp(ansOperators.get(i), operators), ansWordExpressions.get(ansWordExpressions.size() - 1), ansExpressions.get(i + 1));// create a new expression using the current operator, the previously created expression and the next word
					ansWordExpressions.add(nextWordExpression); // add the expression to the list
				}
			}
			finalAnsExpression = ansWordExpressions.get(ansWordExpressions.size() - 1); // assign the arguemnt's finished expression (the last element of the temporary list because each element is building on the previous one) to the finalArgExpression
		}
		List<List<Integer>> pastSolutions = new ArrayList<>(); // list to hold all the permutations that are solutions to the cryptarithm
		for (i = 0; i < permutations.size(); i++) // iterates over every array of integers in permutations
		{
			boolean skip = false;
			for (j = 0; j < varList.size(); j++) // iterates over every variable in varList
			{
				if (firstLets.contains(varList.get(j).toString().charAt(0)) && permutations.get(i)[j] == 0) // if current variable is a first letter and the current integer is 0
				{
					skip = true; // skip the evaluation
					break; // break out of this loop, get rid of this permutation
				}
				varList.get(j).store(permutations.get(i)[j]); // store the current integer to the current variable
			}
			if (!skip && finalArgExpression.eval() == finalAnsExpression.eval())
			{
				List<Integer> temp = new ArrayList<>(); // temporary List to store as many values as there are numbers from the current permutation, because if there are less than 10 variables, we want to ignore the leftover values when comparing for identical solutions caused by those values
				for (j = 0; j < varList.size(); j++) // iterates over the variables in varList
				{
					temp.add(permutations.get(i)[j]); // add the current permutation's current value to the temporary list
				}
				pastSolutions.add(temp); // add the temporary list to the list of past solutions
				boolean same = false;
				if ((pastSolutions.size() > 1)) // if there were no solutions in the list previous to adding the current solution
				{
					for (j = 0; j < pastSolutions.size() - 1; j++) // iterates over all but the last solution in pastSolutions
					{
						for (k = j + 1; k < pastSolutions.size(); k++) // iterates over each solution ahead of the ith solution
						{
							if (pastSolutions.get(j).equals(pastSolutions.get(k))) // if any 2 solutions are equal, the current solution is not valid
							{
								same = true;
							}
						}
					}
				}
				if (!same) // if the current solution is unique
				{
					Map<Character, Integer> solution = new HashMap<>(); // temporary map to hold the mappings of variables to values
					for (j = 0; j < varList.size(); j++) // iterates over every variable in varList
					{
						solution.put(varList.get(j).toString().charAt(0), (int) varList.get(j).eval()); // map the variable's character to the variable's value
					}
					allSolutions.add(solution); // add the map to the solution set
				}
				else // if the current solution is a duplicate
				{
					pastSolutions.remove(pastSolutions.size() - 1); // remove the current solution from the list of past solutions
				}
			}
		}
		if (allSolutions.size() == 0) // if there were no solutions, throw an exception
		{
			throw new NoSolutionException();
		}
		return allSolutions; // return the solution set
	}

	/**
	 * Return the VariableExpression with the same name field as a passed in character
	 * @param c a character
	 *
	 * @param varList is a list of VariableExpressions
	 *
	 * @return a VariableExpression such that the VariableExpressions name field is the same as the passed in character,
	 * 			or null if no such VariableExpression is found
	 */
	private static VariableExpression getVar(Character c, List<VariableExpression> varList) // helper method for solve
	{
		int i;
		VariableExpression var = null;
		for (i = 0; i < varList.size(); i++) // iterates over every variable in varList
		{
			if (varList.get(i).toString().equals(Character.toString(c))) // if the current variable's label is the character
			{
				var = varList.get(i); // assign var the current variable
				break;
			}
		}
		return var;
	}

	/**
	 * Return the operator with the same string representation as a passed in string
	 * @param s a String that is not null
	 *
	 * @param opList is a list of operators
	 *
	 * @return an operator such that the operators toString() method returns the same String as s
	 * 			or null if no such VariableExpression is found
	 */
	private static BinaryOperator getOp(String s, List<BinaryOperator> opList) // helper method for solve
	{
		int i;
		BinaryOperator op = null;
		for (i = 0; i < opList.size(); i++) // iterate over all the operators in oplist
		{
			if (opList.get(i).toString().equals(s)) // if the current operator's string representation is the string
			{
				op = opList.get(i); // assign op the current operator
				break;
			}
		}
		return op;
	}

	/**
	 * Parse a string into the different Components of a cryptarithm
	 * @param cryptarithm is a string.
	 *
	 * @return a String[] where each element is a String that represents part of the
	 *            cryptarithm. All characters will be set to uppercase
	 */
	public static String[] parseCryptarithm(String cryptarithm)
	{
		int eqIndex, i, j;
		String argument, result;
		String[] Args, Ans;

		if (cryptarithm.contains("=") && !cryptarithm.isEmpty()) // if the cryptarithm has an equals sign and isn't null
		{
			eqIndex = cryptarithm.indexOf("="); // find the index of the = in input string
			argument = cryptarithm.substring(0, eqIndex); // take everything in the string before the =
			result = cryptarithm.substring(eqIndex + 2, cryptarithm.length()); // take everything in the string after the =
			Ans = result.split("\\s+"); // split the argument string by any amount of white space
			Args = argument.split("\\s+"); // split the answer string by any amount of white space

			if (Args.length % 2 == 0 || Ans.length % 2 == 0) // if the argument or answer doesn't have an odd number of elements, the cryptarithm is invalid
			{
				throw new IllegalArgumentException("Invalid Cryptarithm");
			}
			else
			{
				if (Arrays.asList(Args).contains("=") || Arrays.asList(Ans).contains("="))
				{
					throw new IllegalArgumentException("Invalid Cryptarithm");
				}
				for (i = 1; i < Args.length; i += 2) // iterate over the odd elements of Args (the operators)
				{
					if (!(Args[i].equals("+") || Args[i].equals("-") || Args[i].equals("*") || Args[i].equals("/"))) // if the current element is not an operator, the cryptarithm is invalid
					{
						throw new IllegalArgumentException("Invalid Cryptarithm");
					}
				}
				for (i = 0; i < Args.length; i += 2) // iterate over the even elements of Args (the words)
				{
					if (Args[i].equals("+") || Args[i].equals("-") || Args[i].equals("*") || Args[i].equals("/")) // if the current element is an operator, the cryptarithm is invalid
					{
						throw new IllegalArgumentException("Invalid Cryptarithm");
					}
					else
					{
						Args[i] = Args[i].toUpperCase(); // set every letter of the current element to upper case
					}
				}
				for (i = 1; i < Ans.length; i += 2)   // iterate over the odd elements of Ans (the operators)
				{
					if (!(Ans[i].equals("+") || Ans[i].equals("-") || Ans[i].equals("*") || Ans[i].equals("/"))) // if the current element is not an operator, the cryptarithm is invalid
					{
						throw new IllegalArgumentException("Invalid Cryptarithm");
					}
				}
				for (i = 0; i < Ans.length; i += 2) // iterate over the even elements of Ans (the words)
				{
					if (Ans[i].equals("+") || Ans[i].equals("-") || Ans[i].equals("*") || Ans[i].equals("/")) // if the current element is an operator, the cryptarithm is invalid
					{
						throw new IllegalArgumentException("Invalid Cryptarithm");
					}
					else
					{
						Ans[i] = Ans[i].toUpperCase(); // set every letter of the current element to upper case
					}
				}
			}
		}
		else
		{
			throw new IllegalArgumentException("Invalid Cryptarithm");
		}
		String[] cryptarithmFinal = new String[Args.length + Ans.length + 1]; // create a String[] to return
		for (i = 0; i < Args.length; i++) // iterate over each element of Args
		{
			cryptarithmFinal[i] = Args[i]; // add the current element to the next location in the return array
		}
		cryptarithmFinal[Args.length] = "="; // add an equals sign at the end of the argument
		j = 0; // counter for the Ans address
		for (i = Args.length + 1; i < Args.length + Ans.length + 1; i++) // iterate over the unassigned elements in the return string
		{
			cryptarithmFinal[i] = Ans[j]; // assign the current element of Ans to the current location in the return array
			j++;
		}
		return cryptarithmFinal;
	}
}
