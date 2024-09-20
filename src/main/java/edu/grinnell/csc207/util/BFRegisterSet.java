package edu.grinnell.csc207.util;

public class BFRegisterSet {
    private BigFraction[] registers = new BigFraction[26];

    public BFRegisterSet() {
        for (int i = 0; i < 26; i++) {
            registers[i] = new BigFraction(0, 1);
        }
    }

    public void store(char register, BigFraction value) {
        if (register >= 'a' && register <= 'z') {
            registers[register - 'a'] = value;
        }
    }

    public BigFraction get(char register) {
        if (register >= 'a' && register <= 'z') {
            return registers[register - 'a'];
        }
        throw new IllegalArgumentException("Invalid register");
    }
}
