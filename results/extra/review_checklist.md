# Review checklist: 50 removed failing tests

For each item: read the test and the failure, then tick **agree** or write the correct label.
Labels: wrong oracle (API semantics / value computation / exception expected / builder state / parameter semantics / output format), test bug, ambiguous documentation, **real bug in Commons Lang**.
Source to check against: `commons-lang-src/src/main/java` (tag rel/commons-lang-3.14.0).

Status: not completed before submission. The paper reports these labels as LLM-assisted triage, not as a manual review. The checklist is kept for a later confirmation.

## 1. BooleanUtils.testIsNotFalse (L-R, run3)

**Failure:** `expected: <true> but was: <false>`

```java
@ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testIsNotFalse(Boolean bool) {
        // Arrange & Act & Assert
        assertEquals(bool != Boolean.FALSE, BooleanUtils.isNotFalse(bool));
    }
```

**Proposed label:** test bug  
**Evidence:** compares Boolean objects with != (identity, not value)

- [ ] agree   - [ ] disagree → correct label: ________

## 2. CharSetUtils.testKeep_normal (S-R, run3)

**Failure:** `expected: <ace> but was: <ac>`

```java
@Test
    public void testKeep_normal() {
        assertEquals("ace", CharSetUtils.keep("abcde", "a", "c"));
    }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** keep() keeps only the listed chars ('a','c')

- [ ] agree   - [ ] disagree → correct label: ________

## 3. CharSetUtils.testContainsAny_emptyOrNullCharSet (S-R, run3)

**Failure:** `expected: <false> but was: <true>`

```java
@ParameterizedTest
    @ValueSource(strings = {"", "a"})
    public void testContainsAny_emptyOrNullCharSet(String charSet) {
        assertFalse(CharSetUtils.containsAny("abc", charSet));
    }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** containsAny("abc","a") is true

- [ ] agree   - [ ] disagree → correct label: ________

## 4. CharUtils.testToCharacterObject_StringPartialBranches (S-RC, run1)

**Failure:** `expected: <0> but was: <1>`

```java
@Test
        public void testToCharacterObject_StringPartialBranches() {
            assertEquals(Character.valueOf('a'), CharUtils.toCharacterObject("abc"));
            assertEquals(Character.valueOf('0'), CharUtils.toCharacterObject("123"));
            assertEquals(Character.valueOf('\t'), CharUtils.toCharacterObject("\ta"));
            assertNull(CharUtils.toCharacterObject(""));
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** toCharacterObject returns the first char

- [ ] agree   - [ ] disagree → correct label: ________

## 5. CharUtils.testIsAsciiControl_NotCoveredBranch (S-RC, run3)

**Failure:** `expected: <true> but was: <false>`

```java
@Test
        public void testIsAsciiControl_NotCoveredBranch() {
            assertTrue(CharUtils.isAsciiControl(' '));
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** ' ' (32) is not an ASCII control char

- [ ] agree   - [ ] disagree → correct label: ________

## 6. ClassUtils.testGetPackageName_privateMethodBranches (L-RC, run1)

**Failure:** `expected: <> but was: <java.lang>`

```java
@Test
        void testGetPackageName_privateMethodBranches() {
            assertEquals("java.lang", ClassUtils.getPackageName("java.lang.String"));
            assertEquals("", ClassUtils.getPackageName("[I"));
            assertEquals("", ClassUtils.getPackageName("[[Ljava.lang.String;"));
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** getPackageName strips array encoding

- [ ] agree   - [ ] disagree → correct label: ________

## 7. ClassUtils.testUseFullBranchesInGetAbbreviatedName (L-RC, run2)

**Failure:** `expected: <o.a.c.l.C> but was: <o.a.c.l.ClassUtils>`

```java
@Test
        void testUseFullBranchesInGetAbbreviatedName() {
            String className = "org.apache.commons.lang3.ClassUtils";
            assertEquals("o.a.c.l.ClassUtils", ClassUtils.getAbbreviatedName(className, 20));
            assertEquals("o.a.c.l.C", ClassUtils.getAbbreviatedName(className, 10));
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** simple class name is never abbreviated

- [ ] agree   - [ ] disagree → correct label: ________

## 8. ClassUtils.testGetClassName_withInnerClass (L-RC, run3)

**Failure:** `expected: <true> but was: <false>`

```java
@Test
        void testGetClassName_withInnerClass() {
            String className = ClassUtils.getName(ClassUtils_GenTest.class, "default");
            assertTrue(className.contains("ClassUtils$"));
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** getName returns the test class name

- [ ] agree   - [ ] disagree → correct label: ________

## 9. ClassUtils.testGetClassWithNullClassLoaderAndNonNullClassName (S-RC, run2)

**Failure:** `Expected java.lang.ClassNotFoundException to be thrown, but nothing was thrown.`

```java
@Test
        public void testGetClassWithNullClassLoaderAndNonNullClassName() throws ClassNotFoundException {
            assertThrows(ClassNotFoundException.class, () -> ClassUtils.getClass((ClassLoader) null, "java.lang.String"));
        }
```

**Proposed label:** wrong oracle: exception expected  
**Evidence:** null class loader still loads java.lang.String

- [ ] agree   - [ ] disagree → correct label: ________

## 10. CompareToBuilder.testAppendIntArray (L-R, run2)

**Failure:** `expected: <1> but was: <-1>`

```java
@Test
    public void testAppendIntArray() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append(new int[]{1}, new int[]{1}).toComparison());
        assertEquals(-1, builder.append(new int[]{0}, new int[]{1}).toComparison());
        assertEquals(1, builder.append(new int[]{1}, new int[]{0}).toComparison());
        assertEquals(-1, builder.append(null, new int[]{1}).toComparison());
        assertEquals(1, builder.append(new int[]{1}, null).toComparison());
        assertEquals(-1, builder.append(new int[]{1}, new int[]{}).toComparison());
        assertEquals(1, builder.append(new int[]{}, new int[]{1}).toComparison());
    }
