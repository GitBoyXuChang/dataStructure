package com.xuchang.ds.tree;


public interface SymbolTable<K extends Comparable<K>, V> {
    void insert(K key, V value); //如果key，已经存在则value覆盖原来的valu
    V  get(K key);
    void remove(K key);
    boolean contains(K key);
    boolean isEmpty();
    int size();

    K min();
    K max();

    void deleteMin();
    void deleteMax();
}
