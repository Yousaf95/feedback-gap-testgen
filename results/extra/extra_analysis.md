# Post-hoc exploratory analyses

Computed after the main analysis from existing artifacts (no new LLM calls); not part of the frozen plan.

## 1. Branch coverage after each refinement round (RC configs)

Round 0 = green R suite before refinement; after an early stop the last value is carried forward. Mean over classes (per-class mean over seeds), %.

| Config | round 0 | round 1 | round 2 | round 3 | final | rounds used (mean) | stopped: ≥90% branch | stopped: no gain |
|---|---|---|---|---|---|---|---|---|
| S-RC | 26.3 | 32.2 | 35.3 | 36.0 | 36.6 | 1.80 | 3 | 43 |
| L-RC | 43.3 | 59.5 | 61.6 | 63.5 | 65.1 | 2.17 | 13 | 25 |

## 2. Cost-effectiveness (per class, mean over classes)

Gain = branch-coverage points above S-ZS. Times as in summary.md (8 concurrent requests per GPU).

| Config | Branch cov. | Gain vs S-ZS (pp) | Total time (min) | Output tokens (k) | pp per minute | pp per 1k output tokens |
|---|---|---|---|---|---|---|
| S-ZS | 25.5 | +0.0 | 0.8 | 1.3 | – | – |
| S-R | 26.3 | +0.8 | 1.3 | 4.3 | 0.64 | 0.19 |
| S-RC | 36.6 | +11.0 | 2.9 | 7.5 | 3.79 | 1.46 |
| L-ZS | 38.2 | +12.7 | 4.8 | 1.6 | 2.63 | 7.69 |
| L-R | 43.3 | +17.8 | 9.2 | 3.2 | 1.94 | 5.52 |
| L-RC | 65.1 | +39.6 | 25.8 | 8.0 | 1.54 | 4.95 |

Marginal cost of each feedback step (mean over classes):

| Step | Δ branch (pp) | Δ time (min) | Δ output tokens (k) |
|---|---|---|---|
| S-ZS → S-R | +0.8 | +0.5 | +3.0 |
| S-R → S-RC | +10.2 | +1.6 | +3.2 |
| L-ZS → L-R | +5.1 | +4.3 | +1.6 |
| L-R → L-RC | +21.8 | +16.6 | +4.8 |
| S-RC → L-ZS | +1.6 | +1.9 | -5.9 |
| S-RC → L-RC | +28.6 | +22.9 | +0.5 |

## 3. Tests removed as failing in Stage 4

Parsed from Surefire output: 1346 failing test methods across all class-runs (ZS and R share T0, so many initial-stage failures appear in both; counts are per config).

| Category | S-ZS | S-R | S-RC | L-ZS | L-R | L-RC |
|---|---|---|---|---|---|---|
| assertion: wrong expected value | 126 | 131 | 114 | 112 | 119 | 199 |
| JUnit setup / parameter error | 102 | 101 | 11 | 0 | 0 | 0 |
| expected exception not thrown | 11 | 11 | 29 | 9 | 11 | 29 |
| different exception thrown | 2 | 2 | 9 | 7 | 7 | 40 |
| unexpected exception: NullPointerException | 3 | 2 | 27 | 2 | 5 | 20 |
| unexpected exception: IllegalArgumentException | 5 | 5 | 5 | 0 | 0 | 14 |
| unexpected exception: RuntimeException | 5 | 5 | 5 | 0 | 0 | 5 |
| unexpected exception: ClassCastException | 3 | 3 | 0 | 0 | 0 | 9 |
| unexpected exception: StringIndexOutOfBoundsException | 0 | 0 | 0 | 2 | 2 | 6 |
| unexpected exception: InaccessibleObjectException | 1 | 1 | 0 | 2 | 2 | 3 |
| assertion: other | 3 | 3 | 0 | 0 | 0 | 2 |
| unexpected exception: UnsupportedOperationException | 2 | 2 | 1 | 1 | 0 | 1 |
| unexpected exception: ArrayIndexOutOfBoundsException | 0 | 0 | 0 | 0 | 0 | 3 |
| unexpected exception: NumberFormatException | 0 | 0 | 1 | 0 | 0 | 1 |
| unexpected exception: IllegalMonitorStateException | 0 | 1 | 0 | 0 | 0 | 0 |
| unexpected exception: CloneFailedException | 0 | 0 | 1 | 0 | 0 | 0 |

