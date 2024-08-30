import java.util.*;

public class Graph<V> {

    /* use a HashMap instance to store the vertices and the edges */
    public Map<V, List<V>> adjList;
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
        for (V vertex : adjList.get(start)) {
            /* if the vertex is not visited, recursively call the DFS_helper method */
            if (!visited.contains(vertex)) {
                if (DFS_helper(vertex, target, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* BFS search for a value */
    public int BFS(V start, V target) {
        List<V> visited = new ArrayList<>();
        return BFS_helper(start, target, visited);
    }

    /* BFS private helper method */
    private int BFS_helper(V start, V target, List<V> visited) {
        int distance = -1;
        Queue<V> queue = new LinkedList<>();

        /* add the start vertex to visited list */
        visited.add(start);

        if (start.equals(target)) {
            return 0;
        }

        /* add the neighbors of the start vertex to the queue */
        for (V vertex : adjList.get(start)) {
            if (!visited.contains(vertex)) {
                queue.add(vertex);
            }
        }

        if (queue.isEmpty()) {
            return distance;
        }

        /* while the queue is not empty */
        while (!queue.isEmpty()) {
            /* get the first vertex in the queue */
            V vertex = queue.poll();

                if (!visited.contains(vertex)) {
                    distance = BFS_helper(vertex, target, visited);
                    if (distance >= 0) {
                        return distance + 1;
                    }
            }
        }

        return distance;

    }

    // Get the neighbors of a node
    public List<V> getNeighbors(V node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    // Get all nodes in the graph
    public Set<V> getNodes() {
        return adjList.keySet();
    }



}