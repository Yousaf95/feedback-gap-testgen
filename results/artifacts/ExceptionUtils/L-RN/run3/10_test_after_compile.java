package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Collectors;

public class ExceptionUtils_GenTest {

    
    @Test
    void testForEach_withNullThrowable() {
        // Arrange
        java.util.function.Consumer<Throwable> consumer = mockConsumer();

        // Act
        ExceptionUtils.forEach(null, consumer);

        // Assert
        // No assertion needed as forEach should handle null gracefully without throwing an exception
    }

    @Test
    void testGetCause_withNullThrowable() {
        // Arrange

        // Act
        Throwable cause = ExceptionUtils.getCause(null);

        // Assert
        assertNull(cause);
    }

    @Test
    void testGetDefaultCauseMethodNames() {
        // Arrange

        // Act
        String[] methodNames = ExceptionUtils.getDefaultCauseMethodNames();

        // Assert
        assertArrayEquals(new String[]{"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"}, methodNames);
    }

    @Test
    void testGetMessage_withNullThrowable() {
        // Arrange

        // Act
        String message = ExceptionUtils.getMessage(null);

        // Assert
        assertEquals("", message);
    }

    @Test
    void testGetRootCause_withNullThrowable() {
        // Arrange

        // Act
        Throwable rootCause = ExceptionUtils.getRootCause(null);

        // Assert
        assertNull(rootCause);
    }

    @Test
    void testGetRootCauseMessage_withNullThrowable() {
        // Arrange

        // Act
        String message = ExceptionUtils.getRootCauseMessage(null);

        // Assert
        assertEquals("", message);
    }

    @Test
    void testGetRootCauseStackTrace_withNullThrowable() {
        // Arrange

        // Act
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(null);

        // Assert
        assertArrayEquals(new String[0], stackTrace);
    }

    @Test
    void testGetStackFrames_withNullThrowable() {
        // Arrange

        // Act
        String[] stackFrames = ExceptionUtils.getStackFrames((Throwable) null);

        // Assert
        assertArrayEquals(new String[0], stackFrames);
    }

    @Test
    void testGetStackTrace_withNullThrowable() {
        // Arrange

        // Act
        String stackTrace = ExceptionUtils.getStackTrace(null);

        // Assert
        assertEquals("", stackTrace);
    }

    @Test
    void testGetThrowableCount_withNullThrowable() {
        // Arrange

        // Act
        int count = ExceptionUtils.getThrowableCount(null);

        // Assert
        assertEquals(0, count);
    }

    @Test
    void testGetThrowableList_withNullThrowable() {
        // Arrange

        // Act
        List<Throwable> list = ExceptionUtils.getThrowableList(null);

        // Assert
        assertTrue(list.isEmpty());
    }

    @Test
    void testHasCause_withNullChain() {
        // Arrange

        // Act
        boolean hasCause = ExceptionUtils.hasCause(null, Exception.class);

        // Assert
        assertFalse(hasCause);
    }

    @Test
    void testIndexOfThrowable_withNullThrowable() {
        // Arrange

        // Act
        int index = ExceptionUtils.indexOfThrowable(null, Exception.class);

        // Assert
        assertEquals(-1, index);
    }

    @Test
    void testIndexOfType_withNullThrowable() {
        // Arrange

        // Act
        int index = ExceptionUtils.indexOfType(null, Exception.class);

        // Assert
        assertEquals(-1, index);
    }

    @Test
    void testIsChecked_withNullThrowable() {
        // Arrange

        // Act
        boolean isChecked = ExceptionUtils.isChecked(null);

        // Assert
        assertFalse(isChecked);
    }

    @Test
    void testIsUnchecked_withNullThrowable() {
        // Arrange

        // Act
        boolean isUnchecked = ExceptionUtils.isUnchecked(null);

        // Assert
        assertFalse(isUnchecked);
    }

    @Test
    void testPrintRootCauseStackTrace_withNullThrowable() {
        // Arrange
        java.io.PrintStream printStream = new java.io.PrintStream(System.out);

        // Act & Assert
        ExceptionUtils.printRootCauseStackTrace(null, printStream);
        // No assertion needed as the method should handle null gracefully without throwing an exception
    }

    @Test
    void testRemoveCommonFrames_withNullLists() {
        // Arrange

        // Act & Assert
        assertThrows(NullPointerException.class, () -> ExceptionUtils.removeCommonFrames(null, List.of()));
        assertThrows(NullPointerException.class, () -> ExceptionUtils.removeCommonFrames(List.of(), null));
    }

    @Test
    void testStream_withNullThrowable() {
        // Arrange

        // Act
        java.util.stream.Stream<Throwable> stream = ExceptionUtils.stream(null);

        // Assert
        assertTrue(stream.count() == 0);
    }

    @Test
    void testThrowableOfThrowable_withNullThrowable() {
        // Arrange

        // Act
        Throwable throwable = ExceptionUtils.throwableOfThrowable(null, Exception.class);

        // Assert
        assertNull(throwable);
    }

    
    @Test
    void testWrapAndThrow_withCheckedException() {
        // Arrange
        Throwable checkedException = new Exception("Checked exception");

        // Act & Assert
        java.lang.reflect.UndeclaredThrowableException undeclaredThrowableException = assertThrows(java.lang.reflect.UndeclaredThrowableException.class, () -> ExceptionUtils.wrapAndThrow(checkedException));
        assertEquals("Checked exception", undeclaredThrowableException.getCause().getMessage());
    }

