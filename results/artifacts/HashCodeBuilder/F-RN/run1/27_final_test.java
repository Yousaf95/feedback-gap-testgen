package org.apache.commons.lang3.builder;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashCodeBuilder_GenTest {

    @Test
    public void testHashCodeBuilderDefaultConstructor() {
        HashCodeBuilder builder = new HashCodeBuilder();
        assertEquals(17, builder.toHashCode());
    }

    
    
    @Test
    public void testAppendByte() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((byte) 5);
        assertEquals(17 * 37 + 5, builder.toHashCode());
    }

    
    @Test
    public void testAppendChar() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append('a');
        assertEquals(17 * 37 + 'a', builder.toHashCode());
    }

    
    
    
    @Test
    public void testAppendFloat() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(1.41f);
        assertEquals(17 * 37 + Float.floatToIntBits(1.41f), builder.toHashCode());
    }

    
    @Test
    public void testAppendInt() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(42);
        assertEquals(17 * 37 + 42, builder.toHashCode());
    }

    
    @Test
    public void testAppendLong() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(987654321L);
        assertEquals(17 * 37 + (int) (987654321L ^ 987654321L >> 32), builder.toHashCode());
    }

    
    @Test
    public void testAppendObject() {
        HashCodeBuilder builder = new HashCodeBuilder();
        Object obj = new Object();
        builder.append(obj);
        assertEquals(17 * 37 + obj.hashCode(), builder.toHashCode());
    }

    
    @Test
    public void testAppendShort() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((short) 25);
        assertEquals(17 * 37 + 25, builder.toHashCode());
    }

    
    @Test
    public void testAppendSuper() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.appendSuper(42);
        assertEquals(17 * 37 + 42, builder.toHashCode());
    }

    
    
    
    
    
    
    
    }
