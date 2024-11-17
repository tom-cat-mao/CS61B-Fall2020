/*package bearmaps.proj2d;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import bearmaps.proj2ab.Point;
import java.util.Arrays;
import java.util.List;

public class KDTreeTest {

    private KDTree kdTree;
    private List<Point> points;

    @Before
    public void setUp() {
        points = Arrays.asList(
            new Point(2, 3),
            new Point(4, 2),
            new Point(4, 5),
            new Point(3, 1),
            new Point(1, 5),
            new Point(4, 4)
        );
        kdTree = new KDTree(points);
    }

    @Test
    public void testInsert() {
        KDTree tree = new KDTree();
        tree.insert(new Point(2, 3));
        tree.insert(new Point(4, 2));
        assertNotNull(tree);
    }

    @Test
    public void testNearest() {
        Point nearest = kdTree.nearest(3, 4);
        assertEquals(new Point(4, 4), nearest);
    }

    @Test
    public void testCompare() {
        KDTree.KDNode node1 = new KDTree.KDNode(new Point(2, 3), true);
        KDTree.KDNode node2 = new KDTree.KDNode(new Point(4, 2), true);
        double result = kdTree.compare(node1, node2);
        assertTrue(result < 0);
    }
}*/