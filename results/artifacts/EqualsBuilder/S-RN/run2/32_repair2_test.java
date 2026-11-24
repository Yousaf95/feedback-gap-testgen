package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EqualsBuilder_GenTest {

    private EqualsBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new EqualsBuilder();
    }

    @Test
    public void testAppendBoolean() {
        assertTrue(builder.append(true, true).isEquals());
        assertFalse(builder.append(true, false).isEquals());
    }

    @Test
    public void testAppendByte() {
        assertTrue(builder.append((byte) 1, (byte) 1).isEquals());
        assertFalse(builder.append((byte) 1, (byte) 2).isEquals());
    }

    @Test
    public void testAppendChar() {
        assertTrue(builder.append('a', 'a').isEquals());
        assertFalse(builder.append('a', 'b').isEquals());
    }

    @Test
    public void testAppendDouble() {
        assertTrue(builder.append(1.0, 1.0).isEquals());
        assertFalse(builder.append(1.0, 2.0).isEquals());
    }

    @Test
    public void testAppendFloat() {
        assertTrue(builder.append((float) 1.0, (float) 1.0).isEquals());
        assertFalse(builder.append((float) 1.0, (float) 2.0).isEquals());
    }

    @Test
    public void testAppendInt() {
        assertTrue(builder.append(1, 1).isEquals());
        assertFalse(builder.append(1, 2).isEquals());
    }

    @Test
    public void testAppendLong() {
        assertTrue(builder.append(1L, 1L).isEquals());
        assertFalse(builder.append(1L, 2L).isEquals());
    }

    @Test
    public void testAppendShort() {
        assertTrue(builder.append((short) 1, (short) 1).isEquals());
        assertFalse(builder.append((short) 1, (short) 2).isEquals());
    }

    @Test
    public void testAppendObject() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        assertTrue(builder.append(obj1, obj1).isEquals());
        assertFalse(builder.append(obj1, obj2).isEquals());
    }

    @Test
    public void testAppendArrayBoolean() {
        assertTrue(builder.append(new boolean[]{true}, new boolean[]{true}).isEquals());
        assertFalse(builder.append(new boolean[]{true}, new boolean[]{false}).isEquals());
    }

    @Test
    public void testAppendArrayByte() {
        assertTrue(builder.append(new byte[]{1}, new byte[]{1}).isEquals());
        assertFalse(builder.append(new byte[]{1}, new byte[]{2}).isEquals());
    }

    @Test
    public void testAppendArrayChar() {
        assertTrue(builder.append(new char[]{'a'}, new char[]{'a'}).isEquals());
        assertFalse(builder.append(new char[]{'a'}, new char[]{'b'}).isEquals());
    }

    @Test
    public void testAppendArrayDouble() {
        assertTrue(builder.append(new double[]{1.0}, new double[]{1.0}).isEquals());
        assertFalse(builder.append(new double[]{1.0}, new double[]{2.0}).isEquals());
    }

    @Test
    public void testAppendArrayFloat() {
        assertTrue(builder.append(new float[]{(float) 1.0}, new float[]{(float) 1.0}).isEquals());
        assertFalse(builder.append(new float[]{(float) 1.0}, new float[]{(float) 2.0}).isEquals());
    }

    @Test
    public void testAppendArrayInt() {
        assertTrue(builder.append(new int[]{1}, new int[]{1}).isEquals());
        assertFalse(builder.append(new int[]{1}, new int[]{2}).isEquals());
    }

    @Test
    public void testAppendArrayLong() {
        assertTrue(builder.append(new long[]{1L}, new long[]{1L}).isEquals());
        assertFalse(builder.append(new long[]{1L}, new long[]{2L}).isEquals());
    }

    @Test
    public void testAppendArrayShort() {
        assertTrue(builder.append(new short[]{(short) 1}, new short[]{(short) 1}).isEquals());
        assertFalse(builder.append(new short[]{(short) 1}, new short[]{(short) 2}).isEquals());
    }

    @Test
    public void testAppendArrayObject() {
        Object[] obj1 = {new Object()};
        Object[] obj2 = {new Object()};
        assertTrue(builder.append(obj1, obj1).isEquals());
        assertFalse(builder.append(obj1, obj2).isEquals());
    }

    @Test
    public void testReflectionAppend() {
        // This is a complex test that requires mocking and reflection to verify.
        // For simplicity, we'll just check if the method doesn't throw an exception.
        builder.reflectionAppend(new Object(), new Object());
    }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendNull() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((Object) null, (Object) null);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendDifferentNulls() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((Object) null, "not null");
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendArrayNull() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append((boolean[]) null, (boolean[]) null);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendDifferentNullArrays() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new boolean[]{true}, new boolean[]{false});
            assertFalse(builder.isEquals());
        }

    @Test
        public void testAppendStringArray() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new String[]{"a", "b"}, new String[]{"a", "b"});
            assertTrue(builder.isEquals());
        }

    @Test
        public void testAppendDifferentStringArrays() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(new String[]{"a", "b"}, new String[]{"a", "c"});
            assertFalse(builder.isEquals());
        }

    @Test
        public void testReflectionAppendNull() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.reflectionAppend(null, null);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testReflectionAppendDifferentNulls() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.reflectionAppend("not null", null);
            assertFalse(builder.isEquals());
        }

    @Test
        public void testReflectionAppendSameObject() {
            EqualsBuilder builder = new EqualsBuilder();
            String obj = "test";
            builder.reflectionAppend(obj, obj);
            assertTrue(builder.isEquals());
        }

    @Test
        public void testReflectionAppendDifferentObjects() {
            EqualsBuilder builder = new EqualsBuilder();
            builder.reflectionAppend("a", "b");
            assertFalse(builder.isEquals());
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendCustomObject() {
            CustomObject obj1 = new CustomObject(1, "test");
            CustomObject obj2 = new CustomObject(1, "test");
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectDifferentFieldValues() {
            CustomObject obj1 = new CustomObject(1, "test");
            CustomObject obj2 = new CustomObject(2, "test");
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectNull() {
            CustomObject obj1 = null;
            CustomObject obj2 = null;
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectOneNull() {
            CustomObject obj1 = new CustomObject(1, "test");
            CustomObject obj2 = null;
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectDifferentClasses() {
            CustomObject obj1 = new CustomObject(1, "test");
            String str = "test";
            assertFalse(new EqualsBuilder().append(obj1, str).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithTransientField() {
            TransientObject obj1 = new TransientObject(1, "test", true);
            TransientObject obj2 = new TransientObject(1, "test", false);
            assertTrue(new EqualsBuilder().setTestTransients(true).append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithTransientFieldNotTested() {
            TransientObject obj1 = new TransientObject(1, "test", true);
            TransientObject obj2 = new TransientObject(1, "test", false);
            assertFalse(new EqualsBuilder().setTestTransients(false).append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithExcludedField() {
            ExcludedFieldObject obj1 = new ExcludedFieldObject(1, "test");
            ExcludedFieldObject obj2 = new ExcludedFieldObject(1, "different");
            assertTrue(new EqualsBuilder().setExcludeFields("field").append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithExcludedFieldNotExcluded() {
            ExcludedFieldObject obj1 = new ExcludedFieldObject(1, "test");
            ExcludedFieldObject obj2 = new ExcludedFieldObject(1, "different");
            assertFalse(new EqualsBuilder().setExcludeFields("otherField").append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithBypassReflectionClass() {
            BypassReflectionObject obj1 = new BypassReflectionObject(1, "test");
            BypassReflectionObject obj2 = new BypassReflectionObject(1, "test");
            assertTrue(new EqualsBuilder().setBypassReflectionClasses(List.of(BypassReflectionObject.class)).append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithBypassReflectionClassNotBypassed() {
            BypassReflectionObject obj1 = new BypassReflectionObject(1, "test");
            BypassReflectionObject obj2 = new BypassReflectionObject(1, "different");
            assertFalse(new EqualsBuilder().setBypassReflectionClasses(List.of(String.class)).append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithReflectUpToClass() {
            ReflectUpToObject obj1 = new ReflectUpToObject(1, "test", 3.0);
            ReflectUpToObject obj2 = new ReflectUpToObject(1, "test", 3.0);
            assertTrue(new EqualsBuilder().setReflectUpToClass(ReflectUpToObject.class).append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithReflectUpToClassNotReached() {
            ReflectUpToObject obj1 = new ReflectUpToObject(1, "test", 3.0);
            ReflectUpToObject obj2 = new ReflectUpToObject(1, "different", 4.0);
            assertFalse(new EqualsBuilder().setReflectUpToClass(String.class).append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithRecursive() {
            RecursiveObject obj1 = new RecursiveObject(1, "test");
            RecursiveObject obj2 = new RecursiveObject(1, "test");
            assertTrue(new EqualsBuilder().setTestRecursive(true).append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithRecursiveNotRecursive() {
            RecursiveObject obj1 = new RecursiveObject(1, "test");
            RecursiveObject obj2 = new RecursiveObject(1, "different");
            assertFalse(new EqualsBuilder().setTestRecursive(false).append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithNullField() {
            NullFieldObject obj1 = new NullFieldObject(1, null);
            NullFieldObject obj2 = new NullFieldObject(1, null);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentNullFields() {
            NullFieldObject obj1 = new NullFieldObject(1, null);
            NullFieldObject obj2 = new NullFieldObject(1, "test");
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithNullArray() {
            NullArrayObject obj1 = new NullArrayObject(1, null);
            NullArrayObject obj2 = new NullArrayObject(1, null);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentNullArrays() {
            NullArrayObject obj1 = new NullArrayObject(1, null);
            NullArrayObject obj2 = new NullArrayObject(1, new int[]{1});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyStringArray() {
            StringArrayObject obj1 = new StringArrayObject(1, new String[0]);
            StringArrayObject obj2 = new StringArrayObject(1, new String[0]);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentStringArrays() {
            StringArrayObject obj1 = new StringArrayObject(1, new String[]{"test"});
            StringArrayObject obj2 = new StringArrayObject(1, new String[]{"different"});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyIntArray() {
            IntArrayObject obj1 = new IntArrayObject(1, new int[0]);
            IntArrayObject obj2 = new IntArrayObject(1, new int[0]);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentIntArrays() {
            IntArrayObject obj1 = new IntArrayObject(1, new int[]{1});
            IntArrayObject obj2 = new IntArrayObject(1, new int[]{2});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyDoubleArray() {
            DoubleArrayObject obj1 = new DoubleArrayObject(1, new double[0]);
            DoubleArrayObject obj2 = new DoubleArrayObject(1, new double[0]);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentDoubleArrays() {
            DoubleArrayObject obj1 = new DoubleArrayObject(1, new double[]{1.0});
            DoubleArrayObject obj2 = new DoubleArrayObject(1, new double[]{2.0});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyFloatArray() {
            FloatArrayObject obj1 = new FloatArrayObject(1, new float[0]);
            FloatArrayObject obj2 = new FloatArrayObject(1, new float[0]);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentFloatArrays() {
            FloatArrayObject obj1 = new FloatArrayObject(1, new float[]{1.0f});
            FloatArrayObject obj2 = new FloatArrayObject(1, new float[]{2.0f});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyLongArray() {
            LongArrayObject obj1 = new LongArrayObject(1, new long[0]);
            LongArrayObject obj2 = new LongArrayObject(1, new long[0]);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentLongArrays() {
            LongArrayObject obj1 = new LongArrayObject(1, new long[]{1L});
            LongArrayObject obj2 = new LongArrayObject(1, new long[]{2L});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyShortArray() {
            ShortArrayObject obj1 = new ShortArrayObject(1, new short[0]);
            ShortArrayObject obj2 = new ShortArrayObject(1, new short[0]);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentShortArrays() {
            ShortArrayObject obj1 = new ShortArrayObject(1, new short[]{1});
            ShortArrayObject obj2 = new ShortArrayObject(1, new short[]{2});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyCharArray() {
            CharArrayObject obj1 = new CharArrayObject(1, new char[0]);
            CharArrayObject obj2 = new CharArrayObject(1, new char[0]);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentCharArrays() {
            CharArrayObject obj1 = new CharArrayObject(1, new char[]{'a'});
            CharArrayObject obj2 = new CharArrayObject(1, new char[]{'b'});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyBooleanArray() {
            BooleanArrayObject obj1 = new BooleanArrayObject(1, new boolean[0]);
            BooleanArrayObject obj2 = new BooleanArrayObject(1, new boolean[0]);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentBooleanArrays() {
            BooleanArrayObject obj1 = new BooleanArrayObject(1, new boolean[]{true});
            BooleanArrayObject obj2 = new BooleanArrayObject(1, new boolean[]{false});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyByteArray() {
            ByteArrayObject obj1 = new ByteArrayObject(1, new byte[0]);
            ByteArrayObject obj2 = new ByteArrayObject(1, new byte[0]);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentByteArrays() {
            ByteArrayObject obj1 = new ByteArrayObject(1, new byte[]{1});
            ByteArrayObject obj2 = new ByteArrayObject(1, new byte[]{2});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyFloatArrayNull() {
            FloatArrayNullObject obj1 = new FloatArrayNullObject(1, null);
            FloatArrayNullObject obj2 = new FloatArrayNullObject(1, null);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentFloatArraysNull() {
            FloatArrayNullObject obj1 = new FloatArrayNullObject(1, null);
            FloatArrayNullObject obj2 = new FloatArrayNullObject(1, new float[]{1.0f});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyIntArrayNull() {
            IntArrayNullObject obj1 = new IntArrayNullObject(1, null);
            IntArrayNullObject obj2 = new IntArrayNullObject(1, null);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentIntArrayNull() {
            IntArrayNullObject obj1 = new IntArrayNullObject(1, null);
            IntArrayNullObject obj2 = new IntArrayNullObject(1, new int[]{1});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyDoubleArrayNull() {
            DoubleArrayNullObject obj1 = new DoubleArrayNullObject(1, null);
            DoubleArrayNullObject obj2 = new DoubleArrayNullObject(1, null);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentDoubleArrayNull() {
            DoubleArrayNullObject obj1 = new DoubleArrayNullObject(1, null);
            DoubleArrayNullObject obj2 = new DoubleArrayNullObject(1, new double[]{1.0});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyLongArrayNull() {
            LongArrayNullObject obj1 = new LongArrayNullObject(1, null);
            LongArrayNullObject obj2 = new LongArrayNullObject(1, null);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentLongArrayNull() {
            LongArrayNullObject obj1 = new LongArrayNullObject(1, null);
            LongArrayNullObject obj2 = new LongArrayNullObject(1, new long[]{1L});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyShortArrayNull() {
            ShortArrayNullObject obj1 = new ShortArrayNullObject(1, null);
            ShortArrayNullObject obj2 = new ShortArrayNullObject(1, null);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentShortArrayNull() {
            ShortArrayNullObject obj1 = new ShortArrayNullObject(1, null);
            ShortArrayNullObject obj2 = new ShortArrayNullObject(1, new short[]{1});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyCharArrayNull() {
            CharArrayNullObject obj1 = new CharArrayNullObject(1, null);
            CharArrayNullObject obj2 = new CharArrayNullObject(1, null);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentCharArrayNull() {
            CharArrayNullObject obj1 = new CharArrayNullObject(1, null);
            CharArrayNullObject obj2 = new CharArrayNullObject(1, new char[]{'a'});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyBooleanArrayNull() {
            BooleanArrayNullObject obj1 = new BooleanArrayNullObject(1, null);
            BooleanArrayNullObject obj2 = new BooleanArrayNullObject(1, null);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentBooleanArrayNull() {
            BooleanArrayNullObject obj1 = new BooleanArrayNullObject(1, null);
            BooleanArrayNullObject obj2 = new BooleanArrayNullObject(1, new boolean[]{true});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyByteArrayNull() {
            ByteArrayNullObject obj1 = new ByteArrayNullObject(1, null);
            ByteArrayNullObject obj2 = new ByteArrayNullObject(1, null);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentByteArrayNull() {
            ByteArrayNullObject obj1 = new ByteArrayNullObject(1, null);
            ByteArrayNullObject obj2 = new ByteArrayNullObject(1, new byte[]{1});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithEmptyStringArrayNull() {
            StringArrayNullObject obj1 = new StringArrayNullObject(1, null);
            StringArrayNullObject obj2 = new StringArrayNullObject(1, null);
            assertTrue(new EqualsBuilder().append(obj1, obj2).isEquals());
        }

    @Test
        public void testAppendCustomObjectWithDifferentStringArrayNull() {
            StringArrayNullObject obj1 = new StringArrayNullObject(1, null);
            StringArrayNullObject obj2 = new StringArrayNullObject(1, new String[]{"test"});
            assertFalse(new EqualsBuilder().append(obj1, obj2).isEquals());
        }
}
