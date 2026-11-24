package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class CompareToBuilder_GenTest {

    @Test
    public void testReflectionCompare_nullObjects() {
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(null, new Object()));
        assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(new Object(), null));
    }

    @Test
    public void testReflectionCompare_sameObject() {
        assertEquals(0, CompareToBuilder.reflectionCompare(new Object(), new Object()));
    }

    @Test
    public void testReflectionCompare_differentObjects() {
        class TestClass {
            int value = 1;
        }
        TestClass lhs = new TestClass();
        TestClass rhs = new TestClass();
        rhs.value = 2;
        assertEquals(-1, CompareToBuilder.reflectionCompare(lhs, rhs));
    }

    
    
    
    @Test
    public void testAppend_byte() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append((byte) 2, (byte) 1);
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppend_char() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append('b', 'a');
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppend_double() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(2.0, 1.0);
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppend_float() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(2.0f, 1.0f);
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppend_int() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(2, 1);
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppend_long() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append(2L, 1L);
        assertEquals(1, builder.toComparison());
    }

    @Test
    public void testAppend_short() {
        CompareToBuilder builder = new CompareToBuilder();
        builder.append((short) 2, (short) 1);
        assertEquals(1, builder.toComparison());
    }

    
    
    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testReflectionCompare_nullObjects_2() {
            assertEquals(0, CompareToBuilder.reflectionCompare(null, null));
        }

    @Test
        public void testReflectionCompare_sameObject_2() {
            Object obj = new Object();
            assertEquals(0, CompareToBuilder.reflectionCompare(obj, obj));
        }

    
    @Test
        public void testAppend_byte_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 1, (byte) 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_char_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('a', 'b');
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_double_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1.0, 2.0);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_float_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1.0f, 2.0f);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_int_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_long_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1L, 2L);
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_short_2() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((short) 1, (short) 2);
            assertEquals(-1, builder.toComparison());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testReflectionCompare_nullObjects_3() {
            assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(null, new Object()));
            assertThrows(NullPointerException.class, () -> CompareToBuilder.reflectionCompare(new Object(), null));
        }

    @Test
        public void testReflectionCompare_sameObject_3() {
            assertEquals(0, CompareToBuilder.reflectionCompare(new Object(), new Object()));
        }

    @Test
        public void testReflectionCompare_differentObjects_2() {
            class TestClass {
                private int value = 42;
            }
            TestClass lhs = new TestClass();
            TestClass rhs = new TestClass();
            rhs.value = 100;
            assertEquals(-1, CompareToBuilder.reflectionCompare(lhs, rhs));
        }

    @Test
        public void testAppend_byte_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((byte) 5, (byte) 3);
            assertEquals(2, builder.toComparison());
        }

    @Test
        public void testAppend_char_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append('a', 'b');
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppend_double_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(3.14, 2.71);
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppend_float_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append((float) 3.14f, (float) 2.71f);
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppend_int_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(42, 24);
            assertEquals(1, builder.toComparison());
        }

    @Test
        public void testAppend_long_3() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(123456789L, 987654321L);
            assertEquals(-1, builder.toComparison());
        }

    }
