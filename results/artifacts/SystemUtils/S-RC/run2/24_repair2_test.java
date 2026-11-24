package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    @Test
        public void testGetJavaHome_2() {
            System.setProperty("JAVA_HOME", "/mock/java/home");
            assertEquals(new File("/mock/java/home"), SystemUtils.getJavaHome());
        }

    @Test
        public void testGetUserNameWithDefaultValue_2() {
            System.clearProperty("USER");
            assertEquals("defaultUser", SystemUtils.getUserName("defaultUser"));
        }

    @ParameterizedTest
        @ValueSource(strings = {"17"})
        public void testIsJavaVersionAtLeast(String version) {
            System.setProperty("java.specification.version", version);
            assertTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_17));
        }

    @ParameterizedTest
        @ValueSource(strings = {"17"})
        public void testIsJavaVersionAtMost(String version) {
            System.setProperty("java.specification.version", version);
            assertTrue(SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_17));
        }

    @Test
        public void testGetEnvironmentVariableDefaultValue() {
            System.clearProperty("NON_EXISTENT_VAR");
            assertEquals("defaultValue", SystemUtils.getEnvironmentVariable("NON_EXISTENT_VAR", "defaultValue"));
        }

    @ParameterizedTest
        @ValueSource(strings = {"MockComputer"})
        public void testGetHostNameWindows(String hostName) {
            System.setProperty("COMPUTERNAME", hostName);
            assertEquals(hostName, SystemUtils.getHostName());
        }

    @ParameterizedTest
        @ValueSource(strings = {"MockHost"})
        public void testGetHostNameUnix(String hostName) {
            System.setProperty("HOSTNAME", hostName);
            assertEquals(hostName, SystemUtils.getHostName());
        }

    @Test
        public void testGetJavaIoTmpDir() {
            System.setProperty("java.io.tmpdir", "/mock/tmp/dir");
            assertEquals(new File("/mock/tmp/dir"), SystemUtils.getJavaIoTmpDir());
        }

    @Test
        public void testGetUserDir() {
            System.setProperty("user.dir", "/mock/user/dir");
            assertEquals(new File("/mock/user/dir"), SystemUtils.getUserDir());
        }

    @Test
        public void testGetUserHome() {
            System.setProperty("user.home", "/mock/user/home");
            assertEquals(new File("/mock/user/home"), SystemUtils.getUserHome());
        }

    @ParameterizedTest
        @ValueSource(strings = {"true"})
        public void testIsJavaAwtHeadlessTrue(String headless) {
            System.setProperty("JAVA_AWT_HEADLESS", headless);
            assertTrue(SystemUtils.isJavaAwtHeadless());
        }

    @ParameterizedTest
        @ValueSource(strings = {"false"})
        public void testIsJavaAwtHeadlessFalse(String headless) {
            System.setProperty("JAVA_AWT_HEADLESS", headless);
            assertFalse(SystemUtils.isJavaAwtHeadless());
        }
}