```

**Proposed label:** wrong oracle: builder state  
**Evidence:** builder keeps the first non-zero comparison

- [ ] agree   - [ ] disagree → correct label: ________

## 11. CompareToBuilder.testAppendFloat (L-R, run2)

**Failure:** `expected: <1> but was: <-1>`

```java
@Test
    public void testAppendFloat() {
        // Arrange
        CompareToBuilder builder = new CompareToBuilder();
        
        // Act & Assert
        assertEquals(0, builder.append(1.0f, 1.0f).toComparison());
        assertEquals(-1, builder.append(0.0f, 1.0f).toComparison());
        assertEquals(1, builder.append(1.0f, 0.0f).toComparison());
    }
```

**Proposed label:** wrong oracle: builder state  
**Evidence:** builder keeps the first non-zero comparison

- [ ] agree   - [ ] disagree → correct label: ________

## 12. CompareToBuilder.testReflectionCompareWithSuperclass (L-RC, run1)

**Failure:** `expected: <-1> but was: <1999248354>`

```java
@Test
        void testReflectionCompareWithSuperclass() {
            class Parent {
                private int value;
    
                public Parent(int value) {
                    this.value = value;
                }
            }
    
            class Child extends Parent implements Comparable<Child> {
                private int anotherValue;
    
                public Child(int value, int anotherValue) {
                    super(value);
                    this.anotherValue = anotherValue;
                }
    
                @Override
                public int compareTo(Child other) {
                    return new CompareToBuilder()
                        .appendSuper(super.hashCode())
                        .append(this.anotherValue, other.anotherValue)
                        .toComparison();
                }
            }
    
            Child child1 = new Child(10, 20);
            Child child2 = new Child(10, 30);
    
            assertEquals(-1, child1.compareTo(child2));
        }
```

**Proposed label:** wrong oracle: value computation  
**Evidence:** depends on hashCode()

- [ ] agree   - [ ] disagree → correct label: ________

## 13. CompareToBuilder.testAppendBooleanArrayAlreadyComparedWithDifferentValues (L-RC, run1)

**Failure:** `expected: <-1> but was: <1>`

```java
@Test
        void testAppendBooleanArrayAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append(new boolean[]{true}, new boolean[]{false});
            assertSame(builder, builder.append(new boolean[]{false}, new boolean[]{true}));
            assertEquals(-1, builder.toComparison());
        }
