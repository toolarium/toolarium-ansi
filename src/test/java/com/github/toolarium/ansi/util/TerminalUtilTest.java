/*
 * TerminalUtilTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


/**
 * Test the {@link TerminalUtil}.
 *
 * @author patrick
 */
public class TerminalUtilTest {

    /**
     * Test singleton instance
     */
    @Test
    public void testGetInstance() {
        TerminalUtil instance1 = TerminalUtil.getInstance();
        TerminalUtil instance2 = TerminalUtil.getInstance();
        assertNotNull(instance1);
        assertEquals(instance1, instance2);
    }


    /**
     * Test isTTY returns without error
     */
    @Test
    public void testIsTTY() {
        assertNotNull(Boolean.valueOf(TerminalUtil.getInstance().isTTY()));
        // Second call should use cached value
        assertNotNull(Boolean.valueOf(TerminalUtil.getInstance().isTTY()));
    }


    /**
     * Test isPseudoTTY returns without error
     */
    @Test
    public void testIsPseudoTTY() {
        assertNotNull(Boolean.valueOf(TerminalUtil.getInstance().isPseudoTTY()));
    }


    /**
     * Test isXterm returns without error
     */
    @Test
    public void testIsXterm() {
        assertNotNull(Boolean.valueOf(TerminalUtil.getInstance().isXterm()));
    }
}
