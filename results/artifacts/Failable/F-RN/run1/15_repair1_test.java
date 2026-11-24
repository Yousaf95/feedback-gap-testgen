package org.apache.commons.lang3.function;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class Failable_GenTest {

    
    
    @Test
    public void testCall() throws IOException {
        FailableCallable<String, IOException> callable = () -> "test";
        String result = Failable.call(callable);
        assertEquals("test", result);
    }

    @Test
    public void testGet() {
        FailableSupplier<Integer, RuntimeException> supplier = () -> 5;
        int result = Failable.get(supplier);
        assertEquals(5, result);
    }

    @Test
    public void testRun() {
        FailableRunnable<RuntimeException> runnable = () -> System.out.println("test");
        Failable.run(runnable);
    }

    
    
    
    
    
    

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAcceptWithException() {
            assertThrows(RuntimeException.class, () -> Failable.accept(e -> { throw new IOException(); }, null, null));
        }

    @Test
        public void testApplyWithException() {
            assertThrows(RuntimeException.class, () -> Failable.apply(e -> { throw new IOException(); }, "input"));
        }

    @Test
        public void testCallWithException() {
            assertThrows(RuntimeException.class, () -> Failable.call(() -> { throw new IOException(); }));
        }

    @Test
        public void testGetWithException() {
            assertThrows(RuntimeException.class, () -> Failable.get(() -> { throw new IOException(); }));
        }

    @Test
        public void testRunWithException() {
            assertThrows(RuntimeException.class, () -> Failable.run(() -> { throw new IOException(); }));
        }
}
