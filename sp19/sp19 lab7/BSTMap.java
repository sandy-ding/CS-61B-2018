import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BSTMap() {}

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node n, K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (n == null) return null;
        int cmp = key.compareTo(n.key);
        if (cmp < 0) return get(n.left, key);
        if (cmp > 0) return get(n.right, key);
        else return n.value;
    }

    @Override
    public int size() {
        return size(this.root);
    }

    private int size(Node n) {
        if (n == null) return 0;
        return n.size;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node n, K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (n == null) return new Node(key, value, 1);
        int cmp = key.compareTo(n.key);
        if (cmp < 0) n.left = put(n.left, key, value);
        if (cmp > 0) n.right = put(n.right, key, value);
        else n.value = value;
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node n) {
        if (size(n) == 1) {
            System.out.println("key: " + n.key + "value: " + n.value);
            return;
        }
        printInOrder(n.left);
        System.out.println("key: " + n.key + "value: " + n.value);
        printInOrder(n.right);
    }
}

