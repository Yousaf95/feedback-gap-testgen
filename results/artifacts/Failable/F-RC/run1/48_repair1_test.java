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
        public void testAccept() {
            FailableBiConsumer<String, String, IOException> consumer = (s1, s2) -> {
                if ("test".equals(s1)) throw new IOException("Test exception");
            };
            assertThrows(RuntimeException.class, () -> Failable.accept(consumer, "test", "value"));
        }

    @Test
        public void testApply() {
            FailableBiFunction<String, String, Integer, IOException> function = (s1, s2) -> {
                if ("test".equals(s1)) throw new IOException("Test exception");
                return s2.length();
            };
            assertThrows(RuntimeException.class, () -> Failable.apply(function, "test", "value"));
        }

    @Test
        public void testApplyAsDouble() {
            FailableDoubleBinaryOperator<IOException> function = (d1, d2) -> {
                if (d1 == 1.0) throw new IOException("Test exception");
                return d1 + d2;
            };
            assertThrows(RuntimeException.class, () -> Failable.applyAsDouble(function, 1.0, 2.0));
        }

    @Test
        public void testBiConsumer() {
            FailableBiConsumer<String, String, IOException> consumer = (s1, s2) -> {
                if ("test".equals(s1)) throw new IOException("Test exception");
            };
            BiConsumer<String, String> biConsumer = Failable.asBiConsumer(consumer);
            assertThrows(RuntimeException.class, () -> biConsumer.accept("test", "value"));
        }

    
    
    
    @Test
        public void testConsumer() {
            FailableConsumer<String, IOException> consumer = s -> {
                if ("test".equals(s)) throw new IOException("Test exception");
            };
            Consumer<String> c = Failable.asConsumer(consumer);
            assertThrows(RuntimeException.class, () -> c.accept("test"));
        }

    @Test
        public void testFunction() {
            FailableFunction<String, Integer, IOException> function = s -> {
                if ("test".equals(s)) throw new IOException("Test exception");
                return s.length();
            };
            Function<String, Integer> f = Failable.asFunction(function);
            assertThrows(RuntimeException.class, () -> f.apply("test"));
        }

    @Test
        public void testPredicate() {
            FailablePredicate<String, IOException> predicate = s -> {
                if ("test".equals(s)) throw new IOException("Test exception");
                return true;
            };
            Predicate<String> p = Failable.asPredicate(predicate);
            assertThrows(RuntimeException.class, () -> p.test("test"));
        }

    @Test
        public void testRunnable() {
            FailableRunnable<IOException> runnable = () -> {
                throw new IOException("Test exception");
            };
            Runnable r = Failable.asRunnable(runnable);
            assertThrows(RuntimeException.class, r::run);
        }

    
    @Test
        public void testTryWithResources() {
            FailableRunnable<IOException> action = () -> {
                throw new IOException("Test exception");
            };
            FailableConsumer<Throwable, IOException> errorHandler = t -> {
                if (t instanceof IOException) throw new IOException("Error handler exception");
            };
            FailableRunnable<IOException> resource1 = () -> {};
            FailableRunnable<IOException> resource2 = () -> {
                throw new IOException("Resource exception");
            };
    
            assertThrows(RuntimeException.class, () -> Failable.tryWithResources(action, errorHandler, resource1, resource2));
        }

    @Test
        public void testTryWithResourcesNoErrorHandler() {
            FailableRunnable<IOException> action = () -> {
                throw new IOException("Test exception");
            };
            FailableRunnable<IOException> resource1 = () -> {};
            FailableRunnable<IOException> resource2 = () -> {
                throw new IOException("Resource exception");
            };
    
            assertThrows(RuntimeException.class, () -> Failable.tryWithResources(action, resource1, resource2));
        }

    // ---- added by coverage-guided refinement ----
    @Test
            public void testAccept_2() {
                FailableBiConsumer<String, Integer, IOException> consumer = (s, i) -> {
                    if ("test".equals(s)) throw new IOException("Test exception");
                };
                assertThrows(UncheckedIOException.class, () -> Failable.accept(consumer, "test", 1));
            }

    @Test
            public void testApply_2() {
                FailableBiFunction<String, Integer, String, IOException> function = (s, i) -> {
                    if ("test".equals(s)) throw new IOException("Test exception");
                    return s + i;
                };
                assertThrows(UncheckedIOException.class, () -> Failable.apply(function, "test", 1));
            }

    @Test
            public void testApplyAsDouble_2() {
                FailableDoubleBinaryOperator<IOException> function = (d1, d2) -> {
                    if (d1 == 1.0) throw new IOException("Test exception");
                    return d1 + d2;
                };
                assertThrows(UncheckedIOException.class, () -> Failable.applyAsDouble(function, 1.0, 2.0));
            }

    @Test
            public void testBiConsumer_2() {
            BiConsumer<String, Integer> consumer = (s, i) -> {
                    if ("test".equals(s)) throw new IOException("Test exception");
                };
                FailableBiConsumer<String, Integer, IOException> failableConsumer = Failable.asBiConsumer(consumer);
                assertThrows(UncheckedIOException.class, () -> failableConsumer.accept("test", 1));
            }

    @Test
            public void testConsumer_2() {
            Consumer<String> consumer = s -> {
                    if ("test".equals(s)) throw new IOException("Test exception");
                };
                FailableConsumer<String, IOException> failableConsumer = Failable.asConsumer(consumer);
                assertThrows(UncheckedIOException.class, () -> failableConsumer.accept("test"));
            }

    @Test
            public void testFunction_2() {
            Function<String, String> function = s -> {
                    if ("test".equals(s)) throw new IOException("Test exception");
                    return s;
                };
                FailableFunction<String, String, IOException> failableFunction = Failable.asFunction(function);
                assertThrows(UncheckedIOException.class, () -> failableFunction.apply("test"));
            }

    @Test
            public void testPredicate_2() {
            Predicate<String> predicate = s -> {
                    if ("test".equals(s)) throw new IOException("Test exception");
                    return true;
                };
                FailablePredicate<String, IOException> failablePredicate = Failable.asPredicate(predicate);
                assertThrows(UncheckedIOException.class, () -> failablePredicate.test("test"));
            }

    @Test
            public void testRunnable_2() {
            Runnable runnable = () -> {
                    throw new IOException("Test exception");
                };
                FailableRunnable<IOException> failableRunnable = Failable.asRunnable(runnable);
                assertThrows(UncheckedIOException.class, () -> failableRunnable.run());
            }

    @Test
            public void testTryWithResources_2() {
            FailableRunnable<IOException> action = () -> {
                    throw new IOException("Test exception");
                };
                FailableConsumer<Throwable, IOException> errorHandler = t -> {
                    if (t instanceof IOException) throw new UncheckedIOException((IOException) t);
                };
                FailableRunnable<IOException> resource = () -> {
                    throw new IOException("Resource exception");
                };
        
                assertThrows(UncheckedIOException.class, () -> Failable.tryWithResources(action, errorHandler, resource));
            }

    @Test
            public void testTryWithResourcesNoErrorHandler_2() {
            FailableRunnable<IOException> action = () -> {
                    throw new IOException("Test exception");
                };
                FailableRunnable<IOException> resource = () -> {
                    throw new IOException("Resource exception");
                };
        
                assertThrows(UncheckedIOException.class, () -> Failable.tryWithResources(action, resource));
            }

    @Test
            public void testGetAsBoolean() {
            FailableBooleanSupplier<IOException> supplier = () -> {
                    if (true) throw new IOException("Test exception");
                    return true;
                };
                assertThrows(UncheckedIOException.class, () -> Failable.getAsBoolean(supplier));
            }

    @Test
            public void testGetAsDouble() {
            FailableDoubleSupplier<IOException> supplier = () -> {
                    if (true) throw new IOException("Test exception");
                    return 1.0;
                };
                assertThrows(UncheckedIOException.class, () -> Failable.getAsDouble(supplier));
            }

    @Test
            public void testGetAsInt() {
            FailableIntSupplier<IOException> supplier = () -> {
                    if (true) throw new IOException("Test exception");
                    return 1;
                };
                assertThrows(UncheckedIOException.class, () -> Failable.getAsInt(supplier));
            }

    @Test
            public void testGetAsLong() {
            FailableLongSupplier<IOException> supplier = () -> {
                    if (true) throw new IOException("Test exception");
                    return 1L;
                };
                assertThrows(UncheckedIOException.class, () -> Failable.getAsLong(supplier));
            }

    @Test
            public void testGetAsShort() {
            FailableShortSupplier<IOException> supplier = () -> {
                    if (true) throw new IOException("Test exception");
                    return 1;
                };
                assertThrows(UncheckedIOException.class, () -> Failable.getAsShort(supplier));
            }
}
