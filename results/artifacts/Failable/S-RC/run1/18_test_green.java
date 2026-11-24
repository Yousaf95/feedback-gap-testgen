package org.apache.commons.lang3.function;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assumptions.*;
import java.io.IOException;
import java.util.Collections;
import java.util.function.Supplier;

public class Failable_GenTest {

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testAcceptWithConsumer(String input) {
        Consumer<String> consumer = s -> System.out.println(s);
        Failable.accept((FailableBiConsumer<String, Void, Exception>) (s, u) -> consumer.accept(s), input, null);
    }

    @Test
    public void testAcceptWithNullConsumer() {
        assertThrows(NullPointerException.class, () -> Failable.accept(null, "test"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testApplyWithFunction(String input) {
        Function<String, String> function = s -> s.toUpperCase();
        assertEquals(function.apply(input), Failable.apply((FailableBiFunction<String, Void, String, Exception>) (s, u) -> function.apply(s), input, null));
    }

    @Test
    public void testApplyWithNullFunction() {
        assertThrows(NullPointerException.class, () -> Failable.apply(null, "test"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testRunWithRunnable(String input) {
        Runnable runnable = () -> System.out.println(input);
        Failable.run((FailableRunnable<Exception>) () -> runnable.run());
    }

    @Test
    public void testRunWithNullRunnable() {
        assertThrows(NullPointerException.class, () -> Failable.run(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testCallWithCallable(String input) throws Exception {
        java.util.concurrent.Callable<String> callable = () -> input.toUpperCase();
        assertEquals(callable.call(), Failable.call((FailableCallable<String, Exception>) () -> callable.call()));
    }

    @Test
    public void testCallWithNullCallable() {
        assertThrows(NullPointerException.class, () -> Failable.call(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testGetWithSupplier(String input) {
        java.util.function.Supplier<String> supplier = () -> input.toUpperCase();
        assertEquals(supplier.get(), Failable.get((FailableSupplier<String, Exception>) () -> supplier.get()));
    }

    @Test
    public void testGetWithNullSupplier() {
        assertThrows(NullPointerException.class, () -> Failable.get(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testTestWithPredicate(String input) {
        Predicate<String> predicate = s -> s.length() > 0;
        assertTrue(Failable.test((FailableBiPredicate<String, Void, Exception>) (s, u) -> predicate.test(s), input, null));
    }

    @Test
    public void testTestWithNullPredicate() {
        assertThrows(NullPointerException.class, () -> Failable.test(null, "test"));
    }

    // ---- added by coverage-guided refinement ----

}
