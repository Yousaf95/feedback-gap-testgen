package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashCodeBuilder_GenTest {

    
    
    
    
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
    public void testAppendDouble() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(3.14);
        long bits = Double.doubleToLongBits(3.14);
        assertEquals(17 * 37 + (int) (bits ^ bits >> 32), builder.toHashCode());
    }

    
    @Test
    public void testAppendFloat() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(1.5f);
        int bits = Float.floatToIntBits(1.5f);
        assertEquals(17 * 37 + bits, builder.toHashCode());
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
        builder.append(123456789L);
        assertEquals(17 * 37 + (int) (123456789L ^ 123456789L >> 32), builder.toHashCode());
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
        builder.append((short) 5);
        assertEquals(17 * 37 + 5, builder.toHashCode());
    }

    
    @Test
    public void testAppendSuper() {
        class SuperClass {
            @Override
            public int hashCode() {
                return 42;
            }
        }
        class SubClass extends SuperClass {
            @Override
            public int hashCode() {
                HashCodeBuilder builder = new HashCodeBuilder();
                builder.appendSuper(super.hashCode());
                return builder.toHashCode();
            }
        }
        assertEquals(17 * 37 + 42, new SubClass().hashCode());
    }

    @Test
    public void testReflectionHashCodeNullObject() {
        assertThrows(NullPointerException.class, () -> HashCodeBuilder.reflectionHashCode(null));
    }

    @Test
    public void testReflectionHashCodeEmptyObject() {
        class EmptyClass {}
        assertEquals(17, HashCodeBuilder.reflectionHashCode(new EmptyClass()));
    }

    
    
    @Test
    public void testReflectionHashCodeExcludeFields() {
        class ExcludeFields {
            int field1 = 42;
            String field2 = "test";
        }
        assertEquals(17 * 37 + 42, HashCodeBuilder.reflectionHashCode(new ExcludeFields(), "field2"));
    }

    @Test
    public void testReflectionHashCodeExcludeNullField() {
        class ExcludeNullField {
            int field1 = 42;
            String field2 = null;
        }
        assertEquals(17 * 37 + 42, HashCodeBuilder.reflectionHashCode(new ExcludeNullField(), "field2"));
    }

    
    
    @Test
        public void testAppendEmptyArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(new Object[0]);
            assertEquals(17, builder.toHashCode());
        }

    
    
    
    
    
    
    
    }
