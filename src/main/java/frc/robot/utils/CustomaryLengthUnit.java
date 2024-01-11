package frc.robot.utils;

public enum CustomaryLengthUnit {
    INCHES(1),
    FEET(12),
    YARDS(36),
    MILES(63360);

    private final long multiplier;

    CustomaryLengthUnit(long multiplier) {
        this.multiplier = multiplier;
    }

    public long getMultiplier() {
        return multiplier;
    }
}
