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

public class SystemUtils_GenTest {

    @BeforeEach
    public void setUp() {
        // Reset system properties to their default values before each test
        Properties defaultProps = new java.util.Properties();
        defaultProps.putAll(System.getProperties());
        System.setProperties(defaultProps);
    }

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

    @ParameterizedTest
    @ValueSource(strings = {"1.7", "16"})
    public void testIsJavaVersionAtMost(String version) {
        System.setProperty("java.specification.version", version);
        assertTrue(SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_1_7));
        assertTrue(SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_16));
    }

    @Test
    public void testIsJavaAwtHeadless() {
        System.setProperty("java.awt.headless", "true");
        assertTrue(SystemUtils.isJavaAwtHeadless());
        System.setProperty("java.awt.headless", "false");
        assertFalse(SystemUtils.isJavaAwtHeadless());
    }

    @Test
    public void testGetUserNameWithDefault() {
        String expectedUserName = "defaultUser";
        System.setProperty("user.name", expectedUserName);
        assertEquals(expectedUserName, SystemUtils.getUserName(expectedUserName));
    }

    @Test
    public void testGetHostName() {
        String expectedHostName = "expectedHost";
        Map<String, String> env = of(
            entry("COMPUTERNAME", null),
            entry("HOSTNAME", expectedHostName)
        );
        System.getenv().clear();
        System.getenv().putAll(env);
        assertEquals(expectedHostName, SystemUtils.getHostName());
    }
}
