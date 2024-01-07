/*
 * TerminalUtil.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi.util;


import java.lang.reflect.Method;


/**
 * Terminal util class.
 * 
 * @author patrick
 */
public final class TerminalUtil {
    private transient Boolean tty;

    
    /**
     * Private class, the only instance of the singelton which will be created by accessing the holder class.
     *
     * @author patrick
     */
    private static class HOLDER {
        static final TerminalUtil INSTANCE = new TerminalUtil();
    }

    
    /**
     * Constructor
     */
    private TerminalUtil() {
        // NOP
    }

    
    /**
     * Get the instance
     *
     * @return the instance
     */
    public static TerminalUtil getInstance() {
        return HOLDER.INSTANCE;
    }

    
    /**
     * Check it it is a terminal
     *
     * @return true or false
     */
    public boolean isTTY() {
        if (tty == null) {
            tty = testTTY();
        }
        
        return tty;
    }

    
    /**
     * Identify if it is a pseudo terminal
     *
     * @return true or false
     */
    public boolean isPseudoTTY() {
        // Cygwin and MSYS use pseudo-tty and console is always null...
        return OSUtil.getInstance().isWindows() 
                && (isXterm() || OSUtil.getInstance().isCygwin() || OSUtil.getInstance().hasOsType());
    }

    
    /**
     * Check if terminal is a xterm
     *
     * @return true or false
     */
    public boolean isXterm() {
        return System.getenv("TERM") != null && System.getenv("TERM").startsWith("xterm");
    }


    /**
     * Test if it is a pseudo terminal or not.
     * http://stackoverflow.com/questions/1403772/how-can-i-check-if-a-java-programs-input-output-streams-are-connected-to-a-term
     * 
     * @return true it it is a terminal
     */
    private boolean testTTY() {
        try {
            Object console = System.class.getDeclaredMethod("console").invoke(null);
            if (console == null) {
                return false;
            }
            
            try { // [#2083][#2084] Java 22 update
                Method isTerminal = Class.forName("java.io.Console").getDeclaredMethod("isTerminal");
                return (Boolean) isTerminal.invoke(console);
            } catch (NoSuchMethodException e) {
                return true;
            }
        } catch (Throwable reflectionFailed) {
            return true;
        }
    }
}
