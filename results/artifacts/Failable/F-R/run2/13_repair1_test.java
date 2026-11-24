package org.apache.commons.lang3.function;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Failable_GenTest {

    @Test
    public void testAccept_ThrowsException() {
        BiConsumer<String, String> throwingConsumer = (s1, s2) -> { throw new IOException("test"); };
        assertThrows(RuntimeException.class, () -> Failable.accept(throwingConsumer, "a", "b"));
    }

    @Test
    public void testAccept_NoException() {
        BiConsumer<String, String> nonThrowingConsumer = (s1, s2) -> { /* do nothing */ };
        Failable.accept(nonThrowingConsumer, "a", "b");
    }

    @Test
    public void testApply_ThrowsException() {
        Function<String, String> throwingFunction = s -> { throw new IOException("test"); };
        assertThrows(RuntimeException.class, () -> Failable.apply(throwingFunction, "a"));
    }

    @Test
    public void testApply_NoException() {
        Function<String, String> nonThrowingFunction = s -> s.toUpperCase();
        assertEquals("A", Failable.apply(nonThrowingFunction, "a"));
    }

    @Test
    public void testCall_ThrowsException() {
        Callable<String> throwingCallable = () -> { throw new IOException("test"); };
        assertThrows(RuntimeException.class, () -> Failable.call(throwingCallable));
    }

    @Test
    public void testCall_NoException() {
        Callable<String> nonThrowingCallable = () -> "a";
        assertEquals("a", Failable.call(nonThrowingCallable));
    }

    @Test
    public void testGet_ThrowsException() {
        Supplier<String> throwingSupplier = () -> { throw new IOException("test"); };
        assertThrows(RuntimeException.class, () -> Failable.get(throwingSupplier));
    }

    @Test
    public void testGet_NoException() {
        Supplier<String> nonThrowingSupplier = () -> "a";
        assertEquals("a", Failable.get(nonThrowingSupplier));
    }
}
