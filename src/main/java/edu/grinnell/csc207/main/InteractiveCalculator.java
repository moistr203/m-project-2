package edu.grinnell.csc207.main;

import java.util.Scanner;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

public class InteractiveCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BFCalculator calculator = new BFCalculator();
        BFRegisterSet registers = new BFRegisterSet();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("QUIT")) {
                break;
            }

            // Handle expressions or commands
            try {
                if (input.startsWith("STORE")) {
                    char register = input.charAt(6);
                    registers.store(register, calculator.get());
                    System.out.println("STORED");
                } else {
                    // Split the expression and evaluate it
                    String[] tokens = input.split(" ");
                    BigFraction result = evaluateExpression(tokens, calculator, registers);
                    System.out.println(result);
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }

        scanner.close();
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
