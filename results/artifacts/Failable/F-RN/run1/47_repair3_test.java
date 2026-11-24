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
            assertThrows(RuntimeException.class, () -> Failable.accept((FailableBiConsumer<Void, Void, IOException>) (o1, o2) -> { throw new IOException(); }, null, null));
        }

    @Test
        public void testApplyWithException() {
            assertThrows(RuntimeException.class, () -> Failable.apply((FailableFunction<String, String, IOException>) s -> { throw new IOException(); }, "input"));
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

    // ---- added by coverage-guided refinement ----
    @Test
            public void testAcceptWithNullConsumer() {
                assertThrows(NullPointerException.class, () -> Failable.accept(null, "test"));
            }

    @Test
            public void testApplyWithNullFunction() {
                assertThrows(NullPointerException.class, () -> Failable.apply(null, "test"));
            }

    @Test
            public void testCallWithNullCallable() {
                assertThrows(NullPointerException.class, () -> Failable.call(null));
            }

    @Test
            public void testGetWithNullSupplier() {
                assertThrows(NullPointerException.class, () -> Failable.get(null));
            }

    @Test
            public void testRunWithNullRunnable() {
            assertThrows(NullPointerException.class, () -> Failable.run(null));
        }

    @Test
        public void testAcceptWithException_2() {
            FailableBiConsumer<String, String, IOException> consumer = (s1, s2) -> {
                throw new IOException("test exception");
            };
            assertThrows(UncheckedIOException.class, () -> Failable.accept(consumer, "test", "test"));
        }

    @Test
        public void testApplyWithException_2() {
            FailableFunction<String, String, IOException> function = (s) -> {
                throw new IOException("test exception");
            };
            assertThrows(UncheckedIOException.class, () -> Failable.apply(function, "test"));
        }

    @Test
        public void testCallWithException_2() {
            FailableCallable<String, IOException> callable = () -> {
                throw new IOException("test exception");
            };
            assertThrows(UncheckedIOException.class, () -> Failable.call(callable));
        }

    @Test
        public void testGetWithException_2() {
            FailableSupplier<String, IOException> supplier = () -> {
                throw new IOException("test exception");
            };
            assertThrows(UncheckedIOException.class, () -> Failable.get(supplier));
        }

    @Test
        public void testRunWithException_2() {
            FailableRunnable<IOException> runnable = () -> {
                throw new IOException("test exception");
            };
            assertThrows(UncheckedIOException.class, () -> Failable.run(runnable));
        }
}
