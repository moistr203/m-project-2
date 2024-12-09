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

                try {
                    // Handle commands and expressions
                    if (input.startsWith("STORE")) {
                        char register = input.charAt(6);
                        registers.store(register, calculator.get());
                        System.out.println("STORED");
                    } else {
                        String[] tokens = input.split(" ");
                        BigFraction result = evaluateExpression(tokens, registers);
                        calculator.clear(); // Reset the calculator
                        calculator.add(result);
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
     * @param tokens    the tokens of the expression
     * @param registers the BFRegisterSet to use
     * @return the result of the evaluated expression
     */
    private static BigFraction evaluateExpression(
            final String[] tokens, final BFRegisterSet registers) {

        if (tokens.length == 0) {
            throw new IllegalArgumentException("Empty expression.");
        }

        BigFraction result = parseToken(tokens[0], registers);
        for (int i = 1; i < tokens.length; i += 2) {
            if (i + 1 >= tokens.length) {
                throw new IllegalArgumentException("Invalid expression format.");
            }
            String operator = tokens[i];
            BigFraction next = parseToken(tokens[i + 1], registers);
            result = applyOperator(result, next, operator);
        }

        return result;
    }

    /**
     * Parses a token into a BigFraction or retrieves a value from a register.
     *
     * @param token     the token to parse
     * @param registers the BFRegisterSet to use
     * @return a BigFraction representing the token
     */
    private static BigFraction parseToken(String token, BFRegisterSet registers) {
        if (token.matches("[a-z]")) {
            // Retrieve a fraction from the register
            return registers.get(token.charAt(0));
        } else if (token.matches("-?\\d+/\\d+|-?\\d+")) {
            // Parse a fraction or integer
            return token.contains("/") ? new BigFraction(token)
                    : new BigFraction(Integer.parseInt(token), 1);
        } else {
            throw new IllegalArgumentException("Invalid token: " + token);
        }
    }

    /**
     * Applies an operator to two BigFraction values.
     *
     * @param left     the left operand
     * @param right    the right operand
     * @param operator the operator to apply
     * @return the result of the operation
     */
    private static BigFraction applyOperator(
            BigFraction left, BigFraction right, String operator) {
        switch (operator) {
            case "+" -> {
                return left.add(right);
            }
            case "-" -> {
                return left.subtract(right);
            }
            case "*" -> {
                return left.multiply(right);
            }
            case "/" -> {
                return left.divide(right);
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