Triage sample: 50 of 634 unique assertion / expected-exception failures (random.seed(2027)); see removed_sample50.jsonl and the triage table below.
Parsed per-method failures: 1346; tests removed as failing according to runs.jsonl: 1814 (the rest left no per-method Surefire line, e.g. JVM crashes, class-level errors, or methods removed together after a crash).


### LLM-assisted triage of a random sample of 50 tests

Labels assigned by LLM-assisted triage against the Commons Lang 3.14 source and Javadoc (not independently confirmed by the authors).

| Label | Count |
|---|---|
| wrong oracle: API semantics | 20 |
| wrong oracle: value computation | 11 |
| wrong oracle: exception expected | 6 |
| wrong oracle: builder state | 4 |
| wrong oracle: parameter semantics | 3 |
| wrong oracle: output format | 3 |
| test bug | 2 |
| ambiguous documentation | 1 |

| # | Class | Config | Method | Label | Note |
|---|---|---|---|---|---|
| 1 | BooleanUtils | L-R | testIsNotFalse | test bug | compares Boolean objects with != (identity, not value) |
| 2 | CharSetUtils | S-R | testKeep_normal | wrong oracle: API semantics | keep() keeps only the listed chars ('a','c') |
| 3 | CharSetUtils | S-R | testContainsAny_emptyOrNullCharSet | wrong oracle: API semantics | containsAny("abc","a") is true |
| 4 | CharUtils | S-RC | testToCharacterObject_StringPartialBranches | wrong oracle: API semantics | toCharacterObject returns the first char |
| 5 | CharUtils | S-RC | testIsAsciiControl_NotCoveredBranch | wrong oracle: API semantics | ' ' (32) is not an ASCII control char |
| 6 | ClassUtils | L-RC | testGetPackageName_privateMethodBranches | wrong oracle: API semantics | getPackageName strips array encoding |
| 7 | ClassUtils | L-RC | testUseFullBranchesInGetAbbreviatedName | wrong oracle: API semantics | simple class name is never abbreviated |
| 8 | ClassUtils | L-RC | testGetClassName_withInnerClass | wrong oracle: API semantics | getName returns the test class name |
| 9 | ClassUtils | S-RC | testGetClassWithNullClassLoaderAndNonNullClassName | wrong oracle: exception expected | null class loader still loads java.lang.String |
| 10 | CompareToBuilder | L-R | testAppendIntArray | wrong oracle: builder state | builder keeps the first non-zero comparison |
| 11 | CompareToBuilder | L-R | testAppendFloat | wrong oracle: builder state | builder keeps the first non-zero comparison |
| 12 | CompareToBuilder | L-RC | testReflectionCompareWithSuperclass | wrong oracle: value computation | depends on hashCode() |
| 13 | CompareToBuilder | L-RC | testAppendBooleanArrayAlreadyComparedWithDifferentValues | wrong oracle: builder state | builder keeps the first non-zero comparison |
| 14 | CompareToBuilder | L-RC | testReflectionCompareWithTransients_2 | wrong oracle: parameter semantics | compareTransients=true includes transients |
| 15 | CompareToBuilder | L-RC | testAppendBooleanAlreadyComparedWithDifferentValues | wrong oracle: builder state | builder keeps the first non-zero comparison |
| 16 | CompareToBuilder | L-RC | testAppendCharArrayDifferentLengths | wrong oracle: API semantics | shorter array compares as less |
| 17 | EqualsBuilder | L-RC | testReflectionEqualsWithDifferentObjects | wrong oracle: API semantics | objects without fields are reflectively equal |
| 18 | EqualsBuilder | L-RC | testReflectionEqualsWithTestTransients | wrong oracle: parameter semantics | testTransients=true includes transients |
| 19 | ExceptionUtils | S-RC | testGetRootCauseMessageWithNullThrowable_2 | wrong oracle: API semantics | getRootCauseMessage(null) is empty |
| 20 | ExceptionUtils | S-RC | testGetRootCauseWithNoCause_2 | wrong oracle: API semantics | exception without message has null message |
| 21 | ExceptionUtils | S-RC | testHasCause_CauseFound | ambiguous documentation | Javadoc first sentence says 'causal chain'; @return and code check only the throwable itself (pipeline keeps first sentence only) |
| 22 | HashCodeBuilder | L-R | testAppendBooleanArray_Values | wrong oracle: value computation | hash arithmetic |
| 23 | HashCodeBuilder | L-R | testAppendCharArray_Empty | wrong oracle: value computation | empty array leaves the hash unchanged |
| 24 | HashCodeBuilder | L-RC | testAppendArray_IntArray | wrong oracle: value computation | hash arithmetic |
| 25 | HashCodeBuilder | L-RC | testAppendBoolean_NotCovered | wrong oracle: value computation | hash arithmetic |
| 26 | HashCodeBuilder | S-R | testAppendObject | wrong oracle: value computation | identityHashCode of a different object |
| 27 | HashCodeBuilder | S-R | testAppendFloatArray | wrong oracle: value computation | hash arithmetic |
| 28 | HashCodeBuilder | S-R | testAppendBooleanArray | wrong oracle: value computation | hash arithmetic |
| 29 | HashCodeBuilder | S-R | testAppendCharArray | wrong oracle: value computation | hash arithmetic |
| 30 | HashCodeBuilder | S-RC | testReflectionAppendArrayEmpty | wrong oracle: value computation | empty array leaves the hash unchanged |
| 31 | HashCodeBuilder | S-RC | testReflectionAppendNullObject | wrong oracle: value computation | null object leaves the hash unchanged |
| 32 | HashCodeBuilder | S-RC | testIsRegisteredNull | wrong oracle: API semantics | registry is internal; null is not registered |
| 33 | JavaVersion | S-RC | testGetJava11 | wrong oracle: API semantics | "1.1" is JAVA_1_1, not JAVA_11 |
| 34 | JavaVersion | S-RC | testGetJava18Decimal_2 | wrong oracle: API semantics | "1.8" is JAVA_1_8, not JAVA_18 |
| 35 | JavaVersion | S-RC | testGetJava13Decimal_2 | wrong oracle: API semantics | "1.3" is JAVA_1_3, not JAVA_13 |
| 36 | LocaleUtils | L-RC | testLocaleLookupList_withDefaultLocaleAlreadyPresent | wrong oracle: API semantics | lookup list adds the language-only locale |
| 37 | LocaleUtils | L-RC | testLocaleLookupList_withCountryOnly | wrong oracle: API semantics | fr_FR gives [fr_FR, fr] |
| 38 | LocaleUtils | L-RC | testIsISO639LanguageCodeWithInvalidLengthOrUpperCase | test bug | asserts on a helper method defined in the test itself |
| 39 | LocaleUtils | S-R | testLocaleLookupList_nullLocale | wrong oracle: API semantics | localeLookupList(null) is empty |
| 40 | ObjectUtils | L-RC | testReadResolve | wrong oracle: API semantics | Null equality is identity |
| 41 | ObjectUtils | S-RC | testCompare | wrong oracle: parameter semantics | nullGreater only affects null arguments |
| 42 | RandomStringUtils | L-RC | testRandomWithStartEndZeroAndNoLettersOrNumbers | wrong oracle: exception expected | start=end=0 means full range; message invented |
| 43 | RandomStringUtils | S-RC | testRandomAlphabeticWithEmptyRange | wrong oracle: exception expected | count 0 returns empty string |
| 44 | RandomStringUtils | S-RC | testRandomWithStartEndZeroAndNullCharsArray_2_2 | wrong oracle: exception expected | null chars array is allowed |
| 45 | Range | S-RC | testOfWithNullComparator | wrong oracle: API semantics | getComparator never returns null (documented) |
| 46 | ReflectionToStringBuilder | L-R | testToStringWithOutputTransients | wrong oracle: output format | toString format differs |
| 47 | ReflectionToStringBuilder | S-R | testToStringNullObject | wrong oracle: output format | null is rendered as <null> |
| 48 | Validate | L-RC | testNotNaNWithNaNValue | wrong oracle: output format | exception message invented |
| 49 | Validate | S-R | testNotEmptyStringThrowsException | wrong oracle: exception expected | notEmpty accepts whitespace-only strings |
| 50 | Validate | S-RC | testExclusiveBetweenComparable_2 | wrong oracle: exception expected | "B" is strictly between "A" and "C" |
