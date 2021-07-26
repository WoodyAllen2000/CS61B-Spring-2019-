import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private static int initialSize = 16;
    private static double loadFactor = 0.75;
    public int size;
    public HashSet<K> hashSet;
    public Entry<K, V>[] buckets;

    public MyHashMap(int initialSize, double loadFactor) {
        if (initialSize < 1 || loadFactor <= 0){
            throw new IllegalArgumentException();
        }
        MyHashMap.initialSize = initialSize;
        MyHashMap.loadFactor = loadFactor;
        this.size = 0;
        this.hashSet = new HashSet<>();
        this.buckets = new Entry[initialSize];
    }

    public MyHashMap(int initialSize) {
        this(initialSize, loadFactor);
    }

    public MyHashMap() {
        this(initialSize, loadFactor);
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        hashSet.clear();
        size = 0;
        buckets = new Entry[initialSize];
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return hashSet.contains(key);
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
        int x = hash(key);
        if (buckets[x] == null) {
            return null;
        }
        Entry e = buckets[x].get(key);
        if (e == null) {
            return null;
        }
        return (V) e.value;
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
        int index = hash(key);
        if (!hashSet.contains(key)) {
            hashSet.add(key);
            if (buckets[index] == null) {
                buckets[index] = new Entry<>(key, value);
            } else {
                buckets[index].add(new Entry(key, value));
            }
            size += 1;
        } else {
            buckets[index].get(key).value = value;
        }
        int threshold = (int) (initialSize * loadFactor);
        if (size > threshold) {
            resize();
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return hashSet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return hashSet.iterator();
    }

    public class Entry<K, V> {
        K key;
        V value;
        Entry next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

         Entry get(K key) {
            if (key == null) {
                throw new IllegalArgumentException();
            }
            if (this.key.equals(key)) {
                return this;
            }
            if (this.next == null) {
                return null;
            }
            return next.get(key);
        }

        void add(Entry x) {
         Entry t = this;
         while (t.next != null) {
             t = t.next;
         }
         t.next = x;
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % initialSize;
    }

    private void resize() {
        int originalSize = initialSize;
         Entry[] e = buckets;
        initialSize *= 2;
        Entry[] BK = new Entry[initialSize];
        for (Entry x : e) {
            if (x == null) {
                continue;
            }
            Entry t =x;
            while (t != null) {
                K k = (K) t.key;
                V v = (V) t.value;
                int code = hash(k);
                if (BK[code] == null) {
                    BK[code] = new Entry(k, v);
                } else {
                    BK[code].add(new Entry(k, v));
                }
                t = t.next;
            }
        }
        buckets = BK;
    }

}
