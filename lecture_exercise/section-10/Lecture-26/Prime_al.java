/* the Prime algrithm to find the shortest tree */
import java.util.*;

public class Prime_al<T> {
    private PQ<T> pq; // the priority queue to store the vertex
    private Map<T, T> parent; // the parent of the vertex

    public Prime_al() {
        parent = new HashMap<T, T>();
    }

    public void find_ST(Graph<T> graph) {
        pq = new PQ<>(graph);
        /* get the first value randomly */
        T current = pq.pop().keySet().iterator().next();

        /* unless there are vertices in the PQ
         * continue to relax the edges
         */
        while(pq.size() != 0) {
            current = relax(graph, current);
        }
    }
    
    /* relax all the edges */
    private T relax(Graph<T> graph, T current) {
        /* list the neighbors into a set */
        for (T neighbor : graph.getNeighbors(current).keySet()) {
            /* get the weight of a neighbor */
            int weight = graph.getWeight(current, neighbor);

            /* if the priority queue has the neighbor
            * find out if the weight is less than the current weight
            * if so, change the priority 
            */
            if (pq.containkey(neighbor) && weight < pq.getWeight(neighbor)) {
                pq.changePriority(neighbor, weight);
                parent.put(neighbor, current);
            }
        }

        /* find out the smallest vertix in the PQ */
        return pq.pop().keySet().iterator().next();
    }

    /* get the parent of the vertex */
    public T getParent(T vertex) {
        return parent.get(vertex);
    }
}