import java.util.Iterator;
import java.util.Set;
import java.util.LinkedList;
import java.util.HashSet;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private static final int INITIAL_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int size;
    private int bucketSize;
    private double loadFactor;
    private LinkedList<ListItem>[] buckets;
    private Set<K> keySet;

    private class ListItem {
        public K key;
        public V value;
        public ListItem(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        this(INITIAL_SIZE, LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, LOAD_FACTOR);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        if (initialSize < 1 || loadFactor <= 0.0) {
            throw new IllegalArgumentException();
        }
        this.size = 0;
        this.bucketSize = initialSize;
        this.loadFactor = loadFactor;
        this.buckets = new LinkedList[bucketSize];
        for (int i = 0, length = buckets.length; i < length; i++) {
            buckets[i] = new LinkedList<>();
        }
        this.keySet = new HashSet();
    }

    private int hash(K key) {
        return hash(key, bucketSize);
    }

    private int hash(K key, int bucketSize) {
        return (key.hashCode() & 0x7fffffff) % bucketSize;
    }

    private void resize() {
        if (size / bucketSize > loadFactor) {
            int doubleBucketSize = bucketSize * 2;
            LinkedList<ListItem>[] newArr = new LinkedList[doubleBucketSize];
            for (int i = 0; i < doubleBucketSize; i++) {
                newArr[i] = new LinkedList<>();
            }
            for (K key : keySet) {
                int i = hash(key, doubleBucketSize);
                newArr[i].add(new ListItem(key, get(key)));
            }
            this.buckets = newArr;
            bucketSize = doubleBucketSize;

        }
    }

    @Override
    public void clear() {
        this.size = 0;
        this.bucketSize = INITIAL_SIZE;
        this.buckets = new LinkedList[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            buckets[i] = new LinkedList<>();
        }
        this.keySet = new HashSet();
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (get(key) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int i = hash(key);
        for (ListItem item : buckets[i]) {
            if (item.key.equals(key)) {
                return item.value;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int i = hash(key);
        LinkedList<ListItem> ll = buckets[i];
        for (ListItem item : ll) {
            if (item.key.equals(key)) {
                item.value = value;
                return;
            }
        }

        ll.add(new ListItem(key, value));
        size += 1;
        keySet.add(key);
        resize();
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }

    @Override
    public V remove(K key) {
        return remove(key, null);
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int i = hash(key);
        LinkedList<ListItem> ll = buckets[i];
        for (ListItem item : ll) {
            if (item.key.equals(key) && (value == null || item.value.equals(value))) {
                V returnValue = item.value;
                ll.remove(item);
                return returnValue;
            }
        }
        return null;
    }
}
