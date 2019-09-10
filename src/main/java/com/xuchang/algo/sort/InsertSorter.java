package com.xuchang.algo.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 是将数组中的元素逐一与已经排序好的数据进行比较，再逐一将该数组元素插入适当的位置
 */
public class InsertSorter implements Sorter {
    @Override
    public void sort(int[] arr) {
        if(arr == null || arr.length <= 1) {
            return;
        }

        //O(n^2) (T(n) = T(n-1) + O(n)
        //Best: ???
        for(int i=1; i<arr.length; i++) {
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

    public static void main(String[] args) {
        int[] arrayToSort = SortUtils.buildRandomIntArray(5);
        SortUtils.printArray(arrayToSort);

        int[] arrayClone = Arrays.copyOf(arrayToSort, arrayToSort.length);
        Arrays.sort(arrayClone);

        Sorter sorter = new InsertSorter();
        sorter.sort(arrayToSort);
        SortUtils.printArray(arrayToSort);

        if(SortUtils.isEquals(arrayClone, arrayToSort)){
            System.out.println("The two arrays is equal ...");
        }else {
            System.out.println("The two arrays is not equal ...");
        }
    }

}
