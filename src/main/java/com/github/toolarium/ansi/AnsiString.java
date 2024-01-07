/*
 * AnsiString.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi;

import com.github.toolarium.ansi.color.BackgroundColor;
import com.github.toolarium.ansi.color.ForegroundColor;


/**
 * Ansi string utility
 *  
 * @author patrick
 */
public final class AnsiString {

    
    /**
     * Constructor
     */
    private AnsiString() {
        // NOP
    }

    
    /**
     * Get the instance
     *
     * @return the instance
     */
    public static Builder getInstance() {
        return new Builder(AnsiColor.AUTO);
    }

    
    /**
     * Get the instance
     *
     * @param ansiColor the ansi color
     * @return the instance
     */
    public static Builder getInstance(AnsiColor ansiColor) {
        return new Builder(ansiColor);
    }


    /**
     * The builder
     * 
     * @author patrick
     */
    public static class Builder {
        private AnsiColor ansiColor;
        
        
        /**
         * Constructor for Builder
         *
         * @param ansiColor the ansi color
         */
        Builder(AnsiColor ansiColor) {
            this.ansiColor = ansiColor;
        }
        
        
        /**
         * Append text with color
         *
         * @param text the text
         * @param foregroundColor the colors
         * @return the output string
         */
        public String appendWithColor(String text, ForegroundColor foregroundColor) {
            AnsiStringBuilder builder = new AnsiStringBuilder().setAnsiColor(ansiColor);
            return builder.color(foregroundColor).append(text).toString();
        }

        
        /**
         * Append text with color
         *
         * @param text the text
         * @param foregroundColor the colors
         * @return the output string
         */
        public AnsiStringBuilder appendWithColor(StringBuilder text, ForegroundColor foregroundColor) {
            AnsiStringBuilder builder = new AnsiStringBuilder().setAnsiColor(ansiColor);
            return builder.color(foregroundColor).append(text).reset();
        }


        /**
         * Append text with color
         *
         * @param text the text
         * @param backgroundColor the colors
         * @return the output string
         */
        public String appendWithColor(String text, BackgroundColor backgroundColor) {
            AnsiStringBuilder builder = new AnsiStringBuilder().setAnsiColor(ansiColor);
            return builder.color(backgroundColor).append(text).reset().toString();
        }

        
        /**
         * Append text with color
         *
         * @param text the text
         * @param backgroundColor the colors
         * @return the output string
         */
        public AnsiStringBuilder appendWithColor(StringBuilder text, BackgroundColor backgroundColor) {
            AnsiStringBuilder builder = new AnsiStringBuilder().setAnsiColor(ansiColor);
            return builder.color(backgroundColor).append(text).reset();
        }


        /**
         * Append text with color
         *
         * @param text the text
         * @param foregroundColor the foreground color
         * @param backgroundColor the background color
         * @return the output string
         */
        public String appendWithColor(String text, ForegroundColor foregroundColor, BackgroundColor backgroundColor) {
            AnsiStringBuilder builder = new AnsiStringBuilder().setAnsiColor(ansiColor);
            return builder.color(foregroundColor, backgroundColor).append(text).reset().toString();
        }


        /**
         * Append text with color
         *
         * @param text the text
         * @param foregroundColor the foreground color
         * @param backgroundColor the background color
         * @return the output string
         */
        public AnsiStringBuilder appendWithColor(StringBuilder text, ForegroundColor foregroundColor, BackgroundColor backgroundColor) {
            AnsiStringBuilder builder = new AnsiStringBuilder().setAnsiColor(ansiColor);
            return builder.color(foregroundColor, backgroundColor).append(text).reset();
        }
    }
}
