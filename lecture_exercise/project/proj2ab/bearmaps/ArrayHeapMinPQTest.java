package bearmaps;
import bearmaps.ArrayHeapMinPQ;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.Stopwatch;

public class ArrayHeapMinPQTest {
    private ArrayHeapMinPQ<String> pq;

    @Test
    public void testTimeConsumption() {
        int[] sizes = {31250, 62500, 125000, 250000, 500000, 1000000};
        int numOperations = 1000000;

        System.out.printf("%12s %12s %12s %12s%n", "N", "time (s)", "# ops", "microsec/op");
        System.out.println("------------------------------------------------------------");

        for (int size : sizes) {
            pq = new ArrayHeapMinPQ<>();
            Stopwatch stopwatch = new Stopwatch();

            // Measure time for add operations
            for (int i = 0; i < size; i++) {
                pq.add("Item" + i, Math.random());
            }
            double addTime = stopwatch.elapsedTime();

            // Measure time for nearest operations
            stopwatch = new Stopwatch();
            for (int i = 0; i < numOperations; i++) {
                pq.changePriority("Item" + (i % size), Math.random());
            }
            double nearestTime = stopwatch.elapsedTime();

            double totalTime = addTime + nearestTime;
            double microsecPerOp = (totalTime / numOperations) * 1e6;

            System.out.printf("%12d %12.2f %12d %12.2f%n", size, totalTime, numOperations, microsecPerOp);
        }
    }
}