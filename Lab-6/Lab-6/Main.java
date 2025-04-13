class Main {
  public static void main(String[] args) {
      MyMap<String, Integer> creditHours = new MyHashMap<>();

      creditHours.put("IT-1025", 3);
      creditHours.put("IT-1050", 3);
      creditHours.put("IT-1150", 3);
      creditHours.put("IT-2310", 3);
      creditHours.put("IT-2320", 4);
      creditHours.put("IT-2351", 4);
      creditHours.put("IT-2650", 4);
      creditHours.put("IT-2660", 4);
      creditHours.put("IT-2030", 4);

      System.out.println("Contains IT-1025? " + creditHours.containsKey("IT-1025")); // true
      System.out.println("Contains IT-2110? " + creditHours.containsKey("IT-2110")); // false

      System.out.println("\nAll values in map before removal:");
      printAllValues(creditHours);

      creditHours.remove("IT-2030");
      creditHours.remove("IT-1150");

      System.out.println("\nAll values in map after removal:");
      printAllValues(creditHours);
  }

  private static void printAllValues(MyMap<String, Integer> map) {
      for (String courseCode : new String[]{
              "IT-1025", "IT-1050", "IT-1150", "IT-2310", "IT-2320",
              "IT-2351", "IT-2650", "IT-2660", "IT-2030"}) {
          Integer value = map.get(courseCode);
          if (value != null) {
              System.out.println(courseCode + ": " + value);
          }
      }
  }
}

interface MyMap<K, V> {
  void put(K key, V value);
  V get(K key);
  boolean containsKey(K key);
  V remove(K key);
  int size();
  boolean isEmpty();
}

class MyHashMap<K, V> implements MyMap<K, V> {
  private static final double LOAD_FACTOR_THRESHOLD = 0.5;

  private K[] keys;
  private V[] values;
  private int capacity = 4;
  private int size = 0;

  // Constructor that allows setting the initial capacity
  @SuppressWarnings("unchecked")
  public MyHashMap(int capacity) {
      this.capacity = capacity;
      keys = (K[]) new Object[capacity];
      values = (V[]) new Object[capacity];
  }

  // Default constructor calling the constructor with capacity 4
  public MyHashMap() {
      this(4);
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
      MyHashMap<K, V> temp = new MyHashMap<>(newCapacity);

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
