
public interface MyMap<K, V> {
    void put(K key, V value);
    V get(K key);
    boolean containsKey(K key);
    V remove(K key);
    int size();
    boolean isEmpty();
}

public class LinearProbingHashMap<K, V> implements MyMap<K, V> {
    private static final double LOAD_FACTOR_THRESHOLD = 0.5;

    private K[] keys;
    private V[] values;
    private int capacity = 4;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public LinearProbingHashMap() {
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

        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }
            index = (index + 1) % capacity;
        }

        keys[index] = key;
        values[index] = value;
        size++;
    }

    @Override
    public V get(K key) {
        int index = hash(key);

        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return values[index];
            }
            index = (index + 1) % capacity;
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

        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                V oldValue = values[index];
                keys[index] = null;
                values[index] = null;
                size--;

                // Rehash following cluster
                index = (index + 1) % capacity;
                while (keys[index] != null) {
                    K tempKey = keys[index];
                    V tempValue = values[index];
                    keys[index] = null;
                    values[index] = null;
                    size--;
                    put(tempKey, tempValue);
                    index = (index + 1) % capacity;
                }

                return oldValue;
            }
            index = (index + 1) % capacity;
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
        LinearProbingHashMap<K, V> temp = new LinearProbingHashMap<>();
        temp.capacity = newCapacity;
        temp.keys = (K[]) new Object[newCapacity];
        temp.values = (V[]) new Object[newCapacity];
        temp.size = 0;

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