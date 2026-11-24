package org.apache.commons.lang3.exception;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ExceptionUtils_GenTest {

    
    
    @Test
    public void testGetCause() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
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
        Throwable throwable = new Exception("test", cause);
        Throwable result = ExceptionUtils.getRootCause(throwable);
        assertEquals(cause, result);
    }

    @Test
    public void testGetRootCauseMessage() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        String result = ExceptionUtils.getRootCauseMessage(throwable);
        assertEquals("RuntimeException: cause", result);
    }

    @Test
    public void testGetRootCauseStackTrace() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        String[] result = ExceptionUtils.getRootCauseStackTrace(throwable);
        assertTrue(Arrays.stream(result).anyMatch(s -> s.contains("cause")));
    }

    @Test
    public void testGetStackFrames() {
        Throwable throwable = new RuntimeException("test");
        String[] frames = ExceptionUtils.getStackFrames(throwable);
        assertTrue(Arrays.stream(frames).anyMatch(s -> s.contains("RuntimeException: test")));
    }

    @Test
    public void testGetStackTrace() {
        Throwable throwable = new RuntimeException("test");
        String stackTrace = ExceptionUtils.getStackTrace(throwable);
        assertTrue(stackTrace.contains("RuntimeException: test"));
    }

    @Test
    public void testGetThrowableCount() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        int count = ExceptionUtils.getThrowableCount(throwable);
        assertEquals(2, count);
    }

    @Test
    public void testGetThrowableList() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        List<Throwable> list = ExceptionUtils.getThrowableList(throwable);
        assertTrue(list.contains(throwable));
        assertTrue(list.contains(cause));
    }

    @Test
    public void testGetThrowables() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        Throwable[] throwables = ExceptionUtils.getThrowables(throwable);
        assertEquals(2, throwables.length);
        assertTrue(Arrays.asList(throwables).contains(throwable));
        assertTrue(Arrays.asList(throwables).contains(cause));
    }

    
    @Test
    public void testIndexOfThrowable() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        int index = ExceptionUtils.indexOfThrowable(throwable, RuntimeException.class);
        assertEquals(1, index);
    }

    @Test
    public void testIsChecked() {
        Throwable checked = new Exception("checked");
        assertTrue(ExceptionUtils.isChecked(checked));
    }

    @Test
    public void testIsUnchecked() {
        Throwable unchecked = new RuntimeException("unchecked");
        assertTrue(ExceptionUtils.isUnchecked(unchecked));
    }

    @Test
    public void testPrintRootCauseStackTrace() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        ExceptionUtils.printRootCauseStackTrace(throwable, System.out);
        // Output is not captured for verification in this simple test
    }

    
    @Test
    public void testRethrow() {
        Throwable throwable = new RuntimeException("test");
        assertThrows(RuntimeException.class, () -> ExceptionUtils.rethrow(throwable));
    }

    @Test
    public void testStream() {
        Throwable cause = new RuntimeException("cause");
        Throwable throwable = new Exception("test", cause);
        List<Throwable> result = ExceptionUtils.stream(throwable).toList();
        assertTrue(result.contains(throwable));
        assertTrue(result.contains(cause));
    }

    @Test
    public void testThrowUnchecked() {
        Throwable unchecked = new RuntimeException("unchecked");
        assertThrows(RuntimeException.class, () -> ExceptionUtils.throwUnchecked(unchecked));
    }

    
    @Test
        public void testGetMessageWithNullThrowable() {
            String message = ExceptionUtils.getMessage(null);
            assertEquals("", message);
        }

    
    @Test
        public void testGetRootCauseMessageWithNullThrowable() {
            String message = ExceptionUtils.getRootCauseMessage(null);
            assertEquals("", message);
        }

    @Test
        public void testGetRootCauseStackTraceWithNoCause() {
            Throwable throwable = new Exception("Top Level");
            String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(throwable);
            assertTrue(stackTrace.length > 0);
        }

    
    @Test
        public void testGetStackTraceWithNullThrowable() {
            String stackTrace = ExceptionUtils.getStackTrace(null);
            assertEquals("", stackTrace);
        }

    @Test
        public void testGetThrowableCountWithNoCause() {
            Throwable throwable = new Exception("Top Level");
            int count = ExceptionUtils.getThrowableCount(throwable);
            assertEquals(1, count);
        }

    @Test
        public void testGetThrowableListWithNoCause() {
            Throwable throwable = new Exception("Top Level");
            List<Throwable> list = ExceptionUtils.getThrowableList(throwable);
            assertEquals(1, list.size());
        }

    @Test
        public void testGetThrowablesWithNoCause() {
            Throwable throwable = new Exception("Top Level");
            Throwable[] throwables = ExceptionUtils.getThrowables(throwable);
            assertEquals(1, throwables.length);
        }

    @Test
        public void testIndexOfThrowableWithNullThrowable() {
            int index = ExceptionUtils.indexOfThrowable(null, RuntimeException.class);
            assertEquals(-1, index);
        }

    @Test
        public void testIsCheckedWithUncheckedException() {
            boolean isChecked = ExceptionUtils.isChecked(new RuntimeException("Unchecked"));
            assertFalse(isChecked);
        }

    
    @Test
        public void testPrintRootCauseStackTraceWithNullThrowable() {
            ExceptionUtils.printRootCauseStackTrace(null, System.err);
        }

    @Test
        public void testRemoveCommonFramesWithNoCommonFrames() {
            List<String> causeFrames = new ArrayList<>();
            causeFrames.add("Frame1");
            causeFrames.add("Frame2");
    
            List<String> wrapperFrames = new ArrayList<>();
            wrapperFrames.add("Frame3");
            wrapperFrames.add("Frame4");
    
            ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
            assertEquals(2, causeFrames.size());
        }

    @Test
        public void testStreamWithNoCause() {
            Throwable throwable = new Exception("Top Level");
            Stream<Throwable> stream = ExceptionUtils.stream(throwable);
            assertEquals(1, stream.count());
        }

    @Test
        public void testThrowUncheckedWithUncheckedException() {
            Throwable throwable = new RuntimeException("Unchecked");
            assertThrows(RuntimeException.class, () -> ExceptionUtils.throwUnchecked(throwable));
        }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testGetRootCauseWithNoCause() {
            Throwable throwable = new RuntimeException("No cause");
            assertNull(ExceptionUtils.getRootCause(throwable));
        }

    @Test
        public void testGetRootCauseMessageWithNoCause() {
            Throwable throwable = new RuntimeException("No cause");
            assertEquals("RuntimeException: No cause", ExceptionUtils.getRootCauseMessage(throwable));
        }

    @Test
        public void testGetRootCauseStackTraceWithNoCause_2() {
            Throwable throwable = new RuntimeException("No cause");
            String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(throwable);
            assertTrue(stackTrace.length > 0);
            assertTrue(stackTrace[0].contains("RuntimeException: No cause"));
        }

    @Test
        public void testGetStackFramesWithNullThrowable() {
            assertNull(ExceptionUtils.getStackFrames((Throwable) null));
        }

    @Test
        public void testGetStackTraceWithNullThrowable_2() {
            assertEquals("", ExceptionUtils.getStackTrace((Throwable) null));
        }

    @Test
        public void testGetThrowableCountWithNoCause_2() {
            Throwable throwable = new RuntimeException("No cause");
            assertEquals(1, ExceptionUtils.getThrowableCount(throwable));
        }

    @Test
        public void testGetThrowableListWithNoCause_2() {
            Throwable throwable = new RuntimeException("No cause");
            List<Throwable> throwables = ExceptionUtils.getThrowableList(throwable);
            assertEquals(1, throwables.size());
            assertEquals(throwable, throwables.get(0));
        }

    @Test
        public void testGetThrowablesWithNoCause_2() {
            Throwable throwable = new RuntimeException("No cause");
            Throwable[] throwables = ExceptionUtils.getThrowables(throwable);
            assertEquals(1, throwables.length);
            assertEquals(throwable, throwables[0]);
        }

    @Test
        public void testIndexOfThrowableWithNullThrowable_2() {
            int index = ExceptionUtils.indexOfThrowable((Throwable) null, RuntimeException.class);
            assertEquals(-1, index);
        }

    @Test
        public void testIsCheckedWithUncheckedException_2() {
            assertFalse(ExceptionUtils.isChecked(new RuntimeException()));
        }

    @Test
        public void testPrintRootCauseStackTraceWithNullThrowable_2() {
            ExceptionUtils.printRootCauseStackTrace((Throwable) null, System.err);
        }

    @Test
        public void testRemoveCommonFramesWithNoCommonFrames_2() {
            List<String> causeFrames = new ArrayList<>();
            causeFrames.add("Frame1");
            causeFrames.add("Frame2");
    
            List<String> wrapperFrames = new ArrayList<>();
            wrapperFrames.add("WrapperFrame1");
            wrapperFrames.add("Frame1");
            wrapperFrames.add("Frame2");
    
            ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
            assertEquals(2, causeFrames.size());
        }

    @Test
        public void testStreamWithNoCause_2() {
            Throwable throwable = new RuntimeException("No cause");
            Stream<Throwable> stream = ExceptionUtils.stream(throwable);
            assertTrue(stream.count() == 1);
        }
}
