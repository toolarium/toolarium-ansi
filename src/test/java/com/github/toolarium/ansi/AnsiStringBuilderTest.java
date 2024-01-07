/*
 * AnsiStringBuilderTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi;

import com.github.toolarium.ansi.color.ForegroundColor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Test the ansi string builder
 * 
 * @author patrick
 */
public class AnsiStringBuilderTest {
    private static final Logger LOG = LoggerFactory.getLogger(AnsiStringBuilderTest.class);
    
    /**
     * Test 
     */
    @Test
    public void test() {
        System.setProperty("toolarium.ansi", "true");

        // All formatting functions support at least three different overloads, each intended for a different use case.
        AnsiStringBuilder a = new AnsiStringBuilder()
                .italic() // Use case 1: Manual Reset
                .append("This is italicized and reset manually.")
                .resetItalic(System.lineSeparator()) // You can optionaly supply an additional append string to any of the reset functions that will be appended after the formating reset has been applied.
                
                .dim("This is dimmed and reset automatically.") // Use case 2: Automatic Reset
                .append(System.lineSeparator())
        
                .underline(sb -> { // Use case 3: Function Consumer 
                    sb.color24("#00ff00", "This is both underlined and green");
                }) // The string builder passed to this function consumer will automatically wrap all content appended to it with the underline formatting.
                .append(System.lineSeparator())
                
                .color(ForegroundColor.CYAN).append("This is in cyan color")
                
                .reset();
        
        LOG.debug("=>" + a.toString());
    }
}
