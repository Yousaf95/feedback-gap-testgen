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

    }
