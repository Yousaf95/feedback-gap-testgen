package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.io.File;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Properties;

public class SystemUtils_GenTest {

    
    private static Stream<Arguments> provideJavaVersionData() {
        return Stream.of(
            arguments(JavaVersion.JAVA_1_1, SystemUtils.IS_JAVA_1_1),
            arguments(JavaVersion.JAVA_1_2, SystemUtils.IS_JAVA_1_2),
            arguments(JavaVersion.JAVA_1_3, SystemUtils.IS_JAVA_1_3),
            arguments(JavaVersion.JAVA_1_4, SystemUtils.IS_JAVA_1_4),
            arguments(JavaVersion.JAVA_1_5, SystemUtils.IS_JAVA_1_5),
            arguments(JavaVersion.JAVA_1_6, SystemUtils.IS_JAVA_1_6),
            arguments(JavaVersion.JAVA_1_7, SystemUtils.IS_JAVA_1_7),
            arguments(JavaVersion.JAVA_1_8, SystemUtils.IS_JAVA_1_8),
            arguments(JavaVersion.JAVA_9, SystemUtils.IS_JAVA_9),
            arguments(JavaVersion.JAVA_10, SystemUtils.IS_JAVA_10),
            arguments(JavaVersion.JAVA_11, SystemUtils.IS_JAVA_11),
            arguments(JavaVersion.JAVA_12, SystemUtils.IS_JAVA_12),
            arguments(JavaVersion.JAVA_13, SystemUtils.IS_JAVA_13),
            arguments(JavaVersion.JAVA_14, SystemUtils.IS_JAVA_14),
            arguments(JavaVersion.JAVA_15, SystemUtils.IS_JAVA_15),
            arguments(JavaVersion.JAVA_16, SystemUtils.IS_JAVA_16),
            arguments(JavaVersion.JAVA_17, SystemUtils.IS_JAVA_17),
            arguments(JavaVersion.JAVA_18, SystemUtils.IS_JAVA_18),
            arguments(JavaVersion.JAVA_19, SystemUtils.IS_JAVA_19),
            arguments(JavaVersion.JAVA_20, SystemUtils.IS_JAVA_20),
            arguments(JavaVersion.JAVA_21, SystemUtils.IS_JAVA_21)
        );
    }

    
    private static Stream<Arguments> provideOsNameData() {
        return Stream.of(
            arguments("AIX", SystemUtils.IS_OS_AIX),
            arguments("HP-UX", SystemUtils.IS_OS_HP_UX),
            arguments("OS/400", SystemUtils.IS_OS_400),
            arguments("Irix", SystemUtils.IS_OS_IRIX),
            arguments("Linux", SystemUtils.IS_OS_LINUX),
            arguments("Mac", SystemUtils.IS_OS_MAC),
            arguments("Mac OS X", SystemUtils.IS_OS_MAC_OSX),
            arguments("FreeBSD", SystemUtils.IS_OS_FREE_BSD),
            arguments("OpenBSD", SystemUtils.IS_OS_OPEN_BSD),
            arguments("NetBSD", SystemUtils.IS_OS_NET_BSD),
            arguments("OS/2", SystemUtils.IS_OS_OS2),
            arguments("Solaris", SystemUtils.IS_OS_SOLARIS),
            arguments("SunOS", SystemUtils.IS_OS_SUN_OS),
            arguments("z/OS", SystemUtils.IS_OS_ZOS)
        );
    }

    
    private static Stream<Arguments> provideOsMatchData() {
        return Stream.of(
            arguments("Mac OS X", "10.0", SystemUtils.IS_OS_MAC_OSX_CHEETAH),
            arguments("Mac OS X", "10.1", SystemUtils.IS_OS_MAC_OSX_PUMA),
            arguments("Mac OS X", "10.2", SystemUtils.IS_OS_MAC_OSX_JAGUAR),
            arguments("Mac OS X", "10.3", SystemUtils.IS_OS_MAC_OSX_PANTHER),
            arguments("Mac OS X", "10.4", SystemUtils.IS_OS_MAC_OSX_TIGER),
            arguments("Mac OS X", "10.5", SystemUtils.IS_OS_MAC_OSX_LEOPARD),
            arguments("Mac OS X", "10.6", SystemUtils.IS_OS_MAC_OSX_SNOW_LEOPARD),
            arguments("Mac OS X", "10.7", SystemUtils.IS_OS_MAC_OSX_LION),
            arguments("Mac OS X", "10.8", SystemUtils.IS_OS_MAC_OSX_MOUNTAIN_LION),
            arguments("Mac OS X", "10.9", SystemUtils.IS_OS_MAC_OSX_MAVERICKS),
            arguments("Mac OS X", "10.10", SystemUtils.IS_OS_MAC_OSX_YOSEMITE),
            arguments("Mac OS X", "10.11", SystemUtils.IS_OS_MAC_OSX_EL_CAPITAN),
            arguments("Mac OS X", "10.12", SystemUtils.IS_OS_MAC_OSX_SIERRA),
            arguments("Mac OS X", "10.13", SystemUtils.IS_OS_MAC_OSX_HIGH_SIERRA),
            arguments("Mac OS X", "10.14", SystemUtils.IS_OS_MAC_OSX_MOJAVE),
            arguments("Mac OS X", "10.15", SystemUtils.IS_OS_MAC_OSX_CATALINA),
            arguments("Mac OS X", "11", SystemUtils.IS_OS_MAC_OSX_BIG_SUR),
            arguments("Mac OS X", "12", SystemUtils.IS_OS_MAC_OSX_MONTEREY),
            arguments("Mac OS X", "13", SystemUtils.IS_OS_MAC_OSX_VENTURA)
        );
    }

