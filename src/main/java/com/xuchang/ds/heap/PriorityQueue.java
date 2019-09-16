package com.xuchang.ds.heap;


public interface PriorityQueue<T extends Comparable<T>> {
    void insert(T v);

    T delMax();

    T max();

    int size();

    boolean isEmpty();
}
