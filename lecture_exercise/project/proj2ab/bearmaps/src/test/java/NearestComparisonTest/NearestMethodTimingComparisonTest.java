/* compare the tiem of KDTree and NaivePointSet */
package test.java.NearestComparisonTest;

import main.java.NaivePointSet.NaivePointSet;
import main.java.PointSet.PointSet;
import main.java.KDTree.KDTree;
import main.java.Point.Point;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class NearestMethodTimingComparisonTest {

    @Test
    public void testNearestMethodTiming() {
        List<Point> points = generateRandomPoints(100000);
        PointSet kdTree = new KDTree(points);
        PointSet naivePointSet = new NaivePointSet(points);
        int numQueries = 1000;

        // Measure time for KDTree
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < numQueries; i++) {
            double x = Math.random() * 2000 - 1000;
            double y = Math.random() * 2000 - 1000;
            kdTree.nearest(x, y);
        }
        double kdTreeTime = sw.elapsedTime();

        // Measure time for NaivePointSet
        sw = new Stopwatch();
        for (int i = 0; i < numQueries; i++) {
            double x = Math.random() * 2000 - 1000;
            double y = Math.random() * 2000 - 1000;
            naivePointSet.nearest(x, y);
        }
        double naiveTime = sw.elapsedTime();

        System.out.printf("KDTree time: %.2f seconds\n", kdTreeTime);
        System.out.printf("NaivePointSet time: %.2f seconds\n", naiveTime);

        // Assert that KDTree is faster than NaivePointSet
        assertTrue(kdTreeTime < naiveTime);
    }

    private List<Point> generateRandomPoints(int n) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(new Point(Math.random() * 2000 - 1000, Math.random() * 2000 - 1000));
        }
        return points;
    }
}