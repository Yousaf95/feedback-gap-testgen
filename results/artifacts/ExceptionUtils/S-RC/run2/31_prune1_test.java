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

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAsRuntimeException() {
            Throwable throwable = new RuntimeException("test");
            RuntimeException result = ExceptionUtils.asRuntimeException(throwable);
            assertEquals(throwable, result);
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
        public void testGetThrowableListWithNullThrowable() {
            assertThrows(NullPointerException.class, () -> ExceptionUtils.getThrowableList(null));
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
        public void testRemoveCommonFrames_2() {
            List<String> causeFrames = Arrays.asList(
                "com.example.Class.method1(file1.java:1)",
                "com.example.Class.method2(file2.java:2)"
            );
            List<String> wrapperFrames = Arrays.asList(
                "com.example.WrapperClass.methodA(file3.java:3)",
                "com.example.Class.method1(file1.java:1)",
                "com.example.Class.method2(file2.java:2)"
            );
            ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
            assertEquals(Arrays.asList("com.example.Class.method1(file1.java:1)", "com.example.Class.method2(file2.java:2)"), causeFrames);
        }

    @Test
        public void testRethrow() {
            Throwable throwable = new RuntimeException("test");
            assertThrows(RuntimeException.class, () -> ExceptionUtils.rethrow(throwable));
        }
}
