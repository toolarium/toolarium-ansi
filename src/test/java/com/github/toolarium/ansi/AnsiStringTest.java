/*
 * AnsiStringTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
     * Test 
     */
    @Test
    public void testColor() {
        assertEquals(MY_COLOR, AnsiString.getInstance(AnsiColor.OFF).appendWithColor(MY_COLOR, ForegroundColor.CYAN).toString());
        assertEquals(AnsiColor.ON.ansify(ForegroundColor.CYAN.getValue()) + MY_COLOR + AnsiColor.ON.ansify(0), AnsiString.getInstance(AnsiColor.ON).appendWithColor(MY_COLOR, ForegroundColor.CYAN).toString());
        assertColor(AnsiColor.ON, ForegroundColor.CYAN, MY_COLOR);
        assertColor(AnsiColor.OFF, ForegroundColor.CYAN, MY_COLOR);
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
            LOG.debug("To test: " + toTest);
            LOG.debug("Reference: " + reference);
            assertEquals(reference, toTest);
        } else {
            String toTest = AnsiString.getInstance(ansiColor).appendWithColor(text, color).toString();
            LOG.debug("To test: " + toTest);
            LOG.debug("Reference: " + reference);
            assertEquals(reference, toTest);
            assertEquals(text, toTest);
            
        }
    }
}
