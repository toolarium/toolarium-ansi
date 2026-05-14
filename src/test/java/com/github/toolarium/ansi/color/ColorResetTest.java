/*
 * ColorResetTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi.color;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Test the {@link ColorReset}.
 *
 * @author patrick
 */
public class ColorResetTest {

    /**
     * Test all enum values exist
     */
    @Test
    public void testValues() {
        assertEquals(3, ColorReset.values().length);
        assertEquals(ColorReset.FG, ColorReset.valueOf("FG"));
        assertEquals(ColorReset.BG, ColorReset.valueOf("BG"));
        assertEquals(ColorReset.ALL, ColorReset.valueOf("ALL"));
    }
}
