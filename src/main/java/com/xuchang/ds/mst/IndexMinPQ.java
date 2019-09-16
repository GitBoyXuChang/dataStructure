package com.xuchang.ds.mst;

import java.util.Iterator;
import java.util.NoSuchElementException;

 
public class IndexMinPQ<T extends Comparable<T>> implements Iterable<Integer> {
    private int capacity;       
    private int count;           
    private int[] index;        
    private int[] reverse;       
    private T[] data;      


    public IndexMinPQ(int maxN) {
        if (maxN < 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = maxN;
        count = 0;
        data = (T[]) new Comparable[maxN + 1];    // make this of length maxN??
        index   = new int[maxN + 1];
        reverse   = new int[maxN + 1];                   // make this of length maxN??
        for (int i = 0; i <= maxN; i++) {
            reverse[i] = -1;
        }
    }


    public boolean isEmpty() {
        return count == 0;
    }


    public boolean contains(int i) {
        if (i < 0 || i >= capacity) {
            throw new IllegalArgumentException();
        }
        return reverse[i] != -1;
    }


    public int size() {
        return count;
    }


    public void insert(int i, T key) {
        if (i < 0 || i >= capacity) {
            throw new IllegalArgumentException();
        }
        if (contains(i)) {
            throw new IllegalArgumentException("index is already in the priority queue");
        }
        count++;
        reverse[i] = count;
        index[count] = i;
        data[i] = key;
        siftup(count);
    }


    public int minIndex() {
        if (count == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return index[1];
    }

    public T minKey() {
        if (count == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return data[index[1]];
    }

    public int delMin() {
        if (count == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        int min = index[1];
        swap(1, count--);
        siftDown(1);
        assert min == index[count+1];
        reverse[min] = -1;        // delete
        data[min] = null;    // to help with garbage collection
        index[count+1] = -1;        // not needed
        return min;
    }


    public T keyOf(int i) {
        if (i < 0 || i >= capacity) {
            throw new IllegalArgumentException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        } else {
            return data[i];
        }
    }


    public void changeKey(int i, T key) {
        if (i < 0 || i >= capacity) {
            throw new IllegalArgumentException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        data[i] = key;
        siftup(reverse[i]);
        siftDown(reverse[i]);
    }


    public void decreaseKey(int i, T key) {
        if (i < 0 || i >= capacity) {
            throw new IllegalArgumentException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        if (data[i].compareTo(key) <= 0) {
            throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
        }
        data[i] = key;
        siftup(reverse[i]);
    }


    public void increaseKey(int i, T key) {
        if (i < 0 || i >= capacity) {
            throw new IllegalArgumentException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        if (data[i].compareTo(key) >= 0) {
            throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
        }
        data[i] = key;
        siftDown(reverse[i]);
    }


    public void delete(int i) {
        if (i < 0 || i >= capacity) {
            throw new IllegalArgumentException();
        }
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        int index = reverse[i];
        swap(index, count--);
        siftup(index);
        siftDown(index);
        data[i] = null;
        reverse[i] = -1;
    }


    private boolean greater(int i, int j) {
        return data[index[i]].compareTo(data[index[j]]) > 0;
    }

    private void swap(int i, int j) {
        int swap = index[i];
        index[i] = index[j];
        index[j] = swap;
        reverse[index[i]] = i;
        reverse[index[j]] = j;
    }


    private void siftup(int k) {
        while (k > 1 && greater(k/2, k)) {
            swap(k, k/2);
            k = k/2;
        }
    }

    private void siftDown(int k) {
        while (2*k <= count) {
            int j = 2*k;
            if (j < count && greater(j, j+1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }


   /***************************************************************************
    * Iterators.
    ***************************************************************************/

    /**
     * Returns an iterator that iterates over the keys on the
     * priority queue in ascending order.
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    @Override
    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IndexMinPQ<T> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IndexMinPQ<T>(index.length - 1);
            for (int i = 1; i <= count; i++) {
                copy.insert(index[i], data[index[i]]);
            }
        }

        @Override
        public boolean hasNext()  { return !copy.isEmpty();                     }
        @Override
        public void remove()      { throw new UnsupportedOperationException();  }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMin();
        }
    }


    /**
     * Unit tests the {@code IndexMinPQ} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.delMin();
            System.out.println(i + " " + strings[i]);
        }
        System.out.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (int i : pq) {
            System.out.println(i + " " + strings[i]);
        }
        while (!pq.isEmpty()) {
            pq.delMin();
        }

    }
}


