package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {

    @Test
    public void testAssertions() {
        // Assert equals
        assertEquals(5, 2 + 3);

        // Assert true
        assertTrue(5 > 3);

        // Assert false
        assertFalse(5 < 3);

        // Assert null
        assertNull(null);

        // Assert not null
        assertNotNull(new Object());
    }

    @Test
    public void testAdditionalAssertions() {
        // Assert String equality
        assertEquals("JUnit Assertion", "JUnit " + "Assertion");

        // Assert Same vs Not Same (reference equality)
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");

        assertSame(str1, str2);
        assertNotSame(str1, str3);

        // Assert Array Equals
        int[] expectedArray = {1, 2, 3};
        int[] actualArray = {1, 2, 3};
        assertArrayEquals(expectedArray, actualArray);
    }
}
