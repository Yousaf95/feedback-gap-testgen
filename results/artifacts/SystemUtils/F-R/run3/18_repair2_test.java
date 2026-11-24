package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SystemUtils_GenTest {

    private Properties mockProperties;

    @BeforeEach
    public void setUp() {
        mockProperties = mock(Properties.class);
        System.setProperties(mockProperties);
    }

    @Test
    public void testGetJavaVersionMatches_true() {
        when(mockProperties.getProperty("java.version")).thenReturn("1.8");
        assertTrue(SystemUtils.IS_JAVA_1_8);
    }

    @Test
    public void testGetJavaVersionMatches_false() {
        when(mockProperties.getProperty("java.version")).thenReturn("1.7");
        assertFalse(SystemUtils.IS_JAVA_1_8);
    }

    @ParameterizedTest
    @ValueSource(strings = {"AIX", "HP-UX", "OS/400", "Irix", "Linux", "Mac", "Mac OS X", "FreeBSD", "OpenBSD", "NetBSD", "OS/2", "Solaris", "SunOS"})
    public void testGetOsMatchesName_true(String osName) {
        when(mockProperties.getProperty("os.name")).thenReturn(osName);
        assertTrue(SystemUtils.IS_OS_LINUX); // This will be true for any OS name
    }

    @ParameterizedTest
    @ValueSource(strings = {"Windows 2000", "Windows 2003", "Windows Server 2008", "Windows Server 2012", "Windows 95", "Windows 98", "Windows ME", "Windows NT", "Windows XP", "Windows Vista", "Windows 7", "Windows 8", "Windows 10", "Windows 11"})
    public void testGetOsMatchesName_windows_true(String osName) {
        when(mockProperties.getProperty("os.name")).thenReturn(osName);
        assertTrue(SystemUtils.IS_OS_WINDOWS); // This will be true for any Windows OS name
    }

    @Test
    public void testGetEnvironmentVariable_defaultValue() {
        assertNull(SystemUtils.getEnvironmentVariable("NON_EXISTENT_VAR", "default"));
    }

    @Test
    public void testGetHostName_windows() {
        when(mockProperties.getProperty("os.name")).thenReturn("Windows 10");
        when(mockProperties.getenv("COMPUTERNAME")).thenReturn("TESTPC");
        assertEquals("TESTPC", SystemUtils.getHostName());
    }

    @Test
    public void testGetHostName_nonWindows() {
        when(mockProperties.getProperty("os.name")).thenReturn("Linux");
        when(mockProperties.getenv("HOSTNAME")).thenReturn("testhost");
        assertEquals("testhost", SystemUtils.getHostName());
    }

    @Test
    public void testGetJavaHome() {
        when(mockProperties.getProperty("java.home")).thenReturn("/usr/lib/jvm/java-17-openjdk-amd64");
        File expected = new File("/usr/lib/jvm/java-17-openjdk-amd64");
        assertEquals(expected, SystemUtils.getJavaHome());
    }

    @Test
    public void testGetJavaIoTmpDir() {
        when(mockProperties.getProperty("java.io.tmpdir")).thenReturn("/tmp");
        File expected = new File("/tmp");
        assertEquals(expected, SystemUtils.getJavaIoTmpDir());
    }

    @Test
    public void testGetUserDir() {
        when(mockProperties.getProperty("user.dir")).thenReturn("/home/user");
        File expected = new File("/home/user");
        assertEquals(expected, SystemUtils.getUserDir());
    }

    @Test
    public void testGetUserHome() {
        when(mockProperties.getProperty("user.home")).thenReturn("/home/user");
        File expected = new File("/home/user");
        assertEquals(expected, SystemUtils.getUserHome());
    }

    @Test
    public void testGetUserName_defaultValue() {
        assertNull(SystemUtils.getUserName("default"));
    }

    @Test
    public void testIsJavaAwtHeadless_false() {
        when(mockProperties.getProperty("java.awt.headless")).thenReturn("false");
        assertFalse(SystemUtils.isJavaAwtHeadless());
    }

    @Test
    public void testIsJavaAwtHeadless_true() {
        when(mockProperties.getProperty("java.awt.headless")).thenReturn("true");
        assertTrue(SystemUtils.isJavaAwtHeadless());
    }
}
