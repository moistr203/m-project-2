package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

public class QuickCalculator {
    public static void main(String[] args) {
        BFCalculator calculator = new BFCalculator();
        BFRegisterSet registers = new BFRegisterSet();

        for (String arg : args) {
            try {
                // Process each expression from the command line
                String[] tokens = arg.split(" ");
                BigFraction result = evaluateExpression(tokens, calculator, registers);
                System.out.println(arg + " -> " + result);
            } catch (Exception e) {
                System.out.println(arg + " FAILED [Invalid expression]");
            }
        }
    }

    private static BigFraction evaluateExpression(String[] tokens, BFCalculator calculator, BFRegisterSet registers) {
        BigFraction result = new BigFraction(0, 1);
        for (String token : tokens) {
            // Parse and compute based on the token (fractions, operators, or registers)
            // You can improve this logic based on specific needs
        }
        return result;
    }
}
