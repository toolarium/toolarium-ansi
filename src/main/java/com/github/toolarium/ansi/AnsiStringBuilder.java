/*
 * AnsiStringBuilder.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi;

import com.github.toolarium.ansi.color.BackgroundColor;
import com.github.toolarium.ansi.color.ColorReset;
import com.github.toolarium.ansi.color.ForegroundColor;

/**
 * A string builder with built-in support for ANSI escape sequences.
 *
 * @author patrick
 * @author Nathan Fiscaletti
 */
public class AnsiStringBuilder {
    private java.lang.StringBuilder internal;
    private AnsiColor ansiColor = AnsiColor.AUTO;

    
    /**
     * Constructs a string builder with no characters in it and an initial capacity of 16 characters.
     */
    public AnsiStringBuilder() {
        this.internal = new java.lang.StringBuilder();
    }

    
    /**
     * Constructs an ansi string builder that contains the same characters as the specified CharSequence.
     *
     * @param charSequence the character sequence
     */
    public AnsiStringBuilder(CharSequence charSequence) {
        this.internal = new java.lang.StringBuilder(charSequence);
    }

    
    /**
     * Constructs an ansi string builder with no characters in it and an
     * initial capacity specified by the capacity argument.
     *
     * @param capacity the capacity
     */
    public AnsiStringBuilder(int capacity) {
        this.internal = new java.lang.StringBuilder(capacity);
    }

    
    /**
     * Constructs an ansi string builder initialized to the contents of the specified string.
     *
     * @param string the string
     */
    public AnsiStringBuilder(String string) {
        this.internal = new java.lang.StringBuilder(string);
    }
    
    
    /**
     * Resets the hidden flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder resetHidden() {
        return this.ansi(Integer.toString(28));
    }

    
    /**
     * Resets the hidden flag and appends the string representation of the value.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder resetHidden(T value) {
        this.resetHidden();
        this.append(value);
        return this;
    }

    
    /**
     * Resets the invert-colors flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder resetInvertColors() {
        return this.ansi(Integer.toString(27));
    }

    
    /**
     * Resets the invert-colors flag and appends the string representation of the value.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder resetInvertColors(T value) {
        this.resetInvertColors();
        this.append(value);
        return this;
    }

    
    /**
     * Resets the blink flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder resetBlink() {
        return this.ansi(Integer.toString(25));
    }

    
    /**
     * Resets the blink flag and appends the string representation of the value.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder resetBlink(T value) {
        this.resetBlink();
        this.append(value);
        return this;
    }

    
    /**
     * Resets the underline flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder resetUnderline() {
        return this.ansi(Integer.toString(24));
    }

    
    /**
     * Resets the underline flag and appends the string representation of the value.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder resetUnderline(T value) {
        this.resetUnderline();
        this.append(value);
        return this;
    }

    
    /**
     * Resets the strike through flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder resetStrikeThrough() {
        return this.ansi(Integer.toString(29));
    }

    
    /**
     * Resets the strike-through flag and appends the string representation of the value.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder resetStrikeThrough(T value) {
        this.resetStrikeThrough();
        this.append(value);
        return this;
    }

    
    /**
     * Resets the dim flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder resetDim() {
        return this.ansi(Integer.toString(22));
    }

    
    /**
     * Resets the dim flag and appends the string representation of the value.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder resetDim(T value) {
        this.resetDim();
        this.append(value);
        return this;
    }

    
    /**
     * Resets the bold flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder resetBold() {
        return this.ansi(Integer.toString(22));
    }

    
    /**
     * Resets the bold flag and appends the string representation of the value.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder resetBold(T value) {
        this.resetBold();
        this.append(value);
        return this;
    }

    
    /**
     * Resets the italic flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder resetItalic() {
        return this.ansi(Integer.toString(23));
    }

    
    /**
     * Resets the italic flag and appends the string representation of the value.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder resetItalic(T value) {
        this.resetItalic();
        this.append(value);
        return this;
    }

    
    /**
     * Resets the formatting to default.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder reset() {
        return this.ansi(Integer.toString(0));
    }

    
    /**
     * Resets the formatting to default and appends the string representation of the value.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder reset(T value) {
        this.reset();
        this.append(value);
        return this;
    }

    
    /**
     * Set the hide flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder hide() {
        return this.ansi(Integer.toString(8));
    }

    
    /**
     * Appends the string representation of the value formatted with hide.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder hide(T value) {
        this.hide();
        this.append(value);
        this.resetHidden();
        return this;
    }

    
    /**
     * Set the strike through flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder strikeThrough() {
        return this.ansi(Integer.toString(9));
    }

    
    /**
     * Appends the string representation of the value formatted with strike-through.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder strikeThrough(T value) {
        this.strikeThrough();
        this.append(value);
        this.resetStrikeThrough();
        return this;
    }

    
    /**
     * Sets the strike-through flag, runs the function consumer on this string builder and resets the strike-through flag once finished.
     *
     * @param consumer the consumer
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder strikeThrough(java.util.function.Consumer<AnsiStringBuilder> consumer) {
        this.strikeThrough();
        consumer.accept(this);
        this.resetStrikeThrough();
        return this;
    }

    
    /**
     * Set the invert color flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder invertColor() {
        return this.ansi(Integer.toString(7));
    }

    
    /**
     * Appends the string representation of the value formatted with inverted colors.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder invertColor(T value) {
        this.invertColor();
        this.append(value);
        return this;
    }

    
    /**
     * Sets the invert-colors flag, runs the function consumer on this string builder and resets the invert-colors flag once finished.
     *
     * @param consumer the consumer
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder invertColor(java.util.function.Consumer<AnsiStringBuilder> consumer) {
        this.invertColor();
        consumer.accept(this);
        this.resetInvertColors();
        return this;
    }

    
    /**
     * Set the blink flag.
     *
     * @return AnsiStringBuilder
     */
    public AnsiStringBuilder blink() {
        return this.ansi(Integer.toString(5));
    }

    
    /**
     * Appends the string representation of the value formatted with blink.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder blink(T value) {
        this.blink();
        this.append(value);
        this.resetBlink();
        return this;
    }

    
    /**
     * Sets the blink flag, runs the function consumer on this string builder and resets the blink flag once finished.
     *
     * @param consumer the consumer
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder blink(java.util.function.Consumer<AnsiStringBuilder> consumer) {
        this.blink();
        consumer.accept(this);
        this.resetBlink();
        return this;
    }

    
    /**
     * Set the underline flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder underline() {
        return this.ansi(Integer.toString(4));
    }

    
    /**
     * Sets the underline flag, runs the function consumer on this string builder and resets the underline flag once finished.
     *
     * @param consumer the consumer
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder underline(java.util.function.Consumer<AnsiStringBuilder> consumer) {
        this.underline();
        consumer.accept(this);
        this.resetUnderline();
        return this;
    }

    
    /**
     * Appends the string representation of the value formatted with underline.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder underline(T value) {
        this.underline();
        this.append(value);
        this.resetUnderline();
        return this;
    }

    
    /**
     * Set the dim flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder dim() {
        return this.ansi(Integer.toString(2));
    }

    
    /**
     * Appends the string representation of the value with dim formatting.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder dim(T value) {
        this.dim();
        this.append(value);
        this.resetDim();
        return this;
    }

    
    /**
     * Sets the dim flag, runs the function consumer on this string builder and resets the dim flag once finished.
     *
     * @param consumer the  consumer
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder dim(java.util.function.Consumer<AnsiStringBuilder> consumer) {
        this.dim();
        consumer.accept(this);
        this.resetDim();
        return this;
    }

    
    /**
     * Set the bold flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder bold() {
        return this.ansi(Integer.toString(1));
    }

    
    /**
     * Appends the string representation of the value with bold formatting.
     *
     * @param <T> the type
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder bold(T value) {
        this.bold();
        this.append(value);
        this.resetBold();
        return this;
    }

    
    /**
     * Sets the bold flag, runs the function consumer on this string builder and resets the bold flag once finished.
     *
     * @param consumer the consumer
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder bold(java.util.function.Consumer<AnsiStringBuilder> consumer) {
        this.bold();
        consumer.accept(this);
        this.resetBold();
        return this;
    }

    
    /**
     * Set the italic flag.
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder italic() {
        return this.ansi(Integer.toString(3));
    }

    
    /**
     * Appends the string representation of the value with italic formatting.
     *
     * @param <T> the type
     * @param value the vlaue
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder italic(T value) {
        this.italic();
        this.append(value);
        this.resetItalic();
        return this;
    }

    
    /**
     * Sets the italic flag, runs the function consumer on this string builder and resets the italic
     * flag once finished.
     *
     * @param consumer the consumer
     *
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder italic(java.util.function.Consumer<AnsiStringBuilder> consumer) {
        this.italic();
        consumer.accept(this);
        this.resetItalic();
        return this;
    }

    
    /**
     * Sets a 4-bit foreground color.
     *
     * @param color the color
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder color(ForegroundColor color) {
        return this.ansi(Integer.toString(color.getValue()));
    }

    
    /**
     * Sets the 4-bit foreground color, runs the function consumer on this string builder and resets
     * the foreground color once finished.
     *
     * @param color the color
     * @param consumer the consumer
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder color(ForegroundColor color, java.util.function.Consumer<AnsiStringBuilder> consumer) {
        this.color(color);
        consumer.accept(this);
        this.resetColor(ColorReset.FG);
        return this;
    }

    
    /**
     * Sets a 4-bit background color.
     *
     * @param color the color
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder color(BackgroundColor color) {
        return this.ansi(Integer.toString(color.getValue()));
    }

    
    /**
     * Sets the 4-bit background color, runs the function consumer on this string builder and resets the background color once finished.
     *
     * @param color the color 
     * @param consumer the consumer 
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder color(BackgroundColor color, java.util.function.Consumer<AnsiStringBuilder> consumer) {
        this.color(color);
        consumer.accept(this);
        this.resetColor(ColorReset.BG);
        return this;
    }

    
    /**
     * Appends the string representation of the value formatted with the specified 4-bit foreground color.
     *
     * @param <T> the type
     * @param color the color 
     * @param value the value 
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder color(ForegroundColor color, T value) {
        this.color(color);
        this.append(value);
        this.resetColor(ColorReset.FG);
        return this;
    }

    /**
     * Appends the string representation of the value formatted with the specified 4-bit background color.
     *
     * @param <T> the type
     * @param color the color 
     * @param value the value 
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder color(BackgroundColor color, T value) {
        this.color(color);
        this.append(value);
        this.resetColor(ColorReset.BG);
        return this;
    }
    
    
    /**
     * Sets a 4-bit foreground and background color.
     *
     * @param fg the foreground color
     * @param bg the background color
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder color(ForegroundColor fg, BackgroundColor bg) {
        this.color(fg);
        this.color(bg);
        return this;
    }

    
    /**
     * Sets the 4-bit foreground and background colors, runs the function consumer on this string builder and resets the foreground and background colors once finished.
     *
     * @param fg the foreground color
     * @param bg the background color
     * @param consumer the consumer
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder color(ForegroundColor fg, BackgroundColor bg, java.util.function.Consumer<AnsiStringBuilder> consumer) {
        this.color(fg);
        this.color(bg);
        consumer.accept(this);
        this.resetColor(ColorReset.ALL);
        return this;
    }

    
    /**
     * Appends the string representation of the value formatted with the specified 4-bit foreground and background color.
     *
     * @param <T> the type
     * @param fg the foreground color
     * @param bg the background color
     * @param value the value
     * @return the AnsiStringBuilder
     */
    public <T> AnsiStringBuilder color(ForegroundColor fg, BackgroundColor bg, T value) {
        this.color(fg);
        this.color(bg);
        this.append(value);
        this.resetColor(ColorReset.ALL);
        return this;
    }
    
    
    /**
     * Resets the color.
     *
     * @param reset the reset color
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder resetColor(ColorReset reset) {
        switch (reset) {
            case FG:
                this.color(ForegroundColor.RESET);
                break;
            case BG:
                this.color(BackgroundColor.RESET);
                break;
            default:
            case ALL:
                this.color(ForegroundColor.RESET);
                this.color(BackgroundColor.RESET);
                break;
        }
        return this;
    }

    
    /**
     * Sets an 8-bit foreground color.
     *
     * @param color the color
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public AnsiStringBuilder color8(int color) throws IllegalArgumentException {
        if (color < 0 || color > 255) {
            throw new IllegalArgumentException("Valid 8-bit colors must be within the range of 0-255.");
        }

        return this.ansi("38;5;" + color);
    }

    
    /**
     * Sets the 8-bit foreground color, runs the function consumer on this string builder and resets the foreground colors once finished.
     *
     * @param color the color
     * @param consumer the consumer
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public AnsiStringBuilder color8(int color, java.util.function.Consumer<AnsiStringBuilder> consumer) throws IllegalArgumentException {
        this.color8(color);
        consumer.accept(this);
        this.resetColor(ColorReset.FG);
        return this;
    }

    
    /**
     * Appends the string representation of the value formatted with the specified 8-bit foreground color.
     *
     * @param <T> the type
     * @param color the color
     * @param value the value
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public <T> AnsiStringBuilder color8(int color, T value) throws IllegalArgumentException {
        this.color8(color);
        this.append(value);
        this.resetColor(ColorReset.FG);
        return this;
    }

    
    /**
     * Sets an 8-bit background color.
     *
     * @param color the color
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public AnsiStringBuilder backgroundColor8(int color) throws IllegalArgumentException {
        if (color < 0 || color > 255) {
            throw new IllegalArgumentException("Valid 8-bit colors must be within the range of 0-255.");
        }

        return this.ansi("48;5;" + color);
    }

    
    /**
     * Sets the 8-bit background color, runs the function consumer on this string builder and resets the background colors once finished.
     *
     * @param color the color
     * @param consumer the consumer
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public AnsiStringBuilder backgroundColor8(int color, java.util.function.Consumer<AnsiStringBuilder> consumer) throws IllegalArgumentException {
        this.backgroundColor8(color);
        consumer.accept(this);
        this.resetColor(ColorReset.BG);
        return this;
    }

    
    /**
     * Appends the string representation of the value formatted with the specified 8-bit background color.
     *
     * @param <T> the type
     * @param color the color
     * @param value the value
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public <T> AnsiStringBuilder backgroundColor8(int color, T value) throws IllegalArgumentException {
        this.backgroundColor8(color);
        this.append(value);
        this.resetColor(ColorReset.BG);
        return this;
    }

    
    /**
     * Sets a 24-bit foreground color.
     *
     * @param r the r-value
     * @param g the g-value
     * @param b the b-value
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public AnsiStringBuilder color24(int r, int g, int b) throws IllegalArgumentException {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException("Valid 24-bit RGB values must be within the range of 0-255.");
        }

        return this.ansi("38;2;" + r + ";" + g + ";" + b);
    }

    
    /**
     * Sets a 24-bit foreground color using a hexadecimal color value.
     * Example: sb.color24('#ffffff')
     *
     * @param hexColor the color
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public AnsiStringBuilder color24(String hexColor) throws IllegalArgumentException {
        if (!hexColor.matches("^\\#[A-Fa-f0-9]{6}$")) {
            throw new IllegalArgumentException("Invalid 24-bit hexadecimal color value.");
        }

        Integer r = Integer.valueOf(hexColor.substring(1, 3), 16);
        Integer g = Integer.valueOf(hexColor.substring(3, 5), 16);
        Integer b = Integer.valueOf(hexColor.substring(5, 7), 16);
        return this.color24(r, g, b);
    }

    
    /**
     * Sets the 24-bit foreground color, runs the function consumer on this string builder and resets the foreground colors once finished.
     *
     * @param r the r-value
     * @param g the g-value
     * @param b the b-value
     * @param consumer the consumer
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public AnsiStringBuilder color24(int r, int g, int b, java.util.function.Consumer<AnsiStringBuilder> consumer) throws IllegalArgumentException {
        this.color24(r, g, b);
        consumer.accept(this);
        this.resetColor(ColorReset.FG);
        return this;
    }

    
    /**
     * Sets the 24-bit foreground color using a hexadecimal color value, runs the function consumer on this string builder and resets the foreground colors once finished.
     *
     * @param hexColor the color
     * @param consumer the consumer
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public AnsiStringBuilder color24(String hexColor, java.util.function.Consumer<AnsiStringBuilder> consumer) throws IllegalArgumentException {
        this.color24(hexColor);
        consumer.accept(this);
        this.resetColor(ColorReset.FG);
        return this;
    }

    
    /**
     * Appends the string representation of the value formatted with the specified 24-bit foreground color.
     *
     * @param <T> the type
     * @param r the r-value
     * @param g the g-value
     * @param b the b-value
     * @param value the value
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public <T> AnsiStringBuilder color24(int r, int g, int b, T value) throws IllegalArgumentException {
        this.color24(r, g, b);
        this.append(value);
        this.resetColor(ColorReset.FG);
        return this;
    }

    
    /**
     * Appends the string representation of the value formatted with the specified 24-bit hexadecimal foreground color.
     *
     * @param <T> the type
     * @param hexColor the color
     * @param value the value
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public <T> AnsiStringBuilder color24(String hexColor, T value) throws IllegalArgumentException {
        this.color24(hexColor);
        this.append(value);
        this.resetColor(ColorReset.FG);
        return this;
    }

    
    /**
     * Sets an 24-bit background color.
     *
     * @param r the r-value
     * @param g the g-value
     * @param b the b-value
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public AnsiStringBuilder backgroundColor24(int r, int g, int b) throws IllegalArgumentException {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException("Valid 24-bit colors must be within the range of 0-255.");
        }

        return this.ansi("48;2;" + r + ";" + g + ";" + b);
    }

    
    /**
     * Sets an 24-bit background color using a hexadecimal color value.
     *
     * @param hexColor the color
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public AnsiStringBuilder backgroundColor24(String hexColor) throws IllegalArgumentException {
        if (!hexColor.matches("^\\#[A-Fa-f0-9]{6}$")) {
            throw new IllegalArgumentException("Invalid 24-bit hexadecimal color value.");
        }

        Integer r = Integer.valueOf(hexColor.substring(1, 3), 16);
        Integer g = Integer.valueOf(hexColor.substring(3, 5), 16);
        Integer b = Integer.valueOf(hexColor.substring(5, 7), 16);
        return backgroundColor24(r, g, b);
    }

    
    /**
     * Sets the 24-bit background color, runs the function consumer on this string builder and resets the background colors once finished.
     *
     * @param r the r-value
     * @param g the g-value
     * @param b the b-value
     * @param consumer the consimer
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public AnsiStringBuilder backgroundColor24(int r, int g, int b, java.util.function.Consumer<AnsiStringBuilder> consumer) throws IllegalArgumentException {
        this.backgroundColor24(r, g, b);
        consumer.accept(this);
        this.resetColor(ColorReset.BG);
        return this;
    }

    
    /**
     * Sets the 24-bit background color using a hexadecimal color value, runs the function consumer
     * on this string builder and resets the background colors once finished.
     *
     * @param hexColor the color
     * @param consumer the consumer
     * @return AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public AnsiStringBuilder backgroundColor24(String hexColor, java.util.function.Consumer<AnsiStringBuilder> consumer) throws IllegalArgumentException {
        this.backgroundColor24(hexColor);
        consumer.accept(this);
        this.resetColor(ColorReset.BG);
        return this;
    }

    
    /**
     * Appends the string representation of the value formatted with the specified 24-bit background color.
     *
     * @param <T> the type
     * @param r the r-value
     * @param g the g-value
     * @param b the b-value
     * @param value the value
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public <T> AnsiStringBuilder backgroundColor24(int r, int g, int b, T value) throws IllegalArgumentException {
        this.backgroundColor24(r, g, b);
        this.append(value);
        this.resetColor(ColorReset.BG);
        return this;
    }

    
    /**
     * Appends the string representation of the value formatted with the specified 24-bit hexadecimal background color.
     *
     * @param <T> the type
     * @param hexColor the hex color
     * @param value the value
     * @return the AnsiStringBuilder
     * @throws IllegalArgumentException In case of an invalid input
     */
    public <T> AnsiStringBuilder backgroundColor24(String hexColor, T value) throws IllegalArgumentException {
        this.backgroundColor24(hexColor);
        this.append(value);
        this.resetColor(ColorReset.BG);
        return this;
    }

    
    /**
     * Appends a custom ANSI flag.
     *
     * @param value the value
     * @return AnsiStringBuilder
     */
    public AnsiStringBuilder ansi(String value) {
        this.append(ansiColor.ansify(value));
        return this;
    }

    
    /**
     * Set the ansi color setting: ON, OFF, AUTO
     *
     * @param ansiColor the enumeration
     * @return the AnsiStringBuilder
     */
    public AnsiStringBuilder setAnsiColor(AnsiColor ansiColor) {
        this.ansiColor = ansiColor;
        return this;
    }

    
    /**
     * Appends the string representation of the value argument.
     *
     * @param <T> the type
     * @param value the value
     * @return AnsiStringBuilder
     */
    public <T> AnsiStringBuilder append(T value) {
        this.internal.append(value);
        return this;
    }

    
    /**
     * Same as calling toString(true)
     *
     * @return the String
     */
    @Override
    public String toString() {
        return this.toString(true);
    }

    
    /**
     * Returns a string representing the data in this sequence.
     *
     * @param reset If true, a final call to {@link #reset()} will be made before preparing the string.
     * @return the String
     */
    public String toString(boolean reset) {
        return this.toString(reset, false);
    }

    
    /**
     * Returns a string representing the data in this sequence.
     *
     * @param reset If true, a final call to {@link #reset()} will be made before preparing the string.
     * @param strip If true, the result will have all ANSI escape sequences removed before returning to caller.
     *
     * @return the String
     */
    public String toString(boolean reset, boolean strip) {
        if (reset) {
            this.reset();
        }

        String result = this.internal.toString();
        if (strip) {
            result = result.replaceAll("\u001B\\[[;\\d]*[ -/]*[@-~]", "");
        }

        return result;
    }
}