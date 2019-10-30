public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char X, char Y) {
        int diff = X - Y;
        return Math.abs(diff) == 1;
    }
}
