package com.xuchang.ds.heap;


import com.xuchang.algo.sort.SortUtils;
import com.xuchang.algo.sort.Sorter;

import java.util.Arrays;


public class HeapSorter implements Sorter {

    //O(nlgn)
    @Override
    public void sort(int[] arr) {
        if(arr == null || arr.length <= 1) {
            return;
        }
        //建堆： n-1, n-1-1/2 = n/2 -1
        int n = arr.length;

        for(int i=n/2-1; i>=0; i--){
            siftDown(arr, i, n);
        }


        //依次取出堆顶元素
        for(int i=n-1; i>=0; i--) {
            swap(arr, 0, i);
            siftDown(arr, 0, i);
        }

    }

    private void siftDown(int[] arr, int i, int n) {
        while (2*i + 1 < n ) {
            int largest = i;
            int leftChild = 2*i + 1;
            int rightChild = 2*i + 2;

            if(leftChild < n && arr[leftChild] > arr[largest]) {
                largest = leftChild;
            }

            if(rightChild < n && arr[rightChild] > arr[largest]) {
                largest = rightChild;
            }

            if(largest != i) {
                swap(arr, i, largest);
                i = largest;
            }
            else{
                break;
            }
        }
    }

    private void swap(int[] arr, int i, int largest) {
        int tmp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = tmp;
    }

    public static void main(String[] args) {
        int[] arrayToSort = SortUtils.buildRandomIntArray(10000);
        SortUtils.printArray(arrayToSort);

        int[] arrayClone = Arrays.copyOf(arrayToSort, arrayToSort.length);
        Arrays.sort(arrayClone);

        Sorter sorter = new HeapSorter();
        sorter.sort(arrayToSort);
        SortUtils.printArray(arrayToSort);

        if(SortUtils.isEquals(arrayClone, arrayToSort)){
            System.out.println("The two arrays is equal ...");
        }else {
            System.out.println("The two arrays is not equal ...");
        }
    }
}
