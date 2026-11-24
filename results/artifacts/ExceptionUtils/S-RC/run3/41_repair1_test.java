package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

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

    // ---- added by coverage-guided refinement ----
    @Test
        public void testIsChecked_2() {
            assertTrue(ExceptionUtils.isChecked(new IOException()));
            assertFalse(ExceptionUtils.isChecked(new RuntimeException()));
        }

    @Test
        public void testIsUnchecked_2() {
            assertFalse(ExceptionUtils.isUnchecked(new IOException()));
            assertTrue(ExceptionUtils.isUnchecked(new RuntimeException()));
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetCause_MethodNameFound() throws NoSuchMethodException {
            Throwable throwable = new RuntimeException("test");
            Method method = throwable.getClass().getMethod("getCause");
            Throwable cause = ExceptionUtils.getCause(throwable, new String[] { "getCause" });
            assertEquals(RuntimeException.class, cause.getClass());
        }

    @Test
        public void testGetRootCause_ChainedThrowables() {
            Throwable cause1 = new IllegalArgumentException("cause1");
            Throwable cause2 = new NullPointerException("cause2");
            cause1.initCause(cause2);
            String message = ExceptionUtils.getRootCauseMessage(cause1);
            assertEquals("NullPointerException: cause2", message);
        }

    @Test
        public void testGetRootCauseStackTrace_ChainedThrowables() {
            Throwable cause1 = new IllegalArgumentException("cause1");
            Throwable cause2 = new NullPointerException("cause2");
            cause1.initCause(cause2);
            List<String> stackTrace = ExceptionUtils.getRootCauseStackTraceList(cause1);
            assertEquals(Arrays.asList(
                "java.lang.IllegalArgumentException: cause1",
                "Wrapped by: java.lang.NullPointerException: cause2"
            ), stackTrace);
        }

    @Test
        public void testGetStackFrames_Null() {
            String[] frames = ExceptionUtils.getStackFrames(null);
            assertTrue(Arrays.equals(ArrayUtils.EMPTY_STRING_ARRAY, frames));
        }

    @Test
        public void testGetThrowableCount_ChainedThrowables() {
            Throwable cause1 = new IllegalArgumentException("cause1");
            Throwable cause2 = new NullPointerException("cause2");
            cause1.initCause(cause2);
            int count = ExceptionUtils.getThrowableCount(cause1);
            assertEquals(3, count);
        }

    @Test
        public void testGetThrowableList_ChainedThrowables() {
            Throwable cause1 = new IllegalArgumentException("cause1");
            Throwable cause2 = new NullPointerException("cause2");
            cause1.initCause(cause2);
            List<Throwable> list = ExceptionUtils.getThrowableList(cause1);
            assertTrue(list.contains(cause1));
            assertTrue(list.contains(cause2));
        }

    @Test
        public void testGetThrowables_ChainedThrowables() {
            Throwable cause1 = new IllegalArgumentException("cause1");
            Throwable cause2 = new NullPointerException("cause2");
            cause1.initCause(cause2);
            Throwable[] throwables = ExceptionUtils.getThrowables(cause1);
            assertTrue(Arrays.asList(throwables).contains(cause1));
            assertTrue(Arrays.asList(throwables).contains(cause2));
        }

    @Test
        public void testHasCause_CauseFound() {
            Throwable cause1 = new IllegalArgumentException("cause1");
            Throwable cause2 = new NullPointerException("cause2");
            cause1.initCause(cause2);
            boolean result = ExceptionUtils.hasCause(cause1, NullPointerException.class);
            assertTrue(result);
        }

    @Test
        public void testIndexOf_Match() {
            Throwable cause1 = new IllegalArgumentException("cause1");
            Throwable cause2 = new NullPointerException("cause2");
            cause1.initCause(cause2);
            int index = ExceptionUtils.indexOfThrowable(cause1, NullPointerException.class);
            assertEquals(2, index);
        }

    @Test
        public void testIndexOfType_Match() {
            Throwable cause1 = new IllegalArgumentException("cause1");
            Throwable cause2 = new NullPointerException("cause2");
            cause1.initCause(cause2);
            int index = ExceptionUtils.indexOfType(cause1, NullPointerException.class);
            assertEquals(2, index);
        }

    @Test
        public void testPrintRootCauseStackTrace_ChainedThrowables() {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(out);
            Throwable cause1 = new IllegalArgumentException("cause1");
            Throwable cause2 = new NullPointerException("cause2");
            cause1.initCause(cause2);
            ExceptionUtils.printRootCauseStackTrace(cause1, printStream);
            assertTrue(out.toString().contains("java.lang.IllegalArgumentException: cause1"));
            assertTrue(out.toString().contains("Wrapped by: java.lang.NullPointerException: cause2"));
        }
}
