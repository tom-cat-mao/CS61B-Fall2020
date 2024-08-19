package hw2;

import edu.princeton.cs.algs4.StdRandom;
import  edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private int T;
    private int N;
    private double[] threshold;
    private double[] time;

    /* perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T) {
        Percolation p;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T should be greater than 0");
        }

        this.N = N;
        this.T = T;
        threshold = new double[T];
        time = new double[T];

        for (int i = 0; i < T; i++) {
            Stopwatch sw = new Stopwatch();
            p = new Percolation(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(0, N );
                int col = StdRandom.uniform(0, N );
                p.open(row, col);
            }
            threshold[i] = (double) p.numberOfOpenSites() / (N * N);
            time[i] = sw.elapsedTime();
        }
    }

    /* the avarage time of the experiments */
    public double time() {
        return StdStats.mean(time);
    }

    /* sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(threshold);
    }

    /* sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    /* low endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    /* high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(200, 100);
        System.out.println("time: " + ps.time());
        System.out.println("mean: " + ps.mean());
        System.out.println("stddev: " + ps.stddev());
        System.out.println("95% confidence interval: [" + ps.confidenceLow() + ", " + ps.confidenceHigh() + "]");
    }
}