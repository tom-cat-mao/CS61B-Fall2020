/* Preorder and Postorder for DFS */
import java.util.*;

public class Graph<V> {

    /* use a HashMap instance to store the vertices and the edges */
    private Map<V, List<V>> adjList;
    private int numVertices;

    /* first constructor */
    public Graph() {
        adjList = new HashMap<>();
        numVertices = 0;
    }

    /* add a vertex */
    public void addVertex(V vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
        numVertices++;
    }

    /* add an edge */
    public void addEdge(V source, V destination) {
        adjList.get(source).add(destination);
        adjList.get(destination).add(source);
    }

    /* remove a vertex */
    public void removeVertex(V vertex) {
        adjList.values().forEach(e -> e.remove(vertex));
        adjList.remove(vertex);
        numVertices--;
    }

    /* print the graph */
    public void printGraph() {
        for (V vertex : adjList.keySet()) {
            System.out.print(vertex + " -> ");
            for (V edge : adjList.get(vertex)) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }

    /* get the number of vertices */
    public int getNumVertices() {
        return numVertices;
    }

    /* DFS search for a value */
    public boolean DFS(V start, V target) {
        List<V> visited = new ArrayList<>();
        System.out.println("DFS starting from: " + start);
        return DFS_helper(start, target, visited);
    }

    /* DFS private helper method */
    private boolean DFS_helper(V start, V target, List<V> visited) {
        /* if the start vertex is the target vertex, return true */
        if (start.equals(target)) {
            System.out.println("Found target: " + target);
            return true;
        }

        /* add the start vertex to the visited list */
        visited.add(start);

        /* for each vertex in the adjacency list of the start vertex */
        for (V vertex : adjList.get(start)) {
            /* if the vertex is not visited, recursively call the DFS_helper method */
            if (!visited.contains(vertex)) {
                /* Preorder */
                System.out.println("Visiting: " + vertex);
                if (DFS_helper(vertex, target, visited)) {
                    /* Postorder */
                    // System.out.println("Visiting: " + vertex);
                    return true;
                }
            }
        }
        return false;
    }

    /* BFS search for a value */
    public boolean BFS(V start, V target) {
        List<V> visited = new ArrayList<>();
        return BFS_helper(start, target, visited);
    }

    /* private BFS helper method */
    private boolean BFS_helper(V start, V target, List<V> visited) {
        /* build a queue to store the nodes */
        Queue<V> queue = new LinkedList<V>();

        /* add the start vertex to the queue */
        queue.add(start);

        /* while the queue is not empty */
        while (!queue.isEmpty()) {
            V vertex = queue.poll();

            /* if the vertex is the target vertex, return true */
            if (vertex.equals(target)) {
                return true;
            }

            for (V neighbor : adjList.get(vertex)) {
                /* if the neighbor is not visited, add it to the queue */
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return false;
    }

    // Get the neighbors of a node
    public List<V> getNeighbors(V node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    // Get all nodes in the graph
    public Set<V> getNodes() {
        return adjList.keySet();
    }

    public void removeEdge(V source, V destination) {
    List<V> sourceNeighbors = adjList.get(source);
    List<V> destinationNeighbors = adjList.get(destination);
    if (sourceNeighbors != null) {
        sourceNeighbors.remove(destination);
    }
    if (destinationNeighbors != null) {
        destinationNeighbors.remove(source);
    }
}
}
