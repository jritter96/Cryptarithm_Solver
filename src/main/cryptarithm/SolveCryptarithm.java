package ca.ubc.ece.cpen221.mp4.cryptarithm;

import java.util.*;

public class SolveCryptarithm
{
	static public void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in); // scans the command line
		do
		{
			System.out.println("Enter a Cryptarithm:");
			String cryptarithm = scanner.nextLine(); // takes the input string from the command line to use for the cryptarithm
			solve(cryptarithm); // passes the string to a method that solves and prints the cryptarithm
		}
		while (true);
	}

	public static void solve(String cryptarithm)
	{
		int i, j;
		try
		{
			Cryptarithm kryptoTheDog = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm)); // creating a cryptarithm using the parsed input string
			List<Map<Character, Integer>> solution = new ArrayList<>(); // list to hold the mapped solutions
			try
			{
				solution = kryptoTheDog.solve(); // assigns the list of all solutions returned by solve to the solution list
			}
			catch(NoSolutionException e) // catches a no solution exception if the solve method throws one
			{}
			if (solution.size() == 1) // if there is only one solution
			{
				System.out.println("1 solution");
			}
			else
			{
				System.out.println(solution.size() + " solutions"); // print the number of solutions
			}
			for (i = 0; i < solution.size(); i++)  // iterates across each mapped solution in the solution list
			{
				List<Character> letters = new ArrayList<>(solution.get(i).keySet()); // list to hold all the characters of the solution(the keys of the solution map)
				List<Integer> numbers = new ArrayList<>(solution.get(i).values()); // list to hold all the mapped integers of the solution (the values of the solution map)
				List<Object> printFormat = new ArrayList<>(); // list to hold the formatted solution for printing
				for (j = 0; j < letters.size(); j++)  // iterates over each unique character in the cryptarithm
				{
					String letterString = Character.toString(letters.get(j)); // cast the character value to a String
					String numberString = Integer.toString(numbers.get(j)); // cast the integer value to a Sting
					String s1 = letterString.concat("="); // add an equals sign to the end of the character string
					String s2 = s1.concat(numberString); // add the integer to the end of the combined equals sign to format one mapping for the solution
					printFormat.add(s2); // add the current mapping to the formatted list
				}
				System.out.println(printFormat); // print the formatted list
			}
		}
		catch (Exception e) // catches any illegal argument exceptions thrown by the parser
		{
			System.out.println("Input format not accepted. Please try again.");
		}
	}
}
