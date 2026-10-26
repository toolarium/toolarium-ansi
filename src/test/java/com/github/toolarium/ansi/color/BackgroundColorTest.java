/*
 * BackgroundColorTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi.color;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Test the {@link BackgroundColor}.
 *
 * @author patrick
 */
public class BackgroundColorTest {

    /**
     * Test all background color ANSI codes
     */
    @Test
    public void testValues() {
        assertEquals(49, BackgroundColor.RESET.getValue());
        assertEquals(40, BackgroundColor.BLACK.getValue());
        assertEquals(41, BackgroundColor.RED.getValue());
        assertEquals(42, BackgroundColor.GREEN.getValue());
        assertEquals(43, BackgroundColor.YELLOW.getValue());
        assertEquals(44, BackgroundColor.BLUE.getValue());
        assertEquals(45, BackgroundColor.MAGENTA.getValue());
        assertEquals(46, BackgroundColor.CYAN.getValue());
        assertEquals(47, BackgroundColor.LIGHT_GRAY.getValue());
        assertEquals(100, BackgroundColor.DARK_GRAY.getValue());
        assertEquals(101, BackgroundColor.LIGHT_RED.getValue());
        assertEquals(102, BackgroundColor.LIGHT_GREEN.getValue());
        assertEquals(103, BackgroundColor.LIGHT_YELLOW.getValue());
        assertEquals(104, BackgroundColor.LIGHT_BLUE.getValue());
        assertEquals(105, BackgroundColor.LIGHT_MAGENTA.getValue());
        assertEquals(106, BackgroundColor.LIGHT_CYAN.getValue());
        assertEquals(107, BackgroundColor.WHITE.getValue());
    }


    /**
     * Test all enum values exist
     */
    @Test
    public void testEnumCount() {
        assertEquals(17, BackgroundColor.values().length);
    }


    /**
     * Test getValueAsString matches integer value
     */
    @Test
    public void testGetValueAsString() {
        for (BackgroundColor color : BackgroundColor.values()) {
            assertEquals(Integer.toString(color.getValue()), color.getValueAsString());
        }
    }
}
