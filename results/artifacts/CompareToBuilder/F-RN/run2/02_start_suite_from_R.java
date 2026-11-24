package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

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
        assertThrows(ClassCastException.class, () -> CompareToBuilder.reflectionCompare(new String("a"), 1));
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testReflectionCompare_SuperClass() {
        class SuperClass implements Comparable<SuperClass> {
            private int value;

            public SuperClass(int value) {
                this.value = value;
            }

            @Override
            public int compareTo(SuperClass other) {
                return Integer.compare(this.value, other.value);
            }
        }

        class SubClass extends SuperClass {
            public SubClass(int value) {
                super(value);
            }
        }

        assertEquals(0, CompareToBuilder.reflectionCompare(new SubClass(1), new SubClass(1)));
        assertEquals(-1, CompareToBuilder.reflectionCompare(new SubClass(0), new SubClass(1)));
        assertEquals(1, CompareToBuilder.reflectionCompare(new SubClass(1), new SubClass(0)));
    }
}
