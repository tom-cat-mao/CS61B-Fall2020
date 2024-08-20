package Lecture-21;

import org.java.comparable.Comparable;

/* this is a Heap simulation */
public class HeapBST<T extends Comparable<T>> {
    /* the size of the whole heap
     * the root node of the heap
     */
    private int size;
    private HeapNode<T> root;

    public HeapBST() {
        this.size = 0;
        this.root = new HeapNode<T>(null);
    }

    /* the node of the Heap */
    private class HeapNode<T> {
        T data;
        HeapNode<T> left;
        HeapNode<T> right;

        /* the constructor */
        public HeapNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /* the insert method */
    public void insert(T data) {
        root = insert_p(root, data);
        return;
    }

    /* the private reverse insert method */
    private HeapNode<T> insert_p(HeapNode<T> root, T data) {
        int index = size;
        root = append(root, data, index);
    }

    /* append the data to the end  */
    private HeapBST<T> append(HeapBST<T> root, T data, int index) {
        if (root == null) {
            return new HeapBST.HeapNode(data);
        }
    }
}
