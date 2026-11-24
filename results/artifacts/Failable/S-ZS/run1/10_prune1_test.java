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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Failable_GenTest {

    
    @Test
    public void testAcceptWithNullConsumer() {
        assertThrows(NullPointerException.class, () -> Failable.accept(null, "test"));
    }

    
    @Test
    public void testApplyWithNullFunction() {
        assertThrows(NullPointerException.class, () -> Failable.apply(null, "test"));
    }

    
    @Test
    public void testRunWithNullRunnable() {
        assertThrows(NullPointerException.class, () -> Failable.run(null));
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
    public void testTestWithNullPredicate() {
        assertThrows(NullPointerException.class, () -> Failable.test(null, "test"));
    }

    
    @Test
    public void testApplyAsDoubleWithNullFunction() {
        assertThrows(NullPointerException.class, () -> Failable.applyAsDouble(null, 1.0, 2.0));
    }
}
