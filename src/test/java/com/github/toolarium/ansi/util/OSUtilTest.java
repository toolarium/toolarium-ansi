/*
 * OSUtilTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.ansi.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;


/**
 * Test the {@link OSUtil}.
 *
 * @author patrick
 */
public class OSUtilTest {

    /**
     * Test singleton instance
     */
    @Test
    public void testGetInstance() {
        OSUtil instance1 = OSUtil.getInstance();
        OSUtil instance2 = OSUtil.getInstance();
        assertNotNull(instance1);
        assertEquals(instance1, instance2);
    }


    /**
     * Test getOSType returns a valid type
     */
    @Test
    public void testGetOSType() {
        OSUtil.OSType osType = OSUtil.getInstance().getOSType();
        assertNotNull(osType);
    }


    /**
     * Test that exactly one OS type check returns true
     */
    @Test
    public void testExactlyOneOsTypeTrue() {
        OSUtil os = OSUtil.getInstance();
        int count = 0;
        if (os.isLinux()) {
            count++;
        }
        if (os.isMac()) {
            count++;
        }
        if (os.isWindows()) {
            count++;
        }
        if (os.isSolaris()) {
            count++;
        }
        if (os.isBSD()) {
            count++;
        }
        if (os.isUnix()) {
            count++;
        }
        assertEquals(1, count, "Exactly one OS type should be detected");
    }


    /**
     * Test OS type matches platform check methods
     */
    @Test
    public void testOsTypeMatchesPlatformCheck() {
        OSUtil os = OSUtil.getInstance();
        OSUtil.OSType type = os.getOSType();
        switch (type) {
            case LINUX:
                assertTrue(os.isLinux());
                break;
            case MAC:
                assertTrue(os.isMac());
                break;
            case WINDOWS:
                assertTrue(os.isWindows());
                break;
            case SOLARIS:
                assertTrue(os.isSolaris());
                break;
            case FREEBSD:
                assertTrue(os.isBSD());
                break;
            case UNIX:
                assertTrue(os.isUnix());
                break;
            default:
                break;
        }
    }


    /**
     * Test getOSArchitecture returns non-null
     */
    @Test
    public void testGetOSArchitecture() {
        String arch = OSUtil.getInstance().getOSArchitecture();
        assertNotNull(arch);
        assertTrue(arch.length() > 0);
    }


    /**
     * Test isCygwin returns without error
     */
    @Test
    public void testIsCygwin() {
        // Cannot control TERM env var but should not throw
        assertNotNull(Boolean.valueOf(OSUtil.getInstance().isCygwin()));
    }


    /**
     * Test isWSL returns without error
     */
    @Test
    public void testIsWSL() {
        assertNotNull(Boolean.valueOf(OSUtil.getInstance().isWSL()));
        // Second call should use cached value
        assertNotNull(Boolean.valueOf(OSUtil.getInstance().isWSL()));
    }


    /**
     * Test hasOsType returns without error
     */
    @Test
    public void testHasOsType() {
        assertNotNull(Boolean.valueOf(OSUtil.getInstance().hasOsType()));
    }


    /**
     * Test getCreationTimestamp for existing file
     */
    @Test
    public void testGetCreationTimestamp() {
        Path path = new File(".").toPath();
        long timestamp = OSUtil.getInstance().getCreationTimestamp(path);
        assertTrue(timestamp > 0, "Creation timestamp for existing directory should be positive");
    }


    /**
     * Test getCreationTimestamp for non-existing file returns -1
     */
    @Test
    public void testGetCreationTimestampNonExisting() {
        Path path = Path.of("non_existing_file_xyz_12345.txt");
        long timestamp = OSUtil.getInstance().getCreationTimestamp(path);
        assertEquals(-1, timestamp);
    }


    /**
     * Test getHostname returns non-null, non-empty string
     */
    @Test
    public void testGetHostname() {
        String hostname = OSUtil.getInstance().getHostname();
        assertNotNull(hostname);
        assertTrue(hostname.length() > 0);
    }


    /**
     * Test isUnlimitedJurisdiction returns without error
     */
    @Test
    public void testIsUnlimitedJurisdiction() {
        assertNotNull(Boolean.valueOf(OSUtil.getInstance().isUnlimitedJurisdiction()));
        // Second call should use cached value
        assertNotNull(Boolean.valueOf(OSUtil.getInstance().isUnlimitedJurisdiction()));
    }
}
