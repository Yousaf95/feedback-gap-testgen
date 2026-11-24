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
        public void testGetUserNameWithDefaultValue_2() {
            System.clearProperty("USER");
            assertEquals("defaultUser", SystemUtils.getUserName("defaultUser"));
        }

    
    
    @Test
        public void testGetEnvironmentVariableDefaultValue() {
            System.clearProperty("NON_EXISTENT_VAR");
            assertEquals("defaultValue", SystemUtils.getEnvironmentVariable("NON_EXISTENT_VAR", "defaultValue"));
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
        @ValueSource(strings = {"false"})
        public void testIsJavaAwtHeadlessFalse(String headless) {
            System.setProperty("JAVA_AWT_HEADLESS", headless);
            assertFalse(SystemUtils.isJavaAwtHeadless());
        }
}
