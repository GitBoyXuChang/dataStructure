package com.xuchang.ds.map;


public class HashMap<K, V> implements Map<K, V> {
    static class Entry<K, V> {
        final K key;
        V value;
        final int hash;
        Entry<K, V> next;

        Entry(int hash, K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }

            Entry e = (Entry) o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = e.getValue();

                if (v1 == v2 || (v1 != null && v1.equals(v2))) {
                    return true;
                }
            }

            return false;
        }

    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static int indexFor(int hash, int length) {
        return hash & (length - 1); //mod
    }

    static final int DEFAULT_CAPACITY = 16;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static final int MAXIMUM_CAPACITY = 1 << 30;

    Entry[] table;

    int size;


    int threshold;

    final float loadFactor;

    public HashMap(int initalCapacity, float loadFactor) {
        if (initalCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initalCapacity);
        }
        if (initalCapacity > MAXIMUM_CAPACITY) {
            initalCapacity = MAXIMUM_CAPACITY;
        }

        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal loadFactor: " + loadFactor);
        }

        int capacity = 1;
        while (capacity < initalCapacity) {
            capacity <<= 1;
        }

        this.loadFactor = loadFactor;
        threshold = (int) (capacity * loadFactor);

        table = new Entry[capacity];
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            return putForNullKey(value);
        }

        int hash = hash(key);
        int i = indexFor(hash, table.length);
        Object k;
        for(Entry<K, V> e=table[i]; e!=null; e=e.next) {
            if(e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }

        addEntry(hash, key, value, i);
        return null;
    }

    private V putForNullKey(V value) {
        for (Entry<K, V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }

        addEntry(0, null, value, 0);

        return null;
    }

    private void addEntry(int hash, K key, V value, int bucketIndex) {
        Entry<K, V> e = table[bucketIndex];
        table[bucketIndex] = new Entry<K, V>(hash, key, value, e);
        
        if(size++ >= threshold) {
            resize(2*table.length);
        }
    }

    private void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if(oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;

        threshold = (int)(newCapacity * loadFactor);
    }

    private void transfer(Entry[] newTable) {
        Entry[] src = table;
        int newCapacity = newTable.length;
        for(int j=0; j<src.length; j++) {
            Entry<K, V> e = src[j];
            if(e != null) {
                src[j] = null;

                do{
                    Entry<K, V> next = e.next;
                    int index = indexFor(e.hash, newCapacity);
                    e.next = newTable[index];
                    newTable[index] = e;
                    e = next;
                } while(e != null);

            }
        }
    }

    @Override
    public V get(K key) {
        if(key == null) {
            return getForNullKey();
        }

        int hash = hash(key);
        int i = indexFor(hash, table.length);

        for(Entry<K, V> e=table[i]; e!=null; e=e.next) {
            Object k;
            if(e.hash == hash && ((k=e.getKey()) == key || key.equals(k))){
                return  e.value;
            }
        }

        return null;
    }

    private V getForNullKey() {
        for(Entry<K, V> e = table[0]; e!=null; e=e.next) {
            if(e.key == null) {
                return e.value;
            }
        }

        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if(value == null) {
            return containNullValue();
        }

        Entry[] tab = table;
        for(int i=0; i<tab.length; i++) {
            for(Entry e=tab[i]; e!=null; e=e.next) {
                if(value.equals(e.value)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean containNullValue() {
        Entry[] tab = table;
        for(int i=0; i<tab.length; i++) {
            for(Entry e=tab[i]; e!=null; e=e.next) {
                if(e.value == null) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public V remove(K key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);

        Entry<K, V> prev = table[index];
        Entry<K, V> e = prev;

        while(e != null) {
            Entry<K, V> next = e.next;
            Object k;
            if(e.hash == hash && ((k=e.key) == key || (key!=null && key.equals(k))))  {
                if(prev == e) {
                    table[index] = next;
                }else {
                    prev.next = next;
                }

                size--;

                return e.value;
            }
            prev = e;
            e = next;
        }

        return (e == null) ? null : e.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Entry[] tab = table;
        for(int i=0; i<tab.length; i++) {
            tab[i] = null;
        }

        size = 0;
    }


    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for(int i=0; i<100; i++) {
            map.put(i+"", i);
        }

        for(int i=0; i<100; i+=5) {
            map.remove(i+"");
        }

        System.out.println("Map size is: " + map.size());

        for(int i=0; i<100; i++) {
            System.out.println("The ith element is: " + map.get(i+""));
        }
    }

}
