package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.sql.SQLException;

public class ExceptionUtils_GenTest {

    
    @Test
    public void testForEach_withNullThrowable() {
        // Arrange
        java.util.function.Consumer<Throwable> consumer = mockConsumer();

        // Act
        ExceptionUtils.forEach(null, consumer);

        // Assert (no exception thrown)
    }

    @Test
    public void testGetCause_withNullThrowable() {
        // Arrange & Act
        Throwable cause = ExceptionUtils.getCause(null);

        // Assert
        assertNull(cause);
    }

    @Test
    public void testGetDefaultCauseMethodNames() {
        // Arrange & Act
        String[] methodNames = ExceptionUtils.getDefaultCauseMethodNames();

        // Assert
        assertArrayEquals(new String[]{"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"}, methodNames);
    }

    @Test
    public void testGetMessage_withNullThrowable() {
        // Arrange & Act
        String message = ExceptionUtils.getMessage(null);

        // Assert
        assertEquals("", message);
    }

    @Test
    public void testGetRootCause_withNestedExceptions() {
        // Arrange
        Throwable rootCause = new RuntimeException("Root Cause");
        Throwable nested = new Exception("Nested", rootCause);

        // Act
        Throwable result = ExceptionUtils.getRootCause(nested);

        // Assert
        assertEquals(rootCause, result);
    }

    @Test
    public void testGetRootCauseMessage_withNullThrowable() {
        // Arrange & Act
        String message = ExceptionUtils.getRootCauseMessage(null);

        // Assert
        assertEquals("", message);
    }

    @Test
    public void testGetRootCauseStackTrace_withNullThrowable() {
        // Arrange & Act
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(null);

        // Assert
        assertArrayEquals(new String[0], stackTrace);
    }

    @Test
    public void testGetStackFrames_withNullThrowable() {
        // Arrange & Act
        String[] stackFrames = ExceptionUtils.getStackFrames((Throwable) null);

        // Assert
        assertArrayEquals(new String[0], stackFrames);
    }

    @Test
    public void testGetStackTrace_withNullThrowable() {
        // Arrange & Act
        String stackTrace = ExceptionUtils.getStackTrace(null);

        // Assert
        assertEquals("", stackTrace);
    }

    @Test
    public void testGetThrowableCount_withNestedExceptions() {
        // Arrange
        Throwable rootCause = new RuntimeException("Root Cause");
        Throwable nested = new Exception("Nested", rootCause);

        // Act
        int count = ExceptionUtils.getThrowableCount(nested);

        // Assert
        assertEquals(2, count);
    }

    @Test
    public void testGetThrowableList_withNullThrowable() {
        // Arrange & Act
        List<Throwable> list = ExceptionUtils.getThrowableList(null);

        // Assert
        assertTrue(list.isEmpty());
    }

    
    @Test
    public void testIndexOfThrowable_withExactMatch() {
        // Arrange
        Throwable rootCause = new RuntimeException("Root Cause");
        Throwable nested = new Exception("Nested", rootCause);

        // Act
        int index = ExceptionUtils.indexOfThrowable(nested, RuntimeException.class);

        // Assert
        assertEquals(1, index);
    }

    @Test
    public void testIndexOfType_withSubclassMatch() {
        // Arrange
        Throwable rootCause = new RuntimeException("Root Cause");
        Throwable nested = new Exception("Nested", rootCause);

        // Act
        int index = ExceptionUtils.indexOfType(nested, Exception.class);

        // Assert
        assertEquals(0, index);
    }

    @Test
    public void testIsChecked_withRuntimeException() {
        // Arrange & Act
        boolean isChecked = ExceptionUtils.isChecked(new RuntimeException());

        // Assert
        assertFalse(isChecked);
    }

    @Test
    public void testIsUnchecked_withError() {
        // Arrange & Act
        boolean isUnchecked = ExceptionUtils.isUnchecked(new Error());

        // Assert
        assertTrue(isUnchecked);
    }

    @Test
    public void testPrintRootCauseStackTrace_withNullThrowable() {
        // Arrange & Act (no exception thrown)
        ExceptionUtils.printRootCauseStackTrace(null);
    }

    @Test
    public void testRemoveCommonFrames_withIdenticalStackTraces() {
        // Arrange
        List<String> causeFrames = new java.util.ArrayList<>(List.of("frame1", "frame2"));
        List<String> wrapperFrames = new java.util.ArrayList<>(List.of("frame1", "frame2"));

        // Act
        ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);

