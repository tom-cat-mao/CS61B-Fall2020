package com.example;

/* x and y are equal if they are off by N from each other */
public class OffByN implements CharacterComparator{
    int N; // the difference between x and y

    /* the constructor that use the distance of x and y as parameter */
    public OffByN(int N) {
        this.N = N;
    }

    /* if the absolute result of x - y is N or 0
     * then x and y are equal in this comparator
     */
    public boolean equalChars(char x, char y) {
        return (Math.abs(x - y) == N || Math.abs(x - y) == 0);
    }
}
