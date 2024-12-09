package edu.grinnell.csc207;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * Tests of the various classes for MP02, including BigFraction,
 * BFCalculator, and BFRegisterSet. Each test case is categorized
 * by the class and functionality it tests.
 *
 * In a typical Maven project, this should be stored in
 * src/test/java/edu/grinnell/csc207.
 *
 * Author: Samuel A. Rebelsky
 */
public class TestMP02 {

    // +-----------+---------------------------------------------------
    // | Constants |
    // +-----------+

    /**
     * Constant representing the fraction 1/2.
     */
    static final BigFraction ONE_HALF = new BigFraction(1, 2);

    /**
     * Constant representing the fraction 1/3.
     */
    static final BigFraction ONE_THIRD = new BigFraction(1, 3);

    /**
     * Constant representing the fraction 2/3.
     */
    static final BigFraction TWO_THIRDS = new BigFraction(2, 3);

    /**
     * Constant representing the fraction 1/5.
     */
    static final BigFraction ONE_FIFTH = new BigFraction(1, 5);

    /**
     * Constant representing the fraction 1/6.
     */
    static final BigFraction ONE_SIXTH = new BigFraction(1, 6);

    // +-----------------------+---------------------------------------
    // | R tests - BigFraction |
    // +-----------------------+

    /**
     * Tests the addition functionality of the BigFraction class.
     */
    @Test
    public void testBfAdd() {
        assertEquals("5/6", ONE_THIRD.add(ONE_HALF).toString(),
            "R: Addition 1");
        assertEquals("13/15", ONE_FIFTH.add(TWO_THIRDS).toString(),
            "R: Addition 2");
        assertEquals("4/3", TWO_THIRDS.add(TWO_THIRDS).toString(),
            "R: Addition 3");
    }

    /**
     * Tests the subtraction functionality of the BigFraction class.
     */
    @Test
    public void testBfSubtract() {
        assertEquals("1/6", ONE_HALF.subtract(ONE_THIRD).toString(),
            "R: Subtraction 1");
        assertEquals("1/3", TWO_THIRDS.subtract(ONE_THIRD).toString(),
            "R: Subtraction 2");
    }

    /**
     * Tests the multiplication functionality of the BigFraction class.
     */
    @Test
    public void testBfMultiply() {
        assertEquals("1/4", ONE_HALF.multiply(ONE_HALF).toString(),
            "R: Multiplication 1");
        assertEquals("2/15", TWO_THIRDS.multiply(ONE_FIFTH).toString(),
            "R: Multiplication 2");
    }

    /**
     * Tests the division functionality of the BigFraction class.
     */
    @Test
    public void testBfDivide() {
        assertEquals("3/2", ONE_HALF.divide(ONE_THIRD).toString(),
            "R: Division 1");
        assertEquals("2/3", ONE_THIRD.divide(ONE_HALF).toString(),
            "R: Division 2");
        assertEquals("10/3", TWO_THIRDS.divide(ONE_FIFTH).toString(),
            "R: Division 3");
    }

    // +-----------------------+---------------------------------------
    // | M tests - BigFraction |
    // +-----------------------+

    /**
     * Tests that BigFraction values are simplified correctly.
     */
    @Test
    public void testBfSimplify() {
        assertEquals("2/3", new BigFraction(4, 6).toString(),
            "M: Simplify 1");
        assertEquals("5/3", new BigFraction(15, 9).toString(),
            "M: Simplify 2");
        assertEquals("1/2", ONE_THIRD.add(ONE_SIXTH).toString(),
            "M: Simplify 3");
    }

    /**
     * Tests the parsing functionality of the BigFraction class.
     */
    @Test
    public void testBfParse() {
        assertEquals("1/13", new BigFraction("1/13").toString(),
            "M: Parsing 1");
        assertEquals("5/11", new BigFraction("5/11").toString(),
            "M: Parsing 2");
        assertEquals(BigInteger.valueOf(22), new BigFraction("22/7").getNumerator(),
            "M: Parsing 3a");
        assertEquals(BigInteger.valueOf(7), new BigFraction("22/7").getDenominator(),
            "M: Parsing 3b");
    }

    /**
     * Tests that fractions parsed from strings are simplified.
     */
    @Test
    public void testBfParseSimplify() {
        assertEquals("2/3", new BigFraction("4/6").toString(),
            "M: Parsing and simplifying 1");
        assertEquals("4/7", new BigFraction("40/70").toString(),
            "M: Parsing and simplifying 2");
        assertEquals(BigInteger.valueOf(3), new BigFraction("15/10").getNumerator(),
            "M: Parsing and simplifying 3a");
        assertEquals(BigInteger.valueOf(2), new BigFraction("15/10").getDenominator(),
            "M: Parsing and simplifying 3b");
    }

    // +------------------------+--------------------------------------
    // | R tests - BFCalculator |
    // +------------------------+

    /**
     * Tests the creation of a new BFCalculator instance.
     */
    @Test
    public void testBfcNew() {
        assertNotNull(new BFCalculator(),
            "R: Create new BFCalculator");
    }

    /**
     * Verifies that a new BFCalculator initializes with a result of 0.
     */
    @Test
    public void testBFcInit() {
        BFCalculator bfc = new BFCalculator();
        assertEquals("0", bfc.get().toString(),
            "R: Calculator starts at 0");
    }

    /**
     * Verifies that clearing a BFCalculator resets its result to 0.
     */
    @Test
    public void testBfcClear() {
        BFCalculator bfc = new BFCalculator();
        bfc.clear();
        assertEquals("0", bfc.get().toString(),
            "R: Clear resets calculator to 0");
    }

    // +-------------------------+-------------------------------------
    // | R tests - BFRegisterSet |
    // +-------------------------+

    /**
     * Tests the creation of a new BFRegisterSet instance.
     */
    @Test
    public void testBfrsNew() {
        assertNotNull(new BFRegisterSet(),
            "R: Create new BFRegisterSet");
    }

    /**
     * Tests storing and retrieving values in registers.
     */
    @Test
    public void testBfrsBasics() {
        BFRegisterSet registers = new BFRegisterSet();

        registers.store('a', ONE_HALF);
        registers.store('b', ONE_THIRD);
        registers.store('p', TWO_THIRDS);
        registers.store('q', ONE_FIFTH);
        registers.store('z', ONE_SIXTH);

        assertEquals("1/2", registers.get('a').toString(),
            "M: Register basics 1");
        assertEquals("1/3", registers.get('b').toString(),
            "M: Register basics 2");
        assertEquals("2/3", registers.get('p').toString(),
            "M: Register basics 3");
        assertEquals("1/5", registers.get('q').toString(),
            "M: Register basics 4");
        assertEquals("1/6", registers.get('z').toString(),
            "M: Register basics 5");
    }

    /**
     * Tests independent updates to register values.
     */
    @Test
    public void testBfrsUpdate() {
        BFRegisterSet registers = new BFRegisterSet();

        registers.store('l', ONE_HALF);
        registers.store('m', ONE_THIRD);

        assertEquals("1/2", registers.get('l').toString(),
            "M: Register update 1a");
        assertEquals("1/3", registers.get('m').toString(),
            "M: Register update 1b");

        registers.store('l', ONE_FIFTH);
        assertEquals("1/5", registers.get('l').toString(),
            "M: Register update 2a");
        assertEquals("1/3", registers.get('m').toString(),
            "M: Register update 2b");
    }
}