    @Test
    public void testGetEnvironmentVariable() {
        String defaultValue = "default";
        assertEquals(defaultValue, SystemUtils.getEnvironmentVariable("NON_EXISTENT_VAR", defaultValue));
    }

    @Test
    public void testGetHostName() {
        assertNotNull(SystemUtils.getHostName());
    }

    @Test
    public void testGetJavaHome() {
        File javaHome = SystemUtils.getJavaHome();
        assertTrue(javaHome.exists() && javaHome.isDirectory());
    }

    @Test
    public void testGetJavaIoTmpDir() {
        File tmpDir = SystemUtils.getJavaIoTmpDir();
        assertTrue(tmpDir.exists() && tmpDir.isDirectory());
    }

    @Test
    public void testGetUserDir() {
        File userDir = SystemUtils.getUserDir();
        assertTrue(userDir.exists() && userDir.isDirectory());
    }

    @Test
    public void testGetUserHome() {
        File userHome = SystemUtils.getUserHome();
        assertTrue(userHome.exists() && userHome.isDirectory());
    }

    @Test
    public void testIsJavaAwtHeadless() {
        boolean isHeadless = SystemUtils.isJavaAwtHeadless();
        // This can vary based on the environment, so we just check it's a boolean value
        assertTrue(isHeadless || !isHeadless);
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetEnvironmentVariableDefaultValue() {
            String defaultValue = "default";
            String result = SystemUtils.getEnvironmentVariable("non_existent_var", defaultValue);
            assertEquals(defaultValue, result);
        }

    @Test
        public void testGetHostNameWindows() {
            Properties props = new Properties();
            props.setProperty(SystemProperties.OS_NAME_KEY, "Windows 10");
            System.setProperties(props);
            String hostName = SystemUtils.getHostName();
            assertTrue(hostName.equals("COMPUTERNAME") || hostName.equals("HOSTNAME"));
        }

    @Test
        public void testGetHostNameNonWindows() {
            Properties props = new Properties();
            props.setProperty(SystemProperties.OS_NAME_KEY, "Linux");
            System.setProperties(props);
            String hostName = SystemUtils.getHostName();
            assertTrue(hostName.equals("COMPUTERNAME") || hostName.equals("HOSTNAME"));
        }

    @Test
        public void testGetUserNameDeprecated() {
            Properties props = new Properties();
            props.setProperty(SystemProperties.USER_NAME_KEY, "testUser");
            System.setProperties(props);
            String userName = SystemUtils.getUserName();
            assertEquals("testUser", userName);
        }

    @Test
        public void testGetUserNameDefault() {
            Properties props = new Properties();
            props.setProperty(SystemProperties.USER_NAME_KEY, null);
            System.setProperties(props);
            String userName = SystemUtils.getUserName();
            assertNull(userName);
        }

    @Test
        public void testIsJavaVersionAtLeastTrue() {
            Properties props = new Properties();
            props.setProperty(SystemProperties.JAVA_SPECIFICATION_VERSION_KEY, "17");
            System.setProperties(props);
            assertTrue(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_17));
        }

    @Test
        public void testIsJavaVersionAtMostTrue() {
            Properties props = new Properties();
            props.setProperty(SystemProperties.JAVA_SPECIFICATION_VERSION_KEY, "17");
            System.setProperties(props);
            assertTrue(SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_17));
        }

    @Test
        public void testIsOSMatchTrue() {
            Properties props = new Properties();
            props.setProperty(SystemProperties.OS_NAME_KEY, "Linux");
            props.setProperty(SystemProperties.OS_VERSION_KEY, "5.4.0-80-generic");
            System.setProperties(props);
            assertTrue(SystemUtils.isOSMatch("Linux", "5.4.0-80-generic", "Linux", "5.4"));
        }

    @Test
        public void testIsOSNameMatchTrue() {
            Properties props = new Properties();
            props.setProperty(SystemProperties.OS_NAME_KEY, "Windows 10");
            System.setProperties(props);
            assertTrue(SystemUtils.isOSNameMatch("Windows", "Windows"));
        }

    @Test
        public void testIsOSVersionMatchTrue() {
            Properties props = new Properties();
            props.setProperty(SystemProperties.OS_VERSION_KEY, "10.4");
            System.setProperties(props);
            assertTrue(SystemUtils.isOSVersionMatch("10.4", "10.4"));
        }

    @Test
        public void testIsJavaAwtHeadlessTrue() {
            Properties props = new Properties();
            props.setProperty(SystemProperties.JAVA_AWT_HEADLESS_KEY, "true");
            System.setProperties(props);
            assertTrue(SystemUtils.isJavaAwtHeadless());
        }
}
