package ca.ubc.ece.cpen221.mp4.permutation;

import java.util.*;

import static java.util.Arrays.*;

public class Permutation<T> implements AbstractPermutation<T> {
	private T[] curPerm;

	/**
	 * Constructor for a permutation, required only to perform getOnePermutation.
	 *
	 * @param curPerm
	 * 		An array that holds the elements that will be contained in the permutation
	 */
	public Permutation(T[] curPerm) {
		this.curPerm = curPerm;
	}

	/**
	 * A static method that acts as a generic permutation generator: generates all permutations for the given array
	 *
	 * @param permuteThis
	 * 		The array to generate permutations for
	 */
	public static <T> ArrayList<T[]> getAllPerms(T[] permuteThis) // based off of Heap's Algorithm
	{
		int n = permuteThis.length;
		//Create the list that will hold all permutations
		ArrayList<T[]> permuteList = new ArrayList<>();
		//Temp used to preserve permuteThis, as heapsAlgo modifies the array passed as a parameter
		T[] temp = copyOf(permuteThis, permuteThis.length);
		//perform heap's algorithm on temp, storing all permutations in permuteList
		heapsAlgo(n, temp, permuteList);
		return permuteList;
	}

	/**
	 * Swap two elements. Used as a helper method to heapsAlgo
	 *
	 * @param permuteThis
	 * 		The array to perform the swap on
	 * @param index1
	 *      The index of one of the elements to be swapped
	 * @param index2
	 * 		The index of the other element to be swapped
	 *
	 * Modifies: permuteThis by swapping two elements
	 */
	private static <T> void swap(T[] permuteThis, int index1, int index2) {
		T temp = permuteThis[index1];
		permuteThis[index1] = permuteThis[index2];
		permuteThis[index2] = temp;
	}

	/**
	 * Perform Heap's Algorithm on array permuteThis. NOTE: this algorithm is (obviously) based off of Heap's Algorithm
	 * 		as described at "https://en.wikipedia.org/wiki/Heap's_algorithm"
	 *
	 * @param n
	 * 		An index representing the portion of the array that Heap's Algorithm is currently focusing on
	 * 		Originally, n is passed the size of the array to permute. It is then modified by recursive calls to this method
	 * @param permuteThis
	 * 		The current permutation to record. After recording, permuteThis is modified to generate a new permutation
	 * @param permuteList
	 *      The list that will contain all permutations after Heap's Algorithm is finished
	 *
	 * Modifies: permuteList to contain all permutations
	 * 			 permuteThis, which is continuously changed to a new and different permutation by Heap's Algorithm
	 */
	private static <T> void heapsAlgo(int n, T[] permuteThis, ArrayList<T[]> permuteList) {
		if(n == 1) {
			//store the current permutation
			permuteList.add(copyOf(permuteThis, permuteThis.length));
		}
		else {
			//Heap's Algorithm fixes the last element and cycles through all permutations with that element as the
			//last element in the array. This for loop is used as a counter for each time the last element is swapped out
			for(int i = 0; i < n - 1; i++) {
				heapsAlgo(n - 1, permuteThis, permuteList);
				//swap out the last element
				if(n % 2 == 0) {
					swap(permuteThis, i, n - 1);
				}
				else {
					swap(permuteThis, 0, n - 1);
				}
			}
			//acquire final permutation
			heapsAlgo(n - 1, permuteThis, permuteList);
		}
	}

	// Since we aren't planning on getting one permutation at a time in our strategy but have to implement this method to
	// satisfy the interface, this method simply returns one constant permutation each time it is called

	/**
	 * get one permutation.
	 *
	 * @return one generic Array of permutation (simply the original string passed to this Permutation instance).
	 */
	@Override
	public T[] getOnePermutation()
	{
		// TODO implement this method
		// Simply return the permutation of the array that was passed as input when creating this Permutation instance
		return copyOf(curPerm, curPerm.length);
	}
}
