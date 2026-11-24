package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

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
        void testAsRuntimeException_withNullPointerException() {
            NullPointerException npe = new NullPointerException("test");
            RuntimeException re = assertThrows(RuntimeException.class, () -> ExceptionUtils.asRuntimeException(npe));
            assertEquals("test", re.getMessage());
        }

    @Test
        void testGetCause_withNullMethodNamesAndNonNullThrowableWithCause() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new IllegalStateException("main", cause);
            Throwable result = ExceptionUtils.getCause(throwable, null);
            assertSame(cause, result);
        }

    @Test
        void testGetCause_withNullMethodNamesAndNonNullThrowableWithoutCause() {
            Throwable throwable = new IllegalStateException("main");
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
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new IllegalStateException("main", cause);
            List<String> stackTrace = ExceptionUtils.getRootCauseStackTraceList(throwable);
            assertFalse(stackTrace.isEmpty());
        }

    @Test
        void testGetStackFrameList_withNonNullThrowable() {
            Throwable throwable = new IllegalArgumentException("test message");
            List<String> frames = ExceptionUtils.getStackFrameList(throwable);
            assertFalse(frames.isEmpty());
        }

    @Test
        void testGetStackFrames_withNonNullThrowable() {
            Throwable throwable = new IllegalArgumentException("test message");
            String[] frames = ExceptionUtils.getStackFrames(throwable);
            assertTrue(frames.length > 0);
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testAsRuntimeException_withNullPointerException_2() {
            NullPointerException npe = new NullPointerException("test");
            RuntimeException re = assertThrows(RuntimeException.class, () -> ExceptionUtils.asRuntimeException(npe));
            assertEquals("test", re.getMessage());
        }

    @Test
        void testGetCause_withNullMethodNamesAndNonNullThrowableWithCause_2() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new IllegalStateException("main", cause);
            Throwable result = ExceptionUtils.getCause(throwable, null);
            assertSame(cause, result);
        }

    @Test
        void testGetCause_withNullMethodNamesAndNonNullThrowableWithoutCause_2() {
            Throwable throwable = new IllegalStateException("main");
            Throwable result = ExceptionUtils.getCause(throwable, null);
            assertNull(result);
        }

    @Test
        void testGetMessage_withNonNullThrowable_2() {
            Throwable throwable = new IllegalArgumentException("test message");
            String message = ExceptionUtils.getMessage(throwable);
            assertEquals("IllegalArgumentException: test message", message);
        }

    @Test
        void testGetRootCauseStackTraceList_withNonNullThrowable_2() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new IllegalStateException("main", cause);
            List<String> stackTrace = ExceptionUtils.getRootCauseStackTraceList(throwable);
            assertFalse(stackTrace.isEmpty());
        }

    @Test
        void testGetStackFrameList_withNonNullThrowable_2() {
            Throwable throwable = new IllegalArgumentException("test message");
            List<String> stackFrames = ExceptionUtils.getStackFrameList(throwable);
            assertFalse(stackFrames.isEmpty());
        }

    @Test
        void testGetStackFrames_withNonNullThrowable_2() {
            Throwable throwable = new IllegalArgumentException("test message");
            String[] stackFrames = ExceptionUtils.getStackFrames(throwable);
            assertTrue(stackFrames.length > 0);
        }

    @Test
        void testGetStackTrace_withNonNullThrowable() {
            Throwable throwable = new IllegalArgumentException("test message");
            String stackTrace = ExceptionUtils.getStackTrace(throwable);
            assertFalse(stackTrace.isEmpty());
        }

    @Test
        void testHasCause_withUndeclaredThrowableException() {
            Throwable cause = new IllegalArgumentException("cause");
            UndeclaredThrowableException ute = new UndeclaredThrowableException(cause);
            boolean hasCause = ExceptionUtils.hasCause(ute, IllegalArgumentException.class);
            assertTrue(hasCause);
        }

    @Test
        void testIndexOf_withNullThrowable() {
            int index = ExceptionUtils.indexOf(null, NullPointerException.class, 0, false);
            assertEquals(-1, index);
        }

    @Test
        void testIndexOf_withNullType() {
            Throwable throwable = new IllegalArgumentException("test");
            int index = ExceptionUtils.indexOf(throwable, null, 0, false);
            assertEquals(-1, index);
        }

    @Test
        void testIndexOf_withNegativeFromIndex() {
            Throwable throwable = new IllegalArgumentException("test");
            int index = ExceptionUtils.indexOf(throwable, NullPointerException.class, -1, false);
            assertEquals(0, index);
        }

    @Test
        void testIndexOf_withFromIndexGreaterThanLength() {
            Throwable throwable = new IllegalArgumentException("test");
            int index = ExceptionUtils.indexOf(throwable, NullPointerException.class, 10, false);
            assertEquals(-1, index);
        }

    @Test
        void testIndexOfThrowable_withNullThrowable() {
            int index = ExceptionUtils.indexOfThrowable(null, NullPointerException.class);
            assertEquals(-1, index);
        }

    @Test
        void testIndexOfType_withNullThrowable() {
            int index = ExceptionUtils.indexOfType(null, NullPointerException.class);
            assertEquals(-1, index);
        }

    @Test
        void testIsChecked_withError() {
            boolean isChecked = ExceptionUtils.isChecked(new Error("test"));
            assertFalse(isChecked);
        }

    @Test
        void testPrintRootCauseStackTrace_withNullThrowableAndPrintStream() {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(baos);
            ExceptionUtils.printRootCauseStackTrace(null, printStream);
            assertEquals("", baos.toString());
        }

    @Test
        void testPrintRootCauseStackTrace_withNullThrowableAndPrintWriter() {
            StringWriter sw = new StringWriter();
            PrintWriter printWriter = new PrintWriter(sw);
            ExceptionUtils.printRootCauseStackTrace(null, printWriter);
            assertEquals("", sw.toString());
        }

    @Test
        void testRemoveCommonFrames_withIdenticalStackTraces_2() {
            List<String> causeFrames = List.of("frame1", "frame2");
            List<String> wrapperFrames = new ArrayList<>(causeFrames);
            ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
            assertTrue(causeFrames.isEmpty());
        }

    @Test
        void testRethrow_withNullPointerException() {
            NullPointerException npe = new NullPointerException("test");
            RuntimeException re = assertThrows(RuntimeException.class, () -> ExceptionUtils.rethrow(npe));
            assertEquals("test", re.getMessage());
        }

    @Test
        void testThrowableOfThrowable_withNullThrowable() {
            Throwable result = ExceptionUtils.throwableOfThrowable(null, NullPointerException.class);
            assertNull(result);
        }

    @Test
        void testThrowableOfType_withNullThrowable() {
            Throwable result = ExceptionUtils.throwableOfType(null, NullPointerException.class);
            assertNull(result);
        }

    @Test
        void testThrowUnchecked_withError() {
            Error error = new Error("test");
            assertThrows(Error.class, () -> ExceptionUtils.throwUnchecked(error));
        }

    @Test
        void testConstructor() {
            ExceptionUtils exceptionUtils = new ExceptionUtils();
            assertNotNull(exceptionUtils);
        }
}
