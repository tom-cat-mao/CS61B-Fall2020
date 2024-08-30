import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BFSTest {
    private Graph<String> graph;

    @Before
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    public void testBFSComplexGraph() {
        // Add vertices
        for (char c = 'A'; c <= 'Z'; c++) {
            graph.addVertex(String.valueOf(c));
        }

        // Add edges to create a complex graph
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");
        graph.addEdge("D", "F");
        graph.addEdge("E", "G");
        graph.addEdge("F", "H");
        graph.addEdge("G", "I");
        graph.addEdge("H", "J");
        graph.addEdge("I", "K");
        graph.addEdge("J", "L");
        graph.addEdge("K", "M");
        graph.addEdge("L", "N");
        graph.addEdge("M", "O");
        graph.addEdge("N", "P");
        graph.addEdge("O", "Q");
        graph.addEdge("P", "R");
        graph.addEdge("Q", "S");
        graph.addEdge("R", "T");
        graph.addEdge("S", "U");
        graph.addEdge("T", "V");
        graph.addEdge("U", "W");
        graph.addEdge("V", "X");
        graph.addEdge("W", "Y");
        graph.addEdge("X", "Z");

        // Test when the target vertex is reachable with a long path
        assertEquals(25, BFS.shortestDistance(graph, "A", "Z"));

        // Test when the target vertex is not reachable
        graph.addVertex("AA");
        assertEquals(-1, BFS.shortestDistance(graph, "A", "AA"));

        // Test when the start vertex is the same as the target vertex
        assertEquals(0, BFS.shortestDistance(graph, "A", "A"));
    }
}