package com.xuchang.algo.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 冒泡排序：O(1)
 * 平均时间复杂度：O(n^2)
 * 最好时间复杂度：O(n)
 * 最坏时间复杂度：O(n^2)
 * 是否稳定：是
 */
public class BubbleSorter implements Sorter{
    @Override
    public void sort(int[] arr) {
        if(arr == null || arr.length <= 1) {
            return;
        }

        //Best O(n)
        //Worst, Avg O(n^2)
        for(int i=0; i<arr.length; i++) {
            boolean flag = false;
            for(int j=0; j<arr.length-1-i; j++) {
                if(arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;

                    flag = true;
                }
            }

            if(!flag) {
                return;
            }
        }
    }
    public static void main(String[] args) {
        int[] arrayToSort = SortUtils.buildRandomIntArray(10);
        SortUtils.printArray(arrayToSort);

        int[] arrayClone = Arrays.copyOf(arrayToSort, arrayToSort.length);
        Arrays.sort(arrayClone);

        Sorter sorter = new BubbleSorter();
        sorter.sort(arrayToSort);
        SortUtils.printArray(arrayToSort);

        if(SortUtils.isEquals(arrayClone, arrayToSort)){
            System.out.println("The two arrays is equal ...");
        }else {
            System.out.println("The two arrays is not equal ...");
        }
    }

}
