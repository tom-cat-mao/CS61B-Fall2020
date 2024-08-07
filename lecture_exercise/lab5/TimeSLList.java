import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        int[] Ns = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        List<Integer> NsList = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        for (int N : Ns) {
            SLList<Integer> L = new SLList<>();
            for (int i = 0; i < N; i += 1) {
                L.addLast(i);
            }

            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < 10000; i += 1) {
                L.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            NsList.add(N);
            times.add(timeInSeconds);
            opCounts.add(10000);
        }
        printTimingTable(NsList, times, opCounts);
    }

}
