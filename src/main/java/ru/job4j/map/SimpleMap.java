package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        expand();
        int bucket = getBucket(key);
        if (table[bucket] == null) {
            table[bucket] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        if (count >= capacity * LOAD_FACTOR) {
            capacity *= 2;
            MapEntry<K, V>[] tmp = new MapEntry[capacity];
            for (var mapElement : table) {
                if (mapElement != null) {
                    int bucket = getBucket(mapElement.key);
                    tmp[bucket] = mapElement;
                }
            }
            table = tmp;
        }
    }

    @Override

    public V get(K key) {
        V rsl = null;
        int index = getBucket(key);
        if (bucketCheck(key, index)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = getBucket(key);
        if (bucketCheck(key, index)) {
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private boolean bucketCheck(K key, int bucket) {
        boolean result = false;
        if (table[bucket] != null
                && Objects.hashCode(key) == Objects.hashCode(table[bucket].key)
                && Objects.equals(key, table[bucket].key)) {
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            private int point;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point != capacity && table[point] == null) {
                    point++;
                }
                return point != capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private int getBucket(K key) {
        return key == null ? 0 : indexFor(hash(key.hashCode()));
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}