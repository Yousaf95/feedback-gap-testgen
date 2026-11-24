package org.apache.commons.lang3.function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
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

public class Failable_GenTest {

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testAcceptWithConsumer(String input) {
        Consumer<String> consumer = s -> System.out.println(s);
        Failable.accept(consumer, input);
    }

    @Test
    public void testAcceptWithNullConsumer() {
        assertThrows(NullPointerException.class, () -> Failable.accept(null, "test"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testApplyWithFunction(String input) {
        Function<String, String> function = s -> s.toUpperCase();
        assertEquals(function.apply(input), Failable.apply(function, input));
    }

    @Test
    public void testApplyWithNullFunction() {
        assertThrows(NullPointerException.class, () -> Failable.apply(null, "test"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testRunWithRunnable(String input) {
        Runnable runnable = () -> System.out.println(input);
        Failable.run(runnable);
    }

    @Test
    public void testRunWithNullRunnable() {
        assertThrows(NullPointerException.class, () -> Failable.run(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testCallWithCallable(String input) throws Exception {
        Callable<String> callable = () -> input.toUpperCase();
        assertEquals(callable.call(), Failable.call(callable));
    }

    @Test
    public void testCallWithNullCallable() {
        assertThrows(NullPointerException.class, () -> Failable.call(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testGetWithSupplier(String input) {
        Supplier<String> supplier = () -> input.toUpperCase();
        assertEquals(supplier.get(), Failable.get(supplier));
    }

    @Test
    public void testGetWithNullSupplier() {
        assertThrows(NullPointerException.class, () -> Failable.get(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testTestWithPredicate(String input) {
        Predicate<String> predicate = s -> s.length() > 0;
        assertTrue(Failable.test(predicate, input));
    }

    @Test
    public void testTestWithNullPredicate() {
        assertThrows(NullPointerException.class, () -> Failable.test(null, "test"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    public void testApplyAsDoubleWithFunction(String input) {
        Function<String, Double> function = s -> s.length() * 1.0;
        assertEquals(function.apply(input), Failable.applyAsDouble(function::apply, input));
    }

    @Test
    public void testApplyAsDoubleWithNullFunction() {
        assertThrows(NullPointerException.class, () -> Failable.applyAsDouble(null, 1.0, 2.0));
    }
}
