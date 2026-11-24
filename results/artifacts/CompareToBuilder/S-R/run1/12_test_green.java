package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompareToBuilder_GenTest {

    @Test
    public void testReflectionCompare_NullObjects() {
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(null, new Object()));
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(new Object(), null));
    }

    @Test
    public void testReflectionCompare_SameObject() {
        assertEquals(0, CompareToBuilder.reflectionCompare(new Object(), new Object()));
    }

    @Test
    public void testReflectionCompare_DifferentClasses() {
        assertThrows(ClassCastException.class, () -> CompareToBuilder.reflectionCompare("string", 123));
    }

    
    
    
    
    
    
    
    
    
    @Test
    public void testAppendObjectArray() {
        CompareToBuilder builder = new CompareToBuilder();
        assertEquals(0, builder.append(new Integer[]{1, 2}, new Integer[]{1, 2}).toComparison());
        assertNotEquals(0, builder.append(new Integer[]{1, 2}, new Integer[]{1, 3}).toComparison());
        assertNotEquals(0, builder.append(new Integer[]{1, 2}, new Integer[]{1}).toComparison());
    }

    }
