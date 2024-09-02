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
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 4);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 1);

        stp = new STP<>(graph, "A", "D");
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