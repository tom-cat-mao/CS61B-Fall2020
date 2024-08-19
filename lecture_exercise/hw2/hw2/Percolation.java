package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.Stopwatch;

public class Percolation {

    private int n;
    private  int openSites;
    private boolean[][] grid;
    private  WeightedQuickUnionUF uf;

    /* change x, y to 1D index
     */
    private int xyTo1D(int x, int y) {
        return x * n + y;
    }

    /* create N by grid
     * with all sites initially blocked
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        this.n = n;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        openSites = 0;
    }

    /* open the site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException("row or col is out of bounds");
        }
        if (grid[row][col]) {
            return;
        }
        grid[row][col] = true;
        openSites++;

        /* connect the first row to the virtual top site */
        if (row == 0) {
            uf.union(xyTo1D(row, col), n * n);
        }

        /* connect the last row to the virtual bottom site */
        if (row == n - 1) {
            uf.union(xyTo1D(row, col), n * n + 1);
        }

        /* check the 4 directions to connect the open sites */
        /* up */
        if (row > 0 && isOpen(row - 1, col)) {
            uf.union(xyTo1D(row, col), (row - 1) * n + col);
        }

        /* down */
        if (row < n - 1 && isOpen(row + 1, col)) {
            uf.union(xyTo1D(row, col), (row + 1) * n + col);
        }

        /* left */
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(xyTo1D(row, col), row * n + col - 1);
        }

        /* right */
        if (col < n - 1 && isOpen(row, col + 1)) {
            uf.union(xyTo1D(row, col), row * n + col + 1);
        }
    }

    /* is the site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException("row or col is out of bounds");
        }
        return grid[row][col];
    }

    /* is the site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException("row or col is out of bounds");
        }
        return uf.connected(xyTo1D(row, col), n * n);
    }

    /* number of open sites
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /* does the system percolate?
     */
    public boolean percolates() {
        return uf.connected(n * n, n * n + 1);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(0, 0);


    }
}
