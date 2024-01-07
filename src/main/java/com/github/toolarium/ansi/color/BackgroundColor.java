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
    RESET(49),
    BLACK(40),
    RED(41),
    GREEN(42),
    YELLOW(43),
    BLUE(44),
    MAGENTA(45),
    CYAN(46),
    LIGHT_GRAY(47),
    DARK_GRAY(100),
    LIGHT_RED(101),
    LIGHT_GREEN(102),
    LIGHT_YELLOW(103),
    LIGHT_BLUE(104),
    LIGHT_MAGENTA(105),
    LIGHT_CYAN(106),
    WHITE(107);

    private int value;


    /**
     * Constructor for BackgroundColor
     *
     * @param value the value
     */
    BackgroundColor(int value) {
        this.value = value;
    }

    
    /**
     * Get the value
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
