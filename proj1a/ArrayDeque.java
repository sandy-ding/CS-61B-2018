public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length - 1;
        nextLast = 0;
    }

    public void addFirst(T item) {
        this.resizeBigger();

        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        }
        size += 1;
    }

    public void addLast(T item) {
        this.resizeBigger();

        items[nextLast] = item;
        nextLast = items.length % (nextLast + 1);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (this.isEmpty()) { return; }

        int ptr = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            System.out.print(items[ptr % items.length] + " ");
            ptr += 1;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.isEmpty()) { return null; }

        nextFirst = items.length % (nextFirst + 1);
        T removedFirst = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;

        this.resizeSmaller();
        return removedFirst;
    }

    public T removeLast() {
        if (this.isEmpty()) { return null; }

        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast -= 1;
        }
        T removedLast = items[nextLast];
        items[nextLast] = null;
        size -= 1;

        this.resizeSmaller();
        return removedLast;
    }

    public T get(int index) {
        if (index >= size) { return null; }

        return items[(nextFirst + 1 + index) % items.length];
    }

    private void resizeBigger() {
        if (size != items.length) { return; }

        T[] newItems = (T[]) new Object[items.length * 2];
        int ptr = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            newItems[i] = items[ptr % items.length];
            ptr += 1;
        }
        nextFirst = newItems.length - 1;
        nextLast = size + 1;
        items = newItems;
    }

    private void resizeSmaller() {
        if (items.length < 16 || (float) size / items.length >= 0.25) { return; }

        T[] newItems = (T[]) new Object[size * 2];
        int ptr = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            newItems[i] = items[ptr % items.length];
            ptr += 1;
        }
        nextFirst = newItems.length - 1;
        nextLast = size + 1;
        items = newItems;
    }
}
