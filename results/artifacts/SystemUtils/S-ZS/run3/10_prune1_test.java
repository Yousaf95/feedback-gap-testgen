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
    public void testGetFileEncoding() {
        systemProperties.setProperty("file.encoding", "UTF-8");
        assertEquals("UTF-8", SystemUtils.FILE_ENCODING);
    }

    
    @ParameterizedTest
    @ValueSource(strings = {"1.8", "11", "20"})
    public void testIsJavaVersionAtLeast(String version) {
        systemProperties.setProperty("java.specification.version", version);
        assertTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.valueOf(version)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"9", "17", "21"})
    public void testIsJavaVersionAtMost(String version) {
        systemProperties.setProperty("java.specification.version", version);
        assertTrue(SystemUtils.isJavaVersionAtMost(JavaVersion.valueOf(version)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    public void testIsJavaAwtHeadless(String value) {
        systemProperties.setProperty("java.awt.headless", value);
        assertEquals(Boolean.parseBoolean(value), SystemUtils.isJavaAwtHeadless());
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

    @Test
    public void testGetEnvironmentVariable() {
        systemProperties.setProperty("MY_ENV_VAR", "my_value");
        assertEquals("my_value", SystemUtils.getEnvironmentVariable("MY_ENV_VAR", "default"));
    }
}
