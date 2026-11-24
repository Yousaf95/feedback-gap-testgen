package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.StringUtils.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import java.util.List;

public class CharSequenceUtils_GenTest {

    
    
    @Test
    public void testSubSequence_withNull() {
        assertNull(CharSequenceUtils.subSequence(null, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "abc"})
    public void testToCharArray_withValidString(CharSequence cs) {
        char[] result = CharSequenceUtils.toCharArray(cs);
        assertArrayEquals(cs.toString().toCharArray(), result);
    }

    @Test
    public void testToCharArray_withNull() {
        char[] result = CharSequenceUtils.toCharArray(null);
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, result);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    }
