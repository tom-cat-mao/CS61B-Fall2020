/* test the correctness of the nearest method in KDTree and NaivePointSet */
import bearmaps.Point;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class NearestMethodComparisonTest {

    @Test
    public void testNearestMethod() {
        List<Point> points = generateRandomPoints(100000);
        PointSet kdTree = new KDTree(points);
        PointSet naivePointSet = new NaivePointSet(points);

        for (int i = 0; i < 10; i++) {
            double x = Math.random() * 2000 - 1000;
            double y = Math.random() * 2000 - 1000;
            Point kdTreeNearest = kdTree.nearest(x, y);
            Point naiveNearest = naivePointSet.nearest(x, y);
            assertEquals(kdTreeNearest, naiveNearest);
        }
    }

    private List<Point> generateRandomPoints(int n) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(new Point(Math.random() * 2000 - 1000, Math.random() * 2000 - 1000));
        }
        return points;
    }
}