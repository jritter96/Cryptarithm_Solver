package ca.ubc.ece.cpen221.mp4.permutation;

/**
 * Abstract Permutation - an interface
 *
 * @param <T>
 *            generic Array
 */
public interface AbstractPermutation<T> {

	// Here T denotes the type of values in the array that we want to permute

	/**
	 * get one permutation.
	 * 
	 * @return one generic Array of permutation.
	 */
	T[] getOnePermutation();

}
