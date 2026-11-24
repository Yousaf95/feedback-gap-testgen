package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

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
        void testAsRuntimeException() {
            IllegalArgumentException exception = new IllegalArgumentException("test");
            RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> ExceptionUtils.asRuntimeException(exception));
            assertEquals("test", runtimeException.getMessage());
        }

    @Test
        void testGetCause_withNullMethodNames() {
            Throwable cause = new NullPointerException();
            Throwable throwable = new IllegalArgumentException(cause);
            Throwable result = ExceptionUtils.getCause(throwable, null);
            assertSame(cause, result);
        }

    @Test
        void testGetCause_withNoCause() {
            Throwable throwable = new IllegalArgumentException();
            Throwable result = ExceptionUtils.getCause(throwable, null);
            assertNull(result);
        }

    @Test
        void testGetMessage_withNonNullThrowable() {
            Throwable throwable = new IllegalArgumentException("test message");
            String message = ExceptionUtils.getMessage(throwable);
            assertEquals("IllegalArgumentException: test message", message);
        }

    @Test
        void testGetRootCauseStackTraceList_withNonNullThrowable() {
            Throwable cause = new NullPointerException();
            Throwable throwable = new IllegalArgumentException(cause);
            List<String> stackTrace = ExceptionUtils.getRootCauseStackTraceList(throwable);
            assertFalse(stackTrace.isEmpty());
        }

    @Test
        void testGetStackFrameList_withNonNullThrowable() {
            Throwable throwable = new IllegalArgumentException("test message");
            List<String> stackFrames = ExceptionUtils.getStackFrameList(throwable);
            assertFalse(stackFrames.isEmpty());
        }

    @Test
        void testGetStackFrames_withNonNullStackTrace() {
            Throwable throwable = new IllegalArgumentException("test message");
            String[] stackFrames = ExceptionUtils.getStackFrames(ExceptionUtils.getStackTrace(throwable));
            assertTrue(stackFrames.length > 0);
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testAsRuntimeException_2() {
            IllegalArgumentException exception = new IllegalArgumentException("test");
            RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> ExceptionUtils.asRuntimeException(exception));
            assertEquals("test", runtimeException.getMessage());
        }

    @Test
        void testGetRootCause_withEmptyList() {
            assertNull(ExceptionUtils.getRootCause(null));
        }

    @Test
        void testGetRootCauseMessage_withRootCause() {
            Throwable rootCause = new IllegalArgumentException("root cause");
            Throwable throwable = new RuntimeException(rootCause);
            assertEquals("IllegalArgumentException: root cause", ExceptionUtils.getRootCauseMessage(throwable));
        }

    @Test
        void testGetStackFrames_withNullThrowable_2() {
            assertArrayEquals(new String[0], ExceptionUtils.getStackFrames((Throwable) null));
        }

    @Test
        void testPrintRootCauseStackTrace_withNullPrintStream() {
            assertThrows(NullPointerException.class, () -> ExceptionUtils.printRootCauseStackTrace(new RuntimeException(), (PrintStream) null));
        }

    @Test
        void testPrintRootCauseStackTrace_withNullPrintWriter() {
            assertThrows(NullPointerException.class, () -> ExceptionUtils.printRootCauseStackTrace(new RuntimeException(), (PrintWriter) null));
        }

    @Test
        void testRemoveCommonFrames_withEmptyLists() {
            List<String> causeFrames = new ArrayList<>();
            List<String> wrapperFrames = new ArrayList<>();
            ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
            assertTrue(causeFrames.isEmpty());
            assertTrue(wrapperFrames.isEmpty());
        }

    @Test
        void testRethrow() {
            IllegalArgumentException exception = new IllegalArgumentException("test");
            RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> ExceptionUtils.rethrow(exception));
            assertEquals("test", runtimeException.getMessage());
        }

    @Test
        void testIsChecked_withNullThrowable_2() {
            assertFalse(ExceptionUtils.isChecked(null));
        }

    @Test
        void testIsUnchecked_withNullThrowable_2() {
            assertFalse(ExceptionUtils.isUnchecked(null));
        }
}
