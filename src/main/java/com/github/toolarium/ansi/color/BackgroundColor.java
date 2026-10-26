/*
 * BackgroundColor.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi.color;

/**
 * 4-bit background color values.
 * 
 * @author patrick
 */
public enum BackgroundColor {
    /** Reset background color to default. */
    RESET(49),
    /** Black background. */
    BLACK(40),
    /** Red background. */
    RED(41),
    /** Green background. */
    GREEN(42),
    /** Yellow background. */
    YELLOW(43),
    /** Blue background. */
    BLUE(44),
    /** Magenta background. */
    MAGENTA(45),
    /** Cyan background. */
    CYAN(46),
    /** Light gray background. */
    LIGHT_GRAY(47),
    /** Dark gray background (high intensity). */
    DARK_GRAY(100),
    /** Light red background (high intensity). */
    LIGHT_RED(101),
    /** Light green background (high intensity). */
    LIGHT_GREEN(102),
    /** Light yellow background (high intensity). */
    LIGHT_YELLOW(103),
    /** Light blue background (high intensity). */
    LIGHT_BLUE(104),
    /** Light magenta background (high intensity). */
    LIGHT_MAGENTA(105),
    /** Light cyan background (high intensity). */
    LIGHT_CYAN(106),
    /** White background (high intensity). */
    WHITE(107);

    private final int value;
    private final String valueAsString;


    /**
     * Constructor for BackgroundColor
     *
     * @param value the value
     */
    BackgroundColor(int value) {
        this.value = value;
        this.valueAsString = Integer.toString(value);
    }


    /**
     * Get the value
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }


    /**
     * Get the value as pre-computed string
     *
     * @return the value as string
     */
    public String getValueAsString() {
        return valueAsString;
    }
}
