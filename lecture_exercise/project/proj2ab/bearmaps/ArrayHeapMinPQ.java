/* the Priority Queue with heap structur
 * the heap is implemented by array
 */
package bearmaps;
import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private static final int DEFAULT_SIZE = 16; // the defalut size of the heap
    private Map<T,Double >[] heap;
    private int size;
    private Map<T, Integer> findIndex; // the find hashmap to find the index of the vertice

    /* constructor */
    public ArrayHeapMinPQ() {
        heap = new HashMap[DEFAULT_SIZE + 1];
        size = 0;
        findIndex = new HashMap<>();
    }

    /* constructor that can insert all the nodes from the graph */
//    public ArrayHeapMinPQ(Graph graph) {
//        findIndex = new HashMap<>();
//        size = 0;
//        heap = new HashMap[graph.getNumVertices() + 1];
//        Set<T> vertices = graph.getNodes();
//
//        for (T v : vertices) {
//            insert(v, Integer.MAX_VALUE);
//        }
//
//        heap[1].put(vertices.iterator().next(), 0);
//        findIndex.put(vertices.iterator().next(), 1);
//    }

    /* get the start vertex
     *  use only in Prime algorithm
     * in order to find the start point
     */
//    public T getStart() {
//        return heap[1].keySet().iterator().next();
//    }

    /* add method */
    @Override
    public void add(T vertex, double weight) {
        if (contains(vertex)) {
            throw new IllegalArgumentException("The vertice is already in the heap");
        }

        /* if the size is smaller than 30% of the heap, resize the heap */
        if (size > heap.length * 0.3) {
            resize();
        }

        size++;

        heap[size] = new HashMap<>();
        heap[size].put(vertex, weight);
        findIndex.put(vertex, size);
        add_helper(size);
    }

    /* if size is smaller than 30% of the heap, resize the heap */
    private void resize() {
        Map<T, Double>[] newHeap = new HashMap[heap.length * 2];
        for (int i = 1; i <= size; i++) {
            newHeap[i] = new HashMap<>(heap[i]);
        }
        heap = newHeap;
    }

    /* return the parent of the index */
    public int parent(int index) {
        return index / 2;
    }

    /* insert help method */
    public void add_helper(int index) {
        /* if the position is root, return */
        if (index == 1) {
            return;
        }

        /* store the parent node */
        Map<T, Double> parent = heap[parent(index)];

        /* if the parent node is greater than the current node, swap */
        if (
            parent.values().iterator().next() >
            heap[index].values().iterator().next()
        ) {
            swap(index, parent(index));
            add_helper(parent(index));
        }
    }

    /* swap two nodes */
    public void swap(int index1, int index2) {
        Map<T, Double> temp = new HashMap<>(heap[index1]);
        findIndex.put(heap[index1].keySet().iterator().next(), index2);
        findIndex.put(heap[index2].keySet().iterator().next(), index1);
        heap[index1] = new HashMap<>(heap[index2]);
        heap[index2] = temp;
    }

    /* remove and return the minimum item */
    @Override
    public T removeSmallest() {
        /* if the heap is empty return null */
        if (size == 0) {
            throw new NoSuchElementException("The heap is empty");
        }

        Map<T, Double> top = new HashMap<>(heap[1]);
        swap(1, size);
        findIndex.remove(heap[size].keySet().iterator().next());
        heap[size] = null;
        size--;

        removeS_helper(1);
        return top.keySet().iterator().next();
    }

    /* the helper method of pop */
    private void removeS_helper(int index) {
        /* if the position is the leaf, return */
        if (index * 2 > size) {
            return;
        }

        /* recursion to find the smaller child */
        int child = smallerchild(index);
        if (
            heap[index].values()
                .iterator()
                .next()
                .compareTo(heap[child].values().iterator().next()) >
            0
        ) {
            swap(index, child);
            removeS_helper(child);
        }
    }

    /* find the smaller child of a node */
    private int smallerchild(int index) {
        /* if there is only one leaf return the leaf */
        if (index * 2 + 1 > size) {
            return index * 2;
        } else {
            /* return the smaller child */
            if (
                heap[index * 2].values()
                    .iterator()
                    .next()
                    .compareTo(heap[index * 2 + 1].values().iterator().next()) <
                0
            ) {
                return index * 2;
            } else {
                return index * 2 + 1;
            }
        }
    }

    /* whether the PQ contain the key */
    @Override
    public boolean contains(T vertice) {
        return findIndex.containsKey(vertice);
    }

    /* find the weight of a specific vertix */
//    public double getWeight(T vertice) {
//        return heap[find(vertice)].values().iterator().next();
//    }

    /* find the specific vertice
     * use the find hashmap, faster
     */
    private int find(T vertice) {
        return findIndex.get(vertice);
    }

    /* change the priority of the value */
    @Override
    public void changePriority(T vertice, double weight) {
        if (!contains(vertice)) {
            throw new NoSuchElementException("The vertice is not in the heap");
        }
        int index = find(vertice);
        if (index == -1) {
            throw new IllegalArgumentException(
                "The vertice is not in the heap"
            );
        }
        heap[index].put(vertice, weight);

        bubbleUp(index);
        bubbleDown(index);
    }

    /* bubble up the node */
    private void bubbleUp(int index) {
        if (index == 1) {
            return;
        }

        if (
            heap[index].values()
                .iterator()
                .next()
                .compareTo(heap[parent(index)].values().iterator().next()) <
            0
        ) {
            swap(index, parent(index));
            bubbleUp(parent(index));
        }
    }

    /* bubble down the node */
    private void bubbleDown(int index) {
        removeS_helper(index);
    }

    /* check the size of the heap */
    @Override
    public int size() {
        return size;
    }

    /* returns the minimum item */
    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("The heap is empty");
        }

        return heap[1].keySet().iterator().next();
    }
}
