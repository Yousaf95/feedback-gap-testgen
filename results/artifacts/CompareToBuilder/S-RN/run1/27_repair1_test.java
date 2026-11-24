package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;

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

    
    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendNullObject() {
            CompareToBuilder builder = new CompareToBuilder();
            int result = builder.append((String) null, (String) null).toComparison();
            assertEquals(0, result);
        }

    @Test
        public void testAppendDifferentNullObjects() {
            CompareToBuilder builder = new CompareToBuilder();
            int result = builder.append((String) null, "test").toComparison();
            assertEquals(-1, result);
        }

    @Test
        public void testAppendSameObject() {
            String str = "test";
            CompareToBuilder builder = new CompareToBuilder();
            int result = builder.append(str, str).toComparison();
            assertEquals(0, result);
        }

    
    @Test
        public void testAppendEmptyString() {
            CompareToBuilder builder = new CompareToBuilder();
            int result = builder.append("", "").toComparison();
            assertEquals(0, result);
        }

    @Test
        public void testAppendNonEmptyStrings() {
            CompareToBuilder builder = new CompareToBuilder();
            int result = builder.append("abc", "def").toComparison();
            assertTrue(result < 0);
        }

    @Test
        public void testAppendEqualArrays() {
            Integer[] arr1 = {1, 2, 3};
            Integer[] arr2 = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            int result = builder.append(arr1, arr2).toComparison();
            assertEquals(0, result);
        }

    @Test
        public void testAppendDifferentArrays() {
            Integer[] arr1 = {1, 2, 3};
            Integer[] arr2 = {4, 5, 6};
            CompareToBuilder builder = new CompareToBuilder();
            int result = builder.append(arr1, arr2).toComparison();
            assertTrue(result < 0);
        }

    @Test
        public void testAppendNullArray() {
            Integer[] arr = null;
            CompareToBuilder builder = new CompareToBuilder();
            int result = builder.append(arr, arr).toComparison();
            assertEquals(0, result);
        }

    @Test
        public void testAppendDifferentLengthArrays() {
            Integer[] arr1 = {1, 2};
            Integer[] arr2 = {1, 2, 3};
            CompareToBuilder builder = new CompareToBuilder();
            int result = builder.append(arr1, arr2).toComparison();
            assertTrue(result < 0);
        }

    @Test
        public void testAppendDifferentNullArray() {
            Integer[] arr = null;
            CompareToBuilder builder = new CompareToBuilder();
            int result = builder.append(arr, "test").toComparison();
            assertEquals(-1, result);
        }

    // ---- added by coverage-guided refinement ----
    @Test
        public void testAppendCustomComparator() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(1, 2, Comparator.reverseOrder());
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendNullWithComparator() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(null, "test", Comparator.naturalOrder());
            assertEquals(-1, builder.toComparison());
        }

    @Test
        public void testAppendNonComparableObjects() {
            CompareToBuilder builder = new CompareToBuilder();
            assertThrows(ClassCastException.class, () -> builder.append(new Object(), new Object()));
        }

    @Test
        public void testAppendCustomComparatorWithNull() {
            CompareToBuilder builder = new CompareToBuilder();
            builder.append("test", null, Comparator.nullsFirst(Comparator.naturalOrder()));
            assertEquals(-1, builder.toComparison());
        }
}
