package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import java.io.File;

public class SystemUtils_GenTest {

    @Test
    void testGetEnvironmentVariable_withExistingVariable() {
        // Arrange
        String variableName = "PATH";
        String expectedValue = System.getenv(variableName);

        // Act
        String result = SystemUtils.getEnvironmentVariable(variableName, "default");

        // Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void testGetEnvironmentVariable_withNonExistingVariable() {
        // Arrange
        String variableName = "NON_EXISTING_VARIABLE";
        String defaultValue = "default";

        // Act
        String result = SystemUtils.getEnvironmentVariable(variableName, defaultValue);

        // Assert
        assertEquals(defaultValue, result);
    }

    
    @Test
    void testGetHostName_onUnix() {
        // Arrange
        Assumptions.assumeTrue(SystemUtils.IS_OS_UNIX);
        String expectedValue = System.getenv("HOSTNAME");

        // Act
        String result = SystemUtils.getHostName();

        // Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void testGetJavaHome() {
        // Arrange
        File expectedFile = new File(System.getProperty("java.home"));

        // Act
        File result = SystemUtils.getJavaHome();

        // Assert
        assertEquals(expectedFile.getAbsolutePath(), result.getAbsolutePath());
    }

    @Test
    void testGetJavaIoTmpDir() {
        // Arrange
        File expectedFile = new File(System.getProperty("java.io.tmpdir"));

        // Act
        File result = SystemUtils.getJavaIoTmpDir();

        // Assert
        assertEquals(expectedFile.getAbsolutePath(), result.getAbsolutePath());
    }

    @Test
    void testGetUserDir() {
        // Arrange
        File expectedFile = new File(System.getProperty("user.dir"));

        // Act
        File result = SystemUtils.getUserDir();

        // Assert
        assertEquals(expectedFile.getAbsolutePath(), result.getAbsolutePath());
    }

    @Test
    void testGetUserHome() {
        // Arrange
        File expectedFile = new File(System.getProperty("user.home"));

        // Act
        File result = SystemUtils.getUserHome();

        // Assert
        assertEquals(expectedFile.getAbsolutePath(), result.getAbsolutePath());
    }

    @Test
    void testGetUserName_withDefault() {
        // Arrange
        String defaultValue = "default";
        String expectedValue = System.getProperty(SystemUtils.USER_NAME_KEY, defaultValue);

        // Act
        String result = SystemUtils.getUserName(defaultValue);

        // Assert
        assertEquals(expectedValue, result);
    }

    
    @Test
    void testIsJavaAwtHeadless_whenFalse() {
        // Arrange
        Assumptions.assumeFalse(Boolean.TRUE.toString().equals(System.getProperty("java.awt.headless")));

        // Act
        boolean result = SystemUtils.isJavaAwtHeadless();

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsJavaVersionAtLeast_withCurrentVersion() {
        // Arrange
        JavaVersion currentVersion = JavaVersion.get(SystemUtils.JAVA_SPECIFICATION_VERSION);

        // Act
        boolean result = SystemUtils.isJavaVersionAtLeast(currentVersion);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsJavaVersionAtMost_withCurrentVersion() {
        // Arrange
        JavaVersion currentVersion = JavaVersion.get(SystemUtils.JAVA_SPECIFICATION_VERSION);

        // Act
        boolean result = SystemUtils.isJavaVersionAtMost(currentVersion);

        // Assert
        assertTrue(result);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testGetEnvironmentVariable_withSecurityException() {
            SecurityManager originalSecurityManager = System.getSecurityManager();
            try {
                System.setSecurityManager(new SecurityManager() {
                    public void checkPermission(Permission perm) {
                        if ("getenv.*".equals(perm.getName())) {
                            throw new SecurityException("Not allowed to read environment variable");
                        }
                    }
                });
                String result = SystemUtils.getEnvironmentVariable("SECURE_VAR", "default");
                assertEquals("default", result);
            } finally {
                System.setSecurityManager(originalSecurityManager);
            }
        }

    @Test
        void testGetHostName_onWindows() {
            Assumptions.assumeTrue(SystemUtils.IS_OS_WINDOWS);
            String hostName = System.getenv("COMPUTERNAME");
            assertEquals(hostName, SystemUtils.getHostName());
        }

    @Test
        void testGetUserName_deprecatedMethod() {
            String userName = System.getProperty(SystemUtils.USER_NAME_KEY);
            assertEquals(userName, SystemUtils.getUserName());
        }

    @Test
        void testIsJavaVersionMatch_withNullVersion() {
            assertFalse(SystemUtils.isJavaVersionAtLeast(null));
        }

    @Test
        void testIsOSMatch_withNullOsName() {
            assertFalse(SystemUtils.getOsMatches(null, "10"));
        }

    @Test
        void testIsOSMatch_withNullOsVersion() {
            assertFalse(SystemUtils.getOsMatches("Windows", null));
        }

    @Test
        void testIsOSNameMatch_withNullOsName() {
            assertFalse(SystemUtils.getOsMatchesName(null));
        }

    @Test
        void testIsOSVersionMatch_withEmptyOsVersion() {
            assertFalse(SystemUtils.getOsMatches("Windows", ""));
        }

    @Test
        void testIsOSVersionMatch_withNonMatchingVersions() {
            assertFalse(SystemUtils.getOsMatches("Windows", "10.10"));
        }

    @Test
        void testIsOSVersionMatch_withMatchingVersions() {
            assertTrue(SystemUtils.getOsMatches("Windows", "10"));
        }
}
