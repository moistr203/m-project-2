package edu.grinnell.csc207.util;

/**
 * A calculator that performs operations on fractions.
 * Provides methods to add, subtract, multiply, and divide fractions.
 *
 * author Moise M.
 */
public final class BFCalculator {
    /**
     * The current result of the calculator.
     */
    private BigFraction result;

    /**
     * Initializes a new BFCalculator with a result of 0.
     */
    public BFCalculator() {
        this.result = new BigFraction(0, 1);  // Start with 0
    }

    /**
     * Gets the current result of the calculator.
     *
     * @return the current fraction result
     */
    public BigFraction get() {
        return this.result;
    }

    /**
     * Adds a fraction to the current result.
     *
     * @param val the fraction to add
     */
    public void add(final BigFraction val) {
        this.result = this.result.add(val);
    }

    /**
     * Subtracts a fraction from the current result.
     *
     * @param val the fraction to subtract
     */
    public void subtract(final BigFraction val) {
        this.result = this.result.subtract(val);
    }

    /**
     * Multiplies the current result by another fraction.
     *
     * @param val the fraction to multiply
     */
    public void multiply(final BigFraction val) {
        this.result = this.result.multiply(val);
    }

    /**
     * Divides the current result by another fraction.
     *
     * @param val the fraction to divide by
     */
    public void divide(final BigFraction val) {
        this.result = this.result.divide(val);
    }

    // Additional methods (clear, etc.) can be added here as needed
}
