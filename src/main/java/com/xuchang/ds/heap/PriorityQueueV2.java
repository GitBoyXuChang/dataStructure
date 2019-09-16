package com.xuchang.ds.heap;


public class PriorityQueueV2<T extends Comparable<T>> implements PriorityQueue<T> {
    private T[] pq; //有序
    private int n; //元数个数

    public PriorityQueueV2(int capacity) {
        pq = (T[]) new Comparable[capacity];
        n = 0;
    }

    //O(n)
    @Override
    public void insert(T v) {
        int i = n - 1;
        while (i >=0 && less(v, pq[i])){
            pq[i+1] = pq[i];
            i--;
        }

        pq[i+1] = v;
        n++;
    }

    //O(1)
    @Override
    public T delMax() {
        return pq[n--];
    }

    //O(1)
    @Override
    public T max() {
        return pq[n];
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

    private boolean less(T i, T j) {
        return i.compareTo(j) < 0;
    }

    private void swap(int i, int j) {
        T tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueueV2<Integer>(10);
        pq.insert(5);
        pq.insert(3);
        pq.insert(8);
        pq.insert(10);

        while(!pq.isEmpty()){
            System.out.println(pq.delMax());
        }
    }
}