```

**Proposed label:** wrong oracle: builder state  
**Evidence:** builder keeps the first non-zero comparison

- [ ] agree   - [ ] disagree → correct label: ________

## 14. CompareToBuilder.testReflectionCompareWithTransients_2 (L-RC, run1)

**Failure:** `expected: <0> but was: <-1>`

```java
@Test
        void testReflectionCompareWithTransients_2() {
            class TestClass {
                public int field1;
                public transient int field2;
    
                public TestClass(int field1, int field2) {
                    this.field1 = field1;
                    this.field2 = field2;
                }
            }
    
            TestClass obj1 = new TestClass(1, 2);
            TestClass obj2 = new TestClass(1, 3);
    
            assertEquals(0, CompareToBuilder.reflectionCompare(obj1, obj2, true));
        }
```

**Proposed label:** wrong oracle: parameter semantics  
**Evidence:** compareTransients=true includes transients

- [ ] agree   - [ ] disagree → correct label: ________

## 15. CompareToBuilder.testAppendBooleanAlreadyComparedWithDifferentValues (L-RC, run1)

**Failure:** `expected: <-1> but was: <1>`

```java
@Test
        void testAppendBooleanAlreadyComparedWithDifferentValues() {
            CompareToBuilder builder = new CompareToBuilder().append(true, false);
            assertSame(builder, builder.append(false, true));
            assertEquals(-1, builder.toComparison());
        }
