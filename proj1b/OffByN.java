public class OffByN implements CharacterComparator {
    private int n;
    public OffByN(int N) {
        n = N;
    }

    @Override
    public boolean equalChars(char X, char Y) {
        int diff = X - Y;
        return Math.abs(diff) == n;
    }
}
