package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class AnnotationUtils_GenTest {

    @Retention(RetentionPolicy.RUNTIME)
    private @interface TestAnnotation {
        String value();
        int number() default 42;
    }

    @Retention(RetentionPolicy.RUNTIME)
    private @interface AnotherTestAnnotation {
        String name();
    }

    @Test
    void testEquals_sameAnnotations() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        TestAnnotation annotation2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };

        // Act & Assert
        assertTrue(AnnotationUtils.equals(annotation1, annotation2));
    }

    @Test
    void testEquals_differentAnnotations() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        AnotherTestAnnotation annotation2 = new AnotherTestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return AnotherTestAnnotation.class;
            }
            @Override
            public String name() {
                return "test";
            }
        };

        // Act & Assert
        assertFalse(AnnotationUtils.equals(annotation1, annotation2));
    }

    @Test
    void testEquals_nullAnnotations() {
        // Arrange
        TestAnnotation annotation = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };

        // Act & Assert
        assertFalse(AnnotationUtils.equals(annotation, null));
        assertFalse(AnnotationUtils.equals(null, annotation));
    }

    @Test
    void testEquals_differentValues() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        TestAnnotation annotation2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "different";
            }
            @Override
            public int number() {
                return 42;
            }
        };

        // Act & Assert
        assertFalse(AnnotationUtils.equals(annotation1, annotation2));
    }

    @Test
    void testHashCode_sameAnnotations() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        TestAnnotation annotation2 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };

        // Act & Assert
        assertEquals(AnnotationUtils.hashCode(annotation1), AnnotationUtils.hashCode(annotation2));
    }

    @Test
    void testHashCode_differentAnnotations() {
        // Arrange
        TestAnnotation annotation1 = new TestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return TestAnnotation.class;
            }
            @Override
            public String value() {
                return "test";
            }
            @Override
            public int number() {
                return 42;
            }
        };
        AnotherTestAnnotation annotation2 = new AnotherTestAnnotation() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return AnotherTestAnnotation.class;
            }
            @Override
            public String name() {
                return "test";
            }
        };

        // Act & Assert
        assertNotEquals(AnnotationUtils.hashCode(annotation1), AnnotationUtils.hashCode(annotation2));
    }

    @Test
    void testHashCode_nullAnnotation() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> AnnotationUtils.hashCode(null));
    }

    @ParameterizedTest
    @ValueSource(classes = {int.class, String.class, Class.class, AnotherTestAnnotation.class})
    void testIsValidAnnotationMemberType_validTypes(Class<?> type) {
        // Arrange & Act & Assert
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(type));
    }

    
    
    
    @Test
    void testToString_nullAnnotation() {
        // Arrange & Act & Assert
        assertThrows(NullPointerException.class, () -> AnnotationUtils.toString(null));
    }

    // ---- added by coverage-guided refinement ----
    @Test
        void testAppendDetail_withAnnotationValue() {
            TestAnnotation annotation = () -> "test";
            TestAnnotation anotherAnnotation = () -> "another";
    
            ToStringBuilder builder = new ToStringBuilder(new Object(), AnnotationUtils.TO_STRING_STYLE);
            builder.append("annotationField", annotation);
    
            String result = builder.build();
            assertTrue(result.contains("@org.apache.commons.lang3.AnnotationUtils_GenTest$TestAnnotation(value=test)"));
        }

    @Test
        void testGetShortClassName_withAnnotationInterface() {
            TestAnnotation annotation = () -> "test";
            String shortClassName = AnnotationUtils.TO_STRING_STYLE.getShortClassName(annotation.annotationType());
            assertEquals("@org.apache.commons.lang3.AnnotationUtils_GenTest$TestAnnotation", shortClassName);
        }

    @Test
        void testAnnotationArrayMemberEquals_withDifferentLengths() {
            TestAnnotation[] a1 = new TestAnnotation[2];
            TestAnnotation[] a2 = new TestAnnotation[1];
            assertFalse(AnnotationUtils.annotationArrayMemberEquals(a1, a2));
        }

    @Test
        void testAnnotationArrayMemberEquals_withDifferentAnnotations() {
            TestAnnotation annotation1 = () -> "test";
            TestAnnotation annotation2 = () -> "another";
    
            TestAnnotation[] a1 = {annotation1};
            TestAnnotation[] a2 = {annotation2};
    
            assertFalse(AnnotationUtils.annotationArrayMemberEquals(a1, a2));
        }

    @Test
        void testAnnotationArrayMemberEquals_withSameAnnotations() {
            TestAnnotation annotation1 = () -> "test";
            TestAnnotation annotation2 = () -> "test";
    
            TestAnnotation[] a1 = {annotation1};
            TestAnnotation[] a2 = {annotation2};
    
            assertTrue(AnnotationUtils.annotationArrayMemberEquals(a1, a2));
        }

    @Test
        void testArrayMemberEquals_withAnnotationArrays() {
            TestAnnotation annotation1 = () -> "test";
            TestAnnotation annotation2 = () -> "another";
    
            TestAnnotation[] a1 = {annotation1};
            TestAnnotation[] a2 = {annotation2};
    
            assertFalse(AnnotationUtils.arrayMemberEquals(TestAnnotation.class, a1, a2));
        }

    @Test
        void testArrayMemberEquals_withByteArrays() {
            byte[] b1 = {1, 2, 3};
            byte[] b2 = {1, 2, 4};
    
            assertFalse(AnnotationUtils.arrayMemberEquals(byte.class, b1, b2));
        }

    @Test
        void testArrayMemberEquals_withShortArrays() {
            short[] s1 = {1, 2, 3};
            short[] s2 = {1, 2, 4};
    
            assertFalse(AnnotationUtils.arrayMemberEquals(short.class, s1, s2));
        }

    @Test
        void testArrayMemberEquals_withIntArrays() {
            int[] i1 = {1, 2, 3};
            int[] i2 = {1, 2, 4};
    
            assertFalse(AnnotationUtils.arrayMemberEquals(int.class, i1, i2));
        }

    @Test
        void testArrayMemberEquals_withCharArrays() {
            char[] c1 = {'a', 'b', 'c'};
            char[] c2 = {'a', 'b', 'd'};
    
            assertFalse(AnnotationUtils.arrayMemberEquals(char.class, c1, c2));
        }

    @Test
        void testArrayMemberEquals_withLongArrays() {
            long[] l1 = {1L, 2L, 3L};
            long[] l2 = {1L, 2L, 4L};
    
            assertFalse(AnnotationUtils.arrayMemberEquals(long.class, l1, l2));
        }

    @Test
        void testArrayMemberEquals_withFloatArrays() {
            float[] f1 = {1.0f, 2.0f, 3.0f};
            float[] f2 = {1.0f, 2.0f, 4.0f};
    
            assertFalse(AnnotationUtils.arrayMemberEquals(float.class, f1, f2));
        }

    @Test
        void testArrayMemberEquals_withDoubleArrays() {
            double[] d1 = {1.0, 2.0, 3.0};
            double[] d2 = {1.0, 2.0, 4.0};
    
            assertFalse(AnnotationUtils.arrayMemberEquals(double.class, d1, d2));
        }

    @Test
        void testArrayMemberEquals_withBooleanArrays() {
            boolean[] bool1 = {true, false};
            boolean[] bool2 = {false, true};
    
            assertFalse(AnnotationUtils.arrayMemberEquals(boolean.class, bool1, bool2));
        }

    @Test
        void testArrayMemberEquals_withObjectArrays() {
            Object[] o1 = {"test", 123};
            Object[] o2 = {"another", 456};
    
            assertFalse(AnnotationUtils.arrayMemberEquals(Object.class, o1, o2));
        }

    @Test
        void testArrayMemberHash_withByteArrays() {
            byte[] b1 = {1, 2, 3};
            int hash1 = AnnotationUtils.arrayMemberHash(byte.class, b1);
            assertEquals(Arrays.hashCode(b1), hash1);
        }

    @Test
        void testArrayMemberHash_withShortArrays() {
            short[] s1 = {1, 2, 3};
            int hash1 = AnnotationUtils.arrayMemberHash(short.class, s1);
            assertEquals(Arrays.hashCode(s1), hash1);
        }

    @Test
        void testArrayMemberHash_withIntArrays() {
            int[] i1 = {1, 2, 3};
            int hash1 = AnnotationUtils.arrayMemberHash(int.class, i1);
            assertEquals(Arrays.hashCode(i1), hash1);
        }

    @Test
        void testArrayMemberHash_withCharArrays() {
            char[] c1 = {'a', 'b', 'c'};
            int hash1 = AnnotationUtils.arrayMemberHash(char.class, c1);
            assertEquals(Arrays.hashCode(c1), hash1);
        }

    @Test
        void testArrayMemberHash_withLongArrays() {
            long[] l1 = {1L, 2L, 3L};
            int hash1 = AnnotationUtils.arrayMemberHash(long.class, l1);
            assertEquals(Arrays.hashCode(l1), hash1);
        }

    @Test
        void testArrayMemberHash_withFloatArrays() {
            float[] f1 = {1.0f, 2.0f, 3.0f};
            int hash1 = AnnotationUtils.arrayMemberHash(float.class, f1);
            assertEquals(Arrays.hashCode(f1), hash1);
        }

    @Test
        void testArrayMemberHash_withDoubleArrays() {
            double[] d1 = {1.0, 2.0, 3.0};
            int hash1 = AnnotationUtils.arrayMemberHash(double.class, d1);
            assertEquals(Arrays.hashCode(d1), hash1);
        }

    @Test
        void testArrayMemberHash_withBooleanArrays() {
            boolean[] bool1 = {true, false};
            int hash1 = AnnotationUtils.arrayMemberHash(boolean.class, bool1);
            assertEquals(Arrays.hashCode(bool1), hash1);
        }

    @Test
        void testArrayMemberHash_withObjectArrays() {
            Object[] o1 = {"test", 123};
            int hash1 = AnnotationUtils.arrayMemberHash(Object.class, o1);
            assertEquals(Arrays.hashCode(o1), hash1);
        }

    @Test
        void testEquals_sameAnnotationsWithSameValues() {
            TestAnnotation annotation1 = () -> "test";
            TestAnnotation annotation2 = () -> "test";
    
            assertTrue(AnnotationUtils.equals(annotation1, annotation2));
        }

    @Test
        void testEquals_withReflectiveOperationException() {
            assertThrows(UncheckedException.class, () -> {
                Annotation invalidAnnotation = new Annotation() {
                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return null;
                    }
                };
                AnnotationUtils.equals(invalidAnnotation, invalidAnnotation);
            });
        }

    @Test
        void testHashCode_withReflectiveOperationException() {
            assertThrows(UncheckedException.class, () -> {
                Annotation invalidAnnotation = new Annotation() {
                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return null;
                    }
                };
                AnnotationUtils.hashCode(invalidAnnotation);
            });
        }

    @Test
        void testHashCode_withNullValue() {
            TestAnnotation annotation = () -> null;
    
            assertThrows(IllegalStateException.class, () -> {
                AnnotationUtils.hashCode(annotation);
            });
        }

    @Test
        void testHashMember_withArrayValue() {
            int[] array = {1, 2, 3};
            int hash = AnnotationUtils.hashMember("numbers", array);
    
            assertEquals(("numbers".hashCode() * 127) ^ Arrays.hashCode(array), hash);
        }
}
