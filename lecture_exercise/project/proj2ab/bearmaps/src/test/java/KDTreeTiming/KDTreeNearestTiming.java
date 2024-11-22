/* this will print out the time taken to run the nearest method on a KDTree for different sizes of input
 * and different numbers of queries. The time is measured in seconds, and the number of operations is the
 * number of nearest calls made. The time and number of operations are printed in a table, along with the
 * time per operation in microseconds.
 */
package test.java.KDTreeTiming;

import main.java.KDTree.KDTree;
import main.java.Point.Point;
import main.java.Stopwatch.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class KDTreeNearestTiming {

    public static void main(String[] args) {
        int[] sizes = {31250, 62500, 125000, 250000, 500000, 1000000};
        int numQueries = 1000000;
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.println("------------------------------------------------------------");

        for (int size : sizes) {
            List<Point> points = generateRandomPoints(size);
            KDTree kdTree = new KDTree(points);
            double time = measureKDTreeNearestTime(kdTree, numQueries);
            double microsecPerOp = (time * 1_000_000) / numQueries;
            System.out.printf("%12d %12.2f %12d %12.2f\n", size, time, numQueries, microsecPerOp);
        }
    }

    private static List<Point> generateRandomPoints(int n) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(new Point(Math.random() * 1000, Math.random() * 1000));
        }
        return points;
    }

    private static double measureKDTreeNearestTime(KDTree kdTree, int numQueries) {
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < numQueries; i++) {
            kdTree.nearest(Math.random() * 100, Math.random() * 100);
        }
        return sw.elapsedTime();
    }
}