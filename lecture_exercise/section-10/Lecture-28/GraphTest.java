import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import java.util.Set;

public class GraphTest {

    private Graph<String> graph;

    @Before
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    public void testAddVertex() {
        graph.addVertex("A");
        assertEquals(1, graph.getNumVertices());
        assertTrue(graph.getNodes().contains("A"));
    }

    @Test
    public void testAddEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 5);
        assertEquals(1, graph.getNeighbors("A").size());
        assertTrue(graph.getNeighbors("A").containsKey("B"));
        assertEquals(5, graph.getWeight("A", "B"));
    }

    @Test
    public void testRemoveVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 5);
        graph.removeVertex("A");
        assertEquals(1, graph.getNumVertices());
        assertFalse(graph.getNodes().contains("A"));
        assertFalse(graph.getNeighbors("B").containsKey("A"));
    }

    @Test
    public void testGetNumVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        assertEquals(2, graph.getNumVertices());
    }

    @Test
    public void testGetNeighbors() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 5);
        Map<String, Integer> neighbors = graph.getNeighbors("A");
        assertEquals(1, neighbors.size());
        assertTrue(neighbors.containsKey("B"));
        assertEquals(5, (int) neighbors.get("B"));
    }

    @Test
    public void testGetNodes() {
        graph.addVertex("A");
        graph.addVertex("B");
        Set<String> nodes = graph.getNodes();
        assertEquals(2, nodes.size());
        assertTrue(nodes.contains("A"));
        assertTrue(nodes.contains("B"));
    }

    @Test
    public void testGetWeight() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 5);
        assertEquals(5, graph.getWeight("A", "B"));
    }
}