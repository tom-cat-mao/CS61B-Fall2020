/* Use BST to implement Jeap */

public class HeapBST<T extends Comparable<T>> {

    /* use array to represent the position more convinent */
    private T[] heap;
    private int size;

    /* constructor */
    public HeapBST(int capacity) {
        heap = (T[]) new Comparable[capacity];
        size = 0;
    }

    /* public insert method */
    public void insert(T data) {
        size++;
        /* append the data to the last place */
        heap[size] = data;

        /* use a private method to reset the position */
        insert_helper(size);
    }

    /* private insert helper method */
    private void insert_helper(int position) {
        /* if the position is the root, return */
        if (position == 1) {
            return;
        }

        if (heap[parent(position)].compareTo(heap[position]) > 0) {
            swap(position, parent(position));
            insert_helper(parent(position));
        }
    }

    /* return the parent of current position */
    private int parent(int position) {
        return position / 2;
    }

    /* swap the element in the two position */
    private void swap(int position1, int position2) {
        T temp = heap[position1];
        heap[position1] = heap[position2];
        heap[position2] = temp;
    }

    /* public pop method */
    public T pop() {
        /* if the heap is empty, return null */
        if (size == 0) {
            return null;
        }

        /* pop out the result
         * then swap the value and change the size
         */
        T result = heap[1];
        swap(1, size);
        heap[size] = null;
        size--;

        /* use a private pop helper to reset the array */
        pop_helper(1);
        return result;
    }

    /* private pop helper method */
    private void pop_helper(int position) {
        /* if the position is the leaf, return */
        if (position * 2 > size) {
            return;
        }

        /* find the smaller child */
        int child = smallerChild(position);
        if (heap[position].compareTo(heap[child]) > 0) {
            swap(position, child);
            pop_helper(child);
        }
    }

    /* return the smaller child */
    private int smallerChild(int position) {
        if (position * 2 + 1 > size) {
            return position * 2;
        } else {
            if (heap[position * 2].compareTo(heap[position * 2 + 1]) < 0) {
                return position * 2;
            } else {
                return position * 2 + 1;
            }
        }
    }

    /* print the heap */
    public void print() {
        for (int i = 1; i <= size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
