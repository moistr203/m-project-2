package edu.grinnell.csc207.util;

import java.math.BigInteger;

public class BigFraction {
    private final BigInteger numerator;
    private final BigInteger denominator;

    public BigFraction(int numerator, int denominator) {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    public BigFraction(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Denominator cannot be zero.");
        }
        // Simplify the fraction upon creation
        BigInteger gcd = numerator.gcd(denominator);
        BigInteger simplifiedNumerator = numerator.divide(gcd);
        BigInteger simplifiedDenominator = denominator.divide(gcd);

        // Handle the sign
        if (simplifiedDenominator.signum() == -1) {
            this.numerator = simplifiedNumerator.negate();
            this.denominator = simplifiedDenominator.negate();
        } else {
            this.numerator = simplifiedNumerator;
            this.denominator = simplifiedDenominator;
        }
    }

    public BigFraction add(BigFraction other) {
        BigInteger newNumerator = this.numerator.multiply(other.denominator)
                .add(other.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(other.denominator);
        return new BigFraction(newNumerator, newDenominator);
    }

    public BigFraction subtract(BigFraction other) {
        BigInteger newNumerator = this.numerator.multiply(other.denominator)
                .subtract(other.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(other.denominator);
        return new BigFraction(newNumerator, newDenominator);
    }

    public BigFraction multiply(BigFraction other) {
        BigInteger newNumerator = this.numerator.multiply(other.numerator);
        BigInteger newDenominator = this.denominator.multiply(other.denominator);
        return new BigFraction(newNumerator, newDenominator);
    }

    public BigFraction divide(BigFraction other) {
        return this.multiply(new BigFraction(other.denominator, other.numerator));
    }

    public BigInteger numerator() {
        return this.numerator;
    }

    public BigInteger denominator() {
        return this.denominator;
    }

    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE)) {
            return numerator.toString();
        }
        return numerator + "/" + denominator;
    }
}