/* insert a couple of vertix into the unit
 * check whether it has been connected
 */
import java.util.*;

public class WQU<T> {

    /* store the value and its parent */
    private Map<T, T> parent;
    public Map<T, Integer> weight;
    private int connected_vertices;

    /* the constructor that add all the vertix into the Map */
    public WQU(Graph graph) {
        connected_vertices = 0;
        parent = new HashMap<>();
        weight = new HashMap<>();

        Set<T> vertices = graph.getNodes();

        for (T v : vertices) {
            parent.put(v, null);
            weight.put(v, 0);
        }
    }

    /* union the two vertices that provided */
    public boolean union(T v1, T v2) {
        /* if both vertices don't have weight
         * then set the weight of v2 to 1
         * and connect v1 to v2
         */
        T root1 = find(v1);
        T root2 = find(v2);

        if (weight.get(root1) == 0 && weight.get(root2) == 0) {
            weight.put(v2, 1);
            parent.put(v1, v2);
            connected_vertices += 2;
            return true;
        }

        /* if they have the same roots
         * it won't be connected
         * as it is a cycle
         */
        if (root1.equals(root2)) {
            return false;
        }

        /* find the heavier tree and connect the other tree to it
         * and update the weight of the root
         */
        if (weight.get(root1) > weight.get(root2)) {
            parent.put(root2, root1);
            weight.put(root1, weight.get(root1) + weight.get(root2));
            weight.put(root2, 0);
        } else {
            parent.put(root1, root2);
            weight.put(root2, weight.get(root1) + weight.get(root2));
            weight.put(root1, 0);
        }

        connected_vertices++;
        return true;
    }

    /* find the root of the vertix */
    public T find(T v) {
        T root = v;
        while (parent.get(root) != null) {
            root = parent.get(root);
        }

        return root;
    }

    /* find out whether two vertices are connected */
    public boolean connected(T v1, T v2) {
        return find(v1).equals(find(v2));
    }

    /* return the number of the connected vetices */
    public int count() {
        return connected_vertices;
    }
}
