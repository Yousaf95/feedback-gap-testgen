package org.apache.commons.lang3.function;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Failable_GenTest {

    @Test
    void testAcceptConsumerNormalCase() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        FailableConsumer<String, IOException> consumer = sb::append;

        // Act
        Failable.accept(consumer, "test");

        // Assert
        assertEquals("test", sb.toString());
    }

    @Test
    void testAcceptConsumerThrowsException() {
        // Arrange
        FailableConsumer<String, IOException> consumer = s -> { throw new IOException("Expected"); };

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.accept(consumer, "test"));
        assertTrue(exception.getCause() instanceof IOException);
    }

    @Test
    void testApplyFunctionNormalCase() {
        // Arrange
        FailableFunction<String, Integer, IOException> function = String::length;

        // Act
        int result = Failable.apply(function, "test");

        // Assert
        assertEquals(4, result);
    }

    @Test
    void testApplyFunctionThrowsException() {
        // Arrange
        FailableFunction<String, Integer, IOException> function = s -> { throw new IOException("Expected"); };

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.apply(function, "test"));
        assertTrue(exception.getCause() instanceof IOException);
    }

    @Test
    void testGetSupplierNormalCase() {
        // Arrange
        FailableSupplier<String, IOException> supplier = () -> "test";

        // Act
        String result = Failable.get(supplier);

        // Assert
        assertEquals("test", result);
    }

    @Test
    void testGetSupplierThrowsException() {
        // Arrange
        FailableSupplier<String, IOException> supplier = () -> { throw new IOException("Expected"); };

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.get(supplier));
        assertTrue(exception.getCause() instanceof IOException);
    }

    @Test
    void testRunRunnableNormalCase() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        FailableRunnable<IOException> runnable = () -> sb.append("test");

        // Act
        Failable.run(runnable);

        // Assert
        assertEquals("test", sb.toString());
    }

    @Test
    void testRunRunnableThrowsException() {
        // Arrange
        FailableRunnable<IOException> runnable = () -> { throw new IOException("Expected"); };

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> Failable.run(runnable));
        assertTrue(exception.getCause() instanceof IOException);
    }

    @Test
    void testAsConsumerNormalCase() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        FailableConsumer<String, IOException> failableConsumer = sb::append;
        java.util.function.Consumer<String> consumer = Failable.asConsumer(failableConsumer);

        // Act
        consumer.accept("test");

        // Assert
        assertEquals("test", sb.toString());
    }

    @Test
    void testAsFunctionNormalCase() {
        // Arrange
        FailableFunction<String, Integer, IOException> failableFunction = String::length;
        java.util.function.Function<String, Integer> function = Failable.asFunction(failableFunction);

        // Act
        int result = function.apply("test");

        // Assert
        assertEquals(4, result);
    }

    
    @Test
    void testTryWithResourcesNormalCase() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        FailableRunnable<IOException> action = () -> sb.append("test");
        FailableRunnable<IOException>[] resources = new FailableRunnable[0];

        // Act
        Failable.tryWithResources(action, resources);

        // Assert
        assertEquals("test", sb.toString());
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        void testAcceptDoubleConsumerNormalCase() {
            Failable.accept((value) -> {}, 1.0);
        }

    @Test
        void testAcceptIntConsumerNormalCase() {
            Failable.accept((FailableIntConsumer<Throwable>) (value) -> {}, 1);
        }

    @Test
        void testAcceptLongConsumerNormalCase() {
            Failable.accept((FailableLongConsumer<Throwable>) (value) -> {}, 1L);
        }

    @Test
        void testApplyBiFunctionNormalCase() {
            String result = Failable.apply((input1, input2) -> input1 + input2, "Hello", "World");
            assertTrue(result.equals("HelloWorld"));
        }

    @Test
        void testApplyAsDoubleNormalCase() {
            double result = Failable.applyAsDouble((left, right) -> left + right, 1.0, 2.0);
            assertTrue(result == 3.0);
        }

    @Test
        void testAsBiConsumerNormalCase() {
            java.util.function.BiConsumer<String, String> biConsumer = Failable.asBiConsumer((input1, input2) -> {});
            biConsumer.accept("test", "value");
        }

    @Test
        void testAsBiFunctionNormalCase() {
            java.util.function.BiFunction<String, String, String> biFunction = Failable.asBiFunction((input1, input2) -> input1 + input2);
            assertTrue(biFunction.apply("Hello", "World").equals("HelloWorld"));
        }

    @Test
        void testAsBiPredicateNormalCase() {
            java.util.function.BiPredicate<String, String> biPredicate = Failable.asBiPredicate((input1, input2) -> input1.equals(input2));
            assertTrue(biPredicate.test("test", "test"));
        }

    @Test
        void testAsStreamCollectionNormalCase() {
            Stream<String> stream = Failable.stream(List.of("a", "b")).stream();
            List<String> result = stream.collect(Collectors.toList());
            assertTrue(result.containsAll(List.of("a", "b")));
        }

    @Test
        void testAsStreamStreamNormalCase() {
            Stream<String> originalStream = Stream.of("a", "b");
            Stream<String> failableStream = Failable.stream(originalStream).stream();
            List<String> result = failableStream.collect(Collectors.toList());
            assertTrue(result.containsAll(List.of("a", "b")));
        }

    @Test
        void testTestBiPredicateNormalCase() {
            boolean result = Failable.test((input1, input2) -> input1.equals(input2), "test", "test");
            assertTrue(result);
        }

    @Test
        void testTryWithResourcesNoErrorHandlerNormalCase() {
            Failable.tryWithResources(() -> {}, () -> {});
        }

    @Test
        void testTryWithResourcesWithErrorHandlerNormalCase() {
            FailableConsumer<Throwable, Throwable> errorHandler = (throwable) -> {};
            Failable.tryWithResources(() -> {}, errorHandler, () -> {});
        }

    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
        void testTryWithResourcesErrorHandlerThrowsException() {
            FailableConsumer<Throwable, Throwable> errorHandler = (throwable) -> { throw new RuntimeException(throwable); };
            assertThrows(RuntimeException.class, () -> 
                Failable.tryWithResources(() -> { throw new IOException(); }, errorHandler, () -> {})
            );
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAsCallableNormalCase() throws Exception {
            Callable<String> callable = Failable.asCallable(() -> "result");
            assertEquals("result", callable.call());
        }

    @Test
        public void testAsCallableThrowsException() {
            Callable<String> callable = Failable.asCallable(() -> { throw new IOException("test"); });
            assertThrows(UncheckedIOException.class, callable::call);
        }

    @Test
        public void testAsPredicateNormalCase() {
            Predicate<String> predicate = Failable.asPredicate(s -> s.equals("test"));
            assertTrue(predicate.test("test"));
        }

    @Test
        public void testAsPredicateThrowsException() {
            Predicate<String> predicate = Failable.asPredicate(s -> { throw new IOException("test"); });
            assertThrows(UncheckedIOException.class, () -> predicate.test("test"));
        }

    @Test
        public void testAsRunnableNormalCase() {
            Runnable runnable = Failable.asRunnable(() -> {});
            runnable.run();
        }

    @Test
        public void testAsRunnableThrowsException() {
            Runnable runnable = Failable.asRunnable(() -> { throw new IOException("test"); });
            assertThrows(UncheckedIOException.class, runnable::run);
        }

    @Test
        public void testAsSupplierNormalCase() {
            Supplier<String> supplier = Failable.asSupplier(() -> "result");
            assertEquals("result", supplier.get());
        }

    @Test
        public void testAsSupplierThrowsException() {
            Supplier<String> supplier = Failable.asSupplier(() -> { throw new IOException("test"); });
            assertThrows(UncheckedIOException.class, supplier::get);
        }

    @Test
        public void testCallNormalCase() throws Exception {
            String result = Failable.call(() -> "result");
            assertEquals("result", result);
        }

    @Test
        public void testCallThrowsException() {
            assertThrows(UncheckedIOException.class, () -> Failable.call(() -> { throw new IOException("test"); }));
        }

    @Test
        public void testGetThrowsException() {
            assertThrows(UncheckedIOException.class, () -> Failable.get(() -> { throw new IOException("test"); }));
        }

    @Test
        public void testGetAsBooleanThrowsException() {
            assertThrows(UncheckedIOException.class, () -> Failable.getAsBoolean(() -> { throw new IOException("test"); }));
        }

    @Test
        public void testGetAsDoubleThrowsException() {
            assertThrows(UncheckedIOException.class, () -> Failable.getAsDouble(() -> { throw new IOException("test"); }));
        }

    @Test
        public void testGetAsIntThrowsException() {
            assertThrows(UncheckedIOException.class, () -> Failable.getAsInt(() -> { throw new IOException("test"); }));
        }

    @Test
        public void testGetAsLongThrowsException() {
            assertThrows(UncheckedIOException.class, () -> Failable.getAsLong(() -> { throw new IOException("test"); }));
        }

    @Test
        public void testGetAsShortThrowsException() {
            assertThrows(UncheckedIOException.class, () -> Failable.getAsShort(() -> { throw new IOException("test"); }));
        }

    @Test
        public void testRethrowWithIOException() {
            assertThrows(UncheckedIOException.class, () -> Failable.rethrow(new IOException("test")));
        }

    @Test
        public void testRethrowWithUndeclaredThrowableException() {
            assertThrows(UndeclaredThrowableException.class, () -> Failable.rethrow(new Exception("test")));
        }

    @Test
        public void testRunThrowsException() {
            assertThrows(UncheckedIOException.class, () -> Failable.run(() -> { throw new IOException("test"); }));
        }

    @Test
        public void testTestPredicateNormalCase() {
            boolean result = Failable.test(s -> s.equals("test"), "test");
            assertTrue(result);
        }

    @Test
        public void testTestPredicateThrowsException() {
            assertThrows(UncheckedIOException.class, () -> Failable.test(s -> { throw new IOException("test"); }, "test"));
        }

    @Test
        public void testTryWithResourcesNoErrorHandlerWithExceptionInAction() {
            assertThrows(UncheckedIOException.class, () -> 
                Failable.tryWithResources(() -> { throw new IOException("action"); })
            );
        }

    @Test
        public void testTryWithResourcesWithErrorHandlerNormalCase_2() {
            Failable.tryWithResources(
                () -> { throw new IOException("action"); },
                th -> {},
                () -> {}
            );
        }

    @Test
        public void testTryWithResourcesWithErrorHandlerThrowsExceptionInActionAndErrorHandler() {
            assertThrows(UndeclaredThrowableException.class, () -> 
                Failable.tryWithResources(
                    () -> { throw new Exception("action"); },
                    th -> { throw new Exception("error handler"); }
                )
            );
        }

    @Test
        public void testTryWithResourcesWithErrorHandlerThrowsExceptionInResource() {
            assertThrows(UndeclaredThrowableException.class, () -> 
                Failable.tryWithResources(
                    () -> {},
                    th -> {},
                    () -> { throw new Exception("resource"); }
                )
            );
        }

    @Test
        public void testTryWithResourcesWithErrorHandlerThrowsExceptionInActionAndResource() {
            assertThrows(UndeclaredThrowableException.class, () -> 
                Failable.tryWithResources(
                    () -> { throw new IOException("action"); },
                    th -> {},
                    () -> { throw new Exception("resource"); }
                )
            );
        }

    @Test
        public void testTryWithResourcesWithErrorHandlerThrowsExceptionInActionAndErrorHandlerAndResource() {
            assertThrows(UndeclaredThrowableException.class, () -> 
                Failable.tryWithResources(
                    () -> { throw new IOException("action"); },
                    th -> { throw new Exception("error handler"); },
                    () -> { throw new Exception("resource"); }
                )
            );
        }

    @Test
        public void testTryWithResourcesNoErrorHandlerWithExceptionInResource() {
            assertThrows(UndeclaredThrowableException.class, () -> 
                Failable.tryWithResources(
                    () -> {},
                    () -> { throw new Exception("resource"); }
                )
            );
        }

    @Test
        public void testTryWithResourcesNoErrorHandlerWithExceptionInActionAndResource() {
            assertThrows(UndeclaredThrowableException.class, () -> 
                Failable.tryWithResources(
                    () -> { throw new IOException("action"); },
                    () -> { throw new Exception("resource"); }
                )
            );
        }
}
