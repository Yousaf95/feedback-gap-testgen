package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.exception.ExceptionUtils.*;

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
        public void testGetDefaultCauseMethodNames_2() {
            String[] expected = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"};
            assertArrayEquals(expected, getDefaultCauseMethodNames());
        }

    @Test
        public void testGetMessage_2() {
            Throwable throwable = new IllegalArgumentException("Invalid argument");
            assertEquals("IllegalArgumentException: Invalid argument", getMessage(throwable));
        }

    @Test
        public void testGetRootCause_2() {
            Throwable rootCause = new RuntimeException("Root cause");
            ExceptionWrapper wrapper = new ExceptionWrapper(rootCause);
            assertSame(rootCause, getRootCause(wrapper));
        }

    @Test
        public void testGetRootCauseMessage_2() {
            Throwable rootCause = new IllegalArgumentException("Invalid argument");
            ExceptionWrapper wrapper = new ExceptionWrapper(rootCause);
            assertEquals("IllegalArgumentException: Invalid argument", getRootCauseMessage(wrapper));
        }

    @Test
        public void testGetRootCauseStackTrace_2() {
            Throwable rootCause = new RuntimeException("Root cause");
            ExceptionWrapper wrapper = new ExceptionWrapper(rootCause);
            List<String> expected = Arrays.asList(
                "ExceptionUtils_GenTest$ExceptionWrapper: Root cause",
                "java.lang.RuntimeException: Root cause"
            );
            assertEquals(expected, getRootCauseStackTraceList(wrapper));
        }

    @Test
        public void testGetStackFrames_2() {
            Throwable throwable = new IllegalArgumentException("Invalid argument");
            List<String> expected = Arrays.asList(
                "ExceptionUtils_GenTest$testGetStackFrames",
                "java.lang.IllegalArgumentException: Invalid argument"
            );
            assertEquals(expected, getStackFrameList(throwable));
        }

    @Test
        public void testGetStackTrace_2() {
            Throwable throwable = new IllegalArgumentException("Invalid argument");
            String expected = "ExceptionUtils_GenTest$testGetStackTrace\n" +
                              "\tat java.lang.IllegalArgumentException.<init>(IllegalArgumentException.java:29)\n" +
                              "\tat ExceptionUtils_GenTest.testGetStackTrace(ExceptionUtils_GenTest.java:100)";
            assertEquals(expected, getStackTrace(throwable));
        }

    @Test
        public void testGetThrowableCount_2() {
            Throwable rootCause = new RuntimeException("Root cause");
            ExceptionWrapper wrapper = new ExceptionWrapper(rootCause);
            assertEquals(2, getThrowableCount(wrapper));
        }

    @Test
        public void testGetThrowableList_2() {
            Throwable rootCause = new RuntimeException("Root cause");
            ExceptionWrapper wrapper = new ExceptionWrapper(rootCause);
            List<Throwable> expected = Arrays.asList(
                wrapper,
                rootCause
            );
            assertEquals(expected, getThrowableList(wrapper));
        }

    @Test
        public void testGetThrowables_2() {
            Throwable rootCause = new RuntimeException("Root cause");
            ExceptionWrapper wrapper = new ExceptionWrapper(rootCause);
            Throwable[] expected = new Throwable[]{wrapper, rootCause};
            assertArrayEquals(expected, getThrowables(wrapper));
        }

    @Test
        public void testHasCause_2() {
            Throwable rootCause = new RuntimeException("Root cause");
            ExceptionWrapper wrapper = new ExceptionWrapper(rootCause);
            assertTrue(hasCause(wrapper, RuntimeException.class));
        }

    @Test
        public void testPrintRootCauseStackTrace_2() {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(out);
            Throwable rootCause = new IllegalArgumentException("Invalid argument");
            ExceptionWrapper wrapper = new ExceptionWrapper(rootCause);
            printRootCauseStackTrace(wrapper, printStream);
            String expected = "ExceptionUtils_GenTest$ExceptionWrapper: Invalid argument\n" +
                              "\tat java.lang.IllegalArgumentException.<init>(IllegalArgumentException.java:29)\n" +
                              "\tat ExceptionUtils_GenTest.testPrintRootCauseStackTrace(ExceptionUtils_GenTest.java:130)";
            assertEquals(expected, out.toString());
        }

    @Test
        public void testRemoveCommonFrames() {
            List<String> causeFrames = Arrays.asList(
                "ExceptionUtils_GenTest$testRemoveCommonFrames",
                "java.lang.RuntimeException: Root cause"
            );
            List<String> wrapperFrames = Arrays.asList(
                "ExceptionUtils_GenTest$testRemoveCommonFrames",
                "java.lang.IllegalArgumentException: Invalid argument",
                "ExceptionUtils_GenTest$testRemoveCommonFrames",
                "java.lang.RuntimeException: Root cause"
            );
            removeCommonFrames(causeFrames, wrapperFrames);
            assertEquals(Arrays.asList("ExceptionUtils_GenTest$testRemoveCommonFrames", "java.lang.RuntimeException: Root cause"), causeFrames);
        }

    @Test
        public void testStream() {
            Throwable rootCause = new RuntimeException("Root cause");
            ExceptionWrapper wrapper = new ExceptionWrapper(rootCause);
            List<Throwable> expected = Arrays.asList(
                wrapper,
                rootCause
            );
            assertEquals(expected, stream(wrapper).toList());
        }

    @Test
        public void testThrowableOfThrowable() {
            Throwable rootCause = new IllegalArgumentException("Invalid argument");
            ExceptionWrapper wrapper = new ExceptionWrapper(rootCause);
            assertSame(rootCause, throwableOfThrowable(wrapper, IllegalArgumentException.class));
        }

    @Test
        public void testThrowUnchecked() {
            RuntimeException exception = new RuntimeException("Unchecked exception");
            assertThrows(RuntimeException.class, () -> throwUnchecked(exception));
        }

    @Test
        public void testWrapAndThrow() {
            Throwable rootCause = new IllegalArgumentException("Invalid argument");
            ExceptionWrapper wrapper = new ExceptionWrapper(rootCause);
            assertThrows(UndeclaredThrowableException.class, () -> wrapAndThrow(wrapper));
        }
}
