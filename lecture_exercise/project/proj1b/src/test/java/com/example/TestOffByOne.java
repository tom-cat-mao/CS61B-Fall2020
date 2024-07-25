package com.example;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    /* test whether the offbyone method is useable */
    @Test
    public void testoffbyone() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('r', 'q'));
        assertFalse(offByOne.equalChars('a', 'e'));
        /* test the equal condition */
        assertTrue(offByOne.equalChars('z', 'z'));
    }
}
//  Uncomment this class once you've created your CharacterComparator interface and OffByOne class. 