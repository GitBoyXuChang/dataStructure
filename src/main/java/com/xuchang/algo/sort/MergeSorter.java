package com.xuchang.algo.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSorter implements Sorter {

    @Override
    public void sort(int[] arr) {
        if(arr == null || arr.length <= 1){
            return;
        }

        int[] tmp = new int[arr.length];
        sortHelper(arr, 0, arr.length-1, tmp);

    }

    public void sortHelper(int[] arr, int start, int end,  int[] tmp) {
        if(start >= end){
            return;
        }

        int mid = (start + end) / 2;

        sortHelper(arr, start, mid, tmp);
        sortHelper(arr, mid+1, end, tmp);

        merge(arr, start, mid, mid+1, end, tmp);

    }

    void merge(int[] arr, int s1, int e1, int s2, int e2, int[] tmp) {

        int i = s1, j = s2, k = 0;

        while(i <= e1 && j <= e2) {
            if(arr[i] <= arr[j]){
                tmp[k++] = arr[i];
                i++;
            }else{
                tmp[k++] = arr[j];
                j++;
            }
        }

        while(i <= e1){
            tmp[k++] = arr[i];
            i++;
        }

        while(j <= e2){
            tmp[k++] = arr[j];
            j++;
        }

        for(int l=0; l<k; l++){
            arr[s1+l] = tmp[l];
        }

    }


    public static void main(String[] args) {
        int[] arrayToSort = SortUtils.buildRandomIntArray(5);
        SortUtils.printArray(arrayToSort);

        int[] arrayClone = Arrays.copyOf(arrayToSort, arrayToSort.length);
        Arrays.sort(arrayClone);

        Sorter sorter = new MergeSorter();
        sorter.sort(arrayToSort);
        SortUtils.printArray(arrayToSort);

        if(SortUtils.isEquals(arrayClone, arrayToSort)){
            System.out.println("The two arrays is equal ...");
        }else {
            System.out.println("The two arrays is not equal ...");
        }
    }
}