```

**Proposed label:** wrong oracle: builder state  
**Evidence:** builder keeps the first non-zero comparison

- [ ] agree   - [ ] disagree → correct label: ________

## 16. CompareToBuilder.testAppendCharArrayDifferentLengths (L-RC, run3)

**Failure:** `expected: <-1> but was: <1>`

```java
@Test
        void testAppendCharArrayDifferentLengths() {
            CompareToBuilder builder = new CompareToBuilder();
            assertEquals(builder, builder.append(new char[]{'a'}, new char[]{}));
            assertEquals(-1, builder.toComparison());
    
            builder = new CompareToBuilder();
            assertEquals(builder, builder.append(new char[]{}, new char[]{'b'}));
            assertEquals(1, builder.toComparison());
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** shorter array compares as less

- [ ] agree   - [ ] disagree → correct label: ________

## 17. EqualsBuilder.testReflectionEqualsWithDifferentObjects (L-RC, run1)

**Failure:** `expected: <false> but was: <true>`

```java
@Test
        void testReflectionEqualsWithDifferentObjects() {
            assertFalse(EqualsBuilder.reflectionEquals(new Object(), new Object()));
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** objects without fields are reflectively equal

- [ ] agree   - [ ] disagree → correct label: ________

## 18. EqualsBuilder.testReflectionEqualsWithTestTransients (L-RC, run2)

**Failure:** `expected: <true> but was: <false>`

```java
@Test
        void testReflectionEqualsWithTestTransients() {
            class TestClass {
                private transient int transientField;
                private int nonTransientField;
    
                public TestClass(int transientField, int nonTransientField) {
                    this.transientField = transientField;
                    this.nonTransientField = nonTransientField;
                }
            }
            TestClass obj1 = new TestClass(1, 2);
            TestClass obj2 = new TestClass(3, 2);
            assertTrue(EqualsBuilder.reflectionEquals(obj1, obj2, true));
        }
```

**Proposed label:** wrong oracle: parameter semantics  
**Evidence:** testTransients=true includes transients

- [ ] agree   - [ ] disagree → correct label: ________

## 19. ExceptionUtils.testGetRootCauseMessageWithNullThrowable_2 (S-RC, run2)

**Failure:** `expected: <true> but was: <false>`

```java
@Test
        public void testGetRootCauseMessageWithNullThrowable_2() {
            String message = ExceptionUtils.getRootCauseMessage(null);
            assertTrue(message.startsWith("RuntimeException: null"));
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** getRootCauseMessage(null) is empty

- [ ] agree   - [ ] disagree → correct label: ________

## 20. ExceptionUtils.testGetRootCauseWithNoCause_2 (S-RC, run2)

**Failure:** `expected: <RuntimeException> but was: <null>`

```java
@Test
        public void testGetRootCauseWithNoCause_2() {
            Throwable throwable = new RuntimeException();
            Throwable rootCause = ExceptionUtils.getRootCause(throwable);
            assertEquals(RuntimeException.class, rootCause.getClass());
            assertEquals("RuntimeException", rootCause.getMessage());
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** exception without message has null message

- [ ] agree   - [ ] disagree → correct label: ________

## 21. ExceptionUtils.testHasCause_CauseFound (S-RC, run3)

**Failure:** `expected: <true> but was: <false>`

```java
@Test
        public void testHasCause_CauseFound() {
            Throwable cause1 = new IllegalArgumentException("cause1");
            Throwable cause2 = new NullPointerException("cause2");
            cause1.initCause(cause2);
            boolean result = ExceptionUtils.hasCause(cause1, NullPointerException.class);
            assertTrue(result);
        }
```

**Proposed label:** ambiguous documentation  
**Evidence:** Javadoc first sentence says 'causal chain'; @return and code check only the throwable itself (pipeline keeps first sentence only)

- [ ] agree   - [ ] disagree → correct label: ________

## 22. HashCodeBuilder.testAppendBooleanArray_Values (L-R, run1)

**Failure:** `expected: <23310> but was: <23274>`

```java
@Test
    public void testAppendBooleanArray_Values() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(new boolean[]{true, false});

        // Assert
        assertEquals((37 * (37 * 17 + 1) + 0), builder.toHashCode());
    }
```

**Proposed label:** wrong oracle: value computation  
**Evidence:** hash arithmetic

- [ ] agree   - [ ] disagree → correct label: ________

## 23. HashCodeBuilder.testAppendCharArray_Empty (L-R, run1)

**Failure:** `expected: <629> but was: <17>`

```java
@Test
    public void testAppendCharArray_Empty() {
        // Arrange
        HashCodeBuilder builder = new HashCodeBuilder();

        // Act
        builder.append(new char[0]);

        // Assert
        assertEquals(37 * 17, builder.toHashCode());
    }
```

**Proposed label:** wrong oracle: value computation  
**Evidence:** empty array leaves the hash unchanged

- [ ] agree   - [ ] disagree → correct label: ________

## 24. HashCodeBuilder.testAppendArray_IntArray (L-RC, run1)

**Failure:** `expected: <1237> but was: <23312>`

```java
@Test
        void testAppendArray_IntArray() {
            int[] array = {1, 2};
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(array);
            assertEquals(1237, builder.toHashCode());
        }
```

**Proposed label:** wrong oracle: value computation  
**Evidence:** hash arithmetic

- [ ] agree   - [ ] disagree → correct label: ________

## 25. HashCodeBuilder.testAppendBoolean_NotCovered (L-RC, run1)

**Failure:** `expected: <61> but was: <629>`

```java
@Test
        void testAppendBoolean_NotCovered() {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(true);
            assertEquals(61, builder.toHashCode());
        }
```

**Proposed label:** wrong oracle: value computation  
**Evidence:** hash arithmetic

- [ ] agree   - [ ] disagree → correct label: ________

## 26. HashCodeBuilder.testAppendObject (S-R, run2)

**Failure:** `expected: <231055440> but was: <1724687499>`

```java
@Test
    public void testAppendObject() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(new Object());
        assertEquals(17 * 37 + System.identityHashCode(new Object()), builder.toHashCode());
    }
```

**Proposed label:** wrong oracle: value computation  
**Evidence:** identityHashCode of a different object

- [ ] agree   - [ ] disagree → correct label: ________

## 27. HashCodeBuilder.testAppendFloatArray (S-R, run2)

**Failure:** `expected: <1553564633> but was: <-1141667027>`

```java
@Test
    public void testAppendFloatArray() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(new float[]{0.1f, 0.2f, 0.3f});
        assertEquals(17 * 37 * 37 * 37 * 37 + Float.floatToIntBits(0.1f) * 37 * 37 * 37 + Float.floatToIntBits(0.2f) * 37 * 37 + Float.floatToIntBits(0.3f), builder.toHashCode());
    }
```

**Proposed label:** wrong oracle: value computation  
**Evidence:** hash arithmetic

- [ ] agree   - [ ] disagree → correct label: ________

## 28. HashCodeBuilder.testAppendBooleanArray (S-R, run3)

**Failure:** `expected: <23274> but was: <861138>`

```java
@Test
    public void testAppendBooleanArray() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(new boolean[]{true, false, true});
        assertEquals(17 * 37 * 37 + 0 + 1 + 0, builder.toHashCode());
    }
