
package edu.grinnell.csc207.main;

import java.util.Scanner;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * An interactive calculator using fractions and registers.
 * It reads user input and performs operations on fractions.
 * author Moise M.
 */
public final class InteractiveCalculator {

    // Constant for the register index in the STORE command
    private static final int REGISTER_INDEX = 6;

    /**
     * Private constructor to prevent instantiation.
     */
    private InteractiveCalculator() {
        // Utility class; no instantiation
    }

    /**
     * Main method for running the interactive calculator.
     *
     * @param args command line arguments (not used)
     */
    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            BFCalculator calculator = new BFCalculator();
            BFRegisterSet registers = new BFRegisterSet();

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if ("QUIT".equalsIgnoreCase(input)) {
                    break;
                }

                // Handle expressions or commands
                try {
                    if (input.startsWith("STORE")) {
                        char register = input.charAt(REGISTER_INDEX);
                        registers.store(register, calculator.get());
                        System.out.println("STORED");
                    } else {
                        // Split the expression and evaluate it
                        String[] tokens = input.split(" ");
                        BigFraction result = evaluateExpression(
                                tokens, calculator, registers);
                        System.out.println(result);
                    }
                } catch (Exception e) {
                    System.err.println("ERROR: " + e.getMessage());
                }
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
            final String[] tokens,
            final BFCalculator calculator,
            final BFRegisterSet registers) {
        BigFraction result = new BigFraction(0, 1);
        for (String token : tokens) {
            // Process tokens as fractions, operators, or register references
            if (token.equals("+")) {
                calculator.add(new BigFraction(1, 1)); // Example operation
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
