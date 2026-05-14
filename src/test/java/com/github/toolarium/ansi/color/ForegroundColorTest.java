/*
 * ForegroundColorTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi.color;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Test the {@link ForegroundColor}.
 *
 * @author patrick
 */
public class ForegroundColorTest {

    /**
     * Test all foreground color ANSI codes
     */
    @Test
    public void testValues() {
        assertEquals(39, ForegroundColor.RESET.getValue());
        assertEquals(30, ForegroundColor.BLACK.getValue());
        assertEquals(31, ForegroundColor.RED.getValue());
        assertEquals(32, ForegroundColor.GREEN.getValue());
        assertEquals(33, ForegroundColor.YELLOW.getValue());
        assertEquals(34, ForegroundColor.BLUE.getValue());
        assertEquals(35, ForegroundColor.MAGENTA.getValue());
        assertEquals(36, ForegroundColor.CYAN.getValue());
        assertEquals(37, ForegroundColor.LIGHT_GRAY.getValue());
        assertEquals(90, ForegroundColor.DARK_GRAY.getValue());
        assertEquals(91, ForegroundColor.LIGHT_RED.getValue());
        assertEquals(92, ForegroundColor.LIGHT_GREEN.getValue());
        assertEquals(93, ForegroundColor.LIGHT_YELLOW.getValue());
        assertEquals(94, ForegroundColor.LIGHT_BLUE.getValue());
        assertEquals(95, ForegroundColor.LIGHT_MAGENTA.getValue());
        assertEquals(96, ForegroundColor.LIGHT_CYAN.getValue());
        assertEquals(97, ForegroundColor.WHITE.getValue());
    }


    /**
     * Test all enum values exist
     */
    @Test
    public void testEnumCount() {
        assertEquals(17, ForegroundColor.values().length);
    }
}
