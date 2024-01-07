/*
 * ForegroundColor.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi.color;


/**
 * 4-bit foreground color values.
 * 
 * @author patrick
 */
public enum ForegroundColor {
    RESET(39),
    BLACK(30),
    RED(31),
    GREEN(32),
    YELLOW(33),
    BLUE(34),
    MAGENTA(35),
    CYAN(36),
    LIGHT_GRAY(37),
    DARK_GRAY(90),
    LIGHT_RED(91),
    LIGHT_GREEN(92),
    LIGHT_YELLOW(93),
    LIGHT_BLUE(94),
    LIGHT_MAGENTA(95),
    LIGHT_CYAN(96),
    WHITE(97);

    private int value;

    
    /**
     * Constructor for ForegroundColor
     *
     * @param value the value
     */
    ForegroundColor(int value) {
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
