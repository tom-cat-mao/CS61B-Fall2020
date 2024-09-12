import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class T_sortTest {

    private Graph<String> graph;

    @Before
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    public void testTSort() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("A", "C", 1);
        graph.addEdge("C", "D", 1);

        Stack<String> sorted = T_sort.t_sort(graph);

        List<String> sortedList = new ArrayList<>();
        while (!sorted.isEmpty()) {
            sortedList.add(sorted.pop());
        }

        List<String> expectedOrder = Arrays.asList("A", "B", "C", "D");
        assertEquals(expectedOrder, sortedList);
    }

   @Test
    public void testComplexGraphTSort() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");

        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 1);
        graph.addEdge("C", "E", 1);
        graph.addEdge("D", "F", 1);
        graph.addEdge("E", "F", 1);
        graph.addEdge("E", "G", 1);
        graph.addEdge("F", "H", 1);
        graph.addEdge("G", "H", 1);

        Stack<String> sorted = T_sort.t_sort(graph);

        List<String> sortedList = new ArrayList<>();
        while (!sorted.isEmpty()) {
            sortedList.add(sorted.pop());
        }

        /* print the sorted list */
        System.out.println(sortedList);
    }
 }
