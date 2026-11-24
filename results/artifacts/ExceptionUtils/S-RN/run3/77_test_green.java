package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionUtils_GenTest {

    
    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testGetDefaultCauseMethodNames(String methodName) {
        List<String> methodNames = Arrays.asList(ExceptionUtils.getDefaultCauseMethodNames());
        assertTrue(methodNames.contains(methodName));
    }

    @Test
    public void testGetMessage() {
        Throwable throwable = new RuntimeException("test");
        String message = ExceptionUtils.getMessage(throwable);
        assertEquals("RuntimeException: test", message);
    }

    @Test
    public void testGetRootCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        Throwable result = ExceptionUtils.getRootCause(rootCause);
        assertEquals(cause, result);
    }

    @Test
    public void testGetRootCauseMessage() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        String message = ExceptionUtils.getRootCauseMessage(rootCause);
        assertEquals("RuntimeException: cause", message);
    }

    @Test
    public void testGetRootCauseStackTrace() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(rootCause);
        assertTrue(stackTrace.length > 0);
    }

    @Test
    public void testGetStackFrames() {
        Throwable throwable = new RuntimeException("test");
        String[] stackFrames = ExceptionUtils.getStackFrames(throwable);
        assertTrue(stackFrames.length > 0);
    }

    @Test
    public void testGetStackTrace() {
        Throwable throwable = new RuntimeException("test");
        String stackTrace = ExceptionUtils.getStackTrace(throwable);
        assertTrue(!stackTrace.isEmpty());
    }

    @Test
    public void testGetThrowableCount() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        int count = ExceptionUtils.getThrowableCount(rootCause);
        assertEquals(2, count);
    }

    @Test
    public void testGetThrowableList() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        List<Throwable> throwableList = ExceptionUtils.getThrowableList(rootCause);
        assertEquals(2, throwableList.size());
    }

    @Test
    public void testGetThrowables() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        Throwable[] throwables = ExceptionUtils.getThrowables(rootCause);
        assertEquals(2, throwables.length);
    }

    @Test
    public void testHasCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        boolean result = ExceptionUtils.hasCause(rootCause, RuntimeException.class);
        assertTrue(result);
    }

    
    
    @Test
    public void testIsChecked() {
        Throwable throwable = new RuntimeException("test");
        boolean result = ExceptionUtils.isChecked(throwable);
        assertFalse(result);
    }

    @Test
    public void testIsUnchecked() {
        Throwable throwable = new RuntimeException("test");
        boolean result = ExceptionUtils.isUnchecked(throwable);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testPrintRootCauseStackTrace(String methodName) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        ExceptionUtils.printRootCauseStackTrace(rootCause, printStream);
        assertTrue(!outputStream.toString().isEmpty());
    }

    
    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testStream(String methodName) {
        Throwable cause = new RuntimeException("cause");
        Throwable rootCause = new RuntimeException("rootCause") {{
            initCause(cause);
        }};
        ExceptionUtils.stream(rootCause).forEach(t -> assertTrue(t instanceof RuntimeException));
    }

    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testThrowUnchecked(String methodName) {
        Throwable throwable = new RuntimeException("test");
        assertThrows(RuntimeException.class, () -> ExceptionUtils.throwUnchecked(throwable));
    }

    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testWrapAndThrow(String methodName) {
        Throwable throwable = new RuntimeException("test");
        assertThrows(RuntimeException.class, () -> ExceptionUtils.wrapAndThrow(throwable));
    }

    
    @Test
        public void testGetStackTraceWithNull() {
            String stackTrace = ExceptionUtils.getStackTrace(null);
            assertEquals("", stackTrace);
        }

    @Test
        public void testGetRootCauseMessageWithNull() {
            String rootCauseMessage = ExceptionUtils.getRootCauseMessage(null);
            assertEquals("", rootCauseMessage);
        }

    @Test
        public void testGetThrowablesWithNull() {
            Throwable[] throwables = ExceptionUtils.getThrowables(null);
            assertTrue(Arrays.equals(new Throwable[0], throwables));
        }

    @Test
        public void testIndexOfThrowableWithNullThrowable() {
            int index = ExceptionUtils.indexOfThrowable(null, NullPointerException.class);
            assertEquals(-1, index);
        }

    @Test
        public void testIndexOfTypeWithNullThrowable() {
            int index = ExceptionUtils.indexOfType(null, NullPointerException.class);
            assertEquals(-1, index);
        }

    @Test
        public void testPrintRootCauseStackTraceWithNullThrowable() {
            assertDoesNotThrow(() -> ExceptionUtils.printRootCauseStackTrace(null));
        }

    
    @Test
        public void testThrowUncheckedWithNullThrowable() {
            assertDoesNotThrow(() -> ExceptionUtils.throwUnchecked((Throwable) null));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetStackTraceWithEmptyThrowable() {
            String[] stackTrace = ExceptionUtils.getStackFrames(new Throwable());
            assertTrue(stackTrace.length > 0);
        }

    
    @Test
        public void testIsUncheckedWithRuntimeException() {
            assertTrue(ExceptionUtils.isUnchecked(new RuntimeException()));
        }

    @Test
        public void testIsCheckedWithError() {
            assertFalse(ExceptionUtils.isChecked(new Error()));
        }

    @Test
        public void testGetThrowableCountWithNestedExceptions() {
            Throwable nestedException = new Exception("Nested exception");
            Throwable rootException = new Exception("Root exception", nestedException);
            assertEquals(2, ExceptionUtils.getThrowableCount(rootException));
        }

    
    
    @Test
        public void testHasCauseWithNestedExceptions() {
            Throwable nestedException = new Exception("Nested exception");
            Throwable rootException = new Exception("Root exception", nestedException);
            assertTrue(ExceptionUtils.hasCause(rootException, Exception.class));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetStackTraceWithCheckedException() {
            Throwable checked = new Exception("Checked exception");
            String stackTrace = ExceptionUtils.getStackTrace(checked);
            assertTrue(stackTrace.contains(Exception.class.getName()));
        }

    @Test
        public void testGetStackTraceWithUncheckedException() {
            Throwable unchecked = new RuntimeException("Unchecked exception");
            String stackTrace = ExceptionUtils.getStackTrace(unchecked);
            assertTrue(stackTrace.contains(RuntimeException.class.getName()));
        }

    @Test
        public void testGetStackTraceWithNestedCheckedExceptions() {
            Throwable nestedChecked = new Exception("Nested checked exception");
            Throwable rootChecked = new Exception("Root checked exception", nestedChecked);
            String stackTrace = ExceptionUtils.getStackTrace(rootChecked);
            assertTrue(stackTrace.contains(Exception.class.getName()));
        }

    @Test
        public void testGetStackTraceWithNestedUncheckedExceptions() {
            Throwable nestedUnchecked = new RuntimeException("Nested unchecked exception");
            Throwable rootUnchecked = new RuntimeException("Root unchecked exception", nestedUnchecked);
            String stackTrace = ExceptionUtils.getStackTrace(rootUnchecked);
            assertTrue(stackTrace.contains(RuntimeException.class.getName()));
        }

    
    @Test
        public void testGetStackTraceWithNullThrowable() {
            String stackTrace = ExceptionUtils.getStackTrace(null);
            assertEquals("", stackTrace);
        }

    @Test
        public void testGetStackTraceWithEmptyThrowable_2() {
            Throwable empty = new Exception();
            String stackTrace = ExceptionUtils.getStackTrace(empty);
            assertTrue(stackTrace.contains(Exception.class.getName()));
        }
}
