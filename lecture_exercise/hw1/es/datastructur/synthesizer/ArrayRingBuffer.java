package es.datastructur.synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {

    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = last = fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (fillCount == rb.length) {
            throw new RuntimeException("Ring buffer overflow");
        }

        rb[last] = x;
        last = (last + 1) % rb.length;
        fillCount++;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }

        T item = rb[first];
        rb[first] = null;
        first = (first + 1) % rb.length;
        fillCount--;
        return item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

    /**
     * Return the size of the buffer.
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * Return the number of items currently in the buffer.
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * the iterator method
     */
    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    /**
     * The iterator class
     */
    private class BufferIterator implements Iterator<T> {

        private int ptr;
        private int count;

        public BufferIterator() {
            ptr = first;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < fillCount;
        }

        @Override
        public T next() {
            T item = rb[ptr];
            ptr = (ptr + 1) % rb.length;
            count++;
            return item;
        }
    }

    /**
     * the equals method for the buffer
     */
    @Override
    public boolean equals(Object o) {
        // check if the object is null or not the same class
        if (o == null) {
            return false;
        }
        // check if the object is the same class
        if (o.getClass() != this.getClass()) {
            return false;
        }

        // cast the object to the ArrayRingBuffer
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        // then check if the fillCount is the same
        if (other.fillCount() != this.fillCount()) {
            return false;
        }

        // check if the elements are the same
        // by iterating through the buffer
        Iterator<T> thisIter = this.iterator();
        Iterator<T> otherIter = other.iterator();
        while (thisIter.hasNext()) {
            if (!thisIter.next().equals(otherIter.next())) {
                return false;
            }
        }
        return true;
    }
}
