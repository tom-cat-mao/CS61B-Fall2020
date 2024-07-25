package com.example;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<Character>();
        for (char c : word.toCharArray()) {
            deque.addLast(c);;
        }

        return deque;
    }

    /* siPalindrome method 
     * return true if the given word is a palindrome
     * returm false otherwise.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);

        /* use a recusion method to verify whether it is a palindrome */
        return isPalindromeHelper(deque, cc);
    }

    public boolean isPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        /* if it's only one character or no word
         * return true
         */
        if (deque.size() <= 1) {
            return true;
        }

        /* store the first and the last character from the deque */
        char first = deque.removeFirst();
        char last = deque.removeLast();

        /* if you don't what to use a comparator 
         * then use the defaut equal method
         * else use the provided comparator
         */
        if (cc == null) {
            if (first != last) {
                return false;
            }
        } else if (!cc.equalChars(first, last)) {
            return false;
        }

        /* recusion the whole method  */
        return isPalindromeHelper(deque, cc);
    }
}
