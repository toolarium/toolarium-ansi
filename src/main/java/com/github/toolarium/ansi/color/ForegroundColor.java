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
    /** Reset foreground color to default. */
    RESET(39),
    /** Black foreground. */
    BLACK(30),
    /** Red foreground. */
    RED(31),
    /** Green foreground. */
    GREEN(32),
    /** Yellow foreground. */
    YELLOW(33),
    /** Blue foreground. */
    BLUE(34),
    /** Magenta foreground. */
    MAGENTA(35),
    /** Cyan foreground. */
    CYAN(36),
    /** Light gray foreground. */
    LIGHT_GRAY(37),
    /** Dark gray foreground (high intensity). */
    DARK_GRAY(90),
    /** Light red foreground (high intensity). */
    LIGHT_RED(91),
    /** Light green foreground (high intensity). */
    LIGHT_GREEN(92),
    /** Light yellow foreground (high intensity). */
    LIGHT_YELLOW(93),
    /** Light blue foreground (high intensity). */
    LIGHT_BLUE(94),
    /** Light magenta foreground (high intensity). */
    LIGHT_MAGENTA(95),
    /** Light cyan foreground (high intensity). */
    LIGHT_CYAN(96),
    /** White foreground (high intensity). */
    WHITE(97);

    
    /*
    // Regular Colors. Normal color, no bold, background color etc.
    BLACK("\033[0;30m"),    // BLACK
    RED("\033[0;31m"),      // RED
    GREEN("\033[0;32m"),    // GREEN
    YELLOW("\033[0;33m"),   // YELLOW
    BLUE("\033[0;34m"),     // BLUE
    MAGENTA("\033[0;35m"),  // MAGENTA
    CYAN("\033[0;36m"),     // CYAN
    WHITE("\033[0;37m"),    // WHITE

    // Bold
    BLACK_BOLD("\033[1;30m"),   // BLACK
    RED_BOLD("\033[1;31m"),     // RED
    GREEN_BOLD("\033[1;32m"),   // GREEN
    YELLOW_BOLD("\033[1;33m"),  // YELLOW
    BLUE_BOLD("\033[1;34m"),    // BLUE
    MAGENTA_BOLD("\033[1;35m"), // MAGENTA
    CYAN_BOLD("\033[1;36m"),    // CYAN
    WHITE_BOLD("\033[1;37m"),   // WHITE

    // Underline
    BLACK_UNDERLINED("\033[4;30m"),     // BLACK
    RED_UNDERLINED("\033[4;31m"),       // RED
    GREEN_UNDERLINED("\033[4;32m"),     // GREEN
    YELLOW_UNDERLINED("\033[4;33m"),    // YELLOW
    BLUE_UNDERLINED("\033[4;34m"),      // BLUE
    MAGENTA_UNDERLINED("\033[4;35m"),   // MAGENTA
    CYAN_UNDERLINED("\033[4;36m"),      // CYAN
    WHITE_UNDERLINED("\033[4;37m"),     // WHITE

    // High Intensity
    BLACK_BRIGHT("\033[0;90m"),     // BLACK
    RED_BRIGHT("\033[0;91m"),       // RED
    GREEN_BRIGHT("\033[0;92m"),     // GREEN
    YELLOW_BRIGHT("\033[0;93m"),    // YELLOW
    BLUE_BRIGHT("\033[0;94m"),      // BLUE
    MAGENTA_BRIGHT("\033[0;95m"),   // MAGENTA
    CYAN_BRIGHT("\033[0;96m"),      // CYAN
    WHITE_BRIGHT("\033[0;97m"),     // WHITE

    // Bold High Intensity
    BLACK_BOLD_BRIGHT("\033[1;90m"),    // BLACK
    RED_BOLD_BRIGHT("\033[1;91m"),      // RED
    GREEN_BOLD_BRIGHT("\033[1;92m"),    // GREEN
    YELLOW_BOLD_BRIGHT("\033[1;93m"),   // YELLOW
    BLUE_BOLD_BRIGHT("\033[1;94m"),     // BLUE
    MAGENTA_BOLD_BRIGHT("\033[1;95m"),  // MAGENTA
    CYAN_BOLD_BRIGHT("\033[1;96m"),     // CYAN
    WHITE_BOLD_BRIGHT("\033[1;97m"),    // WHITE

    // Background
    BLACK_BACKGROUND("\033[40m"),   // BLACK
    RED_BACKGROUND("\033[41m"),     // RED
    GREEN_BACKGROUND("\033[42m"),   // GREEN
    YELLOW_BACKGROUND("\033[43m"),  // YELLOW
    BLUE_BACKGROUND("\033[44m"),    // BLUE
    MAGENTA_BACKGROUND("\033[45m"), // MAGENTA
    CYAN_BACKGROUND("\033[46m"),    // CYAN
    WHITE_BACKGROUND("\033[47m"),   // WHITE


    // High Intensity backgrounds
    BLACK_BACKGROUND_BRIGHT("\033[0;100m"),     // BLACK
    RED_BACKGROUND_BRIGHT("\033[0;101m"),       // RED
    GREEN_BACKGROUND_BRIGHT("\033[0;102m"),     // GREEN
    YELLOW_BACKGROUND_BRIGHT("\033[0;103m"),    // YELLOW
    BLUE_BACKGROUND_BRIGHT("\033[0;104m"),      // BLUE
    MAGENTA_BACKGROUND_BRIGHT("\033[0;105m"),   // MAGENTA
    CYAN_BACKGROUND_BRIGHT("\033[0;106m"),      // CYAN
    WHITE_BACKGROUND_BRIGHT("\033[0;107m");     // WHITE    
    */
    
    private final int value;
    private final String valueAsString;


    /**
     * Constructor for ForegroundColor
     *
     * @param value the value
     */
    ForegroundColor(int value) {
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
