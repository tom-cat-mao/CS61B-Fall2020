/* find the shortest route through the graph */
import java.util.*;

public class STP<T> {

    private Heap<T> heap;
    private Integer[] factor;
    private Map<T, T> path;

    private class Heap<T> {

        private Map<T, Integer>[] heap;
        private int size;
        private Map<T, Integer> findIndex;

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

            findIndex.remove(heap[1].keySet().iterator().next());
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
                        .compareTo(
                            heap[index * 2 + 1].values().iterator().next()
                        ) <
                    0
                ) {
                    return index * 2;
                } else {
                    return index * 2 + 1;
                }
            }
        }

        /* find the specific vertice
         * use the find hashmap, faster
         */
        public int find(T vertice) {
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
    }

    public STP(Graph<T> graph, T start, T target) {
        heap = new Heap<>(graph.getNumVertices() + 1); // the heap of the vertices
        factor = new Integer[graph.getNumVertices() + 1]; // the factor of the vertices

        /* fill the factor with random number from 1 to 100 */
        // Random random = new Random();
        // for (int i : factor) {
        //     factor[i] = random.nextInt(100);
        // }

        ShortestPath(graph, start, target);
        printPath(start, target);
    }

    /* find the shortest path */
    private void ShortestPath(Graph<T> graph, T start, T target) {
        Set<T> flutter = graph.getNodes();
        Map<T, Integer> distance = new HashMap<>();

        /* put all the nodes into the flutter
         * and initialize the start node priority
         */
        for (T i : flutter) {
            if (i.equals(start)) {
                heap.insert(i, 0);
            } else {
                heap.insert(i, Integer.MAX_VALUE);
            }
        }

        while (!flutter.isEmpty()) {
            Map<T, Integer> current = heap.pop();
            T key = current.keySet().iterator().next();
            relax(graph, key, distance);
        }
    }

    /* relax all the edges */
    private void relax(Graph<T> graph, T current, Map<T, Integer> distance) {
        for (T i : graph.getNeighbors(current).keySet()) {
            int weight = graph.getWeight(current, i);
            if (distance.get(i) > distance.get(current) + weight) {
                distance.put(i, distance.get(current) + weight);
                path.put(i, current);
                heap.changePriority(i, distance.get(i));
            }
        }
    }

    /* print out the path */
    public void printPath(T start, T target) {
        T current = target;
        while (!current.equals(start)) {
            System.out.print(current + " <- ");
            current = path.get(current);
        }
        System.out.println(start);
    }
}
