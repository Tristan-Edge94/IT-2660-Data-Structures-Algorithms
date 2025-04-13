import java.util.LinkedList;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static final double LOAD_FACTOR_THRESHOLD = 0.5;

    private K[] keys;
    private V[] values;
    private int capacity = 4;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        keys = (K[]) new Object[capacity];
        values = (V[]) new Object[capacity];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    @Override
    public void put(K key, V value) {
        if ((double) size / capacity > LOAD_FACTOR_THRESHOLD) {
            resize(2 * capacity);
        }

        int index = hash(key);
        int i = 0;

        while (true) {
            int probeIndex = (index + i * i) % capacity;
            if (keys[probeIndex] == null || keys[probeIndex].equals(key)) {
                if (keys[probeIndex] == null) size++;
                keys[probeIndex] = key;
                values[probeIndex] = value;
                return;
            }
            i++;
        }
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        int i = 0;

        while (i < capacity) {
            int probeIndex = (index + i * i) % capacity;
            if (keys[probeIndex] == null) return null;
            if (keys[probeIndex].equals(key)) return values[probeIndex];
            i++;
        }

        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V remove(K key) {
        int index = hash(key);
        int i = 0;

        while (i < capacity) {
            int probeIndex = (index + i * i) % capacity;
            if (keys[probeIndex] == null) return null;
            if (keys[probeIndex].equals(key)) {
                V oldValue = values[probeIndex];
                keys[probeIndex] = null;
                values[probeIndex] = null;
                size--;

                // Rehash cluster
                i++;
                while (i < capacity) {
                    int nextIndex = (index + i * i) % capacity;
                    if (keys[nextIndex] == null) break;
                    K rehashKey = keys[nextIndex];
                    V rehashValue = values[nextIndex];
                    keys[nextIndex] = null;
                    values[nextIndex] = null;
                    size--;
                    put(rehashKey, rehashValue);
                    i++;
                }

                return oldValue;
            }
            i++;
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        MyHashMap<K, V> temp = new MyHashMap<>();
        temp.capacity = newCapacity;
        temp.keys = (K[]) new Object[newCapacity];
        temp.values = (V[]) new Object[newCapacity];

        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], values[i]);
            }
        }

        this.keys = temp.keys;
        this.values = temp.values;
        this.capacity = temp.capacity;
        this.size = temp.size;
    }
}