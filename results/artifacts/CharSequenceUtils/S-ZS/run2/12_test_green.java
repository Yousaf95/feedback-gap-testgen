package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CharSequenceUtils_GenTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "abc"})
    public void testSubSequence(String cs) {
        assertEquals(cs, CharSequenceUtils.subSequence(cs, 0));
    }

    @Test
    public void testSubSequenceNull() {
        assertNull(CharSequenceUtils.subSequence(null, 0));
    }

    
    @Test
    public void testToCharArrayEmpty() {
        assertArrayEquals(new char[0], CharSequenceUtils.toCharArray(""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abc"})
    public void testToCharArray(String cs) {
        assertArrayEquals(cs.toCharArray(), CharSequenceUtils.toCharArray(cs));
    }

    }