        // Assert
        assertTrue(causeFrames.isEmpty());
    }

    @Test
    public void testStream_withNullThrowable() {
        // Arrange & Act
        java.util.stream.Stream<Throwable> stream = ExceptionUtils.stream(null);

        // Assert
        assertEquals(0, stream.count());
    }

    @Test
    public void testThrowableOfThrowable_withExactMatch() {
        // Arrange
        Throwable rootCause = new RuntimeException("Root Cause");
        Throwable nested = new Exception("Nested", rootCause);

        // Act
        Throwable result = ExceptionUtils.throwableOfThrowable(nested, RuntimeException.class);

        // Assert
        assertEquals(rootCause, result);
    }

    @Test
    public void testThrowableOfType_withSubclassMatch() {
        // Arrange
        Throwable rootCause = new RuntimeException("Root Cause");
        Throwable nested = new Exception("Nested", rootCause);

        // Act
        Throwable result = ExceptionUtils.throwableOfType(nested, Exception.class);

        // Assert
        assertEquals(nested, result);
    }

    @Test
    public void testThrowUnchecked_withRuntimeException() {
        // Arrange
        RuntimeException runtimeException = new RuntimeException("Runtime Exception");

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> ExceptionUtils.throwUnchecked(runtimeException));
        assertEquals("Runtime Exception", thrown.getMessage());
    }

    @Test
    public void testWrapAndThrow_withCheckedException() {
        // Arrange
        Throwable checkedException = new Exception("Checked Exception");

        // Act & Assert
        java.lang.reflect.UndeclaredThrowableException undeclared = assertThrows(java.lang.reflect.UndeclaredThrowableException.class, () -> ExceptionUtils.wrapAndThrow(checkedException));
        assertEquals("Checked Exception", undeclared.getCause().getMessage());
    }

    private java.util.function.Consumer<Throwable> mockConsumer() {
        return throwable -> {}; // No-op consumer for testing
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testGetMessage_withNonNullThrowable() {
            Throwable throwable = new IllegalArgumentException("Invalid argument");
            assertEquals("IllegalArgumentException: Invalid argument", ExceptionUtils.getMessage(throwable));
        }

    @Test
        void testIsChecked_withCheckedException() {
            assertTrue(ExceptionUtils.isChecked(new IOException()));
        }

    
    
    @Test
        void testHasCause_withNonMatchingExactType() {
            Throwable cause = new IOException();
            Throwable throwable = new Exception(cause);
            assertFalse(ExceptionUtils.hasCause(throwable, IllegalArgumentException.class));
        }

    @Test
        void testGetRootCauseMessage_withNestedExceptions() {
            Throwable rootCause = new SQLException("Database error");
            Throwable nested = new RuntimeException(rootCause);
            Throwable topLevel = new Exception(nested);
            assertEquals("SQLException: Database error", ExceptionUtils.getRootCauseMessage(topLevel));
        }

    @Test
        void testGetThrowableCount_withSingleException() {
            Throwable throwable = new Exception();
            assertEquals(1, ExceptionUtils.getThrowableCount(throwable));
        }

    @Test
        void testGetThrowableList_withNestedExceptions() {
            Throwable rootCause = new SQLException("Database error");
            Throwable nested = new RuntimeException(rootCause);
            Throwable topLevel = new Exception(nested);
            List<Throwable> list = ExceptionUtils.getThrowableList(topLevel);
            assertEquals(3, list.size());
            assertSame(topLevel, list.get(0));
            assertSame(nested, list.get(1));
            assertSame(rootCause, list.get(2));
        }

    @Test
        void testGetStackFrames_withNonNullThrowable() {
            Throwable throwable = new Exception("Test exception");
            String[] stackFrames = ExceptionUtils.getStackFrames(throwable);
            assertTrue(stackFrames.length > 0);
            assertTrue(stackFrames[0].contains("Exception: Test exception"));
        }

    @Test
        void testGetStackTrace_withNonNullThrowable() {
            Throwable throwable = new Exception("Test exception");
            String stackTrace = ExceptionUtils.getStackTrace(throwable);
            assertTrue(stackTrace.contains("Exception: Test exception"));
        }

    
    }
