import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class Prime_alTest {

    private Prime_al<Integer> primeAl;
    private Graph<Integer> graph;

    @Before
    public void setUp() {
        primeAl = new Prime_al<>();
        graph = new Graph<>();

        // Add nodes to the graph
        for (int i = 1; i <= 10; i++) {
            graph.addVertex(i);
        }

        // Add edges to the graph
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 8);
        graph.addEdge(2, 3, 9);
        graph.addEdge(2, 4, 10);
        graph.addEdge(3, 5, 1);
        graph.addEdge(4, 5, 7);
        graph.addEdge(4, 6, 2);
        graph.addEdge(5, 6, 6);
        graph.addEdge(5, 7, 11);
        graph.addEdge(6, 7, 14);
        graph.addEdge(6, 8, 3);
        graph.addEdge(7, 8, 5);
        graph.addEdge(7, 9, 12);
        graph.addEdge(8, 9, 15);
        graph.addEdge(8, 10, 13);
        graph.addEdge(9, 10, 16);
    }

    @Test
    public void testFindST() {
        primeAl.find_ST(graph);

        // Verify the parent relationships in the MST
        Map<Integer, Integer> expectedParents = new HashMap<>();
        expectedParents.put(2, 1);
        expectedParents.put(3, 1);
        expectedParents.put(4, 6);
        expectedParents.put(5, 3);
        expectedParents.put(6, 5);
        expectedParents.put(7, 8);
        expectedParents.put(8, 6);
        expectedParents.put(9, 7);
        expectedParents.put(10, 8);

        for (Map.Entry<Integer, Integer> entry : expectedParents.entrySet()) {
            assertEquals(entry.getValue(), primeAl.getParent(entry.getKey()));
        }
    }
}