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
import java.util.ArrayList;

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

    
    
    
    
    @Test
        public void testGetCauseWithNull_2() {
            Throwable result = ExceptionUtils.getCause(null);
            assertNull(result);
        }

    
    @Test
        public void testGetRootCauseStackTraceListWithNull() {
            List<String> result = ExceptionUtils.getRootCauseStackTraceList(null);
            assertTrue(result.isEmpty());
        }

    
    @Test
        public void testGetThrowableCountWithNull() {
            int result = ExceptionUtils.getThrowableCount(null);
            assertEquals(0, result);
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

    
    @Test
        public void testForEach() {
            List<String> messages = new ArrayList<>();
            ExceptionUtils.forEach(new NullPointerException(), t -> messages.add(t.toString()));
            assertTrue(messages.contains("java.lang.NullPointerException"));
        }

    @Test
        public void testGetCauseWithNull_3() {
            assertNull(ExceptionUtils.getCause(null));
        }

    
    @Test
        public void testGetDefaultCauseMethodNames_2() {
            String[] methodNames = ExceptionUtils.getDefaultCauseMethodNames();
            assertTrue(Arrays.asList(methodNames).contains("getCause"));
        }

    @Test
        public void testGetMessageWithNull_2() {
            assertEquals("", ExceptionUtils.getMessage(null));
        }

    @Test
        public void testGetRootCauseWithNull_2() {
            assertNull(ExceptionUtils.getRootCause(null));
        }

    @Test
        public void testGetRootCauseMessageWithNull_2() {
            assertEquals("", ExceptionUtils.getRootCauseMessage(null));
        }

    @Test
        public void testGetStackTraceWithNull_2() {
            assertEquals("", ExceptionUtils.getStackTrace(null));
        }

    @Test
        public void testHasCauseWithUndeclaredThrowableException_2() {
            Throwable cause = new java.lang.reflect.UndeclaredThrowableException(new NullPointerException());
            assertTrue(ExceptionUtils.hasCause(cause, NullPointerException.class));
        }

    @Test
        public void testGetRootCauseStackTraceListWithNull_2() {
            List<String> stackTrace = ExceptionUtils.getRootCauseStackTraceList(null);
            assertTrue(stackTrace.isEmpty());
        }

    @Test
        public void testGetThrowableCountWithNull_2() {
            assertEquals(0, ExceptionUtils.getThrowableCount(null));
        }

    
    @Test
        public void testIsCheckedWithRuntimeException_2() {
            assertFalse(ExceptionUtils.isChecked(new RuntimeException()));
        }

    @Test
        public void testPrintRootCauseStackTraceWithNull_2() {
            ExceptionUtils.printRootCauseStackTrace(null);
        }

    }
