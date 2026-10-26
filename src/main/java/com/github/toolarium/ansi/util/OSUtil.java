/*
 * OSUtil.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi.util;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


/**
 * OS util
 *  
 * @author patrick
 */
public final class OSUtil {
    private static final String DEFAULT_CIPHER_NAME = "AES";
    private static final int UNLIMITED_JURISDICTION_KEY_SIZE = 1024;

    private final Method slf4jGetLogger;
    private final Method slf4jDebug;
    private final Method slf4jIsDebugEnabled;
    private final Map<Class<?>, Object> loggerCache = new ConcurrentHashMap<>();

    private OSType osType;
    private String osArchitecture;
    private volatile Boolean isWindowsWsl;
    private volatile Boolean isUnlimitedJurisdiction;


    /**
     * Private class, the only instance of the singelton which will be created by accessing the holder class.
     *
     * @author patrick
     */
    private static final class HOLDER {
        static final OSUtil INSTANCE = new OSUtil();
    }

    
    /**
     * Constructor
     */
    private OSUtil() {
        Method getLogger = null;
        Method debugMethod = null;
        Method isDebugEnabled = null;
        try {
            Class<?> factory = Class.forName("org.slf4j.LoggerFactory");
            getLogger = factory.getMethod("getLogger", Class.class);
            Object logger = getLogger.invoke(null, OSUtil.class);
            debugMethod = logger.getClass().getMethod("debug", String.class);
            isDebugEnabled = logger.getClass().getMethod("isDebugEnabled");
        } catch (Exception | NoClassDefFoundError e) {
            // slf4j not on classpath, debug logging disabled
        }
        this.slf4jGetLogger = getLogger;
        this.slf4jDebug = debugMethod;
        this.slf4jIsDebugEnabled = isDebugEnabled;

        osType = getOSType();
        osArchitecture = getOSArchitecture();
        isWindowsWsl = null;
        isUnlimitedJurisdiction = null;
    }

    
    /**
     * Get the instance
     *
     * @return the instance
     */
    public static OSUtil getInstance() {
        return HOLDER.INSTANCE;
    }

    
    /**
     * Define the OS types
     * 
     * @author patrick
     */
    public enum OSType {
        UNIX,
        LINUX,
        MAC,
        FREEBSD,
        SOLARIS,
        WINDOWS
    }

    
    /**
     * Get the operating system 
     *
     * @return the operating system
     */
    public OSType getOSType() {
        if (osType != null) {
            return osType;
        }
        
        final String osNameRaw = System.getProperty("os.name");
        if (osNameRaw == null) {
            return OSType.UNIX;
        }
        final String osName = osNameRaw.toLowerCase();
        if (osName.contains("linux")) {
            return OSType.LINUX;
        } else if (osName.contains("mac os x") || osName.contains("darwin") || osName.contains("osx")) {
            return OSType.MAC;
        } else if (osName.contains("win")) {
            return OSType.WINDOWS;
        } else if (osName.contains("sunos") || osName.contains("solaris")) {
            return OSType.SOLARIS;
        } else if (osName.contains("freebsd")) {
            return OSType.FREEBSD;
        }
        
        return OSType.UNIX;
    }

    
    /**
     * Check if the underlying operating system is a linux os.
     *
     * @return true if it is linux
     */
    public boolean isLinux() {
        return OSType.LINUX.equals(getOSType());
    }

    
    /**
     * Check if the underlying operating system is a linux os.
     *
     * @return true if it is linux
     */
    public boolean isMac() {
        return OSType.MAC.equals(getOSType());
    }

    
    /**
     * Check if the underlying operating system is a unix os.
     *
     * @return true if it is unix
     */
    public boolean isUnix() {
        return OSType.UNIX.equals(getOSType());
    }

    
    /**
     * Check if the underlying operating system is a freebsd os.
     *
     * @return true if it is freebsd
     */
    public boolean isBSD() {
        return OSType.FREEBSD.equals(getOSType());
    }

    
    /**
     * Check if the underlying operating system is a solaris os.
     *
     * @return true if it is solaris
     */
    public boolean isSolaris() {
        return OSType.SOLARIS.equals(getOSType());
    }

    
    /**
     * Check if the underlying operating system is a windows os.
     *
     * @return true if it is windows
     */
    public boolean isWindows() {
        return OSType.WINDOWS.equals(getOSType());
    }

    
    /**
     * Check if it is a cygwin environment
     *
     * @return true or false
     */
    public boolean isCygwin() {
        String term = System.getenv("TERM");
        return term != null && term.toLowerCase(Locale.ENGLISH).contains("cygwin");
    }


