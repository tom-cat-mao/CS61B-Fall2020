import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/* make the private instance variables in the Graph class public for testing purposes */
public class GraphTest {
    private Graph<String> graph;

    @Before
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    public void testAddVertex() {
        graph.addVertex("A");
        assertTrue(graph.adjList.containsKey("A"));
    }

    @Test
    public void testAddEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        assertTrue(graph.adjList.get("A").contains("B"));
        assertTrue(graph.adjList.get("B").contains("A"));
    }

    @Test
    public void testRemoveVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.removeVertex("A");
        assertFalse(graph.adjList.containsKey("A"));
        assertFalse(graph.adjList.get("B").contains("A"));
    }

    @Test
    public void testPrintGraph() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.printGraph(); // This will print the graph to the console
    }

    @Test
    public void testnumVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
        graph.addVertex("J");
        assertEquals(10, graph.getNumVertices());
    }

    @Test
    public void testDFS() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");

        // Test when the target vertex is reachable
        assertTrue(graph.DFS("A", "E"));

        // Test when the target vertex is not reachable
        assertFalse(graph.DFS("A", "F"));

        // Test when the start vertex is the same as the target vertex
        assertTrue(graph.DFS("A", "A"));
    }
    @Test
    public void testBFS() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");

        // Test when the target vertex is reachable
        assertEquals(2, graph.BFS("A", "E"));

        // Test when the target vertex is not reachable
        assertEquals(-1, graph.BFS("A", "F"));

        // Test when the start vertex is the same as the target vertex
        assertEquals(0, graph.BFS("A", "A"));
    }

   @Test
    public void testBFSComplexGraph() {
        // Create a more complex graph
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
        graph.addVertex("J");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");
        graph.addEdge("C", "G");
        graph.addEdge("D", "H");
        graph.addEdge("E", "I");
        graph.addEdge("F", "J");

        // Test when the target vertex is reachable with multiple paths
        assertEquals(true, graph.BFS("A", "H")); // A -> B -> D -> H

        // Test when the target vertex is not reachable
        assertEquals(false, graph.BFS("A", "Z")); // Z does not exist in the graph

        // Test when the start vertex is the same as the target vertex
        assertEquals(true, graph.BFS("A", "A"));

        // Test when the target vertex is reachable with a longer path
        assertEquals(true, graph.BFS("A", "J")); // A -> C -> F -> J
    }
}