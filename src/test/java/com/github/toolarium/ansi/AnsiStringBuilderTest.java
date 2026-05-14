/*
 * AnsiStringBuilderTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.toolarium.ansi.color.BackgroundColor;
import com.github.toolarium.ansi.color.ColorReset;
import com.github.toolarium.ansi.color.ForegroundColor;
import org.junit.jupiter.api.BeforeEach;
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
    private static final String ESC = AnsiColor.ANSI_START_ESCAPE_SEQUENCE;
    private static final String M = AnsiColor.ANSI_STOP_ESCAPE_SEQUENCE;
    private static final String TEXT = "text";
    private static final String SUFFIX = "suffix";
    private static final String SGR_BOLD = "1";
    private static final String SGR_DIM = "2";
    private static final String SGR_ITALIC = "3";
    private static final String SGR_UNDERLINE = "4";
    private static final String SGR_BLINK = "5";
    private static final String SGR_INVERT = "7";
    private static final String SGR_HIDE = "8";
    private static final String SGR_STRIKE = "9";
    private static final String SGR_RESET_BOLD_DIM = "22";
    private static final String SGR_RESET_ITALIC = "23";
    private static final String SGR_RESET_UNDERLINE = "24";
    private static final String SGR_RESET_BLINK = "25";
    private static final String SGR_RESET_INVERT = "27";
    private static final String SGR_RESET_HIDE = "28";
    private static final String SGR_RESET_STRIKE = "29";
    private static final String SGR_RESET = "0";
    private static final String SGR_FG_RESET = "39";
    private static final String SGR_BG_RESET = "49";

    /**
     * Set up system property before each test
     */
    @BeforeEach
    void setUp() {
        System.setProperty(AnsiColor.TOOLARIUM_ANSI, "true");
    }


    /**
     * Usage sample
     */
    @Test
    public void usageSample() {
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


    /**
     * Test constructors
     */
    @Test
    public void testConstructors() {
        AnsiStringBuilder b1 = new AnsiStringBuilder();
        b1.setAnsiColor(AnsiColor.OFF);
        assertEquals("", b1.toString(false));

        AnsiStringBuilder b2 = new AnsiStringBuilder("hello");
        b2.setAnsiColor(AnsiColor.OFF);
        assertEquals("hello", b2.toString(false));

        AnsiStringBuilder b3 = new AnsiStringBuilder((CharSequence) "hello");
        b3.setAnsiColor(AnsiColor.OFF);
        assertEquals("hello", b3.toString(false));

        AnsiStringBuilder b4 = new AnsiStringBuilder(64);
        b4.setAnsiColor(AnsiColor.OFF);
        assertEquals("", b4.toString(false));
    }


    /**
     * Test bold formatting
     */
    @Test
    public void testBold() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .bold().append(TEXT).resetBold().toString(false);
        assertEquals(ESC + SGR_BOLD + M + TEXT + ESC + SGR_RESET_BOLD_DIM + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .bold(TEXT).toString(false);
        assertEquals(ESC + SGR_BOLD + M + TEXT + ESC + SGR_RESET_BOLD_DIM + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .bold(sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + SGR_BOLD + M + TEXT + ESC + SGR_RESET_BOLD_DIM + M, result);
    }


    /**
     * Test italic formatting
     */
    @Test
    public void testItalic() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .italic().append(TEXT).resetItalic().toString(false);
        assertEquals(ESC + SGR_ITALIC + M + TEXT + ESC + SGR_RESET_ITALIC + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .italic(TEXT).toString(false);
        assertEquals(ESC + SGR_ITALIC + M + TEXT + ESC + SGR_RESET_ITALIC + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .italic(sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + SGR_ITALIC + M + TEXT + ESC + SGR_RESET_ITALIC + M, result);
    }


    /**
     * Test dim formatting
     */
    @Test
    public void testDim() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .dim().append(TEXT).resetDim().toString(false);
        assertEquals(ESC + SGR_DIM + M + TEXT + ESC + SGR_RESET_BOLD_DIM + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .dim(TEXT).toString(false);
        assertEquals(ESC + SGR_DIM + M + TEXT + ESC + SGR_RESET_BOLD_DIM + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .dim(sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + SGR_DIM + M + TEXT + ESC + SGR_RESET_BOLD_DIM + M, result);
    }


    /**
     * Test underline formatting
     */
    @Test
    public void testUnderline() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .underline().append(TEXT).resetUnderline().toString(false);
        assertEquals(ESC + SGR_UNDERLINE + M + TEXT + ESC + SGR_RESET_UNDERLINE + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .underline(TEXT).toString(false);
        assertEquals(ESC + SGR_UNDERLINE + M + TEXT + ESC + SGR_RESET_UNDERLINE + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .underline(sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + SGR_UNDERLINE + M + TEXT + ESC + SGR_RESET_UNDERLINE + M, result);
    }


    /**
     * Test blink formatting
     */
    @Test
    public void testBlink() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .blink().append(TEXT).resetBlink().toString(false);
        assertEquals(ESC + SGR_BLINK + M + TEXT + ESC + SGR_RESET_BLINK + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .blink(TEXT).toString(false);
        assertEquals(ESC + SGR_BLINK + M + TEXT + ESC + SGR_RESET_BLINK + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .blink(sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + SGR_BLINK + M + TEXT + ESC + SGR_RESET_BLINK + M, result);
    }


    /**
     * Test strikeThrough formatting
     */
    @Test
    public void testStrikeThrough() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .strikeThrough().append(TEXT).resetStrikeThrough().toString(false);
        assertEquals(ESC + SGR_STRIKE + M + TEXT + ESC + SGR_RESET_STRIKE + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .strikeThrough(TEXT).toString(false);
        assertEquals(ESC + SGR_STRIKE + M + TEXT + ESC + SGR_RESET_STRIKE + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .strikeThrough(sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + SGR_STRIKE + M + TEXT + ESC + SGR_RESET_STRIKE + M, result);
    }


    /**
     * Test hide formatting
     */
    @Test
    public void testHide() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .hide().append(TEXT).resetHidden().toString(false);
        assertEquals(ESC + SGR_HIDE + M + TEXT + ESC + SGR_RESET_HIDE + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .hide(TEXT).toString(false);
        assertEquals(ESC + SGR_HIDE + M + TEXT + ESC + SGR_RESET_HIDE + M, result);
    }


    /**
     * Test invertColor formatting
     */
    @Test
    public void testInvertColor() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .invertColor().append(TEXT).resetInvertColors().toString(false);
        assertEquals(ESC + SGR_INVERT + M + TEXT + ESC + SGR_RESET_INVERT + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .invertColor(TEXT).toString(false);
        assertEquals(ESC + SGR_INVERT + M + TEXT + ESC + SGR_RESET_INVERT + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .invertColor(sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + SGR_INVERT + M + TEXT + ESC + SGR_RESET_INVERT + M, result);
    }


    /**
     * Test reset with value append
     */
    @Test
    public void testResetWithValue() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .bold().append(TEXT).resetBold(SUFFIX).toString(false);
        assertEquals(ESC + SGR_BOLD + M + TEXT + ESC + SGR_RESET_BOLD_DIM + M + SUFFIX, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .italic().append(TEXT).resetItalic(SUFFIX).toString(false);
        assertEquals(ESC + SGR_ITALIC + M + TEXT + ESC + SGR_RESET_ITALIC + M + SUFFIX, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .dim().append(TEXT).resetDim(SUFFIX).toString(false);
        assertEquals(ESC + SGR_DIM + M + TEXT + ESC + SGR_RESET_BOLD_DIM + M + SUFFIX, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .underline().append(TEXT).resetUnderline(SUFFIX).toString(false);
        assertEquals(ESC + SGR_UNDERLINE + M + TEXT + ESC + SGR_RESET_UNDERLINE + M + SUFFIX, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .blink().append(TEXT).resetBlink(SUFFIX).toString(false);
        assertEquals(ESC + SGR_BLINK + M + TEXT + ESC + SGR_RESET_BLINK + M + SUFFIX, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .hide().append(TEXT).resetHidden(SUFFIX).toString(false);
        assertEquals(ESC + SGR_HIDE + M + TEXT + ESC + SGR_RESET_HIDE + M + SUFFIX, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .invertColor().append(TEXT).resetInvertColors(SUFFIX).toString(false);
        assertEquals(ESC + SGR_INVERT + M + TEXT + ESC + SGR_RESET_INVERT + M + SUFFIX, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .strikeThrough().append(TEXT).resetStrikeThrough(SUFFIX).toString(false);
        assertEquals(ESC + SGR_STRIKE + M + TEXT + ESC + SGR_RESET_STRIKE + M + SUFFIX, result);
    }


    /**
     * Test reset and reset with value
     */
    @Test
    public void testReset() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .bold().append(TEXT).reset().toString(false);
        assertEquals(ESC + SGR_BOLD + M + TEXT + ESC + SGR_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .bold().append(TEXT).reset("after").toString(false);
        assertEquals(ESC + SGR_BOLD + M + TEXT + ESC + SGR_RESET + M + "after", result);
    }


    /**
     * Test 4-bit foreground color
     */
    @Test
    public void testForegroundColor() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color(ForegroundColor.CYAN).append(TEXT).resetColor(ColorReset.FG).toString(false);
        assertEquals(ESC + "36" + M + TEXT + ESC + SGR_FG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color(ForegroundColor.RED, sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + "31" + M + TEXT + ESC + SGR_FG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color(ForegroundColor.GREEN, TEXT).toString(false);
        assertEquals(ESC + "32" + M + TEXT + ESC + SGR_FG_RESET + M, result);
    }


    /**
     * Test 4-bit background color
     */
    @Test
    public void testBackgroundColor() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color(BackgroundColor.BLUE).append(TEXT).resetColor(ColorReset.BG).toString(false);
        assertEquals(ESC + "44" + M + TEXT + ESC + SGR_BG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color(BackgroundColor.RED, sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + "41" + M + TEXT + ESC + SGR_BG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color(BackgroundColor.GREEN, TEXT).toString(false);
        assertEquals(ESC + "42" + M + TEXT + ESC + SGR_BG_RESET + M, result);
    }


    /**
     * Test combined foreground and background color
     */
    @Test
    public void testForegroundAndBackgroundColor() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color(ForegroundColor.CYAN, BackgroundColor.BLUE).append(TEXT).resetColor(ColorReset.ALL).toString(false);
        assertEquals(ESC + "36" + M + ESC + "44" + M + TEXT + ESC + SGR_FG_RESET + M + ESC + SGR_BG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color(ForegroundColor.RED, BackgroundColor.GREEN, sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + "31" + M + ESC + "42" + M + TEXT + ESC + SGR_FG_RESET + M + ESC + SGR_BG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color(ForegroundColor.YELLOW, BackgroundColor.MAGENTA, TEXT).toString(false);
        assertEquals(ESC + "33" + M + ESC + "45" + M + TEXT + ESC + SGR_FG_RESET + M + ESC + SGR_BG_RESET + M, result);
    }


    /**
     * Test 8-bit foreground color
     */
    @Test
    public void testColor8() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color8(200).append(TEXT).resetColor(ColorReset.FG).toString(false);
        assertEquals(ESC + "38;5;200" + M + TEXT + ESC + SGR_FG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color8(100, sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + "38;5;100" + M + TEXT + ESC + SGR_FG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color8(50, TEXT).toString(false);
        assertEquals(ESC + "38;5;50" + M + TEXT + ESC + SGR_FG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color8(0, TEXT).toString(false);
        assertEquals(ESC + "38;5;0" + M + TEXT + ESC + SGR_FG_RESET + M, result);
        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color8(255, TEXT).toString(false);
        assertEquals(ESC + "38;5;255" + M + TEXT + ESC + SGR_FG_RESET + M, result);
    }


    /**
     * Test 8-bit foreground color out of range
     */
    @Test
    public void testColor8Invalid() {
        assertThrows(IllegalArgumentException.class, () -> new AnsiStringBuilder().setAnsiColor(AnsiColor.ON).color8(-1));
        assertThrows(IllegalArgumentException.class, () -> new AnsiStringBuilder().setAnsiColor(AnsiColor.ON).color8(256));
    }


    /**
     * Test 8-bit background color
     */
    @Test
    public void testBackgroundColor8() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .backgroundColor8(200).append(TEXT).resetColor(ColorReset.BG).toString(false);
        assertEquals(ESC + "48;5;200" + M + TEXT + ESC + SGR_BG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .backgroundColor8(100, sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + "48;5;100" + M + TEXT + ESC + SGR_BG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .backgroundColor8(50, TEXT).toString(false);
        assertEquals(ESC + "48;5;50" + M + TEXT + ESC + SGR_BG_RESET + M, result);
    }


    /**
     * Test 8-bit background color out of range
     */
    @Test
    public void testBackgroundColor8Invalid() {
        assertThrows(IllegalArgumentException.class, () -> new AnsiStringBuilder().setAnsiColor(AnsiColor.ON).backgroundColor8(-1));
        assertThrows(IllegalArgumentException.class, () -> new AnsiStringBuilder().setAnsiColor(AnsiColor.ON).backgroundColor8(256));
    }


    /**
     * Test 24-bit foreground color with RGB
     */
    @Test
    public void testColor24Rgb() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color24(255, 128, 0).append(TEXT).resetColor(ColorReset.FG).toString(false);
        assertEquals(ESC + "38;2;255;128;0" + M + TEXT + ESC + SGR_FG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color24(10, 20, 30, sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + "38;2;10;20;30" + M + TEXT + ESC + SGR_FG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color24(0, 255, 0, TEXT).toString(false);
        assertEquals(ESC + "38;2;0;255;0" + M + TEXT + ESC + SGR_FG_RESET + M, result);
    }


    /**
     * Test 24-bit foreground color with hex
     */
    @Test
    public void testColor24Hex() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color24("#ff8000").append(TEXT).resetColor(ColorReset.FG).toString(false);
        assertEquals(ESC + "38;2;255;128;0" + M + TEXT + ESC + SGR_FG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color24("#00ff00", sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + "38;2;0;255;0" + M + TEXT + ESC + SGR_FG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .color24("#0000ff", TEXT).toString(false);
        assertEquals(ESC + "38;2;0;0;255" + M + TEXT + ESC + SGR_FG_RESET + M, result);
    }


    /**
     * Test 24-bit color invalid input
     */
    @Test
    public void testColor24Invalid() {
        assertThrows(IllegalArgumentException.class, () -> new AnsiStringBuilder().setAnsiColor(AnsiColor.ON).color24(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new AnsiStringBuilder().setAnsiColor(AnsiColor.ON).color24(0, 256, 0));
        assertThrows(IllegalArgumentException.class, () -> new AnsiStringBuilder().setAnsiColor(AnsiColor.ON).color24(0, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> new AnsiStringBuilder().setAnsiColor(AnsiColor.ON).color24("invalid"));
        assertThrows(IllegalArgumentException.class, () -> new AnsiStringBuilder().setAnsiColor(AnsiColor.ON).color24("#gggggg"));
        assertThrows(IllegalArgumentException.class, () -> new AnsiStringBuilder().setAnsiColor(AnsiColor.ON).color24("#fff"));
    }


    /**
     * Test 24-bit background color with RGB
     */
    @Test
    public void testBackgroundColor24Rgb() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .backgroundColor24(255, 128, 0).append(TEXT).resetColor(ColorReset.BG).toString(false);
        assertEquals(ESC + "48;2;255;128;0" + M + TEXT + ESC + SGR_BG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .backgroundColor24(10, 20, 30, sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + "48;2;10;20;30" + M + TEXT + ESC + SGR_BG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .backgroundColor24(0, 255, 0, TEXT).toString(false);
        assertEquals(ESC + "48;2;0;255;0" + M + TEXT + ESC + SGR_BG_RESET + M, result);
    }


    /**
     * Test 24-bit background color with hex
     */
    @Test
    public void testBackgroundColor24Hex() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .backgroundColor24("#ff8000").append(TEXT).resetColor(ColorReset.BG).toString(false);
        assertEquals(ESC + "48;2;255;128;0" + M + TEXT + ESC + SGR_BG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .backgroundColor24("#00ff00", sb -> sb.append(TEXT)).toString(false);
        assertEquals(ESC + "48;2;0;255;0" + M + TEXT + ESC + SGR_BG_RESET + M, result);

        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .backgroundColor24("#0000ff", TEXT).toString(false);
        assertEquals(ESC + "48;2;0;0;255" + M + TEXT + ESC + SGR_BG_RESET + M, result);
    }


    /**
     * Test 24-bit background color invalid input
     */
    @Test
    public void testBackgroundColor24Invalid() {
        assertThrows(IllegalArgumentException.class, () -> new AnsiStringBuilder().setAnsiColor(AnsiColor.ON).backgroundColor24(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new AnsiStringBuilder().setAnsiColor(AnsiColor.ON).backgroundColor24(0, 256, 0));
        assertThrows(IllegalArgumentException.class, () -> new AnsiStringBuilder().setAnsiColor(AnsiColor.ON).backgroundColor24("invalid"));
    }


    /**
     * Test ansi method for raw escape codes
     */
    @Test
    public void testAnsi() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .ansi("42").append(TEXT).toString(false);
        assertEquals(ESC + "42" + M + TEXT, result);
    }


    /**
     * Test toString with reset=true auto-appends reset
     */
    @Test
    public void testToStringWithReset() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .bold().append(TEXT).toString(true);
        assertTrue(result.endsWith(ESC + SGR_RESET + M));
    }


    /**
     * Test toString with strip removes ANSI sequences
     */
    @Test
    public void testToStringWithStrip() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .bold().append("hello").reset().color(ForegroundColor.RED).append(" world")
                .toString(false, true);
        assertEquals("hello world", result);
    }


    /**
     * Test that OFF mode produces no escape sequences
     */
    @Test
    public void testAnsiOff() {
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.OFF)
                .bold().append(TEXT).reset().toString(false);
        assertEquals(TEXT, result);
    }


    /**
     * Test setAnsiColor returns builder for chaining
     */
    @Test
    public void testSetAnsiColorChaining() {
        AnsiStringBuilder builder = new AnsiStringBuilder();
        AnsiStringBuilder returned = builder.setAnsiColor(AnsiColor.ON);
        assertEquals(builder, returned);
    }


    /**
     * Test resetColor with each ColorReset value
     */
    @Test
    public void testResetColorVariants() {
        // FG reset
        String result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .resetColor(ColorReset.FG).toString(false);
        assertEquals(ESC + SGR_FG_RESET + M, result);

        // BG reset
        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .resetColor(ColorReset.BG).toString(false);
        assertEquals(ESC + SGR_BG_RESET + M, result);

        // ALL reset
        result = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON)
                .resetColor(ColorReset.ALL).toString(false);
        assertEquals(ESC + SGR_FG_RESET + M + ESC + SGR_BG_RESET + M, result);
    }
}
