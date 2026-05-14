/*
 * AnsiMain.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi.main;

import com.github.toolarium.ansi.AnsiColor;
import com.github.toolarium.ansi.AnsiString;
import com.github.toolarium.ansi.AnsiStringBuilder;
import com.github.toolarium.ansi.color.BackgroundColor;
import com.github.toolarium.ansi.color.ForegroundColor;
import com.github.toolarium.ansi.util.OSUtil;
import com.github.toolarium.ansi.util.TerminalUtil;
import java.io.PrintStream;


/**
 * Main class to demonstrate the capabilities of the toolarium-ansi library.
 *
 * @author patrick
 */
public final class AnsiMain {
    private static final PrintStream OUT = System.out; // CHECKSTYLE_IGNORE_THIS_LINE
    private static final String INDENT = "  ";
    private static final String SPACE = " ";

    /**
     * Constructor
     */
    private AnsiMain() {
        // NOP
    }


    /**
     * Main entry point.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AnsiColor ansiColor = AnsiColor.AUTO;

        // Parse optional argument to force ON/OFF
        if (args.length > 0) {
            if ("on".equalsIgnoreCase(args[0])) {
                ansiColor = AnsiColor.ON;
            } else if ("off".equalsIgnoreCase(args[0])) {
                ansiColor = AnsiColor.OFF;
            }
        }

        printEnvironmentInfo(ansiColor);
        println();
        printFormattingDemo(ansiColor);
        println();
        printForegroundColorDemo(ansiColor);
        println();
        printBackgroundColorDemo(ansiColor);
        println();
        printCombinedColorDemo(ansiColor);
        println();
        print8BitColorDemo(ansiColor);
        println();
        print24BitColorDemo(ansiColor);
        println();
        printAnsiStringDemo(ansiColor);
    }


    /**
     * Print environment and detection information.
     *
     * @param ansiColor the ansi color setting
     */
    private static void printEnvironmentInfo(AnsiColor ansiColor) {
        AnsiStringBuilder sb = new AnsiStringBuilder().setAnsiColor(AnsiColor.ON);
        sb.bold("toolarium-ansi Library Demo").append("\n");
        sb.append("==========================\n\n");
        OUT.println(sb);

        OUT.println("Environment:");
        OUT.println(INDENT + "OS Type:          " + OSUtil.getInstance().getOSType());
        OUT.println(INDENT + "OS Architecture:  " + OSUtil.getInstance().getOSArchitecture());
        OUT.println(INDENT + "OS Name:          " + System.getProperty("os.name"));
        OUT.println(INDENT + "Java Version:     " + System.getProperty("java.version"));
        OUT.println(INDENT + "Is Windows:       " + OSUtil.getInstance().isWindows());
        OUT.println(INDENT + "Is Cygwin:        " + OSUtil.getInstance().isCygwin());
        OUT.println(INDENT + "Has OSTYPE:       " + OSUtil.getInstance().hasOsType());
        OUT.println();

        OUT.println("Terminal Detection:");
        OUT.println(INDENT + "Is TTY:           " + TerminalUtil.getInstance().isTTY());
        OUT.println(INDENT + "Is Pseudo-TTY:    " + TerminalUtil.getInstance().isPseudoTTY());
        OUT.println(INDENT + "Is xterm:         " + TerminalUtil.getInstance().isXterm());
        OUT.println(INDENT + "TERM env:         " + System.getenv("TERM"));
        OUT.println();

        OUT.println("ANSI Color Detection (mode=" + ansiColor + "):");
        OUT.println(INDENT + "isEnabled:        " + ansiColor.isEnabled());
        OUT.println(INDENT + "isForceDisabled:  " + ansiColor.isForceDisabled() + INDENT + "(NO_COLOR=" + System.getenv("NO_COLOR") + ")");
        OUT.println(INDENT + "isForceEnabled:   " + ansiColor.isForceEnabled() + INDENT + "(CLICOLOR_FORCE=" + System.getenv("CLICOLOR_FORCE") + ")");
        OUT.println(INDENT + "isHintDisabled:   " + ansiColor.isHintDisabled());
        OUT.println(INDENT + "isHintEnabled:    " + ansiColor.isHintEnabled());
        OUT.println(INDENT + "JAnsi installed:  " + ansiColor.isJansiConsoleInstalled());

        if (ansiColor.isEnabled()) {
            AnsiStringBuilder result = new AnsiStringBuilder().setAnsiColor(ansiColor);
            result.append("\n  Result:           ").color(ForegroundColor.GREEN, "ANSI colors are SUPPORTED");
            OUT.println(result);
        } else {
            OUT.println("\n  Result:           ANSI colors are NOT supported (or disabled)");
        }
    }


