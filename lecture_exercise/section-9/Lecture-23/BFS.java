import java.util.*;

public class BFS {
    // Method to find the shortest distance from startNode to targetNode
    public static <T> int shortestDistance(Graph<T> graph, T startNode, T targetNode) {
        if (startNode.equals(targetNode)) {
            return 0;
        }

        Map<T, Integer> distances = new HashMap<>();
        Queue<T> queue = new LinkedList<>();

        // Initialize distances
        for (T node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(startNode, 0);

        queue.add(startNode);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            int currentDistance = distances.get(current);

            for (T neighbor : graph.getNeighbors(current)) {
                if (distances.get(neighbor) == Integer.MAX_VALUE) { // Not visited
                    queue.add(neighbor);
                    distances.put(neighbor, currentDistance + 1);

                    if (neighbor.equals(targetNode)) {
                        return distances.get(neighbor);
                    }
                }
            }
        }

        return -1; // If targetNode is not reachable
    }
}
