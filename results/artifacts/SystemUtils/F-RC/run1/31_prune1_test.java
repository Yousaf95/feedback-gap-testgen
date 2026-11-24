package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

import java.io.File;
import java.util.Locale;
import java.util.Properties;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("SystemUtils Tests")
public class SystemUtils_GenTest {

    @BeforeEach
    public void setUp() {
        // Reset system properties to their default values before each test
        Properties defaultProps = System.getProperties();
        defaultProps.forEach((key, value) -> System.setProperty(key.toString(), value.toString()));
    }

    @Test
    @DisplayName("Should return the correct file encoding")
    public void testGetFileEncoding() {
        String expectedEncoding = System.getProperty("file.encoding");
        assertEquals(expectedEncoding, SystemUtils.FILE_ENCODING);
    }

    
    
    
    
    private static Stream<Arguments> osNameProvider() {
        return Stream.of(
            arguments("Windows", true),
            arguments("Linux", true),
            arguments("Mac OS X", true),
            arguments("AIX", true),
            arguments("HP-UX", true),
            arguments("OS/400", true),
            arguments("Irix", true),
            arguments("FreeBSD", true),
            arguments("OpenBSD", true),
            arguments("NetBSD", true),
            arguments("OS/2", true),
            arguments("Solaris", true),
            arguments("SunOS", true),
            arguments("z/OS", true),
            arguments("unknown", false)
        );
    }

    
    private static Stream<Arguments> osMatchesProvider() {
        return Stream.of(
            arguments("Mac OS X", "10.15", true),
            arguments("Windows 10", "", false),
            arguments("unknown", "unknown", false)
        );
    }

    @Test
    @DisplayName("Should return the correct user home directory")
    public void testGetUserHome() {
        File expectedDir = new File(System.getProperty("user.home"));
        assertEquals(expectedDir, SystemUtils.getUserHome());
    }

    
    private static Stream<Arguments> environmentVariableProvider() {
        return Stream.of(
            arguments("JAVA_HOME", "/usr/lib/jvm/java-17-openjdk-amd64", "/usr/lib/jvm/java-17-openjdk-amd64"),
            arguments("UNKNOWN_VAR", "default", "default")
        );
    }

    @Test
    @DisplayName("Should return the correct host name")
    public void testGetHostName() {
        String expectedHost = System.getenv("COMPUTERNAME") != null ? System.getenv("COMPUTERNAME") : System.getenv("HOSTNAME");
        assertEquals(expectedHost, SystemUtils.getHostName());
    }

    @Test
    @DisplayName("Should return the correct Java home directory as a File")
    public void testGetJavaHome() {
        File expectedDir = new File(System.getProperty("java.home"));
        assertEquals(expectedDir, SystemUtils.getJavaHome());
    }

    @Test
    @DisplayName("Should return the correct Java IO temporary directory as a File")
    public void testGetJavaIoTmpDir() {
        File expectedDir = new File(System.getProperty("java.io.tmpdir"));
        assertEquals(expectedDir, SystemUtils.getJavaIoTmpDir());
    }

    @Test
    @DisplayName("Should return the correct user directory as a File")
    public void testGetUserDir() {
        File expectedDir = new File(System.getProperty("user.dir"));
        assertEquals(expectedDir, SystemUtils.getUserDir());
    }

    
    @Test
        public void testIsOSLinux() {
            boolean isLinux = SystemUtils.IS_OS_LINUX;
            if (System.getProperty("os.name").toLowerCase().contains("linux")) {
                assertTrue(isLinux);
            } else {
                assertFalse(isLinux);
            }
        }

    @Test
        public void testIsJavaVersionAtLeast() {
            boolean isJava17OrLater = SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_17);
            if (System.getProperty("java.version").startsWith("17")) {
                assertTrue(isJava17OrLater);
            } else {
                assertFalse(isJava17OrLater);
            }
        }

    @Test
        public void testGetEnvironmentVariable() {
            String value = SystemUtils.getEnvironmentVariable("PATH", "default");
            assertNotNull(value);
        }

    @Test
        public void testIsOSMatch() {
            boolean isMacOsX = SystemUtils.isOSMatch("Mac OS X", "10.15", "Mac", "10.15");
            if (System.getProperty("os.name").toLowerCase().contains("mac os x") && System.getProperty("os.version").startsWith("10.15")) {
                assertTrue(isMacOsX);
            } else {
                assertFalse(isMacOsX);
            }
        }

    @Test
        public void testIsOSNameMatch() {
            boolean isWindows = SystemUtils.isOSNameMatch(System.getProperty("os.name"), "Windows");
            if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
                assertTrue(isWindows);
            } else {
                assertFalse(isWindows);
            }
        }

    @Test
        public void testIsOSVersionMatch() {
            boolean isJava17 = SystemUtils.isOSVersionMatch(System.getProperty("java.version"), "17");
            if (System.getProperty("java.version").startsWith("17")) {
                assertTrue(isJava17);
            } else {
                assertFalse(isJava17);
            }
        }

    @Test
        public void testGetUserName() {
            String userName = SystemUtils.getUserName();
            assertNotNull(userName);
        }

    @Test
        public void testIsOSUnix() {
            boolean isUnix = SystemUtils.IS_OS_UNIX;
            if (System.getProperty("os.name").toLowerCase().contains("nix") || System.getProperty("os.name").toLowerCase().contains("nux")
                    || System.getProperty("os.name").toLowerCase().contains("mac")) {
                assertTrue(isUnix);
            } else {
                assertFalse(isUnix);
            }
        }

    @Test
        public void testIsOSWindows() {
            boolean isWindows = SystemUtils.IS_OS_WINDOWS;
            if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
                assertTrue(isWindows);
            } else {
                assertFalse(isWindows);
            }
        }
}
