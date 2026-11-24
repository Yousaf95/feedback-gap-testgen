package org.apache.commons.lang3;

/** Operations on {@link CharSequence} that are {@code null} safe. */
public class CharSequenceUtils {
    private static final int NOT_FOUND = -1;
    static final int TO_STRING_LIMIT = 16;
    /** Returns a new {@link CharSequence} that is a subsequence of this sequence starting with the {@code char} value at the specified index. */
    public static CharSequence subSequence(final CharSequence cs, final int start) {
        return cs == null ? null : cs.subSequence(start, cs.length());
    }
    /** Converts the given CharSequence to a char[]. */
    public static char[] toCharArray(final CharSequence source) {
        final int len = StringUtils.length(source);
        if (len == 0) {
            return ArrayUtils.EMPTY_CHAR_ARRAY;
        }
        if (source instanceof String) {
            return ((String) source).toCharArray();
        }
        final char[] array = new char[len];
        for (int i = 0; i < len; i++) {
            array[i] = source.charAt(i);
        }
        return array;
    }
    /** {@link CharSequenceUtils} instances should NOT be constructed in standard programming. */
    public CharSequenceUtils() {
    }
}