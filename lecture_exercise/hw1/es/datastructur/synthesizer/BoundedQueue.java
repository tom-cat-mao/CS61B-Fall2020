package es.datastructur.synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    int capacity(); // Returns the capacity of this BoundedQueue.
    int fillCount(); // Returns the number of items currently in this BoundedQueue.
    void enqueue(T x); // Adds an item x to the rear of this BoundedQueue.
    T dequeue(); // Removes and returns the item at the front of this BoundedQueue.
    T peek(); // Returns the item at the front of this BoundedQueue.
    Iterator<T> iterator(); // Returns an iterator for this BoundedQueue.
    boolean equals(Object o); // Returns true if the argument is the same object as this BoundedQueue.

    /* is the buffer empty (fillCount equals zero) ? */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /* is the buffer full (fillCount is same as capacity) ? */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
