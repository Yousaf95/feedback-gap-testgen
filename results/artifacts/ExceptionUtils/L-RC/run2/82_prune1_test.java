package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class ExceptionUtils_GenTest {

    
    @Test
    void testForEach_withNullThrowable() {
        // Arrange
        Consumer<Throwable> consumer = mockConsumer();

        // Act
        ExceptionUtils.forEach(null, consumer);

        // Assert
        // No assertion needed as forEach should not throw and no action is performed on null
    }

    @Test
    void testForEach_withSingleThrowable() {
        // Arrange
        Throwable throwable = new Exception("Test exception");
        Consumer<Throwable> consumer = mockConsumer();
        List<Throwable> consumedThrowables = new ArrayList<>();

        // Act
        ExceptionUtils.forEach(throwable, t -> consumedThrowables.add(t));

        // Assert
        assertEquals(1, consumedThrowables.size());
        assertSame(throwable, consumedThrowables.get(0));
    }

    @Test
    void testGetCause_withNullThrowable() {
        // Arrange & Act
        Throwable cause = ExceptionUtils.getCause(null);

        // Assert
        assertNull(cause);
    }

    @Test
    void testGetCause_withNoCause() {
        // Arrange
        Throwable throwable = new Exception("No cause");

        // Act
        Throwable cause = ExceptionUtils.getCause(throwable);

        // Assert
        assertNull(cause);
    }

    @Test
    void testGetCause_withNestedException() {
        // Arrange
        Throwable nested = new Exception("Nested");
        Throwable throwable = new java.lang.reflect.InvocationTargetException(nested);

        // Act
        Throwable cause = ExceptionUtils.getCause(throwable);

        // Assert
        assertSame(nested, cause);
    }

    @Test
    void testGetDefaultCauseMethodNames() {
        // Arrange & Act
        String[] methodNames = ExceptionUtils.getDefaultCauseMethodNames();

        // Assert
        assertNotNull(methodNames);
        assertEquals(ExceptionUtils.getDefaultCauseMethodNames().length, methodNames.length);
        assertArrayEquals(ExceptionUtils.getDefaultCauseMethodNames(), methodNames);
    }

    @Test
    void testGetMessage_withNullThrowable() {
        // Arrange & Act
        String message = ExceptionUtils.getMessage(null);

        // Assert
        assertEquals("", message);
    }

    @Test
    void testGetMessage_withEmptyMessage() {
        // Arrange
        Throwable throwable = new Exception();

        // Act
        String message = ExceptionUtils.getMessage(throwable);

        // Assert
        assertEquals("Exception: ", message);
    }

    @Test
    void testGetRootCause_withNullThrowable() {
        // Arrange & Act
        Throwable rootCause = ExceptionUtils.getRootCause(null);

        // Assert
        assertNull(rootCause);
    }

    @Test
    void testGetRootCause_withSingleThrowable() {
        // Arrange
        Throwable throwable = new Exception("Single");

        // Act
        Throwable rootCause = ExceptionUtils.getRootCause(throwable);

        // Assert
        assertSame(throwable, rootCause);
    }

    @Test
    void testGetRootCause_withNestedThrowables() {
        // Arrange
        Throwable nested = new Exception("Nested");
        Throwable throwable = new java.lang.reflect.InvocationTargetException(nested);

        // Act
        Throwable rootCause = ExceptionUtils.getRootCause(throwable);

        // Assert
        assertSame(nested, rootCause);
    }

    @Test
    void testGetRootCauseMessage_withNullThrowable() {
        // Arrange & Act
        String message = ExceptionUtils.getRootCauseMessage(null);

        // Assert
        assertEquals("", message);
    }

    @Test
    void testGetRootCauseStackTrace_withNullThrowable() {
        // Arrange & Act
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(null);

        // Assert
        assertNotNull(stackTrace);
        assertEquals(0, stackTrace.length);
    }

    @Test
    void testGetStackFrames_withNullThrowable() {
        // Arrange & Act
        String[] stackFrames = ExceptionUtils.getStackFrames((Throwable) null);

        // Assert
        assertNotNull(stackFrames);
        assertEquals(0, stackFrames.length);
    }

    @Test
    void testGetStackTrace_withNullThrowable() {
        // Arrange & Act
        String stackTrace = ExceptionUtils.getStackTrace(null);

        // Assert
        assertEquals("", stackTrace);
    }

    @Test
    void testGetThrowableCount_withNullThrowable() {
        // Arrange & Act
        int count = ExceptionUtils.getThrowableCount(null);

        // Assert
        assertEquals(0, count);
    }

    @Test
    void testGetThrowableList_withNullThrowable() {
        // Arrange & Act
        List<Throwable> list = ExceptionUtils.getThrowableList(null);

        // Assert
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    
    @Test
    void testIndexOfThrowable_withNoMatch() {
        // Arrange
        Throwable throwable = new Exception("Single");

        // Act
        int index = ExceptionUtils.indexOfThrowable(throwable, java.lang.reflect.InvocationTargetException.class);

        // Assert
        assertEquals(-1, index);
    }

    @Test
    void testIndexOfType_withNoMatch() {
        // Arrange
        Throwable throwable = new Exception("Single");

        // Act
        int index = ExceptionUtils.indexOfType(throwable, java.lang.reflect.InvocationTargetException.class);

        // Assert
        assertEquals(-1, index);
    }

    @Test
    void testIsChecked_withRuntimeException() {
        // Arrange & Act
        boolean isChecked = ExceptionUtils.isChecked(new RuntimeException());

        // Assert
        assertFalse(isChecked);
    }

    @Test
    void testIsUnchecked_withError() {
        // Arrange & Act
        boolean isUnchecked = ExceptionUtils.isUnchecked(new Error());

        // Assert
        assertTrue(isUnchecked);
    }

    @Test
    void testPrintRootCauseStackTrace_withNullThrowable() {
        // Arrange & Act & Assert
        // No assertion needed as printRootCauseStackTrace should not throw and no output is expected on null
        ExceptionUtils.printRootCauseStackTrace(null);
    }

    @Test
    void testRemoveCommonFrames_withEmptyLists() {
        // Arrange
        List<String> causeFrames = new ArrayList<>();
        List<String> wrapperFrames = new ArrayList<>();

        // Act
        ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);

        // Assert
        assertTrue(causeFrames.isEmpty());
        assertTrue(wrapperFrames.isEmpty());
    }

    @Test
    void testStream_withNullThrowable() {
        // Arrange & Act
        Stream<Throwable> stream = ExceptionUtils.stream(null);

        // Assert
        assertNotNull(stream);
        assertEquals(0, stream.count());
    }

    @Test
    void testThrowableOfThrowable_withNoMatch() {
        // Arrange
        Throwable throwable = new Exception("Single");

        // Act
        java.lang.reflect.InvocationTargetException result = ExceptionUtils.throwableOfThrowable(throwable, java.lang.reflect.InvocationTargetException.class);

        // Assert
        assertNull(result);
    }

    @Test
    void testThrowUnchecked_withRuntimeException() {
        // Arrange
        RuntimeException runtimeException = new RuntimeException("Runtime exception");

        // Act & Assert
        assertThrows(RuntimeException.class, () -> ExceptionUtils.throwUnchecked(runtimeException));
    }

    @Test
    void testWrapAndThrow_withCheckedException() {
        // Arrange
        Throwable checkedException = new Exception("Checked exception");

        // Act & Assert
        assertThrows(java.lang.reflect.UndeclaredThrowableException.class, () -> ExceptionUtils.wrapAndThrow(checkedException));
    }

    private Consumer<Throwable> mockConsumer() {
        return t -> {}; // No-op consumer for testing purposes
    }

    
    @Test
        void testGetCause_withNullMethodNames() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new IllegalStateException("root", cause);
            Throwable result = ExceptionUtils.getCause(throwable, null);
            assertSame(cause, result);
        }

    @Test
        void testGetRootCauseMessage_withRootCause() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable rootCause = new NullPointerException("root cause");
            cause.initCause(rootCause);
            String message = ExceptionUtils.getRootCauseMessage(cause);
            assertEquals("NullPointerException: root cause", message);
        }

    
    
    @Test
        void testGetStackFrames_withStackTrace() {
            Throwable throwable = new IllegalStateException("test");
            String[] stackFrames = ExceptionUtils.getStackFrames(throwable);
            assertTrue(stackFrames.length > 0);
            assertTrue(stackFrames[0].contains("java.lang.IllegalStateException: test"));
        }

    
    @Test
        void testGetThrowableList_withCircularReference() {
            Throwable t1 = new IllegalArgumentException("t1");
            Throwable t2 = new IllegalStateException("t2", t1);
            t1.initCause(t2);
            List<Throwable> throwables = ExceptionUtils.getThrowableList(t1);
            assertEquals(2, throwables.size());
        }

    
    @Test
        void testGetRootCauseStackTraceList_withNullThrowable() {
            assertTrue(ExceptionUtils.getRootCauseStackTraceList(null).isEmpty());
        }

    @Test
        void testGetStackFrameList_withNullThrowable() {
            assertTrue(ExceptionUtils.getStackFrameList(null).isEmpty());
        }

    @Test
        void testHasCause_withUndeclaredThrowableException() {
            Throwable undeclared = new java.lang.reflect.UndeclaredThrowableException(new IllegalArgumentException("test"));
            assertTrue(ExceptionUtils.hasCause(undeclared, IllegalArgumentException.class));
        }

    @Test
        void testIndexOfThrowable_withNullThrowable() {
            assertEquals(-1, ExceptionUtils.indexOfThrowable(null, NullPointerException.class));
        }

    @Test
        void testIndexOfType_withNullThrowable() {
            assertEquals(-1, ExceptionUtils.indexOfType(null, NullPointerException.class));
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
        void testPrintRootCauseStackTrace_withNullPrintStream() {
            Throwable throwable = new Exception();
            assertThrows(NullPointerException.class, () -> ExceptionUtils.printRootCauseStackTrace(throwable, (PrintStream) null));
        }

    @Test
        void testPrintRootCauseStackTrace_withNullThrowableAndPrintWriter() {
            ExceptionUtils.printRootCauseStackTrace(null, new PrintWriter(new ByteArrayOutputStream()));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        void testAsRuntimeException() {
            IllegalArgumentException exception = new IllegalArgumentException("test");
            RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> ExceptionUtils.asRuntimeException(exception));
            assertEquals("test", runtimeException.getMessage());
        }

    @Test
        void testGetRootCauseStackTraceList_withNullThrowable_2() {
            assertTrue(ExceptionUtils.getRootCauseStackTraceList(null).isEmpty());
        }

    @Test
        void testGetRootCauseStackTraceList_withSingleThrowable() {
            Throwable throwable = new Exception("test");
            List<String> stackTrace = ExceptionUtils.getRootCauseStackTraceList(throwable);
            assertFalse(stackTrace.isEmpty());
            assertEquals("java.lang.Exception: test", stackTrace.get(0));
        }

    @Test
        void testGetRootCauseStackTraceList_withNestedThrowables() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new Exception("test", cause);
            List<String> stackTrace = ExceptionUtils.getRootCauseStackTraceList(throwable);
            assertFalse(stackTrace.isEmpty());
            assertEquals("java.lang.Exception: test", stackTrace.get(0));
            assertTrue(stackTrace.get(1).contains(" [wrapped] java.lang.IllegalArgumentException: cause"));
        }

    @Test
        void testGetStackFrameList_withNullThrowable_2() {
            assertNull(ExceptionUtils.getStackFrameList(null));
        }

    
    @Test
        void testIndexOfThrowable_withNullThrowable_2() {
            assertEquals(-1, ExceptionUtils.indexOfThrowable(null, Exception.class));
        }

    @Test
        void testIndexOfType_withNullThrowable_2() {
            assertEquals(-1, ExceptionUtils.indexOfType(null, Exception.class));
        }

    @Test
        void testIsChecked_withNullThrowable_2() {
            assertFalse(ExceptionUtils.isChecked(null));
        }

    @Test
        void testPrintRootCauseStackTrace_withNullPrintStream_2() {
            Throwable throwable = new Exception("test");
            assertThrows(NullPointerException.class, () -> ExceptionUtils.printRootCauseStackTrace(throwable, (PrintStream) null));
        }

    @Test
        void testPrintRootCauseStackTrace_withNullThrowableAndPrintWriter_2() {
            Throwable throwable = new Exception("test");
            assertThrows(NullPointerException.class, () -> ExceptionUtils.printRootCauseStackTrace(throwable, (PrintWriter) null));
        }

    @Test
        void testRemoveCommonFrames_withEmptyLists_2() {
            List<String> causeFrames = new ArrayList<>();
            List<String> wrapperFrames = new ArrayList<>();
            ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
            assertTrue(causeFrames.isEmpty());
            assertTrue(wrapperFrames.isEmpty());
        }
}
