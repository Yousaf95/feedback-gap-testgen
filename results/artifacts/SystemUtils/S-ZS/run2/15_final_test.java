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
}