```

**Proposed label:** wrong oracle: value computation  
**Evidence:** hash arithmetic

- [ ] agree   - [ ] disagree → correct label: ________

## 29. HashCodeBuilder.testAppendCharArray (S-R, run3)

**Failure:** `expected: <23567> but was: <997619>`

```java
@Test
    public void testAppendCharArray() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(new char[]{'a', 'b', 'c'});
        assertEquals(17 * 37 * 37 + 'a' + 'b' + 'c', builder.toHashCode());
    }
```

**Proposed label:** wrong oracle: value computation  
**Evidence:** hash arithmetic

- [ ] agree   - [ ] disagree → correct label: ________

## 30. HashCodeBuilder.testReflectionAppendArrayEmpty (S-RC, run3)

**Failure:** `expected: <629> but was: <17>`

```java
@Test
        public void testReflectionAppendArrayEmpty() {
            HashCodeBuilder builder = new HashCodeBuilder();
            reflectionAppend(new Object[0], null, builder, false, null);
            assertEquals(17 * 37, builder.toHashCode());
        }
```

**Proposed label:** wrong oracle: value computation  
**Evidence:** empty array leaves the hash unchanged

- [ ] agree   - [ ] disagree → correct label: ________

## 31. HashCodeBuilder.testReflectionAppendNullObject (S-RC, run3)

**Failure:** `expected: <629> but was: <17>`

```java
@Test
        public void testReflectionAppendNullObject() {
            HashCodeBuilder builder = new HashCodeBuilder();
            reflectionAppend(null, null, builder, false, null);
            assertEquals(17 * 37, builder.toHashCode());
        }
