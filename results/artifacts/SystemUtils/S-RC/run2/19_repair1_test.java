package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

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
            Mockito.mockStatic(System.class);
        }

    @Test
        public void testGetJavaHome_2() {
            Mockito.when(System.getenv("JAVA_HOME")).thenReturn("/mock/java/home");
            assertEquals(new File("/mock/java/home"), SystemUtils.getJavaHome());
        }

    @Test
        public void testGetUserNameWithDefaultValue_2() {
            Mockito.when(System.getenv("USER")).thenReturn(null);
            assertEquals("defaultUser", SystemUtils.getUserName("defaultUser"));
        }

    @Test
        public void testIsJavaVersionAtLeast() {
            Mockito.when(System.getProperty("java.specification.version")).thenReturn("17");
            assertTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_17));
        }

    @Test
        public void testIsJavaVersionAtMost() {
            Mockito.when(System.getProperty("java.specification.version")).thenReturn("17");
            assertTrue(SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_17));
        }

    @Test
        public void testGetEnvironmentVariableDefaultValue() {
            Mockito.when(System.getenv("NON_EXISTENT_VAR")).thenReturn(null);
            assertEquals("defaultValue", SystemUtils.getEnvironmentVariable("NON_EXISTENT_VAR", "defaultValue"));
        }

    @Test
        public void testGetHostNameWindows() {
            Mockito.when(System.getenv("COMPUTERNAME")).thenReturn("MockComputer");
            assertEquals("MockComputer", SystemUtils.getHostName());
        }

    @Test
        public void testGetHostNameUnix() {
            Mockito.when(System.getenv("HOSTNAME")).thenReturn("MockHost");
            assertEquals("MockHost", SystemUtils.getHostName());
        }

    @Test
        public void testGetJavaIoTmpDir() {
            Mockito.when(System.getProperty("java.io.tmpdir")).thenReturn("/mock/tmp/dir");
            assertEquals(new File("/mock/tmp/dir"), SystemUtils.getJavaIoTmpDir());
        }

    @Test
        public void testGetUserDir() {
            Mockito.when(System.getProperty("user.dir")).thenReturn("/mock/user/dir");
            assertEquals(new File("/mock/user/dir"), SystemUtils.getUserDir());
        }

    @Test
        public void testGetUserHome() {
            Mockito.when(System.getProperty("user.home")).thenReturn("/mock/user/home");
            assertEquals(new File("/mock/user/home"), SystemUtils.getUserHome());
        }

    @Test
        public void testIsJavaAwtHeadlessTrue() {
            Mockito.when(System.getenv("JAVA_AWT_HEADLESS")).thenReturn("true");
            assertTrue(SystemUtils.isJavaAwtHeadless());
        }

    @Test
        public void testIsJavaAwtHeadlessFalse() {
            Mockito.when(System.getenv("JAVA_AWT_HEADLESS")).thenReturn("false");
            assertFalse(SystemUtils.isJavaAwtHeadless());
        }

    @Test
        public void testGetUserNameSecurityException() {
            Mockito.doThrow(new SecurityException()).when(System.class).getenv(Mockito.anyString());
            assertEquals("", SystemUtils.getUserName(""));
        }
}
