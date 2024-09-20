package edu.grinnell.csc207.util;

public class BFCalculator {
    private BigFraction result;

    public BFCalculator() {
        this.result = new BigFraction(0, 1);  // Start with 0
    }

    public BigFraction get() {
        return this.result;
    }

    public void add(BigFraction val) {
        this.result = this.result.add(val);
    }

    public void subtract(BigFraction val) {
        this.result = this.result.subtract(val);
    }

    public void multiply(BigFraction val) {
        this.result = this.result.multiply(val);
    }

    public void divide(BigFraction val) {
        this.result = this.result.divide(val);
    }

    public void clear() {
        this.result = new BigFraction(0, 1);
    }
}