/*
 * AnsiStringTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.toolarium.ansi.color.BackgroundColor;
import com.github.toolarium.ansi.color.ForegroundColor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test the ansi string
 *
 * @author patrick
 */
public class AnsiStringTest {
    private static final Logger LOG = LoggerFactory.getLogger(AnsiStringTest.class);
    private static final String MY_COLOR = "my color";


    /**
     * Test foreground color with String
     */
    @Test
    public void testColor() {
        assertEquals(MY_COLOR, AnsiString.getInstance(AnsiColor.OFF).appendWithColor(MY_COLOR, ForegroundColor.CYAN).toString());
        assertEquals(AnsiColor.ON.ansify(ForegroundColor.CYAN.getValue()) + MY_COLOR + AnsiColor.ON.ansify(0), AnsiString.getInstance(AnsiColor.ON).appendWithColor(MY_COLOR, ForegroundColor.CYAN).toString());
        assertColor(AnsiColor.ON, ForegroundColor.CYAN, MY_COLOR);
        assertColor(AnsiColor.OFF, ForegroundColor.CYAN, MY_COLOR);
    }


    /**
     * Test getInstance with no args returns AUTO mode builder
     */
    @Test
    public void testGetInstanceNoArgs() {
        AnsiString.Builder builder = AnsiString.getInstance();
        assertNotNull(builder);
        // Should produce output without error
        String result = builder.appendWithColor(MY_COLOR, ForegroundColor.RED);
        assertNotNull(result);
    }


    /**
     * Test foreground color with StringBuilder
     */
    @Test
    public void testAppendWithColorStringBuilderForeground() {
        StringBuilder text = new StringBuilder(MY_COLOR);
        // OFF mode
        AnsiStringBuilder resultOff = AnsiString.getInstance(AnsiColor.OFF).appendWithColor(text, ForegroundColor.CYAN);
        assertNotNull(resultOff);
        assertEquals(MY_COLOR, resultOff.toString(false, true));

        // ON mode
        AnsiStringBuilder resultOn = AnsiString.getInstance(AnsiColor.ON).appendWithColor(text, ForegroundColor.CYAN);
        assertNotNull(resultOn);
        String result = resultOn.toString(false);
        assertEquals(AnsiColor.ON.ansify(ForegroundColor.CYAN.getValue()) + MY_COLOR + AnsiColor.ON.ansify(0), result);
    }


    /**
     * Test background color with String
     */
    @Test
    public void testAppendWithColorBackgroundString() {
        // OFF mode
        String resultOff = AnsiString.getInstance(AnsiColor.OFF).appendWithColor(MY_COLOR, BackgroundColor.BLUE);
        assertEquals(MY_COLOR, resultOff);

        // ON mode
        String resultOn = AnsiString.getInstance(AnsiColor.ON).appendWithColor(MY_COLOR, BackgroundColor.BLUE);
        String expected = AnsiColor.ON.ansify(BackgroundColor.BLUE.getValue()) + MY_COLOR + AnsiColor.ON.ansify(0);
        assertEquals(expected, resultOn);
    }


    /**
     * Test background color with StringBuilder
     */
    @Test
    public void testAppendWithColorStringBuilderBackground() {
        StringBuilder text = new StringBuilder(MY_COLOR);
        // OFF mode
        AnsiStringBuilder resultOff = AnsiString.getInstance(AnsiColor.OFF).appendWithColor(text, BackgroundColor.RED);
        assertNotNull(resultOff);
        assertEquals(MY_COLOR, resultOff.toString(false, true));

        // ON mode
        AnsiStringBuilder resultOn = AnsiString.getInstance(AnsiColor.ON).appendWithColor(text, BackgroundColor.RED);
        String result = resultOn.toString(false);
        assertEquals(AnsiColor.ON.ansify(BackgroundColor.RED.getValue()) + MY_COLOR + AnsiColor.ON.ansify(0), result);
    }


    /**
     * Test foreground and background color with String
     */
    @Test
    public void testAppendWithColorForegroundAndBackgroundString() {
        // OFF mode
        String resultOff = AnsiString.getInstance(AnsiColor.OFF).appendWithColor(MY_COLOR, ForegroundColor.CYAN, BackgroundColor.BLUE);
        assertEquals(MY_COLOR, resultOff);

        // ON mode
        String resultOn = AnsiString.getInstance(AnsiColor.ON).appendWithColor(MY_COLOR, ForegroundColor.CYAN, BackgroundColor.BLUE);
        String expected = AnsiColor.ON.ansify(ForegroundColor.CYAN.getValue())
                + AnsiColor.ON.ansify(BackgroundColor.BLUE.getValue())
                + MY_COLOR
                + AnsiColor.ON.ansify(0);
        assertEquals(expected, resultOn);
    }


    /**
     * Test foreground and background color with StringBuilder
     */
    @Test
    public void testAppendWithColorStringBuilderForegroundAndBackground() {
        StringBuilder text = new StringBuilder(MY_COLOR);
        // OFF mode
        AnsiStringBuilder resultOff = AnsiString.getInstance(AnsiColor.OFF).appendWithColor(text, ForegroundColor.CYAN, BackgroundColor.BLUE);
        assertNotNull(resultOff);
        assertEquals(MY_COLOR, resultOff.toString(false, true));

        // ON mode
        AnsiStringBuilder resultOn = AnsiString.getInstance(AnsiColor.ON).appendWithColor(text, ForegroundColor.CYAN, BackgroundColor.BLUE);
        String result = resultOn.toString(false);
        String expected = AnsiColor.ON.ansify(ForegroundColor.CYAN.getValue())
                + AnsiColor.ON.ansify(BackgroundColor.BLUE.getValue())
                + MY_COLOR
                + AnsiColor.ON.ansify(0);
        assertEquals(expected, result);
    }


    /**
     * Assert color
     *
     * @param ansiColor the ansi color
     * @param color the color
     * @param text the text
     */
    private void assertColor(AnsiColor ansiColor, ForegroundColor color, String text) {
        String reference = ansiColor.ansify(color.getValue()) + text + ansiColor.ansify("0");
        if (ansiColor.isEnabled()) {
            String toTest = AnsiString.getInstance(ansiColor).appendWithColor(text, color).toString();
            if (LOG.isDebugEnabled()) {
                LOG.debug("To test: " + toTest);
                LOG.debug("Reference: " + reference);
            }
            assertEquals(reference, toTest);
        } else {
            String toTest = AnsiString.getInstance(ansiColor).appendWithColor(text, color).toString();
            if (LOG.isDebugEnabled()) {
                LOG.debug("To test: " + toTest);
                LOG.debug("Reference: " + reference);
            }
            assertEquals(reference, toTest);
            assertEquals(text, toTest);
        }
    }
}
