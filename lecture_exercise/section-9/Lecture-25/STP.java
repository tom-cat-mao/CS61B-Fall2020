/* find the shortest route through the graph */
import java.util.*;

public class STP<T> {
    private Heap<T> heap;
    private Integer[] factor;

    private class Heap<T> {
        private Map<T, Integer>[] heap;
        private int size;

        /* constructor */
        public Heap(int size) {
            heap = new HashMap[size + 1];
            this.size = 0;
        }

        /* insert method */
        public void insert(T vertex, int weight) {
            size++;

            heap[size] = new HashMap<>();
            heap[size].put(vertex, weight);
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
            if (parent.values().iterator().next() > heap[index].values().iterator().next()) {
                swap(index, parent(index));
                insert_helper(parent(index));
            }
        }

        /* swap two nodes */
        public void swap(int index1, int index2) {
            Map<T, Integer> temp = heap[index1];
            heap[index1] = heap[index2];
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
            heap[size] = null;
            size--;

            pop_helper(0);
            return top;
        }

        /* the helper method of pop */
        private void pop_helper(int index) {
            /* if the position is the leaf, return */
            if (index * 2 > size) {
                return;
            }

            /* recussion to find the smaller child */
            int child = smallerchild(index);
            if (heap[index].values().iterator().next().compareTo(heap[child].values().iterator().next()) > 0) {
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
                if (heap[index * 2].values().iterator().next().compareTo(heap[index * 2 + 1].values().iterator().next()) < 0) {
                    return index * 2;
                } else {
                    return index * 2 + 1;
                }
            }
        }
    }

    public STP(Graph<T> graph, T start, T target) {
        heap = new Heap<>(graph.getNumVertices() + 1); // the heap of the vertices
        factor = new Integer[graph.getNumVertices() + 1]; // the factor of the vertices

        /* fill the factor with random number from 1 to 100 */
        Random random = new Random();
        for (int i : factor) {
            factor[i] = random.nextInt(100);
        }

        Integer[] shortestPath = ShortestPath(graph, start, target);
    }

    private Integer[] ShortestPath(Graph<T> graph, T start, T target) {
    }
}