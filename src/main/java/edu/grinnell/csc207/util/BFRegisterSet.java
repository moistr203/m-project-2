package edu.grinnell.csc207.util;

/**
 * A set of registers for storing fractions, indexed by characters 'a' to 'z'.
 * Each register is initialized to 0 and can store a single fraction.
 *
 * author Moise M.
 */
public final class BFRegisterSet {
    /**
     * The number of registers.
     */
    private static final int REGISTER_COUNT = 26;

    /**
     * The array of registers storing fractions.
     */
    private final BigFraction[] registers = new BigFraction[REGISTER_COUNT];

    /**
     * Initializes a new register set with each register set to 0.
     */
    public BFRegisterSet() {
        for (int i = 0; i < REGISTER_COUNT; i++) {
            registers[i] = new BigFraction(0, 1); // Initialize all registers to 0/1
        }
    }

    /**
     * Stores a fraction in a specified register.
     *
     * @param register the register character ('a' to 'z')
     * @param value    the fraction to store
     * @throws IllegalArgumentException if the register is invalid
     * @throws NullPointerException     if the value is null
     */
    public void store(final char register, final BigFraction value) {
        validateRegister(register);
        if (value == null) {
            throw new NullPointerException("Cannot store a null fraction.");
        }
        registers[register - 'a'] = value;
    }

    /**
     * Retrieves the fraction stored in a specified register.
     *
     * @param register the register character ('a' to 'z')
     * @return the fraction in the register
     * @throws IllegalArgumentException if the register is invalid
     */
    public BigFraction get(final char register) {
        validateRegister(register);
        return registers[register - 'a'];
    }

    /**
     * Validates that the given register is within the range 'a' to 'z'.
     *
     * @param register the register character to validate
     * @throws IllegalArgumentException if the register is not valid
     */
    private void validateRegister(final char register) {
        if (register < 'a' || register > 'z') {
            throw new IllegalArgumentException("Register must be a letter between 'a' and 'z'.");
        }
    }
}