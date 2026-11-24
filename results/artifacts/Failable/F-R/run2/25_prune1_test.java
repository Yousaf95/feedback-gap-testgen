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
        assertThrows(RuntimeException.class, () -> Failable.accept((FailableBiConsumer<String, String, IOException>) throwingConsumer, "a", "b"));
    }

    @Test
    public void testAccept_NoException() {
        BiConsumer<String, String> nonThrowingConsumer = (s1, s2) -> { /* do nothing */ };
        Failable.accept((FailableBiConsumer<String, String, IOException>) nonThrowingConsumer, "a", "b");
    }

    
    
    @Test
    public void testGet_ThrowsException() {
        Supplier<String> throwingSupplier = () -> { throw new IOException("test"); };
        assertThrows(RuntimeException.class, () -> Failable.get((FailableSupplier<String, IOException>) throwingSupplier));
    }

    @Test
    public void testGet_NoException() {
        Supplier<String> nonThrowingSupplier = () -> "a";
        assertEquals("a", Failable.get((FailableSupplier<String, IOException>) nonThrowingSupplier));
    }
}
