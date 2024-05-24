package com.peli.demo.model;

// These numbers are arbitrary, but while testing I found that >= 0.9 is a good threshold for a match

public enum Accuracy {
    HIGH(0.9),
    MEDIUM(0.7),
    LOW(0.5),
    NONE(0.0);

    private final double value;

    Accuracy(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static Accuracy valueOf(Double similarity) {
        if (similarity > HIGH.getValue()) {
            return HIGH;
        } else if (similarity > MEDIUM.getValue()) {
            return MEDIUM;
        } else if (similarity > LOW.getValue()) {
            return LOW;
        } else {
            return NONE;
        }
    }
}