    /**
     * Check is it is a window wsl environment
     *
     * @return if it is a window wsl environment
     */
    public boolean isWSL() {
        if (isWindowsWsl != null) {
            return isWindowsWsl;
        }
        
        File file = new File("/proc/version");
        if (file.exists() && file.canRead()) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                isWindowsWsl = (content != null && content.trim().length() > 0 && content.toLowerCase().indexOf("microsoft") >= 0);
            } catch (Exception e) {
                isWindowsWsl = Boolean.FALSE;
            }
        } else {
            isWindowsWsl = Boolean.FALSE;
        }
        
        return isWindowsWsl;
    }
    
    
    /**
     * Check if an os type is et
     *
     * @return true or false
     */
    public boolean hasOsType() {
        // null on Windows unless on Cygwin or MSYS
        return System.getenv("OSTYPE") != null;
    }
    
    
    /**
     * Get the operating system architecture
     *
     * @return the operating system architecture
     */
    public String getOSArchitecture() {
        if (osArchitecture != null) {
            return osArchitecture;
        }

        final String osArch = System.getProperty("os.arch");
        if ("x86".equals(osArch)) {
            return "i386";
        } else if ("x86_64".equals(osArch)) {
            return "amd64";
        } else if ("powerpc".equals(osArch)) {
            return "ppc";
        }

        return osArch;
    }

    
    
    /**
     * Get the creation timestamp of the given path.
     *
     * @param path the path
     * @return the creation timestamp in milliseconds, or {@code -1} if the creation time could not be determined
     */
    public long getCreationTimestamp(Path path) {
        try {
            FileTime creationTime = (FileTime)Files.getAttribute(path, "creationTime");
            return creationTime.to(TimeUnit.MILLISECONDS);
        } catch (IOException e) {
            return -1;
        }
    }
    
    
    /**
     * Get the hostname
     *
     * @return the hostname
     */
    public String getHostname() {
        String hostname;

        // get hostname
        hostname = System.getenv("hostname");
        if (hostname == null || hostname.isEmpty()) {
            hostname = System.getenv("HOSTNAME");
            if (hostname == null || hostname.isEmpty()) {
                hostname = System.getenv("COMPUTERNAME");
            }
        }

        if (hostname == null || hostname.isEmpty()) {
            hostname = "localhost";
        }

        return hostname;
    }

    

    /**
     * Check if there is unlimited jurisdiction
     *
     * @return true if there is unlimited jurisdiction
     */
    public boolean isUnlimitedJurisdiction() {
        if (isUnlimitedJurisdiction != null) {
            return isUnlimitedJurisdiction;
        }
        
        try {
            javax.crypto.Cipher.getInstance(DEFAULT_CIPHER_NAME);
            if (javax.crypto.Cipher.getMaxAllowedKeyLength(DEFAULT_CIPHER_NAME) > UNLIMITED_JURISDICTION_KEY_SIZE) {
                isUnlimitedJurisdiction = Boolean.TRUE;
                return true;
            }
        } catch (java.security.NoSuchAlgorithmException noe) {
            return true;
        } catch (Exception e) {
            // NOP
        }

        isUnlimitedJurisdiction = Boolean.FALSE;
        return isUnlimitedJurisdiction;
    }


    /**
     * Log a debug message if slf4j is on the classpath. This allows optional debug logging
     * without requiring slf4j as a runtime dependency.
     *
     * @param callerClass the calling class (used as logger name)
     * @param msg the message
     */
    public void debug(Class<?> callerClass, String msg) {
        if (slf4jGetLogger == null || slf4jDebug == null) {
            return;
        }

        try {
            Object logger = loggerCache.computeIfAbsent(callerClass, c -> {
                try {
                    return slf4jGetLogger.invoke(null, c);
                } catch (Exception e) {
                    return null;
                }
            });

            if (logger != null && (slf4jIsDebugEnabled == null || Boolean.TRUE.equals(slf4jIsDebugEnabled.invoke(logger)))) {
                slf4jDebug.invoke(logger, msg);
            }
        } catch (Exception e) {
            // ignore
        }
    }
}