    /**
     * Print text formatting demo.
     *
     * @param ansiColor the ansi color setting
     */
    private static void printFormattingDemo(AnsiColor ansiColor) {
        printSectionHeader(ansiColor, "Text Formatting (SGR attributes)");

        AnsiStringBuilder sb = new AnsiStringBuilder().setAnsiColor(ansiColor);
        sb.append(INDENT).bold("Bold text").append(INDENT);
        sb.dim("Dim text").append(INDENT);
        sb.italic("Italic text").append(INDENT);
        sb.underline("Underline text").append(INDENT);
        sb.strikeThrough("Strikethrough text").append(INDENT);
        sb.invertColor("Inverted text").append(INDENT);
        sb.blink("Blink text");
        OUT.println(sb);

        AnsiStringBuilder combined = new AnsiStringBuilder().setAnsiColor(ansiColor);
        combined.append(INDENT);
        combined.bold().italic().underline().append("Bold + Italic + Underline").reset();
        OUT.println(combined);
    }


    /**
     * Print 4-bit foreground color demo.
     *
     * @param ansiColor the ansi color setting
     */
    private static void printForegroundColorDemo(AnsiColor ansiColor) {
        printSectionHeader(ansiColor, "4-bit Foreground Colors");

        AnsiStringBuilder sb = new AnsiStringBuilder().setAnsiColor(ansiColor);
        sb.append(INDENT);
        for (ForegroundColor color : ForegroundColor.values()) {
            if (color == ForegroundColor.RESET) {
                continue;
            }
            sb.color(color, padRight(color.name(), 15));
        }
        OUT.println(sb);
    }


    /**
     * Print 4-bit background color demo.
     *
     * @param ansiColor the ansi color setting
     */
    private static void printBackgroundColorDemo(AnsiColor ansiColor) {
        printSectionHeader(ansiColor, "4-bit Background Colors");

        AnsiStringBuilder sb = new AnsiStringBuilder().setAnsiColor(ansiColor);
        sb.append(INDENT);
        for (BackgroundColor color : BackgroundColor.values()) {
            if (color == BackgroundColor.RESET) {
                continue;
            }
            sb.color(color, SPACE + padRight(color.name(), 14));
        }
        OUT.println(sb);
    }


    /**
     * Print combined foreground and background color demo.
     *
     * @param ansiColor the ansi color setting
     */
    private static void printCombinedColorDemo(AnsiColor ansiColor) {
        printSectionHeader(ansiColor, "Combined Foreground + Background");

        AnsiStringBuilder sb = new AnsiStringBuilder().setAnsiColor(ansiColor);
        sb.append(INDENT);
        sb.color(ForegroundColor.WHITE, BackgroundColor.RED, " White on Red ");
        sb.append(SPACE);
        sb.color(ForegroundColor.BLACK, BackgroundColor.GREEN, " Black on Green ");
        sb.append(SPACE);
        sb.color(ForegroundColor.BLACK, BackgroundColor.YELLOW, " Black on Yellow ");
        sb.append(SPACE);
        sb.color(ForegroundColor.WHITE, BackgroundColor.BLUE, " White on Blue ");
        sb.append(SPACE);
        sb.color(ForegroundColor.WHITE, BackgroundColor.MAGENTA, " White on Magenta ");
        OUT.println(sb);
    }


    /**
     * Print 8-bit color demo.
     *
     * @param ansiColor the ansi color setting
     */
    private static void print8BitColorDemo(AnsiColor ansiColor) {
        printSectionHeader(ansiColor, "8-bit Colors (256 colors)");

        // Standard colors 0-15
        AnsiStringBuilder sb = new AnsiStringBuilder().setAnsiColor(ansiColor);
        sb.append(INDENT + "Standard (0-15):  ");
        for (int i = 0; i < 16; i++) {
            sb.backgroundColor8(i, String.format(" %3d ", i));
        }
        OUT.println(sb);

        // Color cube sample 16-231
        AnsiStringBuilder cube = new AnsiStringBuilder().setAnsiColor(ansiColor);
        cube.append(INDENT + "Color cube:       ");
        for (int i = 16; i < 232; i += 6) {
            cube.backgroundColor8(i, INDENT);
        }
        OUT.println(cube);

        // Grayscale 232-255
        AnsiStringBuilder gray = new AnsiStringBuilder().setAnsiColor(ansiColor);
        gray.append(INDENT + "Grayscale:        ");
        for (int i = 232; i < 256; i++) {
            gray.backgroundColor8(i, INDENT);
        }
        OUT.println(gray);
    }


