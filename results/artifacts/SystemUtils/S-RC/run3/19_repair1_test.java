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
        File expected = new File("/path/to/java/home");
        assertEquals(expected, SystemUtils.getJavaHome());
    }

    @Test
    public void testGetJavaIoTmpDir() {
        systemProperties.setProperty("java.io.tmpdir", "/path/to/tmp");
        File expected = new File("/path/to/tmp");
        assertEquals(expected, SystemUtils.getJavaIoTmpDir());
    }

    @Test
    public void testGetUserDir() {
        systemProperties.setProperty("user.dir", "/path/to/user/dir");
        File expected = new File("/path/to/user/dir");
        assertEquals(expected, SystemUtils.getUserDir());
    }

    @Test
    public void testGetUserHome() {
        systemProperties.setProperty("user.home", "/path/to/user/home");
        File expected = new File("/path/to/user/home");
        assertEquals(expected, SystemUtils.getUserHome());
    }

    @ParameterizedTest
    @ValueSource(strings = {"en_US", "fr_FR"})
    public void testGetUserName(String value) {
        systemProperties.setProperty("user.name", value);
        assertEquals(value, SystemUtils.getUserName());
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetEnvironmentVariableWithNullValue() {
            String defaultValue = "default";
            String value = SystemUtils.getEnvironmentVariable("nonExistentVar", defaultValue);
            assertEquals(defaultValue, value);
        }

    @Test
        public void testGetEnvironmentVariableWithSecurityException() {
            SecurityManager securityManager = new SecurityManager() {
                @Override
                public void checkPermission(java.security.Permission perm) {
                    throw new SecurityException();
                }
            };
            System.setSecurityManager(securityManager);
            String defaultValue = "default";
            String value = SystemUtils.getEnvironmentVariable("nonExistentVar", defaultValue);
            assertEquals(defaultValue, value);
            System.setSecurityManager(null);
        }

    @Test
        public void testGetHostNameOnWindows() {
            System.setProperty("os.name", "Windows 10");
            String hostName = SystemUtils.getHostName();
            assertNotNull(hostName);
        }

    @Test
        public void testGetHostNameOnUnix() {
            System.setProperty("os.name", "Linux");
            String hostName = SystemUtils.getHostName();
            assertNotNull(hostName);
        }

    @Test
        public void testGetUserNameWithDefault() {
            String userName = SystemUtils.getUserName("defaultUser");
            assertNotNull(userName);
        }

    @Test
        public void testIsJavaAwtHeadlessTrue() {
            System.setProperty(SystemUtils.JAVA_AWT_HEADLESS, "true");
            assertTrue(SystemUtils.isJavaAwtHeadless());
            System.clearProperty(SystemUtils.JAVA_AWT_HEADLESS);
        }

    @Test
        public void testIsJavaAwtHeadlessFalse() {
            System.setProperty(SystemUtils.JAVA_AWT_HEADLESS, "false");
            assertFalse(SystemUtils.isJavaAwtHeadless());
            System.clearProperty(SystemUtils.JAVA_AWT_HEADLESS);
        }

    @ParameterizedTest
        @ValueSource(ints = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17})
        public void testIsJavaVersionAtLeastTrue(int version) {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.get("1." + version)));
        }

    @ParameterizedTest
        @ValueSource(ints = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17})
        public void testIsJavaVersionAtMostTrue(int version) {
            assertTrue(SystemUtils.isJavaVersionAtMost(JavaVersion.get("1." + version)));
        }

    @Test
        public void testIsOSMatchWithNullOsName() {
            assertFalse(SystemUtils.isOSMatch(null, "10.0", "Windows", null));
        }

    @Test
        public void testIsOSMatchWithNullOsVersion() {
            assertFalse(SystemUtils.isOSMatch("Windows", null, "Windows", "10.0"));
        }

    @Test
        public void testIsOSNameMatchWithNullOsName() {
            assertFalse(SystemUtils.isOSNameMatch(null, "Windows"));
        }

    @Test
        public void testIsOSVersionMatchWithEmptyOsVersion() {
            assertFalse(SystemUtils.isOSVersionMatch("", "10.0"));
        }
}
