package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

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

    
    
    // ---- added by coverage-guided refinement ----
    @Test
        void testGetMessage_withEmptyMessage() {
            Throwable throwable = new RuntimeException();
            assertEquals("RuntimeException: ", ExceptionUtils.getMessage(throwable));
        }

    @Test
        void testIsChecked_withNullThrowable() {
            assertFalse(ExceptionUtils.isChecked(null));
        }

    @Test
        void testIsUnchecked_withNullThrowable() {
            assertFalse(ExceptionUtils.isUnchecked(null));
        }

    
    @Test
        void testIndexOfThrowable_withNoMatch() {
            Throwable throwable = new RuntimeException(new IllegalArgumentException());
            assertEquals(-1, ExceptionUtils.indexOfThrowable(throwable, NullPointerException.class));
        }

    @Test
        void testIndexOfType_withNoMatch() {
            Throwable throwable = new RuntimeException(new IllegalArgumentException());
            assertEquals(-1, ExceptionUtils.indexOfType(throwable, NullPointerException.class));
        }

    @Test
        void testGetRootCauseMessage_withSingleException() {
            Throwable throwable = new RuntimeException();
            assertEquals("RuntimeException: ", ExceptionUtils.getRootCauseMessage(throwable));
        }

    @Test
        void testGetThrowableCount_withNullThrowable() {
            assertEquals(0, ExceptionUtils.getThrowableCount(null));
        }

    @Test
        void testGetThrowableList_withSingleException() {
            Throwable throwable = new RuntimeException();
            List<Throwable> list = ExceptionUtils.getThrowableList(throwable);
            assertEquals(1, list.size());
            assertEquals(throwable, list.get(0));
        }

    @Test
        void testGetStackFrames_withEmptyStackTrace() {
            Throwable throwable = new RuntimeException();
            String[] frames = ExceptionUtils.getStackFrames(throwable);
            assertTrue(frames.length > 0); // At least one frame should be present
        }

    @Test
        void testPrintRootCauseStackTrace_withSingleException() {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            Throwable throwable = new RuntimeException("test");
            ExceptionUtils.printRootCauseStackTrace(throwable, ps);
            String output = baos.toString().trim();
            assertTrue(output.contains("RuntimeException: test"));
        }

    @Test
        void testRemoveCommonFrames_withNoCommonFrames() {
            List<String> causeFrames = List.of("Frame1", "Frame2");
            List<String> wrapperFrames = List.of("Frame3", "Frame4");
            ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
            assertEquals(List.of("Frame1", "Frame2"), causeFrames);
        }

    @Test
        void testStream_withSingleException() {
            Throwable throwable = new RuntimeException();
            long count = ExceptionUtils.stream(throwable).count();
            assertEquals(1, count);
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testGetDefaultCauseMethodNames_returnsExpectedArray() {
            String[] expected = {"getCause", "getNextException", "getTargetException", "getException",
                    "getSourceException", "getRootCause", "getCausedByException", "getNested",
                    "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"};
            assertArrayEquals(expected, ExceptionUtils.getDefaultCauseMethodNames());
        }

    @Test
        void testGetMessage_withEmptyMessage_returnsClassName() {
            Throwable throwable = new Exception();
            String expected = "Exception: ";
            assertEquals(expected, ExceptionUtils.getMessage(throwable));
        }

    @Test
        void testHasCause_withMatchingExactType_returnsTrue() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new RuntimeException("root", cause);
            assertTrue(ExceptionUtils.hasCause(throwable, IllegalArgumentException.class));
        }

    @Test
        void testIndexOfThrowable_withNoMatch_returnsNegativeOne() {
            Throwable throwable = new Exception();
            assertEquals(-1, ExceptionUtils.indexOfThrowable(throwable, NullPointerException.class));
        }

    @Test
        void testIndexOfType_withNoMatch_returnsNegativeOne() {
            Throwable throwable = new Exception();
            assertEquals(-1, ExceptionUtils.indexOfType(throwable, NullPointerException.class));
        }

    @Test
        void testGetRootCauseMessage_withSingleException_returnsSameAsGetMessage() {
            Throwable throwable = new IllegalArgumentException("single");
            assertEquals(ExceptionUtils.getMessage(throwable), ExceptionUtils.getRootCauseMessage(throwable));
        }

    @Test
        void testGetThrowableCount_withNullThrowable_returnsZero() {
            assertEquals(0, ExceptionUtils.getThrowableCount(null));
        }

    @Test
        void testGetThrowableList_withNullThrowable_returnsEmptyList() {
            assertTrue(ExceptionUtils.getThrowableList(null).isEmpty());
        }

    @Test
        void testGetStackFrames_withNonNullThrowable_returnsNonEmptyArray() {
            Throwable throwable = new Exception("test");
            String[] frames = ExceptionUtils.getStackFrames(throwable);
            assertFalse(frames.length == 0);
        }

    @Test
        void testGetStackTrace_withNonNullThrowable_returnsNonEmptyString() {
            Throwable throwable = new Exception("test");
            String stackTrace = ExceptionUtils.getStackTrace(throwable);
            assertFalse(stackTrace.isEmpty());
        }

    @Test
        void testPrintRootCauseStackTrace_withNullPrintStream_throwsNullPointerException() {
            Throwable throwable = new Exception();
            assertThrows(NullPointerException.class, () -> ExceptionUtils.printRootCauseStackTrace(throwable, (PrintStream) null));
        }

    @Test
        void testPrintRootCauseStackTrace_withNullPrintWriter_throwsNullPointerException() {
            Throwable throwable = new Exception();
            assertThrows(NullPointerException.class, () -> ExceptionUtils.printRootCauseStackTrace(throwable, (PrintWriter) null));
        }

    @Test
        void testRemoveCommonFrames_withNullCauseFrames_throwsNullPointerException() {
            List<String> wrapperFrames = List.of("frame1", "frame2");
            assertThrows(NullPointerException.class, () -> ExceptionUtils.removeCommonFrames(null, wrapperFrames));
        }

    @Test
        void testRemoveCommonFrames_withNullWrapperFrames_throwsNullPointerException() {
            List<String> causeFrames = List.of("frame1", "frame2");
            assertThrows(NullPointerException.class, () -> ExceptionUtils.removeCommonFrames(causeFrames, null));
        }

    @Test
        void testStream_withNonNullThrowable_returnsNonEmptyStream() {
            Throwable throwable = new Exception();
            assertFalse(ExceptionUtils.stream(throwable).toList().isEmpty());
        }
}
