import java.util.*;

/* implement the Map61B interface */
public class MyHashMap<K, V> implements Map61B<K, V> {
    private int INITIAL_SIZE = 16; // initial size of the hash table
    private double LOAD_FACTOR = 0.75; // load factor of the hash table
    private int size;
    private Map<K, V>[] hashTable;

    public MyHashMap() {
        hashTable = (Map<K, V>[]) new HashMap[INITIAL_SIZE];
        size = 0;
    }

    public MyHashMap(int initialSize) {
        INITIAL_SIZE = initialSize;
        hashTable = (Map<K, V>[]) new HashMap[INITIAL_SIZE];
        size = 0;
    }

    public MyHashMap(int initialSize, double loadFactor) {
        INITIAL_SIZE = initialSize;
        hashTable = (Map<K, V>[]) new HashMap[INITIAL_SIZE];
        LOAD_FACTOR = loadFactor;
        size = 0;
    }

    @Override
    public void clear() {
        size = 0;
        INITIAL_SIZE = 16;
    }

    @Override
    public int size() {
        return size;
    }

    private int hashCode(K key) {
        return (key.hashCode() & 0x7fffffff) % hashTable.length;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        int index = hashCode(key);
        if (hashTable[index] == null) {
            return null;
        }
        return hashTable[index].get(key);
    }

    @Override
    public void put(K key, V value) {
        if (size >= hashTable.length * LOAD_FACTOR) {
            resize(hashTable.length * 2);
        }

        int index = hashCode(key);
        if (containsKey(key)) {
            hashTable[index].put(key, value);
        } else {
            hashTable[index] = new HashMap<>();
            hashTable[index].put(key, value);
            size++;
        }
    }

    /* resize the hash table */
    private void resize(int newSize) {
        /* store the previous hash table */
        Map<K, V> previousHashTable[] = hashTable;
        hashTable = (Map<K, V>[]) new HashMap[newSize];
        INITIAL_SIZE = newSize;

        /* rehash all the key-value pairs */
        for (Map<K, V> map : previousHashTable) {
            if (map != null) {
                for (K key : map.keySet()) {
                    int index = hashCode(key);
                    if (hashTable[index] == null) {
                        hashTable[index] = new HashMap<>();
                    }
                    hashTable[index].put(key, map.get(key));
                }
            }
        }
    }

    /* return a set of all the keys */
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Map<K, V> map : hashTable) {
            if (map != null) {
                keySet.addAll(map.keySet());
            }
        }
        return keySet;
    }

    /* remove certain key pair */
    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int index = hashCode(key);
        V value = hashTable[index].get(key);
        hashTable[index].remove(key);
        size--;
        return value;
    }

    /* remove key-value pair */
    @Override
    public V remove(K key, V value) {
        if (!containsKey(key)) {
            return null;
        }
        int index = hashCode(key);
        V val = hashTable[index].get(key);
        if (val.equals(value)) {
            hashTable[index].remove(key);
            size--;
            return val;
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<K> {
        private int index;
        private Set<K> keySet;

        public KeyIterator() {
            index = 0;
            keySet = keySet();
        }

        public boolean hasNext() {
            return index < INITIAL_SIZE;
        }

        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            /* convert the set to an array */
            K key = (K) keySet.toArray()[index];
            index++;
            return key;
        }
    }
}