    /**
     * Print 24-bit color demo.
     *
     * @param ansiColor the ansi color setting
     */
    private static void print24BitColorDemo(AnsiColor ansiColor) {
        printSectionHeader(ansiColor, "24-bit Colors (True Color)");

        // Rainbow gradient
        AnsiStringBuilder rainbow = new AnsiStringBuilder().setAnsiColor(ansiColor);
        rainbow.append(INDENT + "Rainbow:     ");
        for (int i = 0; i < 80; i++) {
            double hue = (double) i / 80 * 360;
            int[] rgb = hueToRgb(hue);
            rainbow.backgroundColor24(rgb[0], rgb[1], rgb[2], SPACE);
        }
        OUT.println(rainbow);

        // Hex color examples
        AnsiStringBuilder hex = new AnsiStringBuilder().setAnsiColor(ansiColor);
        hex.append(INDENT + "Hex colors:  ");
        hex.color24("#FF6B6B", " #FF6B6B ").append(SPACE);
        hex.color24("#4ECDC4", " #4ECDC4 ").append(SPACE);
        hex.color24("#45B7D1", " #45B7D1 ").append(SPACE);
        hex.color24("#96CEB4", " #96CEB4 ").append(SPACE);
        hex.color24("#FFEAA7", " #FFEAA7 ").append(SPACE);
        hex.color24("#DDA0DD", " #DDA0DD ");
        OUT.println(hex);
    }


    /**
     * Print AnsiString utility demo.
     *
     * @param ansiColor the ansi color setting
     */
    private static void printAnsiStringDemo(AnsiColor ansiColor) {
        printSectionHeader(ansiColor, "AnsiString Utility");

        OUT.println(INDENT + AnsiString.getInstance(ansiColor).appendWithColor("Quick red text", ForegroundColor.RED));
        OUT.println(INDENT + AnsiString.getInstance(ansiColor).appendWithColor("Quick green text", ForegroundColor.GREEN));
        OUT.println(INDENT + AnsiString.getInstance(ansiColor).appendWithColor("Cyan on Blue", ForegroundColor.CYAN, BackgroundColor.BLUE));
        OUT.println();

        OUT.println("Usage: java -jar toolarium-ansi.jar [on|off]");
        OUT.println(INDENT + "on  - force ANSI colors on");
        OUT.println(INDENT + "off - force ANSI colors off");
    }


    /**
     * Print a section header.
     *
     * @param ansiColor the ansi color setting
     * @param title the section title
     */
    private static void printSectionHeader(AnsiColor ansiColor, String title) {
        AnsiStringBuilder sb = new AnsiStringBuilder().setAnsiColor(ansiColor);
        sb.bold().underline().append(title).reset();
        OUT.println(sb);
    }


    /**
     * Pad a string to the right.
     *
     * @param text the text
     * @param length the target length
     * @return the padded string
     */
    private static String padRight(String text, int length) {
        if (text.length() >= length) {
            return text;
        }
        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }


    /**
     * Convert a hue value to RGB.
     *
     * @param hue the hue (0-360)
     * @return int array with r, g, b values
     */
    private static int[] hueToRgb(double hue) {
        double c = 1.0;
        double x = 1.0 - Math.abs((hue / 60.0) % 2 - 1.0);
        double r;
        double g;
        double b;

        if (hue < 60) {
            r = c;
            g = x;
            b = 0;
        } else if (hue < 120) {
            r = x;
            g = c;
            b = 0;
        } else if (hue < 180) {
            r = 0;
            g = c;
            b = x;
        } else if (hue < 240) {
            r = 0;
            g = x;
            b = c;
        } else if (hue < 300) {
            r = x;
            g = 0;
            b = c;
        } else {
            r = c;
            g = 0;
            b = x;
        }

        return new int[]{(int) (r * 255), (int) (g * 255), (int) (b * 255)};
    }


    /**
     * Print an empty line.
     */
    private static void println() {
        OUT.println();
    }
}