```

**Proposed label:** wrong oracle: value computation  
**Evidence:** null object leaves the hash unchanged

- [ ] agree   - [ ] disagree → correct label: ________

## 32. HashCodeBuilder.testIsRegisteredNull (S-RC, run3)

**Failure:** `expected: <true> but was: <false>`

```java
// ---- added by coverage-guided refinement ----
    @Test
        public void testIsRegisteredNull() {
            Set<IDKey> registry = new HashSet<>();
            IDKey key = new IDKey(null);
            registry.add(key);
            assertTrue(HashCodeBuilder.isRegistered(null));
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** registry is internal; null is not registered

- [ ] agree   - [ ] disagree → correct label: ________

## 33. JavaVersion.testGetJava11 (S-RC, run3)

**Failure:** `expected: <11> but was: <1.1>`

```java
@Test
        public void testGetJava11() {
            assertEquals(JavaVersion.JAVA_11, JavaVersion.get("1.1"));
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** "1.1" is JAVA_1_1, not JAVA_11

- [ ] agree   - [ ] disagree → correct label: ________

## 34. JavaVersion.testGetJava18Decimal_2 (S-RC, run3)

**Failure:** `expected: <18> but was: <1.8>`

```java
@Test
        public void testGetJava18Decimal_2() {
            assertEquals(JavaVersion.JAVA_18, JavaVersion.get("1.8"));
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** "1.8" is JAVA_1_8, not JAVA_18

- [ ] agree   - [ ] disagree → correct label: ________

## 35. JavaVersion.testGetJava13Decimal_2 (S-RC, run3)

**Failure:** `expected: <13> but was: <1.3>`

```java
@Test
        public void testGetJava13Decimal_2() {
            assertEquals(JavaVersion.JAVA_13, JavaVersion.get("1.3"));
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** "1.3" is JAVA_1_3, not JAVA_13

- [ ] agree   - [ ] disagree → correct label: ________

## 36. LocaleUtils.testLocaleLookupList_withDefaultLocaleAlreadyPresent (L-RC, run1)

**Failure:** `expected: <1> but was: <2>`

```java
@Test
        void testLocaleLookupList_withDefaultLocaleAlreadyPresent() {
            Locale defaultLocale = new Locale("en", "US");
            List<Locale> result = LocaleUtils.localeLookupList(defaultLocale, defaultLocale);
            assertEquals(1, result.size());
            assertEquals(defaultLocale, result.get(0));
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** lookup list adds the language-only locale

- [ ] agree   - [ ] disagree → correct label: ________

## 37. LocaleUtils.testLocaleLookupList_withCountryOnly (L-RC, run1)

**Failure:** `expected: <3> but was: <2>`

```java
@Test
        void testLocaleLookupList_withCountryOnly() {
            Locale localeWithCountry = new Locale("fr", "FR");
            List<Locale> result = LocaleUtils.localeLookupList(localeWithCountry);
            assertEquals(3, result.size());
            assertEquals(localeWithCountry, result.get(0));
            assertEquals(new Locale("fr", ""), result.get(1));
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** fr_FR gives [fr_FR, fr]

- [ ] agree   - [ ] disagree → correct label: ________

## 38. LocaleUtils.testIsISO639LanguageCodeWithInvalidLengthOrUpperCase (L-RC, run2)

**Failure:** `expected: <false> but was: <true>`

```java
@ParameterizedTest
        @ValueSource(strings = {"engg", "EN"})
        void testIsISO639LanguageCodeWithInvalidLengthOrUpperCase(String code) {
            assertFalse(isValidISO639LanguageCode(code));
        }
```

**Proposed label:** test bug  
**Evidence:** asserts on a helper method defined in the test itself

- [ ] agree   - [ ] disagree → correct label: ________

## 39. LocaleUtils.testLocaleLookupList_nullLocale (S-R, run2)

**Failure:** `expected: <4> but was: <0>`

```java
@Test
    public void testLocaleLookupList_nullLocale() {
        List<Locale> locales = LocaleUtils.localeLookupList(null);
        assertNotNull(locales);
        assertEquals(4, locales.size());
        assertTrue(locales.contains(new Locale("en", StringUtils.EMPTY)));
        assertTrue(locales.contains(Locale.getDefault()));
    }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** localeLookupList(null) is empty

- [ ] agree   - [ ] disagree → correct label: ________

## 40. ObjectUtils.testReadResolve (L-RC, run1)

**Failure:** `expected: <org.apache.commons.lang3.ObjectUtils$Null@2766a543> but was: <org.apache.commons.lang3.ObjectUtils$Null@134b6a07>`

```java
// ---- added by coverage-guided refinement ----
    @Test
        void testReadResolve() throws Exception {
            ObjectUtils.Null nullInstance = new ObjectUtils.Null();
            assertEquals(ObjectUtils.NULL, nullInstance);
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** Null equality is identity

- [ ] agree   - [ ] disagree → correct label: ________

## 41. ObjectUtils.testCompare (S-RC, run2)

**Failure:** `expected: <true> but was: <false>`

```java
@Test
        public void testCompare() {
            assertEquals(0, ObjectUtils.compare(1, 1));
            assertEquals(-1, ObjectUtils.compare(1, 2));
            assertEquals(1, ObjectUtils.compare(2, 1));
    
            assertTrue(ObjectUtils.compare(1, 2, true) > 0);
            assertTrue(ObjectUtils.compare(2, 1, true) < 0);
    
            assertNull(ObjectUtils.compare(null, null));
            assertEquals(-1, ObjectUtils.compare(null, 1));
            assertEquals(1, ObjectUtils.compare(1, null));
        }
```

**Proposed label:** wrong oracle: parameter semantics  
**Evidence:** nullGreater only affects null arguments

- [ ] agree   - [ ] disagree → correct label: ________

## 42. RandomStringUtils.testRandomWithStartEndZeroAndNoLettersOrNumbers (L-RC, run3)

**Failure:** `Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.`

```java
@Test
        void testRandomWithStartEndZeroAndNoLettersOrNumbers() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> RandomStringUtils.random(5, 0, 0, false, false));
            assertEquals("Parameter end (0) must be greater then (48) for generating digits or greater then (65) for generating letters.", exception.getMessage());
        }
```

**Proposed label:** wrong oracle: exception expected  
**Evidence:** start=end=0 means full range; message invented

- [ ] agree   - [ ] disagree → correct label: ________

## 43. RandomStringUtils.testRandomAlphabeticWithEmptyRange (S-RC, run1)

**Failure:** `Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.`

```java
@Test
        public void testRandomAlphabeticWithEmptyRange() {
            assertThrows(IllegalArgumentException.class, () -> {
                RandomStringUtils.randomAlphabetic(0);
            });
        }
```

**Proposed label:** wrong oracle: exception expected  
**Evidence:** count 0 returns empty string

- [ ] agree   - [ ] disagree → correct label: ________

## 44. RandomStringUtils.testRandomWithStartEndZeroAndNullCharsArray_2_2 (S-RC, run1)

**Failure:** `Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.`

```java
@Test
        public void testRandomWithStartEndZeroAndNullCharsArray_2_2() {
            assertThrows(IllegalArgumentException.class, () -> random(10, 0, 0, false, false, null));
        }
```

**Proposed label:** wrong oracle: exception expected  
**Evidence:** null chars array is allowed

- [ ] agree   - [ ] disagree → correct label: ________

## 45. Range.testOfWithNullComparator (S-RC, run3)

**Failure:** `expected: <null> but was: <INSTANCE>`

```java
@Test
        public void testOfWithNullComparator() {
            Range<String> range = Range.of("a", "b");
            assertNotNull(range);
            assertEquals("a", range.getMinimum());
            assertEquals("b", range.getMaximum());
            assertNull(range.getComparator());
        }
```

**Proposed label:** wrong oracle: API semantics  
**Evidence:** getComparator never returns null (documented)

- [ ] agree   - [ ] disagree → correct label: ________

## 46. ReflectionToStringBuilder.testToStringWithOutputTransients (L-R, run1)

**Failure:** `expected: <org.apache.commons.lang3.builder.ReflectionToStringBuilder_GenTest$TestClass@<hashcode>(name=test,age=123,transientField=null)> but was: <org.apache.commons.lang3.builder.ReflectionToString`

```java
@Test
    void testToStringWithOutputTransients() {
        // Arrange
        TestClass obj = new TestClass("test", 123);

        // Act
        String result = ReflectionToStringBuilder.toString(obj, null, true);

        // Assert
        assertEquals("org.apache.commons.lang3.builder.ReflectionToStringBuilder_GenTest$TestClass@<hashcode>(name=test,age=123,transientField=null)", 
                     result.replaceAll("@[0-9a-fA-F]+", "@<hashcode>"));
    }
```

**Proposed label:** wrong oracle: output format  
**Evidence:** toString format differs

- [ ] agree   - [ ] disagree → correct label: ________

## 47. ReflectionToStringBuilder.testToStringNullObject (S-R, run2)

**Failure:** `expected: <null> but was: <<null>>`

```java
@Test
    public void testToStringNullObject() {
        String result = ReflectionToStringBuilder.toString(null);
        assertEquals("null", result);
    }
```

**Proposed label:** wrong oracle: output format  
**Evidence:** null is rendered as <null>

- [ ] agree   - [ ] disagree → correct label: ________

## 48. Validate.testNotNaNWithNaNValue (L-RC, run2)

**Failure:** `expected: <The value is invalid: NaN> but was: <The validated value is not a number>`

```java
@Test
        void testNotNaNWithNaNValue() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                Validate.notNaN(Double.NaN);
            });
            assertEquals("The value is invalid: NaN", exception.getMessage());
        }
```

**Proposed label:** wrong oracle: output format  
**Evidence:** exception message invented

- [ ] agree   - [ ] disagree → correct label: ________

## 49. Validate.testNotEmptyStringThrowsException (S-R, run3)

**Failure:** `Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.`

```java
@ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void testNotEmptyStringThrowsException(String str) {
        assertThrows(IllegalArgumentException.class, () -> Validate.notEmpty(str));
    }
```

**Proposed label:** wrong oracle: exception expected  
**Evidence:** notEmpty accepts whitespace-only strings

- [ ] agree   - [ ] disagree → correct label: ________

## 50. Validate.testExclusiveBetweenComparable_2 (S-RC, run1)

**Failure:** `Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.`

```java
@Test
        public void testExclusiveBetweenComparable_2() {
            assertThrows(IllegalArgumentException.class, () -> Validate.exclusiveBetween("A", "C", "B"));
        }
```

**Proposed label:** wrong oracle: exception expected  
**Evidence:** "B" is strictly between "A" and "C"

- [ ] agree   - [ ] disagree → correct label: ________
