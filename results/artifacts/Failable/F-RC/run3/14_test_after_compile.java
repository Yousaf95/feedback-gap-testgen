package org.apache.commons.lang3.function;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.mockito.Mockito.*;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class Failable_GenTest {

    
    
    @ParameterizedTest
    @MethodSource("provideDoubleConsumer")
    public <E extends Throwable> void testAccept(FailableDoubleConsumer<E> consumer, double value) {
        // Arrange
        boolean[] called = {false};
        FailableDoubleConsumer<E> failableConsumer = v -> {
            called[0] = true;
            consumer.accept(v);
        };

        // Act
        Failable.accept(failableConsumer, value);

        // Assert
        assertTrue(called[0]);
    }

    @ParameterizedTest
    @MethodSource("provideIntConsumer")
    public <E extends Throwable> void testAccept(FailableIntConsumer<E> consumer, int value) {
        // Arrange
        boolean[] called = {false};
        FailableIntConsumer<E> failableConsumer = v -> {
            called[0] = true;
            consumer.accept(v);
        };

        // Act
        Failable.accept(failableConsumer, value);

        // Assert
        assertTrue(called[0]);
    }

    @ParameterizedTest
    @MethodSource("provideLongConsumer")
    public <E extends Throwable> void testAccept(FailableLongConsumer<E> consumer, long value) {
        // Arrange
        boolean[] called = {false};
        FailableLongConsumer<E> failableConsumer = v -> {
            called[0] = true;
            consumer.accept(v);
        };

        // Act
        Failable.accept(failableConsumer, value);

        // Assert
        assertTrue(called[0]);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    @ParameterizedTest
    @MethodSource("provideRunnableWithResources")
    public <E extends Throwable> void testTryWithResources(FailableRunnable<E> action,
            FailableConsumer<Throwable, E> errorHandler, FailableRunnable<? extends Throwable>[] resources) {
        // Arrange
        boolean[] called = {false};
        FailableRunnable<E> failableAction = () -> {
            called[0] = true;
            action.run();
        };

        // Act & Assert
        assertDoesNotThrow(() -> Failable.tryWithResources(failableAction, errorHandler, resources));
        assertTrue(called[0]);
    }

    
    
    
    private static Stream<Arguments> provideBiConsumer() {
        return Stream.of(
                arguments((BiConsumer<String, Integer>) (s, i) -> {}, "test", 1),
                arguments((BiConsumer<String, Integer>) (s, i) -> {}, null, 1),
                arguments((BiConsumer<String, Integer>) (s, i) -> {}, "test", null)
        );
    }

    private static Stream<Arguments> provideConsumer() {
        return Stream.of(
                arguments((Consumer<String>) s -> {}, "test"),
                arguments((Consumer<String>) s -> {}, null)
        );
    }

    private static Stream<Arguments> provideDoubleConsumer() {
        return Stream.of(
                arguments((FailableDoubleConsumer<IOException>) v -> {}, 1.0),
                arguments((FailableDoubleConsumer<IOException>) v -> {}, Double.NaN)
        );
    }

    private static Stream<Arguments> provideIntConsumer() {
        return Stream.of(
                arguments((FailableIntConsumer<IOException>) v -> {}, 1),
                arguments((FailableIntConsumer<IOException>) v -> {}, Integer.MAX_VALUE)
        );
    }

    private static Stream<Arguments> provideLongConsumer() {
        return Stream.of(
                arguments((FailableLongConsumer<IOException>) v -> {}, 1L),
                arguments((FailableLongConsumer<IOException>) v -> {}, Long.MAX_VALUE)
        );
    }

    
    
    private static Stream<Arguments> provideDoubleBinaryOperator() {
        return Stream.of(
                arguments((FailableDoubleBinaryOperator<IOException>) (l, r) -> l + r, 1.0, 2.0),
                arguments((FailableDoubleBinaryOperator<IOException>) (l, r) -> l + r, Double.NaN, 2.0)
        );
    }

    private static Stream<Arguments> provideCallable() {
        return Stream.of(
                arguments((FailableCallable<String, IOException>) () -> "test"),
                arguments((FailableCallable<String, IOException>) () -> null)
        );
    }

    private static Stream<Arguments> provideSupplier() {
        return Stream.of(
                arguments((FailableSupplier<String, IOException>) () -> "test"),
                arguments((FailableSupplier<String, IOException>) () -> null)
        );
    }

    private static Stream<Arguments> provideBooleanSupplier() {
        return Stream.of(
                arguments((FailableBooleanSupplier<IOException>) () -> true),
                arguments((FailableBooleanSupplier<IOException>) () -> false)
        );
    }

    private static Stream<Arguments> provideDoubleSupplier() {
        return Stream.of(
                arguments((FailableDoubleSupplier<IOException>) () -> 1.0),
                arguments((FailableDoubleSupplier<IOException>) () -> Double.NaN)
        );
    }

    private static Stream<Arguments> provideIntSupplier() {
        return Stream.of(
                arguments((FailableIntSupplier<IOException>) () -> 1),
                arguments((FailableIntSupplier<IOException>) () -> Integer.MAX_VALUE)
        );
    }

    private static Stream<Arguments> provideLongSupplier() {
        return Stream.of(
                arguments((FailableLongSupplier<IOException>) () -> 1L),
                arguments((FailableLongSupplier<IOException>) () -> Long.MAX_VALUE)
        );
    }

    private static Stream<Arguments> provideShortSupplier() {
        return Stream.of(
                arguments((FailableShortSupplier<IOException>) () -> (short) 1),
                arguments((FailableShortSupplier<IOException>) () -> Short.MAX_VALUE)
        );
    }

    private static Stream<Arguments> provideRunnable() {
        return Stream.of(
                arguments((FailableRunnable<IOException>) () -> {}),
                arguments((FailableRunnable<IOException>) () -> { throw new IOException(); })
        );
    }

    
    
    private static Stream<Arguments> provideRunnableWithResources() {
        return Stream.of(
                arguments((FailableRunnable<IOException>) () -> {},
                        (FailableConsumer<Throwable, IOException>) t -> {},
                        new FailableRunnable[0]),
                arguments((FailableRunnable<IOException>) () -> { throw new IOException(); },
                        (FailableConsumer<Throwable, IOException>) t -> {},
                        new FailableRunnable[0])
        );
    }

    private static Stream<Arguments> provideRunnableWithResourcesThrowsException() {
        return Stream.of(
                arguments((FailableRunnable<IOException>) () -> { throw new IOException(); },
                        (FailableConsumer<Throwable, IOException>) t -> {},
                        new FailableRunnable[0]),
                arguments((FailableRunnable<IOException>) () -> {},
                        (FailableConsumer<Throwable, IOException>) t -> { throw new IOException(); },
                        new FailableRunnable[0])
        );
    }

    private static Stream<Arguments> provideRunnableWithResourcesThrowsExceptionNoErrorHandler() {
        return Stream.of(
                arguments((FailableRunnable<IOException>) () -> { throw new IOException(); },
                        null,
                        new FailableRunnable[0]),
                arguments((FailableRunnable<IOException>) () -> {},
                        (FailableConsumer<Throwable, IOException>) t -> { throw new IOException(); },
                        new FailableRunnable[0])
        );
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAccept_2() {
            FailableBiConsumer<String, Integer, IOException> consumer = (s, i) -> {
                if ("test".equals(s)) {
                    throw new IOException("Expected exception");
                }
            };
            assertThrows(RuntimeException.class, () -> Failable.accept(consumer, "test", 1));
        }

    @Test
        public void testAcceptNoException() {
            FailableBiConsumer<String, Integer, IOException> consumer = (s, i) -> {};
            Failable.accept(consumer, "notest", 2);
        }

    @Test
        public void testApply() {
            FailableBiFunction<String, Integer, String, IOException> function = (s, i) -> {
                if ("test".equals(s)) {
                    throw new IOException("Expected exception");
                }
                return s + i;
            };
            assertThrows(RuntimeException.class, () -> Failable.apply(function, "test", 1));
        }

    @Test
        public void testApplyNoException() {
            FailableBiFunction<String, Integer, String, IOException> function = (s, i) -> s + i;
            assertEquals("notest2", Failable.apply(function, "notest", 2));
        }

    @Test
        public void testApplyAsDouble() {
            FailableDoubleBinaryOperator<IOException> function = (d1, d2) -> {
                if (d1 == 1.0) {
                    throw new IOException("Expected exception");
                }
                return d1 + d2;
            };
            assertThrows(RuntimeException.class, () -> Failable.applyAsDouble(function, 1.0, 2.0));
        }

    @Test
        public void testApplyAsDoubleNoException() {
            FailableDoubleBinaryOperator<IOException> function = (d1, d2) -> d1 + d2;
            assertEquals(3.0, Failable.applyAsDouble(function, 1.0, 2.0));
        }

    @Test
        public void testBiConsumer() {
            FailableBiConsumer<String, Integer, IOException> consumer = (s, i) -> {};
            BiConsumer<String, Integer> biConsumer = Failable.asBiConsumer(consumer);
            biConsumer.accept("test", 1);
        }

    @Test
        public void testBiFunction() {
            FailableBiFunction<String, Integer, String, IOException> function = (s, i) -> s + i;
            BiFunction<String, Integer, String> biFunction = Failable.asBiFunction(function);
            assertEquals("notest2", biFunction.apply("notest", 2));
        }

    @Test
        public void testBiPredicate() {
            FailableBiPredicate<String, Integer, IOException> predicate = (s, i) -> true;
            BiPredicate<String, Integer> biPredicate = Failable.asBiPredicate(predicate);
            assertTrue(biPredicate.test("test", 1));
        }

    @Test
        public void testCallable() {
            FailableCallable<String, IOException> callable = () -> "result";
            Callable<String> call = Failable.asCallable(callable);
            assertEquals("result", call.call());
        }

    @Test
        public void testConsumer() {
            FailableConsumer<String, IOException> consumer = s -> {};
            Consumer<String> cons = Failable.asConsumer(consumer);
            cons.accept("test");
        }

    @Test
        public void testFunction() {
            FailableFunction<String, Integer, IOException> function = s -> 1;
            Function<String, Integer> func = Failable.asFunction(function);
            assertEquals(1, func.apply("test"));
        }

    @Test
        public void testPredicate() {
            FailablePredicate<String, IOException> predicate = s -> true;
            Predicate<String> pred = Failable.asPredicate(predicate);
            assertTrue(pred.test("test"));
        }

    @Test
        public void testRunnable() {
            FailableRunnable<IOException> runnable = () -> {};
            Runnable run = Failable.asRunnable(runnable);
            run.run();
        }

    @Test
        public void testSupplier() {
            FailableSupplier<String, IOException> supplier = () -> "result";
            Supplier<String> sup = Failable.asSupplier(supplier);
            assertEquals("result", sup.get());
        }

    @Test
        public void testCall() {
            FailableCallable<String, IOException> callable = () -> "result";
            assertThrows(RuntimeException.class, () -> Failable.call(callable));
        }

    @Test
        public void testGet() {
            FailableSupplier<String, IOException> supplier = () -> "result";
            assertEquals("result", Failable.get(supplier));
        }

    @Test
        public void testgetAsBoolean() {
            FailableBooleanSupplier<IOException> supplier = () -> true;
            assertTrue(Failable.getAsBoolean(supplier));
        }

    @Test
        public void testgetAsDouble() {
            FailableDoubleSupplier<IOException> supplier = () -> 1.0;
            assertEquals(1.0, Failable.getAsDouble(supplier));
        }

    @Test
        public void testgetAsInt() {
            FailableIntSupplier<IOException> supplier = () -> 1;
            assertEquals(1, Failable.getAsInt(supplier));
        }

    @Test
        public void testgetAsLong() {
            FailableLongSupplier<IOException> supplier = () -> 1L;
            assertEquals(1L, Failable.getAsLong(supplier));
        }

    @Test
        public void testgetAsShort() {
            FailableShortSupplier<IOException> supplier = () -> (short) 1;
            assertEquals((short) 1, Failable.getAsShort(supplier));
        }

    @Test
        public void testRethrow() {
            IOException exception = new IOException("Expected exception");
            assertThrows(RuntimeException.class, () -> Failable.rethrow(exception));
        }

    @Test
        public void testRun() {
            FailableRunnable<IOException> runnable = () -> {};
            assertDoesNotThrow(() -> Failable.run(runnable));
        }

    @Test
        public void testStreamCollection() {
            List<String> list = Arrays.asList("a", "b", "c");
            FailableStream<String> stream = Failable.stream(list);
            assertEquals(3, stream.count());
        }

    @Test
        public void testStreamStream() {
            Stream<String> stream = Arrays.stream(new String[]{"a", "b", "c"});
            FailableStream<String> failableStream = Failable.stream(stream);
            assertEquals(3, failableStream.count());
        }

    @Test
        public void testTestBiPredicate() {
            FailableBiPredicate<String, Integer, IOException> predicate = (s, i) -> true;
            assertThrows(RuntimeException.class, () -> Failable.test(predicate, "test", 1));
        }

    @Test
        public void testTestBiPredicateNoException() {
            FailableBiPredicate<String, Integer, IOException> predicate = (s, i) -> s.equals("test");
            assertTrue(Failable.test(predicate, "test", 1));
        }

    @Test
        public void testTryWithResources_2() {
            FailableRunnable<IOException> action = () -> {};
            FailableConsumer<Throwable, IOException> errorHandler = t -> {};
            FailableRunnable<IOException> resource = () -> { throw new IOException("Expected exception"); };
            assertThrows(RuntimeException.class, () -> Failable.tryWithResources(action, errorHandler, resource));
        }

    @Test
        public void testTryWithResourcesNoException() {
            FailableRunnable<IOException> action = () -> {};
            FailableConsumer<Throwable, IOException> errorHandler = t -> {};
            FailableRunnable<IOException> resource = () -> {};
            assertDoesNotThrow(() -> Failable.tryWithResources(action, errorHandler, resource));
        }

    @Test
        public void testTryWithResourcesNoErrorHandler() {
            FailableRunnable<IOException> action = () -> { throw new IOException("Expected exception"); };
            FailableRunnable<IOException> resource = () -> {};
            assertThrows(RuntimeException.class, () -> Failable.tryWithResources(action, null, resource));
        }
}
