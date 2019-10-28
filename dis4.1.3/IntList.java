public class IntList {
    public int first;
    public IntList rest;
    public IntList (int f, IntList r) {
        this.first = f;
        this.rest = r;
    }

    public static void removeDuplicates(IntList p) {
        if (p == null) {
            return;
        }

        IntList current = p.rest;
        IntList previous = p;
        while ( current != null) {
            if (current.first == previous.first) {
                previous.rest = current.rest;
            } else {
                previous = current;
            }
            current = current.rest;
        }
    }

    public static void main() {
        IntList a = new IntList(3, null);
        a = new IntList(2, a);
        a = new IntList(2, a);
        a = new IntList(2, a);
        a = new IntList(1, a);
        removeDuplicates(a);
    }
}