import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class WQUTest {

    private WQU<Integer> wqu;
    private Graph<Integer> graph;

    @Before
    public void setUp() {
        graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        wqu = new WQU<>(graph);
    }

    @Test
    public void testUnionAndFind() {
        // Initially, each node is its own root
        assertEquals((Integer) 1, wqu.find(1));
        assertEquals((Integer) 2, wqu.find(2));
        assertEquals((Integer) 3, wqu.find(3));
        assertEquals((Integer) 4, wqu.find(4));

        // Union 1 and 2
        assertTrue(wqu.union(1, 2));
        assertEquals(wqu.find(1), wqu.find(2));

        // Union 3 and 4
        assertTrue(wqu.union(3, 4));
        assertEquals(wqu.find(3), wqu.find(4));

        // Union 1 and 3 (which should connect 1, 2, 3, 4)
        assertTrue(wqu.union(1, 3));
        assertEquals(wqu.find(1), wqu.find(3));
        assertEquals(wqu.find(2), wqu.find(4));
        assertEquals(wqu.find(1), wqu.find(4));

        // Union 1 and 4 should return false as they are already connected
        assertFalse(wqu.union(1, 4));
    }

    @Test
    public void testUnionWithWeights() {
        // Union 1 and 2
        assertTrue(wqu.union(1, 2));
        assertEquals((Integer) 1, wqu.weight.get(wqu.find(1)));

        // Union 3 and 4
        assertTrue(wqu.union(3, 4));
        assertEquals((Integer) 1, wqu.weight.get(wqu.find(3)));

        // Union 1 and 3 (which should connect 1, 2, 3, 4)
        assertTrue(wqu.union(1, 3));
        assertEquals((Integer) 2, wqu.weight.get(wqu.find(1)));
    }
}