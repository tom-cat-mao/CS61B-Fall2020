import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class Kru_min_STTest {

    private Kru_min_ST<Integer> kruMinST;
    private Graph<Integer> graph;

    @Before
    public void setUp() {
        kruMinST = new Kru_min_ST<>();
        graph = new Graph<>();

        // Add nodes and edges to the graph
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(1, 2, 10);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 15);
        graph.addEdge(4, 1, 20);
    }

    @Test
    public void testAddEdge() {
        kruMinST.addEdge(1, 2, 10);
        kruMinST.addEdge(2, 3, 5);
        kruMinST.addEdge(3, 4, 15);
        kruMinST.addEdge(4, 1, 20);

        Set<Kru_min_ST<Integer>.Edge<Integer>> edges = kruMinST.edges;
        assertEquals(4, edges.size());

        Iterator<Kru_min_ST<Integer>.Edge<Integer>> iterator = edges.iterator();
        assertEquals(5, iterator.next().weight);
        assertEquals(10, iterator.next().weight);
        assertEquals(15, iterator.next().weight);
        assertEquals(20, iterator.next().weight);
    }

    @Test
    public void testAddAll() {
        kruMinST.addall(graph);

        Set<Kru_min_ST<Integer>.Edge<Integer>> edges = kruMinST.edges;
        assertEquals(4, edges.size());

        Iterator<Kru_min_ST<Integer>.Edge<Integer>> iterator = edges.iterator();
        assertEquals(5, iterator.next().weight);
        assertEquals(10, iterator.next().weight);
        assertEquals(15, iterator.next().weight);
        assertEquals(20, iterator.next().weight);
    }

    @Test
    public void testFindMinST() {
        kruMinST.find_min_ST(graph);

        Map<Integer, Map<Integer, Integer>> minST = kruMinST.min_ST;
        assertEquals(3, minST.size());

        assertTrue(minST.containsKey(1));
        assertTrue(minST.get(1).containsKey(2));
        assertEquals(10, (int) minST.get(1).get(2));

        assertTrue(minST.containsKey(2));
        assertTrue(minST.get(2).containsKey(3));
        assertEquals(5, (int) minST.get(2).get(3));

        assertTrue(minST.containsKey(3));
        assertTrue(minST.get(3).containsKey(4));
        assertEquals(15, (int) minST.get(3).get(4));
    }

    @Test
    public void testPrintST() {
        kruMinST.find_min_ST(graph);

        // Capture the output of print_ST
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        kruMinST.print_ST();

        String expectedOutput = "1 -> 2 : 10\n2 -> 3 : 5\n3 -> 4 : 15\n";
        assertEquals(expectedOutput, outContent.toString().trim());

        // Reset the standard output
        System.setOut(System.out);
    }
}