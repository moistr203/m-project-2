
package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * Represents a fraction using arbitrary-precision integers.
 * Provides basic arithmetic operations like addition, subtraction,
 * multiplication, and division.
 *
 * author Moise M.
 */
public final class BigFraction {
    /**
     * The numerator of the fraction.
     */
    private final BigInteger numerator;

    /**
     * The denominator of the fraction.
     */
    private final BigInteger denominator;

    /**
     * Creates a new fraction given a numerator and a denominator as integers.
     *
     * @param num   the numerator of the fraction
     * @param denom the denominator of the fraction
     */
    public BigFraction(final int num, final int denom) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(denom));
    }

    /**
     * Creates a new fraction given a numerator and a denominator as BigIntegers.
     *
     * @param num   the numerator of the fraction
     * @param denom the denominator of the fraction
     */
    public BigFraction(final BigInteger num, final BigInteger denom) {
        if (denom.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Denominator cannot be zero.");
        }
        final BigInteger gcd = num.gcd(denom);
        final BigInteger simplifiedNumerator = num.divide(gcd);
        final BigInteger simplifiedDenominator = denom.divide(gcd);

        if (simplifiedDenominator.signum() == -1) {
            this.numerator = simplifiedNumerator.negate();
            this.denominator = simplifiedDenominator.negate();
        } else {
            this.numerator = simplifiedNumerator;
            this.denominator = simplifiedDenominator;
        }
    }

    /**
     * Adds this fraction with another fraction.
     *
     * @param other the fraction to add
     * @return a new BigFraction representing the sum
     */
    public BigFraction add(final BigFraction other) {
        final BigInteger newNumerator = this.numerator
                .multiply(other.denominator)
                .add(other.numerator
                        .multiply(this.denominator));
        final BigInteger newDenominator = this.denominator
                .multiply(other.denominator);
        return new BigFraction(newNumerator, newDenominator);
    }

    /**
     * Subtracts another fraction from this fraction.
     *
     * @param other the fraction to subtract
     * @return a new BigFraction representing the difference
     */
    public BigFraction subtract(final BigFraction other) {
        final BigInteger newNumerator = this.numerator
                .multiply(other.denominator)
                .subtract(other.numerator
                        .multiply(this.denominator));
        final BigInteger newDenominator = this.denominator
                .multiply(other.denominator);
        return new BigFraction(newNumerator, newDenominator);
    }

    /**
     * Multiplies this fraction with another fraction.
     *
     * @param other the fraction to multiply with
     * @return a new BigFraction representing the product
     */
    public BigFraction multiply(final BigFraction other) {
        final BigInteger newNumerator = this.numerator
                .multiply(other.numerator);
        final BigInteger newDenominator = this.denominator
                .multiply(other.denominator);
        return new BigFraction(newNumerator, newDenominator);
    }

    /**
     * Divides this fraction by another fraction.
     *
     * @param other the fraction to divide by
     * @return a new BigFraction representing the quotient
     */
    public BigFraction divide(final BigFraction other) {
        return this.multiply(new BigFraction(other.denominator, other.numerator));
    }

    // Other methods like getters for numerator and denominator, toString(), etc.
}
