package org.apache.commons.lang3;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class BooleanUtils_GenTest {
  @Test void negateTrue() { assertEquals(Boolean.FALSE, BooleanUtils.negate(Boolean.TRUE)); }
  @Test void toBooleanYes() { assertTrue(BooleanUtils.toBoolean("yes")); }
}
