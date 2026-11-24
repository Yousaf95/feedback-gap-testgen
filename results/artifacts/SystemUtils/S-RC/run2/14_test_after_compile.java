package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.mockito.Mockito.*;
import java.security.SecurityException;

public class SystemUtils_GenTest {

    private Properties systemProperties;

    @BeforeEach
    public void setUp() {
        systemProperties = new Properties();
        System.setProperties(systemProperties);
    }

    
    
    @Test
    public void testGetJavaHome() {
        systemProperties.setProperty("java.home", "/path/to/java/home");
        File javaHome = SystemUtils.getJavaHome();
        assertEquals("/path/to/java/home", javaHome.getPath());
    }

    
    @Test
    public void testGetUserNameWithDefaultValue() {
        systemProperties.setProperty("user.name", "testUser");
        assertEquals("testUser", SystemUtils.getUserName("defaultUser"));
    }

    @Test
    public void testIsJavaAwtHeadless() {
        systemProperties.setProperty("java.awt.headless", "true");
        assertTrue(SystemUtils.isJavaAwtHeadless());
    }

    // ---- added by coverage-guided refinement ----
    @BeforeEach
        public void setUp_2() {
            // Mock the System.getenv method to return specific values for testing
            mockStatic(System.class);
        }

    @Test
        public void testGetJavaHome_2() {
            when(System.getenv("JAVA_HOME")).thenReturn("/mock/java/home");
            assertEquals(new File("/mock/java/home"), SystemUtils.getJavaHome());
        }

    @Test
        public void testGetUserNameWithDefaultValue_2() {
            when(System.getenv("USER")).thenReturn(null);
            assertEquals("defaultUser", SystemUtils.getUserName("defaultUser"));
        }

    @Test
        public void testIsJavaVersionAtLeast() {
            when(System.getProperty("java.specification.version")).thenReturn("17");
            assertTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_17));
        }

    @Test
        public void testIsJavaVersionAtMost() {
            when(System.getProperty("java.specification.version")).thenReturn("17");
            assertTrue(SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_17));
        }

    @Test
        public void testGetEnvironmentVariableDefaultValue() {
            when(System.getenv("NON_EXISTENT_VAR")).thenReturn(null);
            assertEquals("defaultValue", SystemUtils.getEnvironmentVariable("NON_EXISTENT_VAR", "defaultValue"));
        }

    @Test
        public void testGetHostNameWindows() {
            when(System.getenv("COMPUTERNAME")).thenReturn("MockComputer");
            assertEquals("MockComputer", SystemUtils.getHostName());
        }

    @Test
        public void testGetHostNameUnix() {
            when(System.getenv("HOSTNAME")).thenReturn("MockHost");
            assertEquals("MockHost", SystemUtils.getHostName());
        }

    @Test
        public void testGetJavaIoTmpDir() {
            when(System.getProperty("java.io.tmpdir")).thenReturn("/mock/tmp/dir");
            assertEquals(new File("/mock/tmp/dir"), SystemUtils.getJavaIoTmpDir());
        }

    @Test
        public void testGetUserDir() {
            when(System.getProperty("user.dir")).thenReturn("/mock/user/dir");
            assertEquals(new File("/mock/user/dir"), SystemUtils.getUserDir());
        }

    @Test
        public void testGetUserHome() {
            when(System.getProperty("user.home")).thenReturn("/mock/user/home");
            assertEquals(new File("/mock/user/home"), SystemUtils.getUserHome());
        }

    @Test
        public void testIsJavaAwtHeadlessTrue() {
            when(System.getenv("JAVA_AWT_HEADLESS")).thenReturn("true");
            assertTrue(SystemUtils.isJavaAwtHeadless());
        }

    @Test
        public void testIsJavaAwtHeadlessFalse() {
            when(System.getenv("JAVA_AWT_HEADLESS")).thenReturn("false");
            assertFalse(SystemUtils.isJavaAwtHeadless());
        }

    @Test
        public void testGetUserNameSecurityException() {
            doThrow(new SecurityException()).when(System.class).getenv(anyString());
            assertEquals("", SystemUtils.getUserName(""));
        }
}
