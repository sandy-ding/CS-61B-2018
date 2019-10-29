public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char X, char Y) {
        int smallest = 'a';
        int largest = 'z';
        int x = Character.toLowerCase(X);
        int y = Character.toLowerCase(Y);
        if (x < smallest || x > largest || y < smallest || y > largest) {
            return false;
        }
        int diff = x - y;
        return Math.abs(diff) == 1;
    }
}
