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
        void testGetJavaVersion() {
            assertNotNull(SystemUtils.JAVA_VERSION);
            assertTrue(SystemUtils.JAVA_VERSION.matches("\\d+\\.\\d+(\\.\\d+)?"));
        }

    @Test
        void testIsJavaVersionAtMost() {
            assertTrue(SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_17));
            assertFalse(SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_20));
        }

    @Test
        void testGetOsName() {
            assertNotNull(SystemUtils.OS_NAME);
            assertTrue(System.getProperty("os.name").equals(SystemUtils.OS_NAME));
        }

    @Test
        void testIsJavaAwtHeadless() {
            boolean isHeadless = SystemUtils.isJavaAwtHeadless();
            assertEquals(isHeadless, Boolean.parseBoolean(System.getenv("JAVA_AWT_HEADLESS")));
        }

    @Test
        void testGetUserLanguage() {
            assertNotNull(SystemUtils.USER_LANGUAGE);
            assertTrue(Locale.getDefault().getLanguage().equals(SystemUtils.USER_LANGUAGE));
        }

    @Test
        void testIsJavaVersionAtLeast_2() {
            assertTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_17));
            assertFalse(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_20));
        }

    @Test
        void testGetOsArch() {
            assertNotNull(SystemUtils.OS_ARCH);
            assertTrue(System.getProperty("os.arch").equals(SystemUtils.OS_ARCH));
        }

    @Test
        void testIsJava17() {
            assertTrue(SystemUtils.IS_JAVA_17);
        }

    @Test
        void testIsJava20() {
            assertFalse(SystemUtils.IS_JAVA_20);
        }
}
