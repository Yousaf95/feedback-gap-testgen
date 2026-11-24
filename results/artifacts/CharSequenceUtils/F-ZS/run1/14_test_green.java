package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.lang3.StringUtils.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CharSequenceUtils_GenTest {

    
    
    @Test
    public void testSubSequence_withNull() {
        assertNull(CharSequenceUtils.subSequence(null, 0));
    }

    
    @Test
    public void testToCharArray_withNull() {
        char[] result = CharSequenceUtils.toCharArray(null);
        assertArrayEquals(ArrayUtils.EMPTY_CHAR_ARRAY, result);
    }
}
