package frc.robot.utils;

/**
 * A helpful converter to give meaning to magic length numbers
 * For example
 */
public class CustomaryLength {
    private final double inches;
    public CustomaryLength(double amount, CustomaryLengthUnit type) {
        inches = amount * type.getMultiplier();
    }
    public double get(CustomaryLengthUnit type) {
        return inches / type.getMultiplier();
    }
}


