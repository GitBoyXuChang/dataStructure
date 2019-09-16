package com.xuchang.ds.heap;


import com.xuchang.ds.list.ArrayList;

public class MinHeap<E extends Comparable<E>> {

	private ArrayList<E> data;

	public MinHeap(int capacity) {
		data = new ArrayList<E>(capacity);
	}

	public MinHeap() {
		data = new ArrayList<E>();
	}



	public int size() {
		return data.getSize();
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

 	private int parent(int index) {
		if (index == 0) {
			throw new IllegalArgumentException("index-0 doesn't have parent.");
		}
		return (index - 1) / 2;
	}

 	private int leftChild(int index) {
		return index * 2 + 1;
	}

 	private int rightChild(int index) {
		return index * 2 + 2;
	}

 	public void add(E e) {
		data.add(data.getSize(), e);
		siftUp(data.getSize() - 1);
	}

	private void siftUp(int k) {

		while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) > 0) {
			swap(data, k, parent(k));
			k = parent(k);
		}
	}

	// 看堆中的最大元素
	public E findMin() {
		if (data.getSize() == 0) {
			throw new IllegalArgumentException("Can not findMax when heap is empty.");
		}
		return data.get(0);
	}

	// 取出堆中最大元素
	public E extractMin() {

		E ret = findMin();

		swap(data, 0, data.getSize() - 1);
		data.remove(data.getSize() - 1);
		siftDown(0);

		return ret;
	}

	private void siftDown(int k) {

		while (leftChild(k) < data.getSize()) {
			int j = leftChild(k); // 在此轮循环中,data[k]和data[j]交换位置
			if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) < 0) {
				j++;
			}
			// data[j] 是 leftChild 和 rightChild 中的最大值

			if (data.get(k).compareTo(data.get(j)) <= 0) {
				break;
			}

			swap(data, k, j);
			k = j;
		}
	}

 	public E replace(E e) {

		E ret = findMin();
		data.set(0, e);
		siftDown(0);
		return ret;
	}

	private void swap(ArrayList<E> list, int s1, int s2) {
		E elem = list.get(s1);
		list.set(s1, list.get(s2));
		list.set(s2, elem);
	}

	public static void main(String[] args) {
		MinHeap<Integer> maxHeap = new MinHeap<Integer>(10);
		maxHeap.add(3);
		maxHeap.add(8);
		maxHeap.add(8);
		maxHeap.add(5);
		maxHeap.add(12);
		while (!maxHeap.isEmpty()) {
			System.out.println(maxHeap.extractMin());
		}
	}
}
