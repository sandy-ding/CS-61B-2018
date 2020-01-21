package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int squareSize;
    private int bottomFirst;
    private int numberOfOpenSites;
    private boolean[] isOpenSites;
    private WeightedQuickUnionUF sites;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("size must be larger than 0");
        }
        squareSize = N;
        numberOfOpenSites = 0;
        bottomFirst = N * N + 1;
        sites = new WeightedQuickUnionUF(N * (N + 1) + 1);  // +1 for top, +N for invisible bottom row
        isOpenSites = new boolean[N * N + 1];
    }

    private int checkAndTransfer(int row, int col) {
        if (row < 0 || row > squareSize - 1 || col < 0 || col > squareSize - 1) {
            throw new IndexOutOfBoundsException("row or col out of bounds");
        }
        return row * squareSize + col + 1;
    }

    public void open(int row, int col) {
        int n = checkAndTransfer(row, col);
        if (!isOpenSites[n]) {
            isOpenSites[n] = true;
            numberOfOpenSites += 1;
            if (row == 0) {
                sites.union(n, 0);
            }
            if (row == squareSize - 1) {
                sites.union(n, bottomFirst + col);
            }
            if (row > 0 && isOpenSites[n - squareSize]) {
                sites.union(n, n - squareSize);
            }
            if (row < squareSize - 1 && isOpenSites[n + squareSize]) {
                sites.union(n, n + squareSize);
            }
            if (col > 0 && isOpenSites[n - 1]) {
                sites.union(n, n - 1);
            }
            if (col < squareSize - 1 && isOpenSites[n + 1]) {
                sites.union(n, n + 1);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        int n = checkAndTransfer(row, col);
        return !!isOpenSites[n];
    }

    public boolean isFull(int row, int col) {
        int n = checkAndTransfer(row, col);
        return sites.connected(n, 0);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        for (int i = 0; i < squareSize; i++) {
            if (!!sites.connected(0, bottomFirst + i)) {
                return true;
            };
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
