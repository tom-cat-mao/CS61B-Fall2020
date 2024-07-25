package com.example;

import org.junit.Test;

/* test the offbyN method 
 * (maybe it can replace the offbyOne method)
 */
public class TestOffByN {
    
    @Test
    public void testOffByN() {
        OffByN offBy5 = new OffByN(5);
        org.junit.Assert.assertTrue(offBy5.equalChars('a', 'f'));
        org.junit.Assert.assertTrue(offBy5.equalChars('f', 'a'));
        org.junit.Assert.assertFalse(offBy5.equalChars('f', 'h'));
        org.junit.Assert.assertTrue(offBy5.equalChars('f', 'f'));
    }
}
