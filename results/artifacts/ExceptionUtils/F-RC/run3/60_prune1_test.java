package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionUtils_GenTest {

    
    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testGetDefaultCauseMethodNames(String methodName) {
        List<String> defaultMethods = Arrays.asList(ExceptionUtils.getDefaultCauseMethodNames());
        assertTrue(defaultMethods.contains(methodName));
    }

    @Test
    public void testGetMessage() {
        Throwable throwable = new RuntimeException("test");
        String message = ExceptionUtils.getMessage(throwable);
        assertEquals("RuntimeException: test", message);
    }

    
    
    
    @Test
    public void testGetStackFrames() {
        Throwable throwable = new RuntimeException("test");
        String[] stackFrames = ExceptionUtils.getStackFrames(throwable);
        assertTrue(stackFrames.length > 0);
        assertTrue(Arrays.stream(stackFrames).anyMatch(s -> s.contains("RuntimeException: test")));
    }

    @Test
    public void testGetStackTrace() {
        Throwable throwable = new RuntimeException("test");
        String stackTrace = ExceptionUtils.getStackTrace(throwable);
        assertTrue(stackTrace.contains("RuntimeException: test"));
    }

    
    
    
    
    
    
    @Test
    public void testIsUnchecked() {
        Throwable unchecked = new RuntimeException("unchecked");
        assertTrue(ExceptionUtils.isUnchecked(unchecked));
    }

    @Test
    public void testPrintRootCauseStackTrace() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Throwable rootCause = new RuntimeException("root cause");
        ExceptionUtils.printRootCauseStackTrace(rootCause, printStream);
        assertTrue(outputStream.toString().contains("RuntimeException: root cause"));
    }

    
    
    
    
    
    
    @Test
        public void testGetCause() {
            Throwable cause = new RuntimeException("cause");
            Throwable throwable = new RuntimeException("test", cause);
    
            Throwable result = ExceptionUtils.getCause(throwable);
            assertSame(cause, result);
        }

    @Test
        public void testGetCauseWithMethodNames() {
            Throwable cause = new RuntimeException("cause");
            Throwable throwable = new RuntimeException("test", cause);
    
            String[] methodNames = {"getCause"};
            Throwable result = ExceptionUtils.getCause(throwable, methodNames);
            assertSame(cause, result);
        }

    @Test
        public void testGetMessage_2() {
            Throwable throwable = new RuntimeException("test");
            assertEquals("RuntimeException: test", ExceptionUtils.getMessage(throwable));
        }

    @Test
        public void testGetRootCause() {
            Throwable cause = new RuntimeException("cause");
            Throwable throwable = new RuntimeException("test", cause);
    
            Throwable result = ExceptionUtils.getRootCause(throwable);
            assertSame(cause, result);
        }

    @Test
        public void testGetRootCauseMessage() {
            Throwable cause = new RuntimeException("cause");
            Throwable throwable = new RuntimeException("test", cause);
    
            String result = ExceptionUtils.getRootCauseMessage(throwable);
            assertEquals("RuntimeException: cause", result);
        }

    @Test
        public void testGetRootCauseStackTrace() {
            Throwable throwable = new RuntimeException("test");
    
            String[] result = ExceptionUtils.getRootCauseStackTrace(throwable);
            assertTrue(result.length > 0);
        }

    @Test
        public void testGetStackFrames_2() {
            Throwable throwable = new RuntimeException("test");
    
            String[] result = ExceptionUtils.getStackFrames(throwable);
            assertTrue(result.length > 0);
        }

    @Test
        public void testGetStackTrace_2() {
            Throwable throwable = new RuntimeException("test");
    
            String result = ExceptionUtils.getStackTrace(throwable);
            assertTrue(!result.isEmpty());
        }

    @Test
        public void testGetThrowableCount() {
            Throwable throwable = new RuntimeException("test");
            assertEquals(1, ExceptionUtils.getThrowableCount(throwable));
        }

    @Test
        public void testGetThrowableList() {
            Throwable throwable = new RuntimeException("test");
    
            List<Throwable> result = ExceptionUtils.getThrowableList(throwable);
            assertTrue(!result.isEmpty());
        }

    @Test
        public void testHasCause() {
            Throwable cause = new RuntimeException("cause");
            Throwable throwable = new RuntimeException("test", cause);
    
            assertTrue(ExceptionUtils.hasCause(throwable, RuntimeException.class));
        }

    @Test
        public void testIndexOfThrowable() {
            Throwable cause = new RuntimeException("cause");
            Throwable throwable = new RuntimeException("test", cause);
    
            assertEquals(0, ExceptionUtils.indexOfThrowable(throwable, RuntimeException.class));
        }

    
    @Test
        public void testPrintRootCauseStackTrace_2() {
            Throwable throwable = new RuntimeException("test");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
    
            ExceptionUtils.printRootCauseStackTrace(throwable, ps);
            assertTrue(!baos.toString().isEmpty());
        }

    @Test
        public void testRemoveCommonFrames() {
            List<String> causeFrames = Arrays.asList("causeFrame");
            List<String> wrapperFrames = Arrays.asList("wrapperFrame");
    
            ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
            assertTrue(!causeFrames.isEmpty());
        }

    
    @Test
        public void testThrowUnchecked() {
            RuntimeException runtimeException = new RuntimeException();
            assertThrows(RuntimeException.class, () -> ExceptionUtils.throwUnchecked(runtimeException));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAsRuntimeException() {
            Throwable throwable = new RuntimeException("test");
            RuntimeException result = ExceptionUtils.asRuntimeException(throwable);
            assertEquals(RuntimeException.class, result.getClass());
            assertEquals("test", result.getMessage());
        }

    
    @Test
        public void testGetCauseWithNullThrowable() {
            Throwable result = ExceptionUtils.getCause(null);
            assertNull(result);
        }

    @Test
        public void testGetCauseWithNullMethodNames() {
            Throwable throwable = new RuntimeException("test");
            Throwable result = ExceptionUtils.getCause(throwable, null);
            assertEquals(throwable, result);
        }

    @Test
        public void testGetMessageWithNullThrowable() {
            String result = ExceptionUtils.getMessage(null);
            assertEquals("", result);
        }

    @Test
        public void testGetRootCauseWithNullThrowable() {
            Throwable result = ExceptionUtils.getRootCause(null);
            assertNull(result);
        }

    @Test
        public void testGetRootCauseMessageWithNullThrowable() {
            String result = ExceptionUtils.getRootCauseMessage(null);
            assertEquals("", result);
        }

    @Test
        public void testGetRootCauseStackTraceListWithNullThrowable() {
            List<String> result = ExceptionUtils.getRootCauseStackTraceList(null);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testGetStackTraceWithNullThrowable() {
            String result = ExceptionUtils.getStackTrace(null);
            assertEquals("", result);
        }

    @Test
        public void testGetThrowableCountWithNullThrowable() {
            int result = ExceptionUtils.getThrowableCount(null);
            assertEquals(0, result);
        }

    @Test
        public void testPrintRootCauseStackTraceWithNullThrowable() {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            ExceptionUtils.printRootCauseStackTrace(null, ps);
            assertTrue(baos.toString().isEmpty());
        }

    @Test
        public void testRemoveCommonFrames_2() {
            List<String> causeFrames = Arrays.asList("a", "b", "c");
            List<String> wrapperFrames = Arrays.asList("d", "b", "c");
            ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
            assertEquals(Arrays.asList("a"), causeFrames);
        }

    @Test
        public void testThrowUncheckedWithRuntimeException() {
            RuntimeException runtimeException = new RuntimeException("test");
            Throwable result = assertThrows(RuntimeException.class, () -> ExceptionUtils.throwUnchecked(runtimeException));
            assertEquals(runtimeException, result);
        }
}
