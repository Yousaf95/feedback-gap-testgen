package org.apache.commons.lang3.exception;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import static org.mockito.Mockito.*;
import java.io.StringWriter;

public class ExceptionUtils_GenTest {

    
    @Test
    public void testForEach() {
        Throwable throwable = new RuntimeException("test");
        List<String> messages = new ArrayList<>();
        ExceptionUtils.forEach(throwable, t -> messages.add(t.getMessage()));
        assertEquals(1, messages.size());
        assertEquals("test", messages.get(0));
    }

    @Test
    public void testGetCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        Throwable result = ExceptionUtils.getCause(throwable);
        assertEquals(cause, result);
    }

    @Test
    public void testGetCauseWithNullThrowable() {
        Throwable result = ExceptionUtils.getCause(null);
        assertNull(result);
    }

    
    @Test
    public void testGetMessage() {
        Throwable throwable = new RuntimeException("test");
        String result = ExceptionUtils.getMessage(throwable);
        assertEquals("RuntimeException: test", result);
    }

    @Test
    public void testGetRootCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        Throwable result = ExceptionUtils.getRootCause(throwable);
        assertEquals(cause, result);
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
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        String[] result = ExceptionUtils.getRootCauseStackTrace(throwable);
        assertTrue(result.length > 0);
    }

    @Test
    public void testGetStackFrames() {
        Throwable throwable = new RuntimeException("test");
        String[] result = ExceptionUtils.getStackFrames(throwable);
        assertTrue(result.length > 0);
    }

    @Test
    public void testGetStackTrace() {
        Throwable throwable = new RuntimeException("test");
        String result = ExceptionUtils.getStackTrace(throwable);
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testGetThrowableCount() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        int result = ExceptionUtils.getThrowableCount(throwable);
        assertEquals(2, result);
    }

    @Test
    public void testGetThrowableList() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        List<Throwable> result = ExceptionUtils.getThrowableList(throwable);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetThrowables() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        Throwable[] result = ExceptionUtils.getThrowables(throwable);
        assertEquals(2, result.length);
    }

    @Test
    public void testHasCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        boolean result = ExceptionUtils.hasCause(throwable, RuntimeException.class);
        assertTrue(result);
    }

    @Test
    public void testIndexOfThrowable() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        int result = ExceptionUtils.indexOfThrowable(throwable, RuntimeException.class);
        assertEquals(0, result);
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

    @Test
    public void testPrintRootCauseStackTrace() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        ExceptionUtils.printRootCauseStackTrace(throwable, ps);
        String result = baos.toString();
        assertTrue(result.contains("cause"));
    }

    @Test
    public void testRemoveCommonFrames() {
        List<String> causeFrames = new ArrayList<>();
        causeFrames.add("Frame1");
        causeFrames.add("Frame2");
        List<String> wrapperFrames = new ArrayList<>();
        wrapperFrames.add("Frame3");
        wrapperFrames.add("Frame2");
        ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
        assertEquals(1, causeFrames.size());
        assertEquals("Frame1", causeFrames.get(0));
    }

    
    @Test
    public void testStream() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new RuntimeException("test", cause);
        Stream<Throwable> stream = ExceptionUtils.stream(throwable);
        List<Throwable> result = stream.toList();
        assertEquals(2, result.size());
    }

    
    
    @Test
        public void testGetCauseWithNullThrowable_2() {
            assertNull(ExceptionUtils.getCause(null));
        }

    @Test
        public void testGetCauseWithNoCause() {
            Throwable throwable = new RuntimeException("test");
            assertNull(ExceptionUtils.getCause(throwable));
        }

    @Test
        public void testGetCauseWithCause() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertEquals(cause, ExceptionUtils.getCause(throwable));
        }

    @Test
        public void testGetRootCauseWithNullThrowable() {
            assertNull(ExceptionUtils.getRootCause(null));
        }

    @Test
        public void testGetRootCauseWithNoCause() {
            Throwable throwable = new RuntimeException("test");
            assertEquals(throwable, ExceptionUtils.getRootCause(throwable));
        }

    @Test
        public void testGetRootCauseWithCause() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertEquals(cause, ExceptionUtils.getRootCause(throwable));
        }

    @Test
        public void testGetRootCauseMessageWithNullThrowable() {
            assertEquals("", ExceptionUtils.getRootCauseMessage(null));
        }

    @Test
        public void testGetRootCauseMessageWithNoCause() {
            Throwable throwable = new RuntimeException("test");
            assertEquals("RuntimeException: test", ExceptionUtils.getRootCauseMessage(throwable));
        }

    @Test
        public void testGetRootCauseMessageWithCause() {
            Throwable cause = new IllegalArgumentException("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertEquals("IllegalArgumentException: cause", ExceptionUtils.getRootCauseMessage(throwable));
        }

    
    @Test
        public void testGetStackTraceWithNullThrowable() {
            assertEquals("", ExceptionUtils.getStackTrace(null));
        }

    
    @Test
        public void testHasCauseWithNullThrowable() {
            assertFalse(ExceptionUtils.hasCause(null, RuntimeException.class));
        }

    @Test
        public void testIndexOfThrowableWithNullThrowable() {
            assertEquals(-1, ExceptionUtils.indexOfThrowable(null, RuntimeException.class));
        }

    @Test
        public void testIsCheckedWithNullThrowable() {
            assertFalse(ExceptionUtils.isChecked(null));
        }

    @Test
        public void testIsUncheckedWithNullThrowable() {
            assertFalse(ExceptionUtils.isUnchecked(null));
        }

    @Test
        public void testPrintRootCauseStackTraceWithNullThrowable() {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(out);
            ExceptionUtils.printRootCauseStackTrace(null, printStream);
            assertEquals("", out.toString());
        }

    
    @Test
        public void testRethrow() {
            Throwable throwable = new RuntimeException("test");
            assertThrows(RuntimeException.class, () -> ExceptionUtils.rethrow(throwable));
        }

    
    @Test
        public void testGetCauseWithNullThrowable_3() {
            Throwable cause = ExceptionUtils.getCause(null);
            assertNull(cause);
        }

    @Test
        public void testGetCauseWithNoCause_2() {
            Throwable throwable = new RuntimeException();
            Throwable cause = ExceptionUtils.getCause(throwable);
            assertNull(cause);
        }

    @Test
        public void testGetCauseWithCause_2() {
            Throwable cause = new IllegalArgumentException("test");
            Throwable throwable = new RuntimeException(cause);
            Throwable result = ExceptionUtils.getCause(throwable);
            assertEquals(IllegalArgumentException.class, result.getClass());
            assertEquals("test", result.getMessage());
        }

    @Test
        public void testGetRootCauseWithNullThrowable_2() {
            Throwable rootCause = ExceptionUtils.getRootCause(null);
            assertNull(rootCause);
        }

    
    @Test
        public void testGetRootCauseWithCause_2() {
            Throwable cause = new IllegalArgumentException("test");
            Throwable throwable = new RuntimeException(cause);
            Throwable rootCause = ExceptionUtils.getRootCause(throwable);
            assertEquals(IllegalArgumentException.class, rootCause.getClass());
            assertEquals("test", rootCause.getMessage());
        }

    
    
    @Test
        public void testGetRootCauseMessageWithCause_2() {
            Throwable cause = new IllegalArgumentException("test");
            Throwable throwable = new RuntimeException(cause);
            String message = ExceptionUtils.getRootCauseMessage(throwable);
            assertTrue(message.startsWith("IllegalArgumentException: test"));
        }

    @Test
        public void testGetStackTraceWithNullThrowable_2() {
            String stackTrace = ExceptionUtils.getStackTrace(null);
            assertEquals("", stackTrace);
        }

    @Test
        public void testHasCauseWithNullThrowable_2() {
            boolean result = ExceptionUtils.hasCause(null, RuntimeException.class);
            assertFalse(result);
        }

    @Test
        public void testIndexOfThrowableWithNullThrowable_2() {
            int index = ExceptionUtils.indexOfThrowable(null, RuntimeException.class);
            assertEquals(-1, index);
        }

    @Test
        public void testIsCheckedWithNullThrowable_2() {
            boolean result = ExceptionUtils.isChecked(null);
            assertFalse(result);
        }

    @Test
        public void testIsUncheckedWithNullThrowable_2() {
            boolean result = ExceptionUtils.isUnchecked(null);
            assertFalse(result);
        }

    @Test
        public void testPrintRootCauseStackTraceWithNullThrowable_2() {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintWriter writer = new PrintWriter(out);
            ExceptionUtils.printRootCauseStackTrace(null, writer);
            assertTrue(out.toString().isEmpty());
        }

    @Test
        public void testRethrow_2() {
            Throwable throwable = new RuntimeException("test");
            assertThrows(RuntimeException.class, () -> ExceptionUtils.rethrow(throwable));
        }

    @Test
        public void testThrowableOfThrowable() {
            Throwable cause = new IllegalArgumentException("test");
            Throwable throwable = new RuntimeException(cause);
            IllegalArgumentException result = ExceptionUtils.throwableOfThrowable(throwable, IllegalArgumentException.class);
            assertEquals(IllegalArgumentException.class, result.getClass());
            assertEquals("test", result.getMessage());
        }

    @Test
        public void testThrowableOfType() {
            Throwable cause = new IllegalArgumentException("test");
            Throwable throwable = new RuntimeException(cause);
            IllegalArgumentException result = ExceptionUtils.throwableOfType(throwable, IllegalArgumentException.class);
            assertEquals(IllegalArgumentException.class, result.getClass());
            assertEquals("test", result.getMessage());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAsRuntimeException() {
            Throwable throwable = new RuntimeException("test");
            RuntimeException result = ExceptionUtils.asRuntimeException(throwable);
            assertEquals(throwable, result);
        }

    @Test
        public void testGetCauseWithNullThrowable_4() {
            assertNull(ExceptionUtils.getCause(null));
        }

    @Test
        public void testGetCauseWithNoCause_3() {
            Throwable throwable = new RuntimeException("test");
            assertNull(ExceptionUtils.getCause(throwable));
        }

    @Test
        public void testGetCauseWithCause_3() {
            Throwable cause = new Exception("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertSame(cause, ExceptionUtils.getCause(throwable));
        }

    @Test
        public void testGetRootCauseWithNullThrowable_3() {
            assertNull(ExceptionUtils.getRootCause(null));
        }

    @Test
        public void testGetRootCauseWithNoCause_2() {
            Throwable throwable = new RuntimeException("test");
            assertSame(throwable, ExceptionUtils.getRootCause(throwable));
        }

    @Test
        public void testGetRootCauseWithCause_3() {
            Throwable cause = new Exception("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertSame(cause, ExceptionUtils.getRootCause(throwable));
        }

    @Test
        public void testGetRootCauseMessageWithNullThrowable_2() {
            assertEquals("", ExceptionUtils.getRootCauseMessage(null));
        }

    @Test
        public void testGetRootCauseMessageWithNoCause_2() {
            Throwable throwable = new RuntimeException("test");
            assertEquals("RuntimeException: test", ExceptionUtils.getRootCauseMessage(throwable));
        }

    @Test
        public void testGetRootCauseMessageWithCause_3() {
            Throwable cause = new Exception("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertEquals("Exception: cause", ExceptionUtils.getRootCauseMessage(throwable));
        }

    @Test
        public void testGetStackTraceWithNullThrowable_3() {
            assertEquals("", ExceptionUtils.getStackTrace(null));
        }

    @Test
        public void testHasCauseWithNullThrowable_3() {
            assertFalse(ExceptionUtils.hasCause(null, RuntimeException.class));
        }

    @Test
        public void testIndexOfThrowableWithNullThrowable_3() {
            assertEquals(-1, ExceptionUtils.indexOfThrowable(null, RuntimeException.class));
        }

    @Test
        public void testIsCheckedWithNullThrowable_3() {
            assertFalse(ExceptionUtils.isChecked(null));
        }

    @Test
        public void testIsUncheckedWithNullThrowable_3() {
            assertTrue(ExceptionUtils.isUnchecked(null));
        }

    @Test
        public void testPrintRootCauseStackTraceWithNullThrowable_3() {
            ExceptionUtils.printRootCauseStackTrace(null);
        }

    @Test
        public void testRethrow_3() {
            Throwable throwable = new RuntimeException("test");
            RuntimeException result = ExceptionUtils.rethrow(throwable);
            assertEquals(throwable, result);
        }

    @Test
        public void testThrowableOfThrowable_2() {
            Throwable cause = new Exception("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertSame(cause, ExceptionUtils.throwableOfThrowable(throwable, Exception.class));
        }

    @Test
        public void testThrowableOfType_2() {
            Throwable cause = new Exception("cause");
            Throwable throwable = new RuntimeException("test", cause);
            assertSame(cause, ExceptionUtils.throwableOfType(throwable, Exception.class));
        }

    @Test
        public void testThrowUnchecked() {
            Throwable throwable = new RuntimeException("test");
            RuntimeException result = ExceptionUtils.throwUnchecked(throwable);
            assertEquals(throwable, result);
        }

    @Test
        public void testWrapAndThrow() {
            Throwable throwable = new RuntimeException("test");
            assertThrows(UndeclaredThrowableException.class, () -> ExceptionUtils.wrapAndThrow(throwable));
        }
}
