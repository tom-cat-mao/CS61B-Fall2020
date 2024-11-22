/* the basic constructor time test
 * the time is O(nlogn)
 * the time is much faster than the NaivePointSet
 * the time is much faster than the brute force
 */
package test.java.KDTreeTiming;

import main.java.Point.Point;
import main.java.KDTree.KDTree;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class KDTreeTiming {

    public static void main(String[] args) {
        int[] sizes = {31250, 62500, 125000, 250000, 500000, 1000000, 2000000};
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.println("------------------------------------------------------------");

        for (int size : sizes) {
            List<Point> points = generateRandomPoints(size);
            double time = measureKDTreeConstructionTime(points);
            double microsecPerOp = (time * 1_000_000) / size;
            System.out.printf("%12d %12.2f %12d %12.2f\n", size, time, size, microsecPerOp);
        }
    }

    private static List<Point> generateRandomPoints(int n) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(new Point(Math.random() * 100000, Math.random() * 100000));
        }
        return points;
    }

    private static double measureKDTreeConstructionTime(List<Point> points) {
        Stopwatch sw = new Stopwatch();
        new KDTree(points);
        return sw.elapsedTime();
    }
}