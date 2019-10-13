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
        nextFirst -= 1;
        size += 1;
    }

    public void addLast(T item) {
        this.resizeBigger();

        items[nextLast] = item;
        nextLast += 1;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (this.isEmpty()) return;

        int ptr = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            System.out.print(items[ptr % items.length] + " ");
            ptr += 1;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.isEmpty()) return null;

        this.resizeSmaller();

        T removedFirst = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst += 1;
        size -= 1;
        return removedFirst;
    }

    public T removeLast() {
        if (this.isEmpty()) return null;

        this.resizeSmaller();

        T removedLast = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast -= 1;
        size -= 1;
        return removedLast;
    }

    public T get(int index) {
        if (index >= size) return null;

        return items[(nextFirst + 1 + index) % items.length];
    }

    private void resizeBigger() {
        if (size != items.length) return;

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
        if (items.length < 16 || (float) size / items.length >= 0.25) return;

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
