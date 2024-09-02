import java.util.*;

public class Graph<V> {

    /* use a HashMap instance to store the vertices and the edges */
    private Map<V, Map<V, Integer>>adjList;
    private int numVertices;

    /* first constructor */
    public Graph() {
        adjList = new HashMap<>();
        numVertices = 0;
    }

    /* add a vertex */
    public void addVertex(V vertex) {
        adjList.putIfAbsent(vertex, new HashMap<>());
        numVertices++;
    }

    /* add an edge */
    public void addEdge(V source, V destination, int weight) {
        adjList.get(source).put(destination, weight);
        adjList.get(destination).put(source, weight);
    }

    /* remove a vertex */
    public void removeVertex(V vertex) {
        adjList.values().forEach(e -> e.remove(vertex));
        adjList.remove(vertex);
        numVertices--;
    }

    /* get the number of vertices */
    public int getNumVertices() {
        return numVertices;
    }

    /* DFS search for a value */
    public boolean DFS(V start, V target) {
        List<V> visited = new ArrayList<>();
        return DFS_helper(start, target, visited);
    }

    /* DFS private helper method */
    private boolean DFS_helper(V start, V target, List<V> visited) {
        /* if the start vertex is the target vertex, return true */
        if (start.equals(target)) {
            return true;
        }

        /* add the start vertex to the visited list */
        visited.add(start);

        /* for each vertex in the adjacency list of the start vertex */
        // for (V vertex : adjList.get(start)) {
        //     /* if the vertex is not visited, recursively call the DFS_helper method */
        //     if (!visited.contains(vertex)) {
        //         if (DFS_helper(vertex, target, visited)) {
        //             return true;
        //         }
        //     }
        // }
        for (V vertex : adjList.get(start).keySet()) {
            if (!visited.contains(vertex)) {
                if (DFS_helper(vertex, target, visited)) {
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

            // for (V neighbor : adjList.get(vertex)) {
            //     /* if the neighbor is not visited, add it to the queue */
            //     if (!visited.contains(neighbor)) {
            //         queue.add(neighbor);
            //         visited.add(neighbor);
            //     }
            // }
            for (V neighbor : adjList.get(vertex).keySet()) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return false;
    }

    // Get the neighbors of a node
    public Map<V, Integer> getNeighbors(V key) {
        return adjList.getOrDefault(key, new HashMap<>());
    }

    // Get all nodes in the graph
    public Set<V> getNodes() {
        return adjList.keySet();
    }

    /* Get the distance to the target */
    public int getWeight(V source, V destination) {
        return adjList.get(source).get(destination);
    }
}