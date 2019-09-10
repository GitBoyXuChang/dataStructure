package com.xuchang.algo.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 不是稳定排序算法
 * 最快及平均情况下时间复杂度为o(nlog2 n)
 * 最佳：o(log2 n)
 * 最差：o(n^2)
 * 快速排序是平均运行时间最快的排序算法
 */
public class QuickSorter implements Sorter {

    private static final int THRESHOLD = 10 ;

    @Override
    public void sort(int[] arr) {
        if(arr == null || arr.length <= 1){
            return;
        }

        sortThreeWay(arr, 0, arr.length - 1);
        //sortHelper(arr, 0, arr.length-1);

    }

    private int medianOf3Nums(int[] arr, int lo, int center, int hi) {
        if(arr[lo] < arr[center]) {
            if(arr[center] < arr[hi]){
                return center;
            }else {
                return (arr[lo] < arr[hi]) ? hi : lo;
            }
        }else{
            if(arr[hi] < arr[center]){
                return center;
            }else {
                return (arr[hi] > arr[lo]) ? hi : lo;
            }
        }
    }

    public void insertSort(int[] arr, int start, int end) {
        if(start >= end) {
            return;
        }

        //O(n^2) (T(n) = T(n-1) + O(n)
        //Best: ???
        for(int i=start; i<=end; i++) {
            // 0 - (i-1) is ordered
            int elem = arr[i];
            int j = i-1;

            //二分查找：O(n) -> O(lgn)
            while(j>=0 && elem < arr[j]) {
                arr[j+1] = arr[j];
                j--;
            }

            arr[j+1] = elem;
        }
    }

    private void sortThreeWay(int[] arr, int start, int end) {
        if(end - start + 1 <= THRESHOLD) {
            insertSort(arr, start, end);
            return;
        }
        //if( start >= end){
        //    return;
        //}

        int pivot = arr[start];
        int lt = start;
        int i = start+1;
        int gt = end;

        while(i <= gt) {
            if(arr[i] < pivot) {
                swap(arr, lt, i);
                lt++;
                i++;
            }else if(arr[i] > pivot) {
                swap(arr, i, gt);
                gt--;
            } else{
                i++;
            }
        }


        sortHelper(arr, start, lt-1);
        sortHelper(arr, gt+1, end);
    }

    private void sortHelper(int[] arr, int start, int end) {
        if(end - start + 1 <= THRESHOLD) {
            insertSort(arr, start, end);
            return;
        }
        //if( start >= end){
        //    return;
        //}

        int medianIndex = medianOf3Nums(arr, start, (end-start)/2, end);
        swap(arr, start, medianIndex);
        int index = partition2(arr, start, end);
        sortHelper(arr, start, index - 1);
        sortHelper(arr, index+1, end);
    }

    private int partition2(int[] arr, int start, int end) {
        int pivot = arr[start];

        int i = start+1, j=end;
        while(i <= j) {
            while(i <= j && arr[i] <= pivot) {
                i++;
            }

            while(i <= j && arr[j] > pivot) {
                j--;
            }

            if(i < j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        swap(arr, start, i-1);

        return i-1;


    }

    private int partition(int[] arr, int start, int end) {
        int pivot = arr[start];
        int i = start+1;
        for(int j=start+1; j<=end; j++) { //i->j: >pivot
            if(arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, start, i-1);

        return i-1;
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        int[] arrayToSort = SortUtils.buildRandomIntArray(10);
        SortUtils.printArray(arrayToSort);

        int[] arrayClone = Arrays.copyOf(arrayToSort, arrayToSort.length);
        Arrays.sort(arrayClone);

        Sorter sorter = new QuickSorter();
        sorter.sort(arrayToSort);
        SortUtils.printArray(arrayToSort);

        if(SortUtils.isEquals(arrayClone, arrayToSort)){
            System.out.println("The two arrays is equal ...");
        }else {
            System.out.println("The two arrays is not equal ...");
        }
    }
}
