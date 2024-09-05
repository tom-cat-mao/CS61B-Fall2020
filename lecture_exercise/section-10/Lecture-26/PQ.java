import java.util.*;

public class PQ<T> {

    private Map<T, Integer>[] heap;
    private int size;
    private Map<T, Integer> findIndex;

    /* constructor */
    public PQ(int size) {
        heap = new HashMap[size + 1];
        this.size = 0;
        findIndex = new HashMap<>();
    }

    /* constructor that can insert all the nodes from the graph */
    public PQ(Graph graph) {
        findIndex = new HashMap<>();
        size = 0;
        heap = new HashMap[graph.getNumVertices() + 1];
        Set<T> vertices = graph.getNodes();

        for (T v : vertices) {
            insert(v, Integer.MAX_VALUE);
        }

        heap[1].put(vertices.iterator().next(), 0);
        findIndex.put(vertices.iterator().next(), 1);
    }

    /* get the start vertex
     *  use only in Prime algorithm
     * in order to find the start point
     */
//    public T getStart() {
//        return heap[1].keySet().iterator().next();
//    }

    /* insert method */
    public void insert(T vertex, int weight) {
        size++;

        heap[size] = new HashMap<>();
        heap[size].put(vertex, weight);
        findIndex.put(vertex, size);
        insert_helper(size);
    }

    /* return the parent of the index */
    public int parent(int index) {
        return index / 2;
    }

    /* insert help method */
    public void insert_helper(int index) {
        /* if the position is root, return */
        if (index == 1) {
            return;
        }

        /* store the parent node */
        Map<T, Integer> parent = heap[parent(index)];

        /* if the parent node is greater than the current node, swap */
        if (
            parent.values().iterator().next() >
            heap[index].values().iterator().next()
        ) {
            swap(index, parent(index));
            insert_helper(parent(index));
        }
    }

    /* swap two nodes */
    public void swap(int index1, int index2) {
        Map<T, Integer> temp = new HashMap<>(heap[index1]);
        findIndex.put(heap[index1].keySet().iterator().next(), index2);
        findIndex.put(heap[index2].keySet().iterator().next(), index1);
        heap[index1] = new HashMap<>(heap[index2]);
        heap[index2] = temp;
    }

    /* pop the top node */
    public Map<T, Integer> pop() {
        /* if the heap is empty return null */
        if (size == 0) {
            return null;
        }

        Map<T, Integer> top = new HashMap<>(heap[1]);
        swap(1, size);
        findIndex.remove(heap[size].keySet().iterator().next());
        heap[size] = null;
        size--;

        pop_helper(1);
        return top;
    }

    /* the helper method of pop */
    private void pop_helper(int index) {
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
            pop_helper(child);
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
    public boolean containkey(T vertice) {
        return findIndex.containsKey(vertice);
    }

    /* find the weight of a specific vertix */
    public int getWeight(T vertice) {
        return heap[find(vertice)].values().iterator().next();
    }

    /* find the specific vertice
     * use the find hashmap, faster
     */
    private int find(T vertice) {
        return findIndex.get(vertice);
    }

    /* change the priority of the value */
    public void changePriority(T vertice, int weight) {
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
    public void bubbleUp(int index) {
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
    public void bubbleDown(int index) {
        pop_helper(index);
    }

    /* check the size of the heap */
    public int size() {
        return size;
    }
}
