package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static java.util.Map.entry;
import static java.util.Map.of;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Locale;

public class SystemUtils_GenTest {

    
    @Test
    public void testGetJavaHome() {
        String expectedJavaHome = "/path/to/java/home";
        System.setProperty("java.home", expectedJavaHome);
        assertEquals(expectedJavaHome, SystemUtils.getJavaHome().getAbsolutePath());
    }

    @Test
    public void testGetJavaIoTmpDir() {
        String expectedTempDir = "/path/to/temp/dir";
        System.setProperty("java.io.tmpdir", expectedTempDir);
        assertEquals(expectedTempDir, SystemUtils.getJavaIoTmpDir().getAbsolutePath());
    }

    @Test
    public void testGetUserDir() {
        String expectedUserDir = "/path/to/user/dir";
        System.setProperty("user.dir", expectedUserDir);
        assertEquals(expectedUserDir, SystemUtils.getUserDir().getAbsolutePath());
    }

    @Test
    public void testGetUserHome() {
        String expectedUserHome = "/path/to/user/home";
        System.setProperty("user.home", expectedUserHome);
        assertEquals(expectedUserHome, SystemUtils.getUserHome().getAbsolutePath());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.8", "17"})
    public void testIsJavaVersionAtLeast(String version) {
        System.setProperty("java.specification.version", version);
        assertTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_8));
        assertTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_17));
    }

    
    
    @Test
    public void testGetUserNameWithDefault() {
        String expectedUserName = "defaultUser";
        System.setProperty("user.name", expectedUserName);
        assertEquals(expectedUserName, SystemUtils.getUserName(expectedUserName));
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetEnvironmentVariableWithNullValue() {
            String defaultValue = "default";
            String value = SystemUtils.getEnvironmentVariable("nonExistentVar", defaultValue);
            assertEquals(defaultValue, value);
        }

    
    
    @Test
        public void testIsJavaAwtHeadlessTrue() {
            System.setProperty(SystemProperties.JAVA_AWT_HEADLESS, "true");
            assertTrue(SystemUtils.isJavaAwtHeadless());
        }

    
    @Test
        public void testIsJavaVersionAtMostTrue() {
            assertTrue(SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_17));
        }

    
    @Test
        public void testIsOSMatchTrue() {
            assertTrue(SystemUtils.isOSMatch("Linux", "5.4.0-81-generic", "Linux", "5.4"));
        }

    @Test
        public void testIsOSMatchFalse() {
            assertFalse(SystemUtils.isOSMatch("Windows", "10.0", "Linux", "5.4"));
        }

    @Test
        public void testIsOSNameMatchTrue() {
            assertTrue(SystemUtils.isOSNameMatch("Linux", "Linux"));
        }

    @Test
        public void testIsOSNameMatchFalse() {
            assertFalse(SystemUtils.isOSNameMatch("Windows", "Linux"));
        }

    @Test
        public void testIsOSVersionMatchTrue() {
            assertTrue(SystemUtils.isOSVersionMatch("5.4.0-81-generic", "5.4"));
        }

    @Test
        public void testIsOSVersionMatchFalse() {
            assertFalse(SystemUtils.isOSVersionMatch("5.4.0-81-generic", "6.0"));
        }

    
    
    
    
    
    
    
    @Test
        public void testIsJavaVersionMatchNullVersion() {
            assertFalse(SystemUtils.isJavaVersionMatch(null, "1.8"));
        }

    @Test
        public void testIsJavaVersionMatchMismatchedVersion() {
            assertFalse(SystemUtils.isJavaVersionMatch("1.7", "1.8"));
        }

    @Test
        public void testIsJavaVersionMatchMatchedVersion() {
            assertTrue(SystemUtils.isJavaVersionMatch("1.8", "1.8"));
        }

    @Test
        public void testIsOSMatchNullOsNameAndVersion() {
            assertFalse(SystemUtils.isOSMatch(null, null, "Windows", "10"));
        }

    @Test
        public void testIsOSMatchMismatchedOsName() {
            assertFalse(SystemUtils.isOSMatch("Linux", "10", "Windows", "10"));
        }

    @Test
        public void testIsOSMatchMismatchedOsVersion() {
            assertFalse(SystemUtils.isOSMatch("Windows", "9", "Windows", "10"));
        }

    @Test
        public void testIsOSMatchMatchedOsNameAndVersion() {
            assertTrue(SystemUtils.isOSMatch("Windows", "10", "Windows", "10"));
        }

    @Test
        public void testIsOSNameMatchNullOsName() {
            assertFalse(SystemUtils.isOSNameMatch(null, "Windows"));
        }

    @Test
        public void testIsOSNameMatchMismatchedOsName() {
            assertFalse(SystemUtils.isOSNameMatch("Linux", "Windows"));
        }

    @Test
        public void testIsOSNameMatchMatchedOsName() {
            assertTrue(SystemUtils.isOSNameMatch("Windows", "Windows"));
        }

    @Test
        public void testIsOSVersionMatchEmptyOsVersion() {
            assertFalse(SystemUtils.isOSVersionMatch("", "10.0"));
        }

    @Test
        public void testIsOSVersionMatchMismatchedOsVersion() {
            assertFalse(SystemUtils.isOSVersionMatch("10.1", "10.0"));
        }

    @Test
        public void testIsOSVersionMatchMatchedOsVersion() {
            assertTrue(SystemUtils.isOSVersionMatch("10.0", "10.0"));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetUserCountryWithDefault() {
            String defaultValue = "US";
            String result = SystemUtils.getUserCountry(defaultValue);
            assertEquals(defaultValue, result);
        }

    @Test
        public void testIsOSLinuxTrue() {
            System.setProperty("os.name", "Linux");
            assertTrue(SystemUtils.IS_OS_LINUX);
        }

    @Test
        public void testIsOSLinuxFalse() {
            System.setProperty("os.name", "Windows");
            assertFalse(SystemUtils.IS_OS_LINUX);
        }

    @Test
        public void testIsOSUnixTrue() {
            System.setProperty("os.name", "Linux");
            assertTrue(SystemUtils.IS_OS_UNIX);
        }

    @Test
        public void testIsOSUnixFalse() {
            System.setProperty("os.name", "Windows");
            assertFalse(SystemUtils.IS_OS_UNIX);
        }

    @Test
        public void testGetEnvironmentVariableWithNullValue_2() {
            String name = "NON_EXISTENT_ENV_VAR";
            String defaultValue = "default";
            String result = SystemUtils.getEnvironmentVariable(name, defaultValue);
            assertEquals(defaultValue, result);
        }

    @Test
        public void testGetHostNameWindows() {
            System.setProperty("os.name", "Windows 10");
            String result = SystemUtils.getHostName();
            assertNotNull(result);
        }

    @Test
        public void testGetHostNameNonWindows() {
            System.setProperty("os.name", "Linux");
            String result = SystemUtils.getHostName();
            assertNotNull(result);
        }

    @Test
        public void testGetUserNameWithDefault_2() {
            String defaultValue = "defaultUser";
            String result = SystemUtils.getUserName(defaultValue);
            assertEquals(defaultValue, result);
        }
}
