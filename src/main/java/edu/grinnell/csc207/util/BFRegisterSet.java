package edu.grinnell.csc207.util;

/**
 * A set of registers for storing fractions, indexed by characters 'a' to 'z'.
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
            registers[i] = new BigFraction(0, 1);
        }
    }

    /**
     * Stores a fraction in a specified register.
     * @param register the register character ('a' to 'z')
     * @param value    the fraction to store
     */
    public void store(final char register, final BigFraction value) {
        if (register >= 'a' && register <= 'z') {
            registers[register - 'a'] = value;
        }
    }

    /**
     * Retrieves the fraction stored in a specified register.
     * @param register the register character ('a' to 'z')
     * @return the fraction in the register
     */
    public BigFraction get(final char register) {
        if (register >= 'a' && register <= 'z') {
            return registers[register - 'a'];
        }
        throw new IllegalArgumentException("Invalid register");
    }
}
