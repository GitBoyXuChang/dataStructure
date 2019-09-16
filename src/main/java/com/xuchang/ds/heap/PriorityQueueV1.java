package com.xuchang.ds.heap;


public class PriorityQueueV1<T extends Comparable<T>> implements PriorityQueue<T> {
    private T[] pq; //无序
    private int n; //元数个数

    public PriorityQueueV1(int capacity) {
        pq = (T[]) new Comparable[capacity];
        n = 0;
    }

    //O(1)
    @Override
    public void insert(T v) {
        pq[n++] = v; //容量不够时，扩容 ???
    }

    //O(n)
    @Override
    public T delMax() {
        int max = 0;
        for(int i=1; i<n; i++) {
            if(less(max, i)) {
                max = i;
            }
        }

        swap(max, n-1);
        n -= 1;

        return pq[n];
    }

    //O(n)
    @Override
    public T max() {
        int max = 0;
        for(int i=1; i<n; i++) {
            if(less(max, i)) {
                max = i;
            }
        }

        return pq[max];
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swap(int i, int j) {
        T tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueueV1<Integer>(10);
        pq.insert(5);
        pq.insert(3);
        pq.insert(8);
        pq.insert(10);

        while(!pq.isEmpty()){
            System.out.println(pq.delMax());
        }
    }
}
