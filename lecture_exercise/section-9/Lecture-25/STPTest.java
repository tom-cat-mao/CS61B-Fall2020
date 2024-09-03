import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class STPTest {

    private Graph<String> graph;
    private STP<String> stp;

    @Before
    public void setUp() {
        graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addEdge("A", "B", 7);
        graph.addEdge("A", "C", 9);
        graph.addEdge("A", "D", 14);
        graph.addEdge("B", "C", 1);
        graph.addEdge("C", "D", 1);
        graph.addEdge("B", "E", 6);
        graph.addEdge("B", "F", 7);
        graph.addEdge("C", "F", 10);
        graph.addEdge("D", "F", 2);
        graph.addEdge("E", "F", 11);
        graph.addEdge("F", "G", 6);
        graph.addEdge("G", "H", 1);
        graph.addEdge("D", "G", 15);
        graph.addEdge("D", "H", 9);

        stp = new STP<>(graph, "A", "G");
        return;
    }

    @Test
    public void testShortestPath() {
        Map<String, Integer> expectedDistances = new HashMap<>();
        expectedDistances.put("A", 0);
        expectedDistances.put("B", 1);
        expectedDistances.put("C", 3);
        expectedDistances.put("D", 4);

//        for (Map.Entry<String, Integer> entry : expectedDistances.entrySet()) {
//            assertEquals(entry.getValue(), stp.getDistance(entry.getKey()));
//        }
    }

    @Test
    public void testPrintPath() {
        String expectedOutput = "D <- C <- B <- A\n";
//        assertEquals(expectedOutput, stp.getPath("A", "D"));
    }
}
