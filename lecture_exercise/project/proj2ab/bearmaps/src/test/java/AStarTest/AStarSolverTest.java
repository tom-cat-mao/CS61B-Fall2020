// File: src/test/java/AStarTest/AStarSolverTest.java

package test.java.AStarTest;

import static org.junit.Assert.*;

import java.util.*;
import main.java.AStar.*;
import main.java.ArrayHeapMinPQ.ArrayHeapMinPQ;
import main.java.ExtrinsicMinPQ.ExtrinsicMinPQ;
import main.java.Graph.Graph;
import org.junit.Before;
import org.junit.Test;

public class AStarSolverTest {

    public AStarGraph<Integer> graph;

    @Before
    public void setUp() {
        graph = new Graph<>();
        // Add vertices
        for (int i = 0; i < 10; i++) {
            graph.addVertex(i);
        }
        // Add edges with weights
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 5, 3);
        graph.addEdge(4, 5, 1);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 3);
        graph.addEdge(8, 9, 2);
        graph.addEdge(1, 9, 10);
    }

    @Test
    public void testAStarSolver() {
        AStarSolver<Integer> solver = new AStarSolver<>(graph, 0, 9, 10);
        assertNotNull(solver);
        // Add more assertions to verify the solution
    }
}
