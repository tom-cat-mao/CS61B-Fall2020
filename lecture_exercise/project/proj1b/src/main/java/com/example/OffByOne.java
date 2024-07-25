package com.example;

public class OffByOne implements CharacterComparator {

    /* if the absolute number of x minus y is less than 1
     * then they are off by one
     * so they are equal number in this comparator
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) <= 1;
    }
}