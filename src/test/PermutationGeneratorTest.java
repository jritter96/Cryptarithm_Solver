package ca.ubc.ece.cpen221.mp4.tests;

import ca.ubc.ece.cpen221.mp4.permutation.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.*;

import static ca.ubc.ece.cpen221.mp4.permutation.Permutation.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class PermutationGeneratorTest
{
    @Test
    public void test0() // tests permutation generator and getOnePermutation
    {
        List<List<Integer>> perms = new ArrayList(getAllPerms(new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
        assertEquals(3628800, perms.size());
    }

    @Test
    public void test1() // tests uniqueness of permutations
    {
        int i, j;
        boolean unique = true;
        ArrayList<Integer[]> perms = getAllPerms(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7}); // Note: more than 8 value takes an unreasonable amount of time (~30 secs - ~49 mins) so reduced test to 8 elements
        for (i = 0; i < perms.size() - 1; i++)
        {
            for (j = i + 1; j < perms.size(); j++)
            {
                if (perms.get(i).equals(perms.get(j)))
                {
                    unique = false;
                }
            }
        }
        assertTrue(unique);
    }

    @Test
    public void test2() // tests getOnePermutation
    {
        ArrayList<Integer[]> permutations = Permutation.getAllPerms(new Integer[]{1, 2, 3});
        assertEquals(6, permutations.size());
        Integer[] getOneTest = {1, 2, 3};
        Permutation numsThree = new Permutation(getOneTest);
        getOneTest = (Integer[]) numsThree.getOnePermutation();
        assertEquals(3, getOneTest.length);
        String test = Arrays.toString(getOneTest);
        assertTrue(test.contains("1"));
        assertTrue(test.contains("2"));
        assertTrue(test.contains("3"));
    }
}