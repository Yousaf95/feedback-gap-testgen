package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EqualsBuilder_GenTest {

    private EqualsBuilder equalsBuilder;

    @BeforeEach
    void setUp() {
        equalsBuilder = new EqualsBuilder();
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    void testAppendArrayBoolean() {
        boolean[] lhs = {true, false};
        boolean[] rhs = {true, false};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayByte() {
        byte[] lhs = {(byte) 0, (byte) 1};
        byte[] rhs = {(byte) 0, (byte) 1};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayChar() {
        char[] lhs = {'a', 'b'};
        char[] rhs = {'a', 'b'};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayDouble() {
        double[] lhs = {0.0, 1.0};
        double[] rhs = {0.0, 1.0};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayFloat() {
        float[] lhs = {0.0f, 1.0f};
        float[] rhs = {0.0f, 1.0f};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayInt() {
        int[] lhs = {0, 1};
        int[] rhs = {0, 1};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayLong() {
        long[] lhs = {0L, 1L};
        long[] rhs = {0L, 1L};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendArrayShort() {
        short[] lhs = {(short) 0, (short) 1};
        short[] rhs = {(short) 0, (short) 1};
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendObject() {
        Object lhs = new Object();
        Object rhs = new Object();
        assertFalse(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendObjectNull() {
        Object lhs = null;
        Object rhs = null;
        assertTrue(equalsBuilder.append(lhs, rhs).isEquals());
    }

    @Test
    void testAppendObjectSame() {
        Object obj = new Object();
        assertTrue(equalsBuilder.append(obj, obj).isEquals());
    }

    
    
    @Test
    void testReflectionEqualsSameObject() {
        Object obj = new Object();
        assertTrue(EqualsBuilder.reflectionEquals(obj, obj));
    }

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendArrayBooleanDifferentLengths() {
            boolean[] lhs = {true, false};
            boolean[] rhs = {true};
            assertFalse(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArrayByteDifferentLengths() {
            byte[] lhs = {1, 2};
            byte[] rhs = {1};
            assertFalse(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArrayCharDifferentLengths() {
            char[] lhs = {'a', 'b'};
            char[] rhs = {'a'};
            assertFalse(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArrayDoubleDifferentLengths() {
            double[] lhs = {1.0, 2.0};
            double[] rhs = {1.0};
            assertFalse(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArrayFloatDifferentLengths() {
            float[] lhs = {1.0f, 2.0f};
            float[] rhs = {1.0f};
            assertFalse(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArrayIntDifferentLengths() {
            int[] lhs = {1, 2};
            int[] rhs = {1};
            assertFalse(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArrayLongDifferentLengths() {
            long[] lhs = {1L, 2L};
            long[] rhs = {1L};
            assertFalse(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testAppendArrayShortDifferentLengths() {
            short[] lhs = {1, 2};
            short[] rhs = {1};
            assertFalse(new EqualsBuilder().append(lhs, rhs).isEquals());
        }

    @Test
        public void testReflectionEqualsWithNullFields() {
            class TestClass {
                private String field1;
                private Integer field2;
    
                public TestClass(String field1, Integer field2) {
                    this.field1 = field1;
                    this.field2 = field2;
                }
            }
    
            TestClass obj1 = new TestClass("test", null);
            TestClass obj2 = new TestClass("test", null);
            assertTrue(EqualsBuilder.reflectionEquals(obj1, obj2));
        }

    @Test
        public void testReflectionEqualsWithDifferentNullFields() {
            class TestClass {
                private String field1;
                private Integer field2;
    
                public TestClass(String field1, Integer field2) {
                    this.field1 = field1;
                    this.field2 = field2;
                }
            }
    
            TestClass obj1 = new TestClass("test", null);
            TestClass obj2 = new TestClass(null, 123);
            assertFalse(EqualsBuilder.reflectionEquals(obj1, obj2));
        }

    @Test
        public void testReflectionEqualsWithDifferentValues() {
            class TestClass {
                private String field1;
                private Integer field2;
    
                public TestClass(String field1, Integer field2) {
                    this.field1 = field1;
                    this.field2 = field2;
                }
            }
    
            TestClass obj1 = new TestClass("test", 123);
            TestClass obj2 = new TestClass("other", 456);
            assertFalse(EqualsBuilder.reflectionEquals(obj1, obj2));
        }

    @Test
        public void testReflectionEqualsWithExcludedFields() {
            class TestClass {
                private String field1;
                private Integer field2;
    
                public TestClass(String field1, Integer field2) {
                    this.field1 = field1;
                    this.field2 = field2;
                }
            }
    
            TestClass obj1 = new TestClass("test", 123);
            TestClass obj2 = new TestClass("test", 456);
            assertTrue(EqualsBuilder.reflectionEquals(obj1, obj2, "field2"));
        }

    
    
    
    @Test
        public void testReflectionEqualsWithNonPrimitiveArrays() {
            class TestClass {
                private String[] field1;
                private Integer[] field2;
    
                public TestClass(String[] field1, Integer[] field2) {
                    this.field1 = field1;
                    this.field2 = field2;
                }
            }
    
            TestClass obj1 = new TestClass(new String[]{"a", "b"}, new Integer[]{1, 2});
            TestClass obj2 = new TestClass(new String[]{"a", "b"}, new Integer[]{1, 2});
            assertTrue(EqualsBuilder.reflectionEquals(obj1, obj2));
        }

    @Test
        public void testReflectionEqualsWithNonPrimitiveArraysDifferentLengths() {
            class TestClass {
                private String[] field1;
                private Integer[] field2;
    
                public TestClass(String[] field1, Integer[] field2) {
                    this.field1 = field1;
                    this.field2 = field2;
                }
            }
    
            TestClass obj1 = new TestClass(new String[]{"a", "b"}, new Integer[]{1, 2});
            TestClass obj2 = new TestClass(new String[]{"a", "b"}, new Integer[]{1});
            assertFalse(EqualsBuilder.reflectionEquals(obj1, obj2));
        }
}
