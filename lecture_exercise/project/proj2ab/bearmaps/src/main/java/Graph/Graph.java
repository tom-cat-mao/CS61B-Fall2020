/* this is an improved DAG
 * use private member Edges to store the edges and level of the vertex
 * so it is capable of Topological Sort
 */

package main.java.Graph;

import java.util.*;
import main.java.AStar.*;

public class Graph<V> implements AStarGraph<V> {

    /* use a HashMap instance to store the vertices and the edges */
    private Map<V, Edges> adjList;
    private int numVertices;
    private List<V> level0;

    private class Edges {

        private static final int DEFAULT_LEVEL = 0;
        private Map<V, Integer> edges; // store the edges
        private int level; // store the input edges as level

        /* constructor set the default level to default level */
        public Edges() {
            edges = new HashMap<>();
            level = DEFAULT_LEVEL;
        }

        /* add edges to the source */
        public void addEdge(V destination, int weight) {
            edges.put(destination, weight);
        }

        /* remove the vertex in the edges */
        // public void remove(V vertix) {
        //     edges.remove(vertix);
        // }

        /* increase the level */
        public void increaselevel() {
            level++;
        }

        /* decrease the level */
        public void decreaselevel() {
            level--;
        }

        /* get the weight of the edge */
        public int getWeight(V vertix) {
            return edges.get(vertix);
        }

        /* return the neighbors */
        public Map<V, Integer> getNeighbors() {
            return edges;
        }

        public int getlevel() {
            return level;
        }
    }

    /* first constructor */
    public Graph() {
        adjList = new HashMap<>();
        numVertices = 0;
        level0 = new ArrayList<>();
    }

    /* add a vertex */
    @Override
    public void addVertex(V vertex) {
        adjList.putIfAbsent(vertex, new Edges());
        level0.add(vertex);
        numVertices++;
    }

    /* add an edge */
    @Override
    public void addEdge(V source, V destination, int weight) {
        adjList.get(source).addEdge(destination, weight);
        adjList.get(destination).increaselevel();
        level0.remove(destination); // destination's level is not 0 remove is from the list
    }

    /* remove a vertex */
    @Override
    public void removeVertex(V vertex) {
        // adjList.values().forEach(e -> e.remove(vertex));
        /* get the edges, get the destinations of the edge then decrease the level of each vertix */
        for (V v : adjList.get(vertex).getNeighbors().keySet()) {
            adjList.get(v).decreaselevel();
            /* if level is 0, add the vertex to the level0 list */
            if (getlevel(v) == 0) {
                level0.add(v);
            }
        }

        adjList.remove(vertex);
        numVertices--;
    }

    /* get the number of vertices */
    @Override
    public int getNumVertices() {
        return numVertices;
    }

    // /* DFS search for a value */
    // public boolean DFS(V start, V target) {
    //     List<V> visited = new ArrayList<>();
    //     return DFS_helper(start, target, visited);
    // }

    // /* DFS private helper method */
    // private boolean DFS_helper(V start, V target, List<V> visited) {
    //     /* if the start vertex is the target vertex, return true */
    //     if (start.equals(target)) {
    //         return true;
    //     }

    //     /* add the start vertex to the visited list */
    //     visited.add(start);

    //     /* for each vertex in the adjacency list of the start vertex */
    //     // for (V vertex : adjList.get(start)) {
    //     //     /* if the vertex is not visited, recursively call the DFS_helper method */
    //     //     if (!visited.contains(vertex)) {
    //     //         if (DFS_helper(vertex, target, visited)) {
    //     //             return true;
    //     //         }
    //     //     }
    //     // }
    //     for (V vertex : adjList.get(start).keySet()) {
    //         if (!visited.contains(vertex)) {
    //             if (DFS_helper(vertex, target, visited)) {
    //                 return true;
    //             }
    //         }
    //     }
    //     return false;
    // }

    // /* BFS search for a value */
    // public boolean BFS(V start, V target) {
    //     List<V> visited = new ArrayList<>();
    //     return BFS_helper(start, target, visited);
    // }

    // /* private BFS helper method */
    // private boolean BFS_helper(V start, V target, List<V> visited) {
    //     /* build a queue to store the nodes */
    //     Queue<V> queue = new LinkedList<V>();

    //     /* add the start vertex to the queue */
    //     queue.add(start);

    //     /* while the queue is not empty */
    //     while (!queue.isEmpty()) {
    //         V vertex = queue.poll();

    //         /* if the vertex is the target vertex, return true */
    //         if (vertex.equals(target)) {
    //             return true;
    //         }

    //         // for (V neighbor : adjList.get(vertex)) {
    //         //     /* if the neighbor is not visited, add it to the queue */
    //         //     if (!visited.contains(neighbor)) {
    //         //         queue.add(neighbor);
    //         //         visited.add(neighbor);
    //         //     }
    //         // }
    //         for (V neighbor : adjList.get(vertex).keySet()) {
    //             if (!visited.contains(neighbor)) {
    //                 queue.add(neighbor);
    //                 visited.add(neighbor);
    //             }
    //         }
    //     }

    //     return false;
    // }

    // Get the neighbors of a node
    @Override
    public List<WeightedEdge<V>> neighbors(V current) {
        Map<V, Integer> neighbors = adjList.get(current).getNeighbors();
        List<WeightedEdge<V>> result = new ArrayList<>();

        for (V key : neighbors.keySet()) {
            result.add(new WeightedEdge<>(current, key, neighbors.get(key)));
        }

        return result;
    }

    /* estimate the distance to the goal */
    @Override
    public double estimatedDistanceToGoal(V current, V goal) {
        return 0;
    }

    // Get all nodes in the graph
    @Override
    public Set<V> getNodes() {
        return adjList.keySet();
    }

    /* Get the distance to the target */
    @Override
    public int getWeight(V source, V destination) {
        return adjList.get(source).getWeight(destination);
    }

    /* get the level of a specific vertex */
    public int getlevel(V vertex) {
        return adjList.get(vertex).getlevel();
    }
    //public List<V> getlevel0() {
    //return level0;
    //}
}
