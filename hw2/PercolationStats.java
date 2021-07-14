package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] fractions;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.fractions = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation sample = pf.make(N);
            while (!sample.percolates()) {
                int random = StdRandom.uniform(N * N);
                int row = random / N;
                int col = random % N;
                sample.open(row, col);
            }
            int openSite = sample.numberOfOpenSites();
            double fraction = (double) openSite / (N * N);
            fractions[i] = fraction;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for (double fraction : fractions) {
            sum += fraction;
        }
        return sum / fractions.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return Math.sqrt(stddevHelper());
    }

    private double stddevHelper() {
        double sum = 0;
        for (double fraction : fractions) {
            sum += Math.pow((fraction - mean()), 2);
        }
        return sum / (fractions.length - 1);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.sqrt(fractions.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.sqrt(fractions.length);
    }

}
