package ca.ubc.ece.cpen221.mp4.tests;

import ca.ubc.ece.cpen221.mp4.cryptarithm.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class CryptarithmTest
{
    @Test
    public void test0() // tests SEND + MORE = MONEY
    {
        String cryptarithm = "SEND + MORE = MONEY";
        Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
        List<Map<Character, Integer>> solution = new ArrayList<>();
        try
        {
            solution = crypto.solve();
        }
        catch (NoSolutionException e)
        {}
        assertEquals(1, solution.size());
        assertEquals(9, solution.get(0).get('S'), 0.00000001);
        assertEquals(5, solution.get(0).get('E'), 0.00000001);
        assertEquals(6, solution.get(0).get('N'), 0.00000001);
        assertEquals(7, solution.get(0).get('D'), 0.00000001);
        assertEquals(1, solution.get(0).get('M'), 0.00000001);
        assertEquals(0, solution.get(0).get('O'), 0.00000001);
        assertEquals(8, solution.get(0).get('R'), 0.00000001);
        assertEquals(2, solution.get(0).get('Y'), 0.00000001);
    }

    @Test
    public void test1() // tests NORTH / SOUTH = EAST / WEST and capitalization
    {
        String cryptarithm = "NoRtH / SoUtH = eAsT / wEsT";
        Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
        List<Map<Character, Integer>> solution = new ArrayList<>();
        try
        {
            solution = crypto.solve();
        }
        catch (NoSolutionException e)
        {}
        assertEquals(1, solution.size());
        assertEquals(5, solution.get(0).get('N'), 0.00000001);
        assertEquals(1, solution.get(0).get('O'), 0.00000001);
        assertEquals(3, solution.get(0).get('R'), 0.00000001);
        assertEquals(0, solution.get(0).get('T'), 0.00000001);
        assertEquals(4, solution.get(0).get('H'), 0.00000001);
        assertEquals(6, solution.get(0).get('S'), 0.00000001);
        assertEquals(9, solution.get(0).get('U'), 0.00000001);
        assertEquals(7, solution.get(0).get('E'), 0.00000001);
        assertEquals(2, solution.get(0).get('A'), 0.00000001);
        assertEquals(8, solution.get(0).get('W'), 0.00000001);
    }

    @Test
    public void test2() // tests JEDER + LIEBT = BERLIN
    {
        String cryptarithm = "jeder + liebt = berlin";
        Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
        List<Map<Character, Integer>> solution = new ArrayList<>();
        try
        {
            solution = crypto.solve();
        }
        catch (NoSolutionException e)
        {}
        assertEquals(2, solution.size());
        assertEquals(6, solution.get(0).get('J'), 0.00000001);
        assertEquals(3, solution.get(0).get('E'), 0.00000001);
        assertEquals(4, solution.get(0).get('D'), 0.00000001);
        assertEquals(8, solution.get(0).get('R'), 0.00000001);
        assertEquals(7, solution.get(0).get('L'), 0.00000001);
        assertEquals(5, solution.get(0).get('I'), 0.00000001);
        assertEquals(1, solution.get(0).get('B'), 0.00000001);
        assertEquals(2, solution.get(0).get('T'), 0.00000001);
        assertEquals(0, solution.get(0).get('N'), 0.00000001);
        assertEquals(4, solution.get(1).get('J'), 0.00000001);
        assertEquals(3, solution.get(1).get('E'), 0.00000001);
        assertEquals(6, solution.get(1).get('D'), 0.00000001);
        assertEquals(8, solution.get(1).get('R'), 0.00000001);
        assertEquals(9, solution.get(1).get('L'), 0.00000001);
        assertEquals(5, solution.get(1).get('I'), 0.00000001);
        assertEquals(1, solution.get(1).get('B'), 0.00000001);
        assertEquals(2, solution.get(1).get('T'), 0.00000001);
        assertEquals(0, solution.get(1).get('N'), 0.00000001);
    }

    @Test
    public void test3() // tests WINTER + IS + WINDIER + SUMMER + IS = SUNNIER
    {
        String cryptarithm = "SUNNIER = WINTER + IS + WINDIER + SUMMER + IS";
        Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
        List<Map<Character, Integer>> solution = new ArrayList<>();
        try
        {
            solution = crypto.solve();
        }
        catch (NoSolutionException e)
        {}
        assertEquals(1, solution.size());
        assertEquals(7, solution.get(0).get('W'), 0.00000001);
        assertEquals(6, solution.get(0).get('I'), 0.00000001);
        assertEquals(0, solution.get(0).get('N'), 0.00000001);
        assertEquals(2, solution.get(0).get('T'), 0.00000001);
        assertEquals(8, solution.get(0).get('E'), 0.00000001);
        assertEquals(1, solution.get(0).get('R'), 0.00000001);
        assertEquals(9, solution.get(0).get('S'), 0.00000001);
        assertEquals(4, solution.get(0).get('D'), 0.00000001);
        assertEquals(3, solution.get(0).get('U'), 0.00000001);
        assertEquals(5, solution.get(0).get('M'), 0.00000001);
    }

    @Test
    public void test4() // tests I + CANT + GET = NO + SATISFACTION
    {
        String cryptarithm = "I + CANT + GET = NO + SATISFACTION";
        Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
        List<Map<Character, Integer>> solution = new ArrayList<>();
        try
        {
            solution = crypto.solve();
        }
        catch (NoSolutionException e)
        {}
        assertEquals(0, solution.size());
    }

    @Test
    public void test5() // tests X = Y
    {
        String cryptarithm = "X = Y";
        Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
        List<Map<Character, Integer>> solution = new ArrayList<>();
        try
        {
            solution = crypto.solve();
        }
        catch (NoSolutionException e)
        {}
        assertEquals(0, solution.size());
    }

    @Test
    public void test6() // tests an invalid cryptarithm - missing an expression
    {
        boolean exception = false;
        String cryptarithm = "Error + = INvalid";
        try
        {
            Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void test7() // tests an invalid cryptarithm - non-alphabetic character
    {
        boolean exception = false;
        String cryptarithm = "5rror = INvalid";
        try
        {
            Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
            List<Map<Character, Integer>> solution = new ArrayList<>();
            try
            {
                solution = crypto.solve();
            }
            catch (NoSolutionException e)
            {}
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void test8() // tests an invalid cryptarithm - too many letters
    {
        boolean exception = false;
        String cryptarithm = "abcdefg = hijkl";
        try
        {
            Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
            List<Map<Character, Integer>> solution = new ArrayList<>();
            try
            {
                solution = crypto.solve();
            }
            catch (NoSolutionException e)
            {}
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void test9() // tests an invalid cryptarithm - null input
    {
        boolean exception = false;
        String cryptarithm = "";
        try
        {
            Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void test10() // tests an invalid cryptarithm - argument missing operator
    {
        boolean exception = false;
        String cryptarithm = "This is so = wrong";
        try
        {
            Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void test11() // tests an invalid cryptarithm - answer missing operator
    {
        boolean exception = false;
        String cryptarithm = "Send + more = sketch drug money";
        try
        {
            Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void test12() // tests an invalid cryptarithm - too many operators in argument
    {
        boolean exception = false;
        String cryptarithm = "+ - why = not";
        try
        {
            Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void test13() // tests an invalid cryptarithm - too many operators in answer
    {
        boolean exception = false;
        String cryptarithm = "pls = no + /";
        try
        {
            Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void test14() // tests an invalid cryptarithm - too many equals signs
    {
        boolean exception = false;
        String cryptarithm = "this = shouldnt = work";
        try
        {
            Cryptarithm crypto = new Cryptarithm(Cryptarithm.parseCryptarithm(cryptarithm));
        }
        catch (IllegalArgumentException e)
        {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void test17() // tests the solution printer for 1 solution
    {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String cryptarithm = "SEND + MORE = MONEY";
        SolveCryptarithm.solve(cryptarithm);
        assertEquals("1 solution\r\n[R=8, S=9, D=7, E=5, Y=2, M=1, N=6, O=0]\r\n", out.toString());
    }

    @Test
    public void test18() // tests the solution printer for 2 solutions
    {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String cryptarithm = "jeder + liebt = berlin";
        SolveCryptarithm.solve(cryptarithm);
        assertEquals("2 solutions\r\n[R=8, B=1, D=4, T=2, E=3, I=5, J=6, L=7, N=0]\r\n[R=8, B=1, D=6, T=2, E=3, I=5, J=4, L=9, N=0]\r\n", out.toString());
    }

    @Test
    public void test19() // tests the solution printer for no solutions
    {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String cryptarithm = "X = Y";
        SolveCryptarithm.solve(cryptarithm);
        assertEquals("0 solutions\r\n", out.toString());
    }

    @Test
    public void test20() // tests an invalid cryptarithm
    {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String cryptarithm = "X = + Y";
        SolveCryptarithm.solve(cryptarithm);
        assertEquals("Input format not accepted. Please try again.\r\n", out.toString());
    }

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void test21() // tests the command line input method
    {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        systemInMock.provideLines("north / south = east / west");
        String[] s = new String[] {};
        try
        {
            SolveCryptarithm.main(s);
        }
        catch(NoSuchElementException e)
        {}
        assertEquals("Enter a Cryptarithm:\r\n1 solution\r\n[A=2, R=3, S=6, T=0, U=9, E=7, W=8, H=4, N=5, O=1]\r\nEnter a Cryptarithm:\r\n", out.toString());
    }
}