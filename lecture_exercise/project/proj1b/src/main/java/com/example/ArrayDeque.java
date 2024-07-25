package com.example;

public class ArrayDeque<T> implements Deque<T> {
    private T[] array;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        size = 0;
        array = (T[]) new Object[8];
        nextFirst = 7;
        nextLast = 0;
    }

    /* justify if the array should be resize */
    public boolean isOversize() {
        return size >= array.length / 4;
    }

    /* 4 times the current array */
    public void resizearray() {
        T[] newArray = (T[]) new Object[array.length * 4];

        /* copy all the items which was added from first to the newArray */
        System.arraycopy(this.array, nextFirst + 1, newArray, newArray.length - array.length + nextFirst + 1, array.length - nextFirst - 1);
        // for (int i = array.length - 1; i > nextFirst; i--) {
        //     newArray[newArray.length - array.length + i] = array[i];
        // }

        /* copy all the items whilch was added from last to the newArray */
        System.arraycopy(this.array, 0, newArray, 0, nextLast);

        /* reset the nextFirst place */
        nextFirst = newArray.length - array.length + nextFirst;

        array = newArray;

    }

    @Override
    public void addFirst(T item) {
        /* we need to justify whether the array is oversized
         * the whole size should be at most 25% of the array length
        */
        if (isOversize()) {
            resizearray();
        }

        array[nextFirst] = item;
        nextFirst--;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (isOversize()) {
            resizearray();
        }

        array[nextLast] = item;
        nextLast++;
        size++;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        if (nextFirst == array.length - 1) {
            nextFirst = -1;
        }
        nextFirst++;
        T item = array[nextFirst];
        array[nextFirst] = null;
        size--;
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (nextLast == 0) {
            nextLast = array.length;
        }
        nextLast--;
        T item = array[nextLast];
        array[nextLast] = null;
        size--;
        return item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        else if (array.length - nextFirst - 1 > index) {
            return array[1 + nextFirst + index];
        }
        else {
            return array[index - (array.length - nextFirst - 1)];
        }
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}