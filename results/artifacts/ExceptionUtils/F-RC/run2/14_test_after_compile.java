package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

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

    
    
    
    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAsRuntimeException() {
            Throwable throwable = new RuntimeException("test");
            RuntimeException result = ExceptionUtils.asRuntimeException(throwable);
            assertSame(throwable, result);
        }

    @Test
        public void testForEach() {
            List<Throwable> list = Arrays.asList(new RuntimeException(), new NullPointerException());
            Consumer<Throwable> consumer = mock(Consumer.class);
            ExceptionUtils.forEach(list.get(0), consumer);
            verify(consumer).accept(list.get(0));
        }

    @Test
        public void testGetCauseWithNull() {
            Throwable result = ExceptionUtils.getCause(null);
            assertNull(result);
        }

    @Test
        public void testGetCauseWithMethodNames() {
            Throwable throwable = new RuntimeException();
            String[] methodNames = {"getCause"};
            Throwable result = ExceptionUtils.getCause(throwable, methodNames);
            assertSame(throwable, result);
        }

    @Test
        public void testGetMessageWithNull() {
            String result = ExceptionUtils.getMessage(null);
            assertEquals(StringUtils.EMPTY, result);
        }

    @Test
        public void testGetRootCauseWithNull() {
            Throwable result = ExceptionUtils.getRootCause(null);
            assertNull(result);
        }

    @Test
        public void testGetRootCauseMessageWithNull() {
            String result = ExceptionUtils.getRootCauseMessage(null);
            assertEquals(StringUtils.EMPTY, result);
        }

    @Test
        public void testGetRootCauseStackTraceListWithNull() {
            List<String> result = ExceptionUtils.getRootCauseStackTraceList(null);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testGetStackFramesWithNull() {
            String[] result = ExceptionUtils.getStackFrames(null);
            assertTrue(Arrays.equals(ArrayUtils.EMPTY_STRING_ARRAY, result));
        }

    @Test
        public void testGetStackTraceWithNull() {
            String result = ExceptionUtils.getStackTrace(null);
            assertEquals(StringUtils.EMPTY, result);
        }

    @Test
        public void testGetThrowableCountWithNull() {
            int result = ExceptionUtils.getThrowableCount(null);
            assertEquals(0, result);
        }

    @Test
        public void testGetThrowableListWithNull() {
            List<Throwable> result = ExceptionUtils.getThrowableList(null);
            assertTrue(result.isEmpty());
        }

    @Test
        public void testHasCauseWithUndeclaredThrowableException() {
            Throwable throwable = new UndeclaredThrowableException(new RuntimeException("test"));
            boolean result = ExceptionUtils.hasCause(throwable, RuntimeException.class);
            assertTrue(result);
        }

    @Test
        public void testIndexOfThrowableWithNull() {
            int result = ExceptionUtils.indexOfThrowable(null, RuntimeException.class);
            assertEquals(NOT_FOUND, result);
        }

    @Test
        public void testPrintRootCauseStackTraceWithNull() {
            ExceptionUtils.printRootCauseStackTrace(null);
        }

    @Test
        public void testRemoveCommonFrames() {
            List<String> causeFrames = Arrays.asList("a", "b", "c");
            List<String> wrapperFrames = Arrays.asList("d", "b", "c");
            ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
            assertEquals(Arrays.asList("a"), causeFrames);
        }

    @Test
        public void testThrowUncheckedWithRuntimeException() {
            RuntimeException runtimeException = new RuntimeException();
            Throwable result = ExceptionUtils.throwUnchecked(runtimeException);
            assertSame(runtimeException, result);
        }

    @Test
        public void testWrapAndThrow() {
            Throwable throwable = new RuntimeException("test");
            UndeclaredThrowableException exception = ExceptionUtils.wrapAndThrow(throwable);
            assertTrue(exception.getCause() instanceof RuntimeException);
        }
}
