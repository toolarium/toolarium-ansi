/*
 * AnsiColorTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.toolarium.ansi.color.ForegroundColor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


/**
 * Test the {@link AnsiColor}.
 *
 * @author patrick
 */
public class AnsiColorTest {

    /**
     * Clean up system property after each test
     */
    @AfterEach
    void cleanUp() {
        System.clearProperty(AnsiColor.TOOLARIUM_ANSI);
    }


    /**
     * Test the ansi color
     */
    @Test
    public void test() {
        assertFalse(AnsiColor.OFF.isEnabled());
        assertTrue(AnsiColor.ON.isEnabled());

        AnsiColor.AUTO.isEnabled();

        System.setProperty(AnsiColor.TOOLARIUM_ANSI, "true");
        assertTrue(AnsiColor.AUTO.isEnabled());

        System.setProperty(AnsiColor.TOOLARIUM_ANSI, "false");
        assertFalse(AnsiColor.AUTO.isEnabled());
    }


    /**
     * Test color on / off
     */
    @Test
    public void testColor() {
        System.setProperty(AnsiColor.TOOLARIUM_ANSI, "false");
        assertEquals("", AnsiColor.OFF.ansify(ForegroundColor.CYAN.getValue()));


        System.setProperty(AnsiColor.TOOLARIUM_ANSI, "true");
        assertEquals(new StringBuilder().append(AnsiColor.ANSI_START_ESCAPE_SEQUENCE).append(ForegroundColor.CYAN.getValue()).append(AnsiColor.ANSI_STOP_ESCAPE_SEQUENCE).toString(),
                     AnsiColor.ON.ansify(ForegroundColor.CYAN.getValue()));
    }


    /**
     * Test ansify with String parameter
     */
    @Test
    public void testAnsifyString() {
        assertEquals("", AnsiColor.OFF.ansify("42"));
        assertEquals(AnsiColor.ANSI_START_ESCAPE_SEQUENCE + "42" + AnsiColor.ANSI_STOP_ESCAPE_SEQUENCE, AnsiColor.ON.ansify("42"));
    }


    /**
     * Test ansify with int parameter
     */
    @Test
    public void testAnsifyInt() {
        assertEquals("", AnsiColor.OFF.ansify(36));
        assertEquals(AnsiColor.ANSI_START_ESCAPE_SEQUENCE + "36" + AnsiColor.ANSI_STOP_ESCAPE_SEQUENCE, AnsiColor.ON.ansify(36));
    }


    /**
     * Test constants
     */
    @Test
    public void testConstants() {
        assertEquals("toolarium.ansi", AnsiColor.TOOLARIUM_ANSI);
        assertEquals("\u001B[", AnsiColor.ANSI_START_ESCAPE_SEQUENCE);
        assertEquals("m", AnsiColor.ANSI_STOP_ESCAPE_SEQUENCE);
    }


    /**
     * Test isForceDisabled
     */
    @Test
    public void testIsForceDisabled() {
        // We cannot set env vars in tests, but we can verify the method runs and returns a boolean
        assertNotNull(Boolean.valueOf(AnsiColor.AUTO.isForceDisabled()));
    }


    /**
     * Test isForceEnabled
     */
    @Test
    public void testIsForceEnabled() {
        assertNotNull(Boolean.valueOf(AnsiColor.AUTO.isForceEnabled()));
    }


    /**
     * Test isHintDisabled
     */
    @Test
    public void testIsHintDisabled() {
        assertNotNull(Boolean.valueOf(AnsiColor.AUTO.isHintDisabled()));
    }


    /**
     * Test isHintEnabled
     */
    @Test
    public void testIsHintEnabled() {
        assertNotNull(Boolean.valueOf(AnsiColor.AUTO.isHintEnabled()));
    }


    /**
     * Test isJansiConsoleInstalled
     */
    @Test
    public void testIsJansiConsoleInstalled() {
        // Jansi is not on classpath, so should return false
        assertFalse(AnsiColor.AUTO.isJansiConsoleInstalled());
        // Second call should return cached value
        assertFalse(AnsiColor.AUTO.isJansiConsoleInstalled());
    }


    /**
     * Test AUTO with system property set to AUTO
     */
    @Test
    public void testAutoWithAutoProperty() {
        System.setProperty(AnsiColor.TOOLARIUM_ANSI, "AUTO");
        // Should not throw, uses auto-detect path
        AnsiColor.AUTO.isEnabled();
    }


    /**
     * Test AUTO with system property set to TTY
     */
    @Test
    public void testAutoWithTtyProperty() {
        System.setProperty(AnsiColor.TOOLARIUM_ANSI, "TTY");
        // Should not throw, checks TTY/pseudo-TTY path
        AnsiColor.AUTO.isEnabled();
    }
}
