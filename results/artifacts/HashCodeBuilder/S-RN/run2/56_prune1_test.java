package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HashCodeBuilder_GenTest {

    
    
    @Test
    public void testAppendBoolean() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(true);
        assertEquals(17 * 37 + 0, builder.toHashCode());
        builder.append(false);
        assertEquals(17 * 37 * 37 + 1, builder.toHashCode());
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
        builder.append('A');
        assertEquals(17 * 37 + 'A', builder.toHashCode());
    }

    
    
    
    @Test
    public void testAppendFloat() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(2.718f);
        assertEquals(17 * 37 + Float.floatToIntBits(2.718f), builder.toHashCode());
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
    public void testAppendShort() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((short) 10);
        assertEquals(17 * 37 + 10, builder.toHashCode());
    }

    
    @Test
    public void testAppendSuper() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.appendSuper(42);
        assertEquals(17 * 37 + 42, builder.toHashCode());
    }

    @Test
    public void testBuild() {
        HashCodeBuilder builder = new HashCodeBuilder();
        Integer hashCode = builder.build();
        assertNotNull(hashCode);
        assertEquals(builder.toHashCode(), hashCode.intValue());
    }

    @Test
    public void testEquals() {
        HashCodeBuilder builder1 = new HashCodeBuilder(23, 47).append(1).append(2);
        HashCodeBuilder builder2 = new HashCodeBuilder(23, 47).append(1).append(2);
        assertTrue(builder1.equals(builder2));
    }

    @Test
    public void testHashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        assertEquals(builder.toHashCode(), builder.hashCode());
    }

    
    
    
    
    @Test
        public void testAppendNullObjectArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            Object[] array = null;
            builder.append(array);
            assertEquals(17 * 37, builder.toHashCode());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendDouble() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(3.14);
            assertEquals(26, builder.toHashCode());
        }

    @Test
        public void testAppendDoubleArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(new double[]{3.14, 2.71});
            assertEquals(-85, builder.toHashCode());
        }

    @Test
        public void testAppendFloatArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(new float[]{3.14f, 2.71f});
            assertEquals(69, builder.toHashCode());
        }

    @Test
        public void testAppendLongArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(new long[]{1L, 2L, 3L});
            assertEquals(-104, builder.toHashCode());
        }

    @Test
        public void testAppendShortArray() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(new short[]{1, 2, 3});
            assertEquals(87, builder.toHashCode());
        }

    @Test
        public void testAppendNullObjectArray_2() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append((Object[]) null);
            assertEquals(-50, builder.toHashCode());
        }

    @Test
        public void testAppendNullObject() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append((Object) null);
            assertEquals(17, builder.toHashCode());
        }

    @Test
        public void testReflectionHashCodeWithNull() {
            Object obj = new Object() {
                @Override
                public int hashCode() {
                    return 42;
                }
            };
            assertEquals(42, HashCodeBuilder.reflectionHashCode(obj));
        }

    @Test
        public void testReflectionHashCodeWithTransientField() {
            Object obj = new Object() {
                private transient int transientField = 10;
    
                @Override
                public int hashCode() {
                    return 42;
                }
            };
            assertEquals(42, HashCodeBuilder.reflectionHashCode(obj));
        }

    @Test
        public void testReflectionHashCodeWithExcludeFields() {
            Object obj = new Object() {
                private int field1 = 10;
                private int field2 = 20;
    
                @Override
                public int hashCode() {
                    return 42;
                }
            };
            assertEquals(42, HashCodeBuilder.reflectionHashCode(obj, "field1"));
        }

    @Test
        public void testReflectionHashCodeWithExcludeFieldsArray() {
            Object obj = new Object() {
                private int field1 = 10;
                private int field2 = 20;
    
                @Override
                public int hashCode() {
                    return 42;
                }
            };
            assertEquals(42, HashCodeBuilder.reflectionHashCode(obj, new String[]{"field1"}));
        }

    @Test
        public void testReflectionHashCodeWithExcludeFieldsCollection() {
            Object obj = new Object() {
                private int field1 = 10;
                private int field2 = 20;
    
                @Override
                public int hashCode() {
                    return 42;
                }
            };
            Set<String> excludeFields = new HashSet<>(Arrays.asList("field1"));
            assertEquals(42, HashCodeBuilder.reflectionHashCode(obj, excludeFields));
        }

    @Test
        public void testReflectionHashCodeWithSuperClass() {
            class Parent {
                private int parentField = 30;
            }
            class Child extends Parent {
                private int childField = 40;
    
                @Override
                public int hashCode() {
                    return 42;
                }
            }
            Child obj = new Child();
            assertEquals(17 * 37 + 42, HashCodeBuilder.reflectionHashCode(obj));
        }

    
    
    }
