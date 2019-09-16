package com.xuchang.ds.heap;


import com.xuchang.ds.list.ArrayList;

public class MaxHeap<T extends Comparable<T>> {
    private ArrayList<T> data;

    public MaxHeap(int capacity) {
        data = new ArrayList<T>(capacity);
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index == 0) {
            return 0;
        }

        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    //O(lgn)
    public void insert(T v) {
        data.add(data.getSize(), v);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int i) {
        while (i > 0 && data.get(parent(i)).compareTo(data.get(i)) < 0) {
            swap(data, i, parent(i));
            i = parent(i);
        }
    }

    //O(1)
    public T max() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("heap is empty");
        }

        return data.get(0);
    }

    //O(lgn)
    public T delMax() {
        T ret = max();

        swap(data, 0, data.getSize() - 1);
        data.remove(data.getSize() - 1);
        siftDown(0);

        return ret;
    }

    private void siftDown(int i) {
        while (leftChild(i) < data.getSize()) {
            int j = leftChild(i);
            if ((j + 1) < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++;
            }

            if (data.get(i).compareTo(data.get(j)) >= 0) {
                break;
            }

            swap(data, i, j);
            i = j;
        }
    }

    private void swap(ArrayList<T> data, int i, int parent) {
        T tmp = data.get(i);
        data.set(i, data.get(parent));
        data.set(parent, tmp);
    }

    public static void main(String[] args) {
        MaxHeap<Integer> pq = new MaxHeap<Integer>(10);
        pq.insert(5);
        pq.insert(3);
        pq.insert(8);
        pq.insert(10);

        while(!pq.isEmpty()){
            System.out.println(pq.delMax());
        }
    }
}
