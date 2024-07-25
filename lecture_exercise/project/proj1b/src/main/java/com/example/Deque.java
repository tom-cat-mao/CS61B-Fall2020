package com.example;

public interface Deque<T> {
    /* addFirst method add a item in the first place */
    public void addFirst(T item);

    /* addLast method add a itme in the last place */
    public void addLast(T item);

    /* isEmpty method examine whether the array is empty */
    public default boolean isEmpty() {
        return size() == 0;
    }

    /* size method figure out the size of the array */
    public int size();

    /* printDeque method print the whole Deque in the right order*/
    public void printDeque();

    /* removeFirst mothod remove the first item in the array */
    public T removeFirst();

    /* removeLast method remove the last item in the array */
    public T removeLast();

    /* get method get the itme on the index */
    public T get(int index);
}