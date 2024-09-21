/* the junit 4 test class for AStarSolver.java */
package test.java.AStarTest;

import java.util.*;
import main.java.AStar.*;
// import junit 4 method
import main.java.ArrayHeapMinPQ.ArrayHeapMinPQ;
import main.java.ExtrinsicMinPQ.ExtrinsicMinPQ;
import main.java.Graph.Graph;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AStarSolverTest {

            public AStarGraph<Integer> graph;
            @Before
            public void setUp() {
                graph = new Graph<>();
                graph.addVertex(0);
                graph.addVertex(1);
                graph.addVertex(2);
                graph.addVertex(3);
                graph.addVertex(4);
                graph.addVertex(5);
                graph.addVertex(6);
                graph.addVertex(7);
                graph.addVertex(8);
                graph.addVertex(9);
                graph.addVertex(10);
                graph.addVertex(11);
                graph.addVertex(12);
                graph.addVertex(13);
                graph.addVertex(14);
                graph.addVertex(15);
                graph.addVertex(16);
                graph.addVertex(17);
                graph.addVertex(18);
                graph.addVertex(19);
                graph.addVertex(20);
                graph.addVertex(21);
                graph.addVertex(22);
                graph.addVertex(23);
                graph.addVertex(24);
                graph.addVertex(25);
                graph.addVertex(26);
                graph.addVertex(27);
                graph.addVertex(28);
                graph.addVertex(29);
                graph.addVertex(30);
                graph.addVertex(31);
                graph.addVertex(32);
                graph.addVertex(33);
                graph.addVertex(34);
                graph.addVertex(35);
                graph.addVertex(36);
                graph.addVertex(37);
                graph.addVertex(38);
                graph.addVertex(39);
                graph.addVertex(40);
                graph.addVertex(41);
                graph.addVertex(42);
                graph.addVertex(43);
                graph.addVertex(44);
                graph.addVertex(45);
                graph.addVertex(46);
                graph.addVertex(47);
                graph.addVertex(48);
                graph.addVertex(49);
                graph.addVertex(50);
                graph.addVertex(51);
                graph.addVertex(52);
                graph.addVertex(53);
                graph.addVertex(54);
                graph.addVertex(55);
                graph.addVertex(56);
                graph.addVertex(57);
                graph.addVertex(58);
                graph.addVertex(59);
                graph.addVertex(60);
                graph.addVertex(61);
                graph.addVertex(62);
                graph.addVertex(63);
                graph.addVertex(64);
                graph
        /* the test for the constructor of AStarSolver */
        @Test
        public void testAStarSolver() {
            AStarSolver<Integer> solver = new AStarSolver<>(graph, 0, 1, 10);
            assertNotNull(solver);
        }

        /* the test for the relax method */
        @Test
        public void testRelax() {
            AStarSolver<Integer> solver = new AStarSolver<>(graph, 0, 1, 10);
            WeightedEdge<Integer> e = new WeightedEdge<>(0, 1, 1);
            List<Integer> visited = new ArrayList<>();
            ExtrinsicMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
            Map<Integer, Integer> edgeTo = new HashMap<>();
            Map<Integer, Double> distTo = new HashMap<>();
            solver.relax(e, visited, pq, graph, edgeTo, distTo);
            assertTrue(visited.contains(0));
            assertTrue(visited.contains(1));
        }
}