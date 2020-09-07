package com.xuchang.algo.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 平均时间复杂度：O(n^2)
 * 最好时间复杂度：O(n^2)
 * 最坏时间复杂度：O(n^2)
 * 是否稳定：是
 */
public class SelectSorter implements Sorter {

    @Override
    public void sort(int[] arr) {
        if(arr == null || arr.length <= 1) {
            return;
        }

        //O(n^2)
        //Best: ???
        for(int i=0; i<arr.length; i++) {
            int minIndex = i; //[0, i-1] is ordered
            for(int j=i+1; j<arr.length; j++) {
                if(arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arrayToSort = SortUtils.buildRandomIntArray(5);
        SortUtils.printArray(arrayToSort);

        int[] arrayClone = Arrays.copyOf(arrayToSort, arrayToSort.length);
        Arrays.sort(arrayClone);

        Sorter sorter = new SelectSorter();
        sorter.sort(arrayToSort);
        SortUtils.printArray(arrayToSort);

        if(SortUtils.isEquals(arrayClone, arrayToSort)){
            System.out.println("The two arrays is equal ...");
        }else {
            System.out.println("The two arrays is not equal ...");
        }
    }
}