    private java.util.function.Consumer<Throwable> mockConsumer() {
        return throwable -> {}; // No-op consumer
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testGetMessage_withNonNullThrowable() {
            Throwable throwable = new IllegalArgumentException("Invalid argument");
            assertEquals("IllegalArgumentException: Invalid argument", ExceptionUtils.getMessage(throwable));
        }

    @Test
        void testIsChecked_withCheckedException() {
            assertTrue(ExceptionUtils.isChecked(new Exception()));
        }

    @Test
        void testIsUnchecked_withRuntimeException() {
            assertTrue(ExceptionUtils.isUnchecked(new RuntimeException()));
        }

    @Test
        void testIsUnchecked_withError() {
            assertTrue(ExceptionUtils.isUnchecked(new Error()));
        }

    @Test
        void testGetRootCauseMessage_withNonNullThrowable() {
            Throwable cause = new IllegalArgumentException("Invalid argument");
            Throwable throwable = new Exception("Outer exception", cause);
            assertEquals("IllegalArgumentException: Invalid argument", ExceptionUtils.getRootCauseMessage(throwable));
        }

    @Test
        void testGetRootCauseStackTrace_withNonNullThrowable() {
            Throwable cause = new IllegalArgumentException("Invalid argument");
            Throwable throwable = new Exception("Outer exception", cause);
            String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(throwable);
            assertTrue(stackTrace.length > 0);
            assertEquals("java.lang.IllegalArgumentException: Invalid argument", stackTrace[stackTrace.length - 1]);
        }

    @Test
        void testGetStackFrames_withNonNullThrowable() {
            Throwable throwable = new IllegalArgumentException("Invalid argument");
            String[] stackFrames = ExceptionUtils.getStackFrames(throwable);
            assertTrue(stackFrames.length > 0);
            assertEquals("java.lang.IllegalArgumentException: Invalid argument", stackFrames[0]);
        }

    @Test
        void testGetStackTrace_withNonNullThrowable() {
            Throwable throwable = new IllegalArgumentException("Invalid argument");
            String stackTrace = ExceptionUtils.getStackTrace(throwable);
            assertTrue(stackTrace.contains("java.lang.IllegalArgumentException: Invalid argument"));
        }

    @Test
        void testGetThrowableCount_withNonNullThrowable() {
            Throwable cause = new IllegalArgumentException("Invalid argument");
            Throwable throwable = new Exception("Outer exception", cause);
            assertEquals(2, ExceptionUtils.getThrowableCount(throwable));
        }

    @Test
        void testGetThrowableList_withNonNullThrowable() {
            Throwable cause = new IllegalArgumentException("Invalid argument");
            Throwable throwable = new Exception("Outer exception", cause);
            List<Throwable> list = ExceptionUtils.getThrowableList(throwable);
            assertEquals(2, list.size());
            assertEquals(cause, list.get(1));
        }

    @Test
        void testHasCause_withNonNullChain() {
            Throwable cause = new IllegalArgumentException("Invalid argument");
            Throwable throwable = new Exception("Outer exception", cause);
            assertTrue(ExceptionUtils.hasCause(throwable, IllegalArgumentException.class));
        }

    @Test
        void testIndexOfThrowable_withNonNullThrowable() {
            Throwable cause = new IllegalArgumentException("Invalid argument");
            Throwable throwable = new Exception("Outer exception", cause);
            assertEquals(1, ExceptionUtils.indexOfThrowable(throwable, IllegalArgumentException.class));
        }

    @Test
        void testIndexOfType_withNonNullThrowable() {
            Throwable cause = new IllegalArgumentException("Invalid argument");
            Throwable throwable = new Exception("Outer exception", cause);
            assertEquals(1, ExceptionUtils.indexOfType(throwable, IllegalArgumentException.class));
        }

    @Test
        void testPrintRootCauseStackTrace_withNonNullThrowable() {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            Throwable cause = new IllegalArgumentException("Invalid argument");
            Throwable throwable = new Exception("Outer exception", cause);
            ExceptionUtils.printRootCauseStackTrace(throwable, ps);
            String output = baos.toString().trim();
            assertTrue(output.contains("java.lang.IllegalArgumentException: Invalid argument"));
        }

    @Test
        void testRemoveCommonFrames_withNonNullLists() {
            List<String> causeFrames = List.of(
                "java.lang.IllegalArgumentException: Invalid argument",
                "at org.apache.commons.lang3.exception.ExceptionUtils_GenTest.testRemoveCommonFrames_withNonNullLists(ExceptionUtils_GenTest.java:123)",
                "at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)"
            );
            List<String> wrapperFrames = List.of(
                "java.lang.Exception: Outer exception",
                "at org.apache.commons.lang3.exception.ExceptionUtils_GenTest.testRemoveCommonFrames_withNonNullLists(ExceptionUtils_GenTest.java:123)",
                "at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)"
            );
            ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
            assertEquals(List.of("java.lang.IllegalArgumentException: Invalid argument"), causeFrames);
        }

    @Test
        void testStream_withNonNullThrowable() {
            Throwable cause = new IllegalArgumentException("Invalid argument");
            Throwable throwable = new Exception("Outer exception", cause);
            List<Throwable> list = ExceptionUtils.stream(throwable).collect(Collectors.toList());
            assertEquals(2, list.size());
            assertEquals(cause, list.get(1));
        }

    @Test
        void testThrowableOfThrowable_withNonNullThrowable() {
            Throwable cause = new IllegalArgumentException("Invalid argument");
            Throwable throwable = new Exception("Outer exception", cause);
            assertEquals(cause, ExceptionUtils.throwableOfThrowable(throwable, IllegalArgumentException.class));
        }
}
