package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int size;
    boolean[][] grid;
    WeightedQuickUnionUF uf_A;
    WeightedQuickUnionUF uf_B;
    int numberOfOpenSites;
    boolean virtualTopSite;
    boolean virtualBottomSite;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N < 0){
            throw new IllegalArgumentException();
        }
        this.size = N;
        this.grid = new boolean[N][N];
        for (int i = 0; i < N; i++){
            for (int k = 0; k < N; k++){
                this.grid[i][k] = false;
            }
        }
        this.numberOfOpenSites = 0;
        this.uf_A = new WeightedQuickUnionUF(N * N + 2);
        this.uf_B = new WeightedQuickUnionUF(N * N + 1);
        this.virtualTopSite = false;
        this.virtualBottomSite = false;
    }

    // open the site(row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (grid[row][col]) {
            return;
        }
        grid[row][col] = true;

        // virtual top site
        if (row == 0) {
            virtualTopSite = true;
            uf_A.union(xyTo1D(row, col), size * size);
            uf_B.union(xyTo1D(row, col), size * size);
        }

        // virtual bottom site
        if (row == size - 1) {
            virtualBottomSite = true;
            uf_A.union(xyTo1D(row, col), size * size + 1);
        }

        // up
        if (row >= 1 && isOpen(row - 1, col)) {
            uf_A.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            uf_B.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }

        // down
        if (row < size - 1 && isOpen(row + 1, col)) {
            uf_A.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            uf_B.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }

        // left
        if (col >= 1 && isOpen(row, col - 1)) {
            uf_A.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            uf_B.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }

        // right
        if (col < size - 1 && isOpen(row, col + 1)){
            uf_A.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            uf_B.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
        numberOfOpenSites += 1;
    }

    // is the site(row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    // is the site(row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        return uf_B.connected(xyTo1D(row, col), size * size);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf_A.connected(size * size, size * size + 1);
    }

    // transform 2-dimension to 1-dimension
    private int xyTo1D(int row, int col) {
        return row * size + col;
    }

    // use for unit testing (not required, but keep this here for the autoGrader)
    public static void main(String[] args) {}

}
