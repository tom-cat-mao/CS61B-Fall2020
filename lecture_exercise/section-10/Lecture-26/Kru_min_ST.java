import java.util.*;
import javax.swing.table.TableColumn;

public class Kru_min_ST<T> {

    private Set<Edge<T>> edges; // store the edges in order
    private WQU<T> wqu; // store the vertices and the parent
    private Map<T, Map<T, Integer>> min_ST; // store the shortest tree

    /* store the vertices and the edge */
    private class Edge<T> {
        private T[] vertices;
        private int weight;

        public Edge(T a, T b, int weight) {
            vertices = (T[]) new Object[2];
            vertices[0] = a;
            vertices[1] = b;
            this.weight = weight;
        }

        /* return the vertices between the edge */
        public T[] getvertices() {
            return vertices;
        }

        /* return the weight of the edge */
        public int getWeight() {
            return weight;
        }
    }

    /* the edge comparator that compare the edge instance */
    class EdgeComparator implements Comparator<Edge<T>> {

        @Override
        public int compare(Edge<T> a, Edge<T> b) {
            if (a.weight < b.weight) {
                return -1;
            }

            return 1;
        }
    }
    
    /* constructor */
    public Kru_min_ST() {
        edges = new TreeSet<>(new EdgeComparator());
    }

    /* add all the edges to the set */
    public void addall(Graph graph) {
        Set<T> vertices = graph.getNodes();
        Set<T> visited = new HashSet<>();
        for (T v : vertices) {
            Set<T>  neighbors = graph.getNeighbors(v).keySet();
            visited.add(v);
            for (T n : neighbors) {
                if (visited.contains(n)) {
                    continue;
                }
                int weight = graph.getWeight(v, n);
                addEdge(v, n, weight);
            }
        }
    }

    /* add edges to the treeset */
    public void addEdge(T a, T b, int weight) {
        edges.add(new Edge<T>(a, b, weight));
    }

    /* find the shortest weight tree in the graph */
    public void find_min_ST(Graph graph) {
        /* add all the edges to the set in order */
        addall(graph);
        wqu = new WQU<>(graph);
        min_ST = new HashMap<>();

        for (Edge<T> e : edges) {
            /* define the break condition */
            if (wqu.count() == graph.getNumVertices()) {
                break;
            }

            /* find out wether union is true */
            if (wqu.union(e.getvertices()[0], e.getvertices()[1])) {
                /* put the shortest way to the HashMap */
                min_ST.putIfAbsent(e.getvertices()[0], new HashMap<>());
                min_ST.get(e.getvertices()[0]).put(e.getvertices()[1], e.getWeight());
            }
        }
    }

    /* print the shortest Map */
    public void print_ST() {
        for (T v : min_ST.keySet()) {
            for (T n : min_ST.get(v).keySet()) {
                System.out.println(v + " -> " + n + " : " + min_ST.get(v).get(n));
            }
        }

    }
}