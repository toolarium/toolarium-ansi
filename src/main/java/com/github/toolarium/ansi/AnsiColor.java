/*
 * AnsiColor.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi;


import com.github.toolarium.ansi.util.OSUtil;
import com.github.toolarium.ansi.util.TerminalUtil;
import java.lang.reflect.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Ansi detection: If Ansi.ON or Ansi.OFF is explicitly specified, either via system property toolariumi.ansi or programmatically, this value is used:
 * <ul>
 * <li>ANSI is disabled when environment variable NO_COLOR is defined (regardless of its value).</li>
 * <li>ANSI is enabled when environment variable CLICOLOR_FORCE is defined and has any value other than 0 (zero).</li>
 * <li>ANSI is enabled when system property os.name starts with "Windows" and JAnsi Console is installed.</li>
 * <li>ANSI is disabled when environment variable CLICOLOR == 0.</li>
 * <li>ANSI is disabled when environment variable ConEmuANSI == OFF.</li>
 * <li>ANSI is disabled when it guessed the program’s output stream is not connected to a terminal:</li> 
 *     when System.console() returns null. This check is omitted if guesses the program is running in a Windows Cygwin, MSYS or MSYS2 environment:</li> 
 *     when system property os.name starts with "Windows" and either environment variable TERM contains cygwin or starts with xterm or environment variable OSTYPE is defined.</li>
 * <li>ANSI is enabled when environment variable ANSICON is defined.</li>
 * <li>ANSI is enabled when environment variable CLICOLOR == 1.</li>
 * <li>ANSI is enabled when environment variable ConEmuANSI == ON.</li>
 * <li>ANSI is enabled when it detects the program is running in a non-Windows OS (system property os.name does not start with "Windows").</li>
 * <li>ANSI is enabled when it guesses the program is running in a Cygwin, MSYS or MSYS2 environment (either environment variable TERM contains cygwin or starts with xterm or environment variable OSTYPE is defined).</li>
 * </ul>
 * <p>The implementation is compatipel with the <a href=https://github.com/remkop/picocli>picoli</a></p>
 * 
 * @author patrick
 */
public enum AnsiColor {
    /** 
     * Define to auto detect if the platform supports the ansi colors and system property {@code "toolarium.ansi"} is not set to any value other than {@code "true"} (case insensitive). 
     */
    AUTO,
    
    /** 
     * Forced ON: always enabled ansi colors regardless of the platform. 
     */
    ON,
    
    /** 
     * Forced OFF: always enabled ansi colors regardless of the platform. 
     */
    OFF;
    
    /** toolarium ansi settimng */
    public static final String TOOLARIUM_ANSI = "toolarium.ansi";

    /** ANSI START ESCAPE SEQUENCE */
    public static final String ANSI_START_ESCAPE_SEQUENCE = "\u001B[";
    
    /** ANSI END ESCAPE SEQUENCE */
    public static final String ANSI_STOP_ESCAPE_SEQUENCE = "m";
    
    private static final Logger LOG = LoggerFactory.getLogger(AnsiColor.class);
    private Boolean jansiInstalled;
    private Boolean autoDetect;
    private Boolean toolariumTty;
    private String tooalriumSystemProperty;

    
    /** 
     * Returns {@code true} if ANSI escape codes should be emitted, {@code false} otherwise.
     * 
     * @return ON: {@code true}, 
     *         OFF: {@code false}, 
     *         AUTO: if system property {@code "toolarium.ansi"} has value {@code "tty"} (case-insensitive), then return {@code true} 
     *         if either {@code System.console() != null} or it guesses the application is running in a pseudo-terminal pty on a Linux emulator in Windows.
     *         If system property {@code "toolarium.ansi"} has value {@code "true"} (case-sensitive) then return {@code true}.
     *         Otherwise use it's heuristics for enabling ansi to determine whether the platform supports ANSI escape codes. 
     */
    public boolean isEnabled() {
        if (this == ON) {
            return true;
        }
        
        if (this == OFF) {
            return false;
        }
        
        if (tooalriumSystemProperty == null || !tooalriumSystemProperty.equals(System.getProperty(TOOLARIUM_ANSI))) {
            tooalriumSystemProperty = System.getProperty(TOOLARIUM_ANSI);
            
            if (tooalriumSystemProperty != null) {
                LOG.debug("Toolarim ansi setting: " + tooalriumSystemProperty);
            }
        }
        
        if (tooalriumSystemProperty == null || "AUTO".equalsIgnoreCase(tooalriumSystemProperty.trim())) {
            if (autoDetect == null) {
                autoDetect = doAutodetect();
                LOG.debug("Ansi auto detection result: " + autoDetect);
            }
            return autoDetect;
        }

        if (toolariumTty == null && tooalriumSystemProperty != null) {
            if ("TTY".equalsIgnoreCase(tooalriumSystemProperty.trim())) {
                toolariumTty = TerminalUtil.getInstance().isTTY() || TerminalUtil.getInstance().isPseudoTTY();
                if (toolariumTty) {
                    LOG.debug("Valid ansi tty setting and is proper tty or psedu tty: " + toolariumTty);
                } else {
                    LOG.debug("Valid ansi tty setting but is not proper tty nor psedu tty: " + toolariumTty);
                }
            } else {
                toolariumTty = Boolean.FALSE;
            }
        }
        
        return toolariumTty || ((tooalriumSystemProperty != null) && "true".equalsIgnoreCase(tooalriumSystemProperty.trim()));
    }

    
    /**
     * Force disabled: https://no-color.org/
     *
     * @return true or false
     */
    public boolean isForceDisabled() {
        return System.getenv("NO_COLOR") != null;
    }

    
    /**
     * Force enabled: Jan Niklas Hasse's https://bixense.com/clicolors/ proposal 
     * 
     * @return true or false
     */
    public boolean isForceEnabled() {
        return System.getenv("CLICOLOR_FORCE") != null && !"0".equals(System.getenv("CLICOLOR_FORCE"));
    }


