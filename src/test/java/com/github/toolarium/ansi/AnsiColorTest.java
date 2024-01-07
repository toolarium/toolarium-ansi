/*
 * AnsiColorTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.toolarium.ansi.color.ForegroundColor;
import org.junit.jupiter.api.Test;


/**
 * Test the {@link AnsiColor}.
 *  
 * @author patrick
 */
public class AnsiColorTest {

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
}
