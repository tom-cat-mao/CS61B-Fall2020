package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        /* instansize the comparator */
        OffByOne offByOne = new OffByOne();

        /* test 
         * using the default comparator
         */
        assertTrue(palindrome.isPalindrome("noon", null));
        assertFalse(palindrome.isPalindrome("horse", null));
        assertTrue(palindrome.isPalindrome("a", null));
        
        /* test 
        * using the offByOne comparator
        */
        assertFalse(palindrome.isPalindrome("rancor", offByOne));
        assertTrue(palindrome.isPalindrome("racecar", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertFalse(palindrome.isPalindrome("flakes", offByOne));

    }
}     
// Uncomment this class once you've created your Palindrome class. 