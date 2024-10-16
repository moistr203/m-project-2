package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * A quick calculator that takes expressions as command line arguments.
 * author Moise M.
 */
public final class QuickCalculator {

    /**
     * Private constructor to prevent instantiation.
     */
    private QuickCalculator() {
        // Utility class; no instantiation
    }

    /**
     * Main method for processing command line arguments as expressions.
     *
     * @param args command line arguments representing expressions
     */
    public static void main(final String[] args) {
        final BFCalculator calculator = new BFCalculator();
        final BFRegisterSet registers = new BFRegisterSet();

        for (String arg : args) {
            try {
                // Process each expression from the command line
                final String[] tokens = arg.split(" ");
                final BigFraction result = evaluateExpression(
                    tokens, calculator, registers);
                System.out.println(arg + " -> " + result);
            } catch (Exception e) {
                System.err.println(arg + " FAILED [Invalid expression]");
            }
        }
    }

    /**
     * Evaluates an expression based on the provided tokens.
     *
     * @param tokens     the tokens of the expression
     * @param calculator the BFCalculator to use
     * @param registers  the BFRegisterSet to use
     * @return the result of the evaluated expression
     */
    private static BigFraction evaluateExpression(
        final String[] tokens, final BFCalculator calculator,
        final BFRegisterSet registers) {
        BigFraction result = new BigFraction(0, 1);
        for (String token : tokens) {
            // Process tokens as fractions, operators, or register references
            if (token.equals("+")) {
                calculator.add(new BigFraction(1, 1));
            } else if (token.equals("-")) {
                calculator.subtract(new BigFraction(1, 1));
            } else if (token.matches("[a-z]")) {
                result = registers.get(token.charAt(0));
            } else {
                result = new BigFraction(Integer.parseInt(token), 1);
            }
        }
        return result;
    }
}