    /**
     * Check if hint is disabled.
     * See Jan Niklas Hasse's https://bixense.com/clicolors/ proposal https://conemu.github.io/en/AnsiEscapeCodes.html#Environment_variable
     *
     * @return true or false
     */
    public boolean isHintDisabled() {
        return "0".equals(System.getenv("CLICOLOR")) || "OFF".equals(System.getenv("ConEmuANSI"));
    }
    

    /**
     * Check if hint is enabled.
     * https://github.com/adoxa/ansicon/blob/master/readme.txt, Jan Niklas Hasse's https://bixense.com/clicolors/
     * proposal, https://conemu.github.io/en/AnsiEscapeCodes.html#Environment_variable
     * 
     * @return true or false
     */
    public boolean isHintEnabled() {
        return System.getenv("ANSICON") != null || "1".equals(System.getenv("CLICOLOR")) || "ON".equals(System.getenv("ConEmuANSI"));
    }

    
    /**
     * The first time this method is called, it invokes the {@link #calcIsJansiConsoleInstalled()} method, caches its
     * result and returns this result; subsequently it returns the cached result.
     * 
     * @return true or false
     */
    public boolean isJansiConsoleInstalled() {
        if (jansiInstalled == null) {
            jansiInstalled = calcIsJansiConsoleInstalled();
        }
        return jansiInstalled;
    }


    /**
     * Appends a custom ANSI flag.
     *
     * @param value the value
     * @return AnsiStringBuilder
     */
    public String ansify(String value) {
        if (isEnabled()) {
            return new StringBuilder().append(ANSI_START_ESCAPE_SEQUENCE).append(value).append(ANSI_STOP_ESCAPE_SEQUENCE).toString();
        } else {
            return "";
        }
    }

    
    /**
     * Appends a custom ANSI flag.
     *
     * @param value the value
     * @return AnsiStringBuilder
     */
    public String ansify(int value) {
        if (isEnabled()) {
            return new StringBuilder().append(ANSI_START_ESCAPE_SEQUENCE).append(value).append(ANSI_STOP_ESCAPE_SEQUENCE).toString();
        } else {
            return "";
        }
    }

    
    /**
     * Auto detect if ansi is enabled
     *
     * @return true or false
     */
    private boolean doAutodetect() {
        if (isForceDisabled()) {
            LOG.debug("Force to disable ansi.");
            return false;
        }
        
        if (isForceEnabled()) {
            LOG.debug("Force to enable ansi.");
            return true;
        }
        
        if (OSUtil.getInstance().isWindows() && isJansiConsoleInstalled()) {
            LOG.debug("Found jansi support");
            return true;
        }
        // #630 JVM crash loading jansi.AnsiConsole on Linux
        if (isHintDisabled()) {
            LOG.debug("Found hint to disable ansi.");
            return false;
        }
        
        if (!TerminalUtil.getInstance().isTTY() && !TerminalUtil.getInstance().isPseudoTTY()) {
            LOG.debug("No tty or pseaudo tty, ansi disabled.");
            return false;
        }

        if (isHintEnabled()) {
            LOG.debug("Found hint to enable ansi.");
            return true;
        }

        if (!OSUtil.getInstance().isWindows()) {
            LOG.debug("Found hint to enable ansi.");
            return true;
        }

        if (TerminalUtil.getInstance().isXterm()) {
            LOG.debug("Found xterm to enable ansi.");
            return true;
        }

        
        if (OSUtil.getInstance().isCygwin()) {
            LOG.debug("Found cygwein environment to enable ansi.");
            return true;
        }

        /*
        if (OSUtil.getInstance().isWSL()) {
            LOG.debug("Found cygwein environment to enable ansi.");
            return true;
        }*/
        
        if (OSUtil.getInstance().hasOsType()) {
            LOG.debug("Found OS type to enable ansi.");
            return true;
        }

        return false;
    }

    
    /**
     * Returns {@code false} if system property {@code org.fusesource.jansi.Ansi.disable} is set to {@code "true"}
     * (case-insensitive); otherwise, returns {@code false} if the Jansi library is in the classpath but has been
     * disabled (either via system property {@code org.fusesource.jansi.Ansi.disable} or via a Jansi API call);
     * otherwise, returns {@code true} if the Jansi library is in the classpath and has been installed.
     * 
     * @return true or false
     */
    private boolean calcIsJansiConsoleInstalled() {
        try {
            // first check if JANSI was explicitly disabled _without loading any JANSI classes
            if (Boolean.getBoolean("org.fusesource.jansi.Ansi.disable")) {
                return false;
            }
            
            // the Ansi class internally also checks system property "org.fusesource.jansi.Ansi.disable" but may also have been set with Ansi.setEnabled or a custom detector
            Class<?> ansi = Class.forName("org.fusesource.jansi.Ansi");
            Boolean enabled = (Boolean) ansi.getDeclaredMethod("isEnabled").invoke(null);
            if (!enabled) {
                return false;
            }
            
            // loading this class will load the native library org.fusesource.jansi.internal.CLibrary
            Class<?> ansiConsole = Class.forName("org.fusesource.jansi.AnsiConsole");
            Field out = ansiConsole.getField("out");
            return out.get(null) == System.out;
        } catch (Exception reflectionFailed) {
            return false;
        }
    }
}