package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] t;
    private double mean;
    private double stddev;
    private static final double CONSTANT = 1.96;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        t = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }
            t[i] = (double) p.numberOfOpenSites() / (N * N);
        }
        mean = mean();
        stddev = stddev();
    }

    public double mean() {
        return StdStats.mean(t);
    }

    public double stddev() {
        return StdStats.stddev(t);
    }

    public double confidenceLow() {
        return mean - CONSTANT * stddev / Math.sqrt(t.length);
    }

    public double confidenceHigh() {
        return mean + CONSTANT * stddev / Math.sqrt(t.length);
    }
}
