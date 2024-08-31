import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {
    private Graph<Integer> graph;

    @Before
    public void setUp() {
        graph = new Graph<>();
        // Add vertices
        for (int i = 1; i <= 10; i++) {
            graph.addVertex(i);
        }
        // Add edges
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);
        graph.addEdge(9, 10);
    }

    @Test
    public void testDFSReachable() {
        assertTrue(graph.DFS(1, 10)); // Path exists
    }

    @Test
    public void testDFSNotReachable() {
        graph.removeEdge(6, 7); // Disconnect part of the graph
        assertFalse(graph.DFS(1, 10)); // Path does not exist
    }

    @Test
    public void testDFSStartEqualsTarget() {
        assertTrue(graph.DFS(1, 1)); // Start and target are the same
    }

    @Test
    public void testDFSDisconnectedGraph() {
        Graph<Integer> disconnectedGraph = new Graph<>();
        disconnectedGraph.addVertex(1);
        disconnectedGraph.addVertex(2);
        assertFalse(disconnectedGraph.DFS(1, 2)); // No edges, hence no path
    }
}