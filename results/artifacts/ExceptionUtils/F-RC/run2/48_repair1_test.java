package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import java.util.function.Consumer;

public class ExceptionUtils_GenTest {

    
    @ParameterizedTest
    @ValueSource(strings = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"})
    public void testGetDefaultCauseMethodNames(String methodName) {
        assertTrue(Arrays.asList(ExceptionUtils.getDefaultCauseMethodNames()).contains(methodName));
    }

    @Test
    public void testGetMessage() {
        Throwable ex = new RuntimeException("test");
        assertEquals("RuntimeException: test", ExceptionUtils.getMessage(ex));
    }

    @Test
    public void testGetRootCause() {
        Throwable root = new RuntimeException("root");
        Throwable cause = new RuntimeException("cause", root);
        assertSame(root, ExceptionUtils.getRootCause(cause));
    }

    @Test
    public void testGetRootCauseMessage() {
        Throwable root = new RuntimeException("root");
        Throwable cause = new RuntimeException("cause", root);
        assertEquals("RuntimeException: root", ExceptionUtils.getRootCauseMessage(cause));
    }

    @Test
    public void testGetRootCauseStackTrace() {
        Throwable ex = new RuntimeException("test");
        String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(ex);
        assertTrue(stackTrace.length > 0);
    }

    @Test
    public void testGetStackFrames() {
        Throwable ex = new RuntimeException("test");
        String[] frames = ExceptionUtils.getStackFrames(ex);
        assertTrue(frames.length > 0);
    }

    @Test
    public void testGetStackTrace() {
        Throwable ex = new RuntimeException("test");
        String stackTrace = ExceptionUtils.getStackTrace(ex);
        assertTrue(stackTrace.contains("test"));
    }

    @Test
    public void testGetThrowableCount() {
        Throwable root = new RuntimeException("root");
        Throwable cause = new RuntimeException("cause", root);
        assertEquals(2, ExceptionUtils.getThrowableCount(cause));
    }

    @Test
    public void testGetThrowableList() {
        Throwable root = new RuntimeException("root");
        Throwable cause = new RuntimeException("cause", root);
        List<Throwable> list = ExceptionUtils.getThrowableList(cause);
        assertEquals(2, list.size());
    }

    @Test
    public void testGetThrowables() {
        Throwable root = new RuntimeException("root");
        Throwable cause = new RuntimeException("cause", root);
        Throwable[] throwables = ExceptionUtils.getThrowables(cause);
        assertEquals(2, throwables.length);
    }

    @Test
    public void testHasCause() {
        Throwable root = new RuntimeException("root");
        Throwable cause = new RuntimeException("cause", root);
        assertTrue(ExceptionUtils.hasCause(cause, RuntimeException.class));
    }

    
    
    
    
    @Test
    public void testPrintRootCauseStackTrace() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        ExceptionUtils.printRootCauseStackTrace(new RuntimeException("test"), printStream);
        assertTrue(out.toString().contains("test"));
    }

    
    
    
    
    
    
    
    
    @Test
        public void testGetCauseWithNull() {
            Throwable result = ExceptionUtils.getCause(null);
            assertNull(result);
        }

    @Test
        public void testGetMessageWithNull() {
            String result = ExceptionUtils.getMessage(null);
            assertEquals("", result);
        }

    @Test
        public void testGetRootCauseWithNull() {
            Throwable result = ExceptionUtils.getRootCause(null);
            assertNull(result);
        }

    @Test
        public void testGetRootCauseMessageWithNull() {
            String result = ExceptionUtils.getRootCauseMessage(null);
            assertEquals("", result);
        }

    
    @Test
        public void testGetStackTraceWithNull() {
            String result = ExceptionUtils.getStackTrace(null);
            assertEquals("", result);
        }

    @Test
        public void testHasCauseWithUndeclaredThrowableException() {
            Throwable throwable = new RuntimeException("test");
            boolean result = ExceptionUtils.hasCause(throwable, RuntimeException.class);
            assertTrue(result);
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
        public void testForEach() {
            List<String> messages = Arrays.asList("message1", "message2");
            Throwable throwable = new RuntimeException();
            Consumer<Throwable> consumer = t -> messages.add(t.getMessage());
            ExceptionUtils.forEach(throwable, consumer);
            assertEquals(Arrays.asList("message1", "message2"), messages);
        }

    @Test
        public void testGetCauseWithNull_2() {
            Throwable result = ExceptionUtils.getCause(null);
            assertNull(result);
        }

    @Test
        public void testGetCauseWithMethodNames() {
            Throwable throwable = new RuntimeException();
            String[] methodNames = {"getCause"};
            Throwable result = ExceptionUtils.getCause(throwable, methodNames);
            assertEquals(RuntimeException.class, result.getClass());
        }

    @Test
        public void testGetRootCauseStackTraceListWithNull() {
            List<String> result = ExceptionUtils.getRootCauseStackTraceList(null);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testGetStackFramesWithNull() {
            String[] result = ExceptionUtils.getStackFrames(null);
            assertEquals(0, result.length);
        }

    @Test
        public void testGetThrowableCountWithNull() {
            int result = ExceptionUtils.getThrowableCount(null);
            assertEquals(0, result);
        }

    @Test
        public void testHasCauseWithUndeclaredThrowableException_2() {
            Throwable throwable = new UndeclaredThrowableException(new RuntimeException("test"));
            boolean result = ExceptionUtils.hasCause(throwable, RuntimeException.class);
            assertTrue(result);
        }

    @Test
        public void testIndexOfThrowable() {
            Throwable throwable = new RuntimeException();
            int result = ExceptionUtils.indexOfThrowable(throwable, RuntimeException.class);
            assertEquals(0, result);
        }

    @Test
        public void testIsCheckedWithRuntimeException() {
            boolean result = ExceptionUtils.isChecked(new RuntimeException("test"));
            assertFalse(result);
        }

    @Test
        public void testPrintRootCauseStackTraceWithNull() {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            ExceptionUtils.printRootCauseStackTrace(null, printStream);
            assertTrue(outputStream.toString().isEmpty());
        }
}
