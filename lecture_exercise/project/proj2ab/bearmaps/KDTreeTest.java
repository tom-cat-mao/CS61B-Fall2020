package bearmaps;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Before
    public void setUp() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);

        List<Point> points = List.of(p1, p2, p3, p4, p5, p6);
    }

    @Test
    public void testNearest() {
        KDTree kd = new KDTree(points);
        Point actual = kd.nearest(0, 7);
        Point expected = new Point(1, 5);
        assertEquals(expected, actual);
    }

    @Test
    public void testinsert() {
        KDTree kd = new KDTree(points);
        Point actual = kd.find(new Point(4, 5));
        Point expected = new Point(4, 5);
        assertEquals(expected, actual);
    }

}
